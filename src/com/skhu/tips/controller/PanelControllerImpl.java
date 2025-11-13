package com.skhu.tips.controller;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;
import com.skhu.tips.model.service.DataService;
import com.skhu.tips.view.panel.MainLeftPanel;

/**
 * @class PanelControllerImpl
 * @brief PanelController 인터페이스의 구현체 (템플릿)
 */
public class PanelControllerImpl implements PanelController {

    private final MainLeftPanel mainLeftPanel;
    private final DataService dataService;
    private MapController mapController;

    // TODO: 조민성 (구현 필요)

    /**
     * @brief PanelControllerImpl 생성자
     * @param mainLeftPanel 제어할 MainLeftPanel 뷰
     * @param dataService DataService 인터페이스
     */
    public PanelControllerImpl(MainLeftPanel mainLeftPanel, DataService dataService) {
        this.mainLeftPanel = mainLeftPanel;
        this.dataService = dataService;

        // attachViewListeners();
    }

    // --- PanelController Interface Implementation ---

    @Override
    public void openBuildingDetail(Building building) {
        // new BuildingDetailPanel(building).setVisible(true);
    }

    @Override
    public void openFacilityDetail(Facility facility) {
        // new FacilityDetailPanel(facility).setVisible(true);
    }

    @Override
    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    // --- Private Helper Methods ---

    // private void attachViewListeners() {
    //     // 예: mainLeftPanel.getBuildingList().addListSelectionListener(...)
    //     // 리스트 클릭 시 mapController.focusOn(...) 호출
    // }
}