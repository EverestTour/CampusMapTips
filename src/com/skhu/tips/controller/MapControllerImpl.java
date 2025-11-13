package com.skhu.tips.controller;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;
import com.skhu.tips.model.service.DataService;
import com.skhu.tips.view.map.MapPanel;

/**
 * @class MapControllerImpl
 * @brief MapController 인터페이스의 구현체 (템플릿)
 */
public class MapControllerImpl implements MapController {

    private final MapPanel mapPanel;
    private final DataService dataService;
    private PanelController panelController;

    /**
     * @brief MapControllerImpl 생성자
     * @param mapPanel 제어할 MapPanel 뷰
     * @param dataService DataService 인터페이스
     */
    public MapControllerImpl(MapPanel mapPanel, DataService dataService) {
        this.mapPanel = mapPanel;
        this.dataService = dataService;
    }

    // --- MapController Interface Implementation ---

    // TODO: 김준 (구현 필요)

    @Override
    public void focusOn(Building building) {

    }

    @Override
    public void focusOn(Facility facility) {

    }

    @Override
    public void switchToBuildingView() {

    }

    @Override
    public void switchToFacilityView() {

    }

    @Override
    public void setPanelController(PanelController panelController) {
        this.panelController = panelController;
    }


    // --- Private Helper Methods ---

    // private void attachViewListeners() {
    //     ...
    // }

    // private void handleMapClick(int x, int y) {
    //     ...
    // }
}