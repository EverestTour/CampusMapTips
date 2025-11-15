package com.skhu.tips.controller;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;
import com.skhu.tips.model.service.DataService;
import com.skhu.tips.view.panel.MainLeftPanel;

/**
 * @class PanelControllerImpl
 * @brief PanelController 인터페이스의 구현체. 좌측 패널의 이벤트를 처리하고 뷰를 제어합니다.
 */
public class PanelControllerImpl implements PanelController {

    // --- 1. Fields (DI 및 상태) ---
    private final MainLeftPanel mainLeftPanel;
    private final DataService dataService;
    private MapController mapController; // (Setter DI로 주입받을 MapController 인터페이스)

    /**
     * @brief 생성자: DI 및 초기 설정
     */
    public PanelControllerImpl(MainLeftPanel mainLeftPanel, DataService dataService) {
        this.mainLeftPanel = mainLeftPanel;
        this.dataService = dataService;

        // 뷰에 초기 데이터 채우기
        this.mainLeftPanel.setBuildingListData(dataService.getBuildings());
        this.mainLeftPanel.setFacilityListData(dataService.getFacilities());

        // 뷰 컴포넌트에 리스너 등록
        attachViewListeners();

        // 초기 뷰 상태 설정
        showBuildingView();
    }

    // =======================================================================
    // --- 2. PanelController Interface Implementation (외부 노출 API) ---
    // =======================================================================

    /**
     * @brief [DI Setter] MapController 인터페이스를 주입받습니다.
     */
    @Override
    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    /**
     * @brief [MapController 호출] 건물 상세 정보 팝업창을 엽니다.
     */
    @Override
    public void openBuildingDetail(Building building) {
        // TODO: (구현 필요) new BuildingDetailPanel(building).setVisible(true);
        System.out.println("[PanelController] " + building.getName() + " 팝업 열기 요청 받음");
    }

    /**
     * @brief [MapController 호출] 시설 상세 정보 팝업창을 엽니다.
     */
    @Override
    public void openFacilityDetail(Facility facility) {
        // TODO: (구현 필요) new FacilityDetailPanel(facility).setVisible(true);
        System.out.println("[PanelController] " + facility.getName() + " 팝업 열기 요청 받음");
    }

    /**
     * @brief [MapController 호출] 뷰를 건물 리스트 뷰로 전환합니다.
     */
    @Override
    public void switchToBuildingView() {
        showBuildingView();
    }

    /**
     * @brief [MapController 호출] 뷰를 시설 리스트 뷰로 전환합니다.
     */
    @Override
    public void switchToFacilityView() {
        showFacilityView();
    }

    // =======================================================================
    // --- 3. Private Logic Helpers (실제 로직 구현) ---
    // =======================================================================

    /**
     * @brief 건물 뷰로 전환하는 핵심 로직. View에 명령하고 MapController에 알립니다.
     */
    private void showBuildingView() {
        mainLeftPanel.showBuildingList();
        mainLeftPanel.updateButtonColors(true); // 건물 버튼 활성화 색상
        if (mapController != null) {
            mapController.switchToBuildingView();
        }
    }

    /**
     * @brief 시설 뷰로 전환하는 핵심 로직. View에 명령하고 MapController에 알립니다.
     */
    private void showFacilityView() {
        mainLeftPanel.showFacilityList();
        mainLeftPanel.updateButtonColors(false); // 시설 버튼 활성화 색상
        if (mapController != null) {
            mapController.switchToFacilityView();
        }
    }

    // =======================================================================
    // --- 4. Event Listener Registration (리스너 등록) ---
    // =======================================================================

    /**
     * @brief 뷰(MainLeftPanel)의 스윙 컴포넌트(Getter로 접근)에 이벤트 리스너를 등록합니다.
     */
    private void attachViewListeners() {

        // 1. 건물 리스트(JList) 클릭 시
        mainLeftPanel.getBuildingList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Building selected = mainLeftPanel.getBuildingList().getSelectedValue();
                if (selected != null && mapController != null) {
                    // 지도 컨트롤러에게 초점 이동 명령
                    mapController.focusOn(selected);
                }
            }
        });

        // 2. 시설 리스트(JList) 클릭 시
        mainLeftPanel.getFacilityList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Facility selected = mainLeftPanel.getFacilityList().getSelectedValue();
                if (selected != null && mapController != null) {
                    // 지도 컨트롤러에게 초점 이동 명령
                    mapController.focusOn(selected);
                }
            }
        });

        // 3. 카테고리 버튼 클릭 시 (내부 헬퍼 메소드 호출)
        mainLeftPanel.getBuildingButton().addActionListener(e -> showBuildingView());
        mainLeftPanel.getFacilityButton().addActionListener(e -> showFacilityView());
    }
}