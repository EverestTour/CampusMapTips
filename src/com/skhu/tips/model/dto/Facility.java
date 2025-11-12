package com.skhu.tips.model.dto;

/*
 * ***************************
 *  파일명: PanelController
 *  작성자: 김주환
 *  작성일: 2025-11-04  
 *  내용: 시설 Dto 클래스
 * ***************************
 */


import java.util.ArrayList;
import java.util.List;

public class Facility {
    private String name;
    private String floor;
    private String description;
    private List<String> tips;

    public Facility(String name, String floor, String description) {
        this.name = name;
        this.floor = floor;
        this.description = description;
        this.tips = new ArrayList<>();
    }

    public void addTip(String tip) {
        tips.add(tip);
    }

    public String getTip(int index) {
        if (index < 0 || index >= tips.size()) {
            return "해당 번호의 팁이 없습니다.";
        }
        return tips.get(index);
    }

    public List<String> getAllTips() {
        return tips;
    }

    public int getTipCount() {
        return tips.size();
    }

    public String getName() {
        return name;
    }

    public String getFloor() {
        return floor;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return floor + " - " + name;
    }
}
