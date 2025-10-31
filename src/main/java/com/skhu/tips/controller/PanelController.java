package com.skhu.tips.controller;

/*
 * ***************************
 *  파일명: PanelController
 *  작성자: 조민성
 *  작성일:
 *  내용:
 * ***************************
 */

import com.skhu.tips.model.dto.Building;
import com.skhu.tips.model.dto.Facility;
import com.skhu.tips.view.panel.BuildingDetailPanel;
import com.skhu.tips.view.panel.FacilityDetailPanel;
import com.skhu.tips.view.panel.MainLeftPanel;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class PanelController {
	private final MainLeftPanel leftPanel;
	private final JPanel detailContainer;
	private JComponent currentDetail;

	public PanelController(MainLeftPanel leftPanel, JPanel detailContainer) {
		this.leftPanel = leftPanel;
		this.detailContainer = detailContainer;
		this.detailContainer.setLayout(new BorderLayout());
		this.leftPanel.setSelectionHandler(this::onListSelection);
	}

	public void onListSelection(Object entity) {
		if (entity instanceof Building) {
			showBuildingDetail((Building) entity);
		} else if (entity instanceof Facility) {
			showFacilityDetail((Facility) entity);
		}
	}

	public void closeDetail() {
		if (currentDetail != null) {
			detailContainer.remove(currentDetail);
			detailContainer.revalidate();
			detailContainer.repaint();
			currentDetail = null;
			leftPanel.clearSelection();
		}
	}

	private void showBuildingDetail(Building building) {
		swapDetail(new BuildingDetailPanel(building, this::closeDetail));
	}

	private void showFacilityDetail(Facility facility) {
		swapDetail(new FacilityDetailPanel(facility, this::closeDetail));
	}

	private void swapDetail(JComponent next) {
		if (currentDetail != null) detailContainer.remove(currentDetail);
		currentDetail = next;
		detailContainer.add(next, BorderLayout.CENTER);
		detailContainer.revalidate();
		detailContainer.repaint();
	}
}
