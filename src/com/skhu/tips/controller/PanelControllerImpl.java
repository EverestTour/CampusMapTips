package com.skhu.tips.controller;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;
import com.skhu.tips.model.service.DataService; // DataService 인터페이스
import com.skhu.tips.view.panel.MainLeftPanel;

/**
 * @class PanelControllerImpl
 * @brief "똑똑한 컨트롤러" - 뷰의 컴포넌트에 직접 리스너를 등록하고,
 * 모든 이벤트 처리와 뷰 전환 로직을 담당합니다.
 */
public class PanelControllerImpl implements PanelController {

    private final MainLeftPanel mainLeftPanel;
    private final DataService dataService;
    private MapController mapController;

    public PanelControllerImpl(MainLeftPanel mainLeftPanel, DataService dataService) {
        this.mainLeftPanel = mainLeftPanel;
        this.dataService = dataService;

        // 1. 뷰에 데이터 채우기
        this.mainLeftPanel.setBuildingListData(dataService.getBuildings());
        this.mainLeftPanel.setFacilityListData(dataService.getFacilities());

        // 2. 뷰의 컴포넌트에 직접 리스너를 등록
        attachViewListeners();

        // 3. 초기 뷰 상태 설정 (앱 시작 시 건물 뷰를 기본으로 설정)
        showBuildingView(); // --- [변경] 초기 상태 설정 ---
    }

    /**
     * @brief 뷰(MainLeftPanel)의 스윙 컴포넌트에 이벤트 리스너를 등록합니다.
     */
    private void attachViewListeners() {

        // 1. 건물 리스트(JList) 클릭 시
        mainLeftPanel.getBuildingList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Building selected = mainLeftPanel.getBuildingList().getSelectedValue();
                if (selected != null && mapController != null) {
                    mapController.focusOn(selected);
                }
            }
        });

        // 2. 시설 리스트(JList) 클릭 시
        mainLeftPanel.getFacilityList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Facility selected = mainLeftPanel.getFacilityList().getSelectedValue();
                if (selected != null && mapController != null) {
                    mapController.focusOn(selected);
                }
            }
        });

        // 3. 카테고리 버튼 클릭 시 (컨트롤러의 private 메소드 호출로 변경)
        mainLeftPanel.getBuildingButton().addActionListener(e -> showBuildingView()); // --- [변경] ---
        mainLeftPanel.getFacilityButton().addActionListener(e -> showFacilityView()); // --- [변경] ---
    }

    // --- PanelController Interface Implementation ---

    /**
     * @brief "건물 뷰 보이기" 로직을 수행 (인터페이스 구현)
     * (MapController가 이 메소드를 호출할 수 있음)
     */
    @Override
    public void switchToBuildingView() {
        showBuildingView(); // --- [변경] ---
    }

    /**
     * @brief "시설 뷰 보이기" 로직을 수행 (인터페이스 구현)
     * (MapController가 이 메소드를 호출할 수 있음)
     */
    @Override
    public void switchToFacilityView() {
        showFacilityView(); // --- [변경] ---
    }

    @Override
    public void openBuildingDetail(Building building) {
        // TODO: (구현 필요)
        System.out.println("[PanelController] " + building.getName() + " 팝업 열기 요청 받음");
    }

    @Override
    public void openFacilityDetail(Facility facility) {
        // TODO: (구현 필요)
        System.out.println("[PanelController] " + facility.getName() + " 팝업 열기 요청 받음");
    }

    @Override
    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    // --- [추가] Private Helper Methods (Controller's Own Logic) ---
    // (MainLeftPanel에 있던 로직을 컨트롤러로 이동)

    /**
     * @brief 실제 "건물 뷰 보이기" 로직.
     * 뷰(View)의 단순 동작 메소드를 호출하고, MapController에게도 알립니다.
     */
    private void showBuildingView() {
        // 1. 뷰에게 "건물 리스트 보여줘" 명령
        mainLeftPanel.showBuildingList();
        // 2. 뷰에게 "버튼 색상 (건물 활성) 칠해줘" 명령
        mainLeftPanel.updateButtonColors(true);
        // 3. 맵 컨트롤러에게 "지도 뷰 (건물) 바꿔줘" 명령
        if (mapController != null) {
            mapController.switchToBuildingView();
        }
    }

    /**
     * @brief 실제 "시설 뷰 보이기" 로직.
     */
    private void showFacilityView() {
        // 1. 뷰에게 "시설 리스트 보여줘" 명령
        mainLeftPanel.showFacilityList();
        // 2. 뷰에게 "버튼 색상 (시설 활성) 칠해줘" 명령
        mainLeftPanel.updateButtonColors(false);
        // 3. 맵 컨트롤러에게 "지도 뷰 (시설) 바꿔줘" 명령
        if (mapController != null) {
            mapController.switchToFacilityView();
        }
    }
}