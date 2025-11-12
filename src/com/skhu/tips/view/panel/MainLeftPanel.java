package com.skhu.tips.view.panel;

/*
 * ***************************
 *  파일명: AppInfoPanel
 *  작성자: 조민성
 *  작성일:
 *  내용:
 * ***************************
 */

import com.skhu.tips.model.dto.Building;
import com.skhu.tips.model.dto.Facility;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.util.List;
import java.util.function.Consumer;

public class MainLeftPanel extends JPanel {
	private final JTabbedPane tabbedPane = new JTabbedPane();
	private final DefaultListModel<Building> buildingModel = new DefaultListModel<>();
	private final DefaultListModel<Facility> facilityModel = new DefaultListModel<>();
	private final JList<Building> buildingList = new JList<>(buildingModel);
	private final JList<Facility> facilityList = new JList<>(facilityModel);
	private Consumer<Object> selectionHandler;

	public MainLeftPanel() {
		setLayout(new BorderLayout());
		buildingList.addListSelectionListener(createSelectionForwarder());
		facilityList.addListSelectionListener(createSelectionForwarder());
		tabbedPane.addTab("건물", new JScrollPane(buildingList));
		tabbedPane.addTab("주요시설", new JScrollPane(facilityList));
		add(tabbedPane, BorderLayout.CENTER);
	}

	public void setData(List<Building> buildings, List<Facility> facilities) {
		buildingModel.clear();
		facilityModel.clear();
		if (buildings != null) buildings.forEach(buildingModel::addElement);
		if (facilities != null) facilities.forEach(facilityModel::addElement);
	}

	public void setSelectionHandler(Consumer<Object> handler) {
		this.selectionHandler = handler;
	}

	public void clearSelection() {
		buildingList.clearSelection();
		facilityList.clearSelection();
	}

	private ListSelectionListener createSelectionForwarder() {
		return e -> {
			if (e.getValueIsAdjusting()) return;
			Object selected = null;
			if (tabbedPane.getSelectedIndex() == 0) {
				selected = buildingList.getSelectedValue();
			} else {
				selected = facilityList.getSelectedValue();
			}
			if (selected != null && selectionHandler != null) selectionHandler.accept(selected);
		};
	}
}
