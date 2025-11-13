package com.skhu.tips.model.service;

import java.util.ArrayList;
import java.util.List;

import com.skhu.tips.model.dto.Building;
import com.skhu.tips.model.dto.Facility;

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
        Building b1 = new Building(1, "승연관", "행정처와 강의실, 연구실이 함께 있는 본부 및 교육 지원 건물");

        Facility f1 = new Facility("홍보처", "1층", "학교 홍보 및 영상 제작 부서입니다.");
        f1.addTip("홍보 영상, 포스터 제작 등 홍보자료를 관리합니다.");
        f1.addTip("학교 공식 SNS 홍보 콘텐츠도 이 부서에서 담당합니다.");
        b1.addFacility(f1);

        Facility f2 = new Facility("예비군대대", "1층", "예비군 및 병역 관련 업무를 담당하는 부서입니다.");
        f2.addTip("학생 예비군 등록 및 훈련 일정 안내를 받을 수 있습니다.");
        f2.addTip("군 관련 행정 서류 처리 가능합니다.");
        b1.addFacility(f2);

        Facility f3 = new Facility("교무처", "2층", "학생 학사 관련 주요 행정 업무를 담당합니다.");
        f3.addTip("수강신청, 학적변동, 학점문의 등 학사 업무 처리 가능합니다.");
        f3.addTip("교무 관련 문의는 이곳에서 가능합니다.");
        b1.addFacility(f3);
        
        Facility f4 = new Facility("학습상담센터", "2층", "학생들의 학습·진로·심리 상담을 지원하는 공간입니다.");
        f4.addTip("학습법, 시간관리, 진로설정 등 다양한 주제로 전문 상담을 받을 수 있습니다.");
        f4.addTip("상담은 사전 예약제로 운영되며, 학교 홈페이지나 전화로 예약 가능합니다.");
        b1.addFacility(f4);

        buildings.add(b1);

        // =====================
        // 02. 일만관
        // =====================
        Building b2 = new Building(2, "일만관", "연구·교육 중심 건물로, 실습실·연구소·행정부서가 위치한 건물");
        Facility f5 = new Facility("사회교육원", "3층", "평생교육 및 외부 연계 교육과정 운영 부서입니다.");
        f5.addTip("일반인 대상 프로그램을 운영합니다.");
        f5.addTip("인문학 특강 및 자격과정도 진행됩니다.");
        b2.addFacility(f5);

        buildings.add(b2);

        // =====================
        // 03. 월당관
        // =====================
        Building b3 = new Building(3, "월당관", "강의실과 실습실, 열람실 등이 모여 있는 학습·지원 중심 건물");
       
        Facility f6 = new Facility("시설명", "층", "간단한 설명");
        f6.addTip("~");
        f6.addTip("~~");
        b3.addFacility(f6);

        buildings.add(b3);
        
        
        // =====================
        // 05. 나눔관(학생회관)
        // =====================
        Building b5 = new Building(5, "나눔관(학생회관)", "학생회와 동아리, 복지시설이 함께 있는 학생활동 공간");
        
        Facility f7 = new Facility("탁구장", "1층", "학생들이 자유롭게 이용할 수 있는 탁구 공간입니다.");
        f7.addTip("공과 라켓을 개인 지참하면 바로 이용 가능해요.");
        f7.addTip("");
        b5.addFacility(f7);
        
        Facility f8 = new Facility("복사실", "1층", "프린트, 복사, 제본 등 문서 작업을 지원합니다.");
        f8.addTip("시험 기간엔 사용자가 있을 수 있으니 여유 있게 방문하세요!");
        f8.addTip("소액의 결제가 필요해요");
        b5.addFacility(f8);

        buildings.add(b5);
        // =====================
        // 06. 이천환기념관(정보과학관)
        // =====================
        Building b6 = new Building(6, "이천환기념관(정보과학관)", "소프트웨어융합전공 및 IT융합자율학부 중심의 공학계 실습 중심 건물");
        
        Facility f9 = new Facility("시설명", "층", "");
        f9.addTip("~");
        f9.addTip("~~");
        b6.addFacility(f9);
        
        buildings.add(b6);
        // =====================
        // 07. 새천년관(인문사회관)
        // =====================
        Building b7 = new Building(7, "새천년관(인문사회관)", "인문사회계 학과 중심의 교육·연구 및 학생복지 복합 건물");
        
        Facility f10 = new Facility("대학식당", "지하1층", "학생과 교직원을 위한 식당입니다.");
        f10.addTip("다양한 메뉴가 있어요");
        f10.addTip("월~목까지는 오전11시부터 저녁 7시까지 운영(라스트 오더 6:30까지) 금요일은 오후 2시까지 영업해요. 영업시간 준수해주세요");
        f10.addTip("식당 쓰레기통에는 식당에서 나온 쓰레기만 넣어주세요. 외부 쓰레기는 자제해주세요!");
        b7.addFacility(f10);
        
        Facility f11 = new Facility("이마트 편의점", "지하1층", "식당 바로 옆에 있는 편의점");
        f11.addTip("아침과 점심시간 외에는 무인운영합니다.");
        b7.addFacility(f11);
        
        Facility f12 = new Facility("ATM기기", "1층", "학생회관 입구 쪽에 위치한 현금 인출기입니다.");
        f12.addTip("소정의 수수료가 있어요");
        b7.addFacility(f12);
        
        buildings.add(b7);
        // =====================
        // 08. 중앙 도서관
        // =====================
        Building b8 = new Building(8, " 중앙도서관", "다양한 전공 서적과 학습자료를 제공하는 본교의 주요 도서관");
       
        Facility f13 = new Facility("시설", "층", "간단한 설명");
        f13.addTip("조용히 이용해주세요!");
        f13.addTip("다양한 전공 서적 및 학습자료가 있으니 적극적으로 이용 권장합니다");
        f13.addTip("도서관 전용앱이 있으니 다운받아 사용하세요!");
        b8.addFacility(f13);
        
        buildings.add(b8);
        // =====================
        // 09. 성미가엘성당/피츠버그홀
        // =====================
        Building b9 = new Building(9, "성미가엘성당/피츠버그홀", "성당과 피츠버그홀, 교목실이 있는 신앙·예배 건물");
        
  
        
        
        buildings.add(b9);
        // =====================
        // 10. 구두인관
        // =====================
        Building b10 = new Building(10, "구두인관", "신학연구와 지역사회 연계 프로그램, 학생 사회진출을 지원하는 연구·사회협력 건물");
        Facility f14 = new Facility("시설명", "층", "간단한 설명");
        f14.addTip("~");
        f14.addTip("~~");
        b10.addFacility(f14);
        
        buildings.add(b10);
        // =====================
        // 11. 성베드로학교
        // =====================
        Building b11 = new Building(11, "성베드로학교", "본교 부속 특수교육 기관으로, 학생 맞춤형 교육을 실현하는 포용교육 시설");
        Facility f15 = new Facility("성베드로학교", "건물전체", "학교 내 부속 특수학교입니다");
        f15.addTip("학생 봉사 프로그램을 이곳에서 자주 진행해요.");
        b11.addFacility(f15);
        
        
        buildings.add(b11);
        // =====================
        // 12.미가엘관
        // =====================
        Building b12 = new Building(12, "미가엘관", "학생 식당과 헬스장, 강의실, 그리고 기숙사가 함께 있는 생활·학습 복합 건물");
        Facility f16 = new Facility("밥풀(식당)", "1층", "1인당 5천원 가량의 뷔페식 식당");
        f16.addTip("잔반을 남기지 않도록 주의해주세요");
        f16.addTip("입구 바로 옆 식권 발권기를 사용하여 식권을 발권받은 후 오른쪽 바구니에 넣고 드실 수 있는 만큼 마음껏 드시면 됩니다!");
        b12.addFacility(f16);
        
        Facility f17 = new Facility("멋GYM(헬스장)", "1층", "러닝머신, 아령 등 간단한 운동기구들이 있는 헬스장");
        f17.addTip("");
        f17.addTip("");
        b12.addFacility(f17);
        
        
        buildings.add(b12);
        // =====================
        // 13. 행복기숙사 
        // =====================
        Building b13 = new Building(13, "행복기숙사", "사감실과 관리실을 갖춘 학생 기숙사 건물");
        Facility f18 = new Facility("행복기숙사", "건물전체", "학생들을 위한 기숙사 건물입니다. 미가엘관도 5층부터 기숙사입니다.");
        f18.addTip("출입 카드를 항상 소지해야 출입가능합니다.");
        f18.addTip("출입 카드 분실 시 1층 행정실에서 재발급 받을 수 있습니다.");
        f18.addTip("휴일이나 행정실 업무시간 종료 이후에는 관리실(경비실)에서 게스트카드(임시 카드)를 대여받을 수 있어요");
        f18.addTip("자세한 내용은 https://skhu.happydorm.or.kr/ 행복기숙사 홈페이지를 사용하세요!");
        b13.addFacility(f18);
        
        buildings.add(b13);
        
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
