package com.skhu.tips.model.service;

import com.skhu.tips.model.dto.Building;
import com.skhu.tips.model.dto.Facility;
import java.util.ArrayList;
import java.util.List;

/*
 * ***************************
 *  파일명: DataService
 *  작성자: 김주환
 *  작성일: 2025-11-04
 *  내용: 교내 건물 및 시설 데이터 관리 클래스 
 * ***************************
 */
public class DataService {

    private List<Building> buildings;

    public DataService() {
        buildings = new ArrayList<>();
        initializeData();
    }

    private void initializeData() {
        // =====================
        // 01. 승연관
        // =====================
        Building b1 = new Building(1, "승연관", "행정 및 대학본부 기능을 담당하는 건물.");

        Facility f1 = new Facility("홍보처", "1층", "학교 홍보 및 영상 제작 부서입니다.");
        f1.addTip("홍보 영상, 포스터 제작 등 홍보자료를 관리합니다.");
        f1.addTip("학교 공식 SNS 홍보 콘텐츠도 이 부서에서 담당합니다.");
        b1.addFacility(f1);

        Facility f2 = new Facility("예비군대대", "1층", "예비군 및 병역 관련 업무를 담당하는 부서입니다.");
        f2.addTip("학생 예비군 등록 및 훈련 일정 안내를 받을 수 있습니다.");
        f2.addTip("군 관련 행정 서류 처리 가능합니다.");
        b1.addFacility(f2);

        Facility f3 = new Facility("영상실", "1층", "학교 홍보 영상 및 강의 촬영을 위한 공간입니다.");
        f3.addTip("촬영 장비와 조명이 구비되어 있습니다.");
        f3.addTip("학내 행사 녹화나 홍보 영상 촬영에 활용됩니다.");
        b1.addFacility(f3);

        Facility f4 = new Facility("교무처", "2층", "학생 학사 관련 주요 행정 업무를 담당합니다.");
        f4.addTip("수강신청, 학적변동 등 학사 업무 처리 가능합니다.");
        f4.addTip("교무 관련 문의는 이곳에서 가능합니다.");
        b1.addFacility(f4);

        buildings.add(b1);

        // =====================
        // 02. 일만관
        // =====================
        Building b2 = new Building(2, "일만관", "인문사회 계열 연구 중심 건물.");
        Facility f5 = new Facility("사회교육원", "3층", "평생교육 및 외부 연계 교육과정 운영 부서입니다.");
        f5.addTip("일반인 대상 프로그램을 운영합니다.");
        f5.addTip("인문학 특강 및 자격과정도 진행됩니다.");
        b2.addFacility(f5);

        buildings.add(b2);

        // =====================
        // 03. 월당관
        // =====================
        Building b3 = new Building(3, "월당관", "전자정보소프트웨어 중심 건물.");
        Facility f6 = new Facility("시설명", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b3.addFacility(f6);

        buildings.add(b3);
        
        
        // =====================
        // 05. 나눔관(학생회관)
        // =====================
        Building b5 = new Building(5, "나눔관(학생회관)", "간단한 설명");
        Facility f7 = new Facility("시설명", "층", "");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);

        buildings.add(b5);
        // =====================
        // 06. 이천환기념관(정보과학관)
        // =====================
        Building b6 = new Building(6, "이천환기념관(정보과학관)", "간단한 설명");
        Facility f7 = new Facility("시설명", "층", "");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);
        
        
        // =====================
        // 07. 새천년관(인문사회관)
        // =====================
        Building b7 = new Building(7, "새천년관(인문사회관)", "간단한 설명");
        Facility f7 = new Facility("시설", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);
        
        // =====================
        // 08. 중앙 도서관
        // =====================
        Building b8 = new Building(8, " 중앙도서관", "간단한 설명");
        Facility f7 = new Facility("시설", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);
        // =====================
        // 09. 성미가엘성당/피츠버그홀
        // =====================
        Building b9 = new Building(9, "성미가엘성당/피츠버그홀", "간단한 설명");
        Facility f7 = new Facility("시설명", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);
        // =====================
        // 10. 구두인관
        // =====================
        Building b10 = new Building(10, "구두인관", "간단한 설명");
        Facility f7 = new Facility("시설명", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);
        // =====================
        // 11. 성베드로학교
        // =====================
        Building b11 = new Building(11, "성베드로학교", "간단한 설명");
        Facility f7 = new Facility("시설명", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);
        // =====================
        // 12.미가엘관
        // =====================
        Building b12 = new Building(12, "미가엘관", "간단한 설명");
        Facility f7 = new Facility("시설명", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);
        // =====================
        // 13. 행복기숙사 
        // =====================
        Building b13 = new Building(13, "행복기숙사", "간단한 설명");
        Facility f7 = new Facility("시설명", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b5.addFacility(f7);
        
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public Building findBuildingByName(String name) {
        for (Building b : buildings) {
            if (b.getName().equals(name))
                return b;
        }
        return null;
    }

    public Building findBuildingById(int id) {
        for (Building b : buildings) {
            if (b.getId() == id)
                return b;
        }
        return null;
    }
}
