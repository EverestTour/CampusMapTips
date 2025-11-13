package com.skhu.tips.controller;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;
import com.skhu.tips.model.service.DataService; // (DataServiceImpl이 아닌 인터페이스)
import com.skhu.tips.view.panel.MainLeftPanel;

/**
 * @class PanelControllerImpl
 * @brief 뷰의 컴포넌트에 직접 리스너를 등록합니다.
 */
public class PanelControllerImpl implements PanelController {

    private final MainLeftPanel mainLeftPanel;
    private final DataService dataService;
    private MapController mapController;

    public PanelControllerImpl(MainLeftPanel mainLeftPanel, DataService dataService) {
        this.mainLeftPanel = mainLeftPanel;
        this.dataService = dataService;

        // 1. 뷰에 데이터 채우기 (컨트롤러의 역할)
        this.mainLeftPanel.setBuildingListData(dataService.getBuildings());
        this.mainLeftPanel.setFacilityListData(dataService.getFacilities());

        // 2. 뷰의 컴포넌트에 직접 리스너를 등록
        attachViewListeners();
    }

    /**
     * @brief 뷰(MainLeftPanel)의 스윙 컴포넌트에 이벤트 리스너를 등록합니다.
     */
    private void attachViewListeners() {

        // 1. 건물 리스트(JList)에 리스너 등록
        mainLeftPanel.getBuildingList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Building selected = mainLeftPanel.getBuildingList().getSelectedValue();

                if (selected != null && mapController != null) {
                    // 2. 컨트롤러의 핵심 로직: MapController에게 명령
                    mapController.focusOn(selected);
                }
            }
        });

        // 2. 시설 리스트(JList)에도 리스너 등록
        mainLeftPanel.getFacilityList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Facility selected = mainLeftPanel.getFacilityList().getSelectedValue();
                if (selected != null && mapController != null) {
                    mapController.focusOn(selected);
                }
            }
        });

        // 3. 탭 변경 리스너 등록
        mainLeftPanel.getTabbedPane().addChangeListener(e -> {
            if (mapController != null) {
                if (mainLeftPanel.getTabbedPane().getSelectedIndex() == 0) {
                    mapController.switchToBuildingView();
                } else {
                    mapController.switchToFacilityView();
                }
            }
        });
    }

    // --- (이하 PanelController 인터페이스 구현은 동일) ---

    @Override
    public void openBuildingDetail(Building building) {
        // (구현 필요)
    }

    @Override
    public void openFacilityDetail(Facility facility) {
        // (구현 필요)
    }

    @Override
    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }
}