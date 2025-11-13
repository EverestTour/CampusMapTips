package com.skhu.tips.view.panel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;

/**
 * @class MainLeftPanel
 * @brief "멍청한 뷰" - 오직 컴포넌트를 소유하고 Getter만 제공합니다.
 */
public class MainLeftPanel extends JPanel {

    // 1. 뷰가 내부적으로 소유한 컴포넌트
    private JList<Building> buildingList;
    private JList<Facility> facilityList;
    private JTabbedPane tabbedPane;

    // (JList에 데이터를 채우기 위한 모델)
    private DefaultListModel<Building> buildingModel = new DefaultListModel<>();
    private DefaultListModel<Facility> facilityModel = new DefaultListModel<>();

    public MainLeftPanel() {
        super(new BorderLayout());

        // 2. 컴포넌트 생성
        buildingList = new JList<>(buildingModel);
        facilityList = new JList<>(facilityModel);
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("건물", new JScrollPane(buildingList));
        tabbedPane.addTab("시설", new JScrollPane(facilityList));
        add(tabbedPane, BorderLayout.CENTER);

        // 3. 뷰는 리스너를 등록하지 않습니다!
    }

    // --- 4. 컨트롤러가 접근할 수 있도록 Getter를 제공 ---

    public JList<Building> getBuildingList() {
        return buildingList;
    }

    public JList<Facility> getFacilityList() {
        return facilityList;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    // --- 5. 뷰가 데이터를 표시하기 위한 메소드 ---
    public void setBuildingListData(List<Building> data) {
        buildingModel.clear();
        data.forEach(buildingModel::addElement);
    }

    public void setFacilityListData(List<Facility> data) {
        facilityModel.clear();
        data.forEach(facilityModel::addElement);
    }
}