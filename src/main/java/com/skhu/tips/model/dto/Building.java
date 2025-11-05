package com.skhu.tips.model.dto;

/*
 * ***************************
 *  파일명: Building
 *  작성자: 김주환
 *  작성일: 2025-11-04  
 *  내용: Building Dto 클래스
 * ***************************
 */

import java.util.ArrayList;
import java.util.List;

public class Building {
    private int id;
    private String name;
    private String description;
    private List<Facility> facilities;

    public Building(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.facilities = new ArrayList<>();
    }

    public void addFacility(Facility facility) {
        facilities.add(facility);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    @Override
    public String toString() {
        return name;
    }
}
