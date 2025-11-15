package com.skhu.tips.model.service;

import java.util.Arrays;
import java.util.List;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;

/**
 * @class DataService
 * @brief 애플리케이션의 핵심 데이터(Building, Facility)를 담고 있는 저장소
 * 핵심 로직을 상단에, 데이터 초기화 로직을 하단에 배치
 */
public class DataServiceImpl implements DataService {

    // --- 1. Fields (데이터 저장소 및 상태) ---

    private final List<Building> allBuildings;
    private final List<Facility> allFacilities;

    private Building selectedBuilding;
    private Facility selectedFacility;


    // --- 2. Constructor (생성자) ---

    public DataServiceImpl() {
        // 데이터 초기화 메소드를 호출
        this.allBuildings = createInitialBuildings();
        this.allFacilities = createInitialFacilities();

        System.out.printf("[DataService] Data loaded successfully. Buildings: %d, Facilities: %d%n",
                          allBuildings.size(), allFacilities.size());
    }


    // --- 3. Public API (데이터 접근 및 상태 관리) ---

    @Override
	public List<Building> getBuildings() { return allBuildings; }
    @Override
	public List<Facility> getFacilities() { return allFacilities; }

    @Override
	public Building getSelectedBuilding() { return selectedBuilding; }
    @Override
	public Facility getSelectedFacility() { return selectedFacility; }

    @Override
	public void setSelectedBuilding(Building building) {
        this.selectedBuilding = building;
    }
    @Override
	public void setSelectedFacility(Facility facility) {
        this.selectedFacility = facility;
    }

    /**
     * @brief ID로 건물을 찾는 헬퍼 메소드
     */
    @Override
	public Building getBuildingById(int id) {
        return allBuildings.stream()
                           .filter(b -> b.getId() == id)
                           .findFirst()
                           .orElse(null);
    }

    /**
     * @brief ID로 시설을 찾는 헬퍼 메소드
     */
    @Override
	public Facility getFacilityById(int id) {
        return allFacilities.stream()
                            .filter(f -> f.getId() == id)
                            .findFirst()
                            .orElse(null);
    }


    // =======================================================================
    // --- 4. Private Initial Data Helpers (데이터 초기화 로직) ---
    // (이 부분은 클래스 맨 아래에 위치하여 가독성을 확보합니다)
    // =======================================================================

    // TODO: 김주환 (수정 필요)
    /**
     * @brief Building 초기 데이터를 생성합니다. (제공된 8개 데이터)
     */
    private List<Building> createInitialBuildings() {
        Building[] initialBuildings = new Building[] {
            new Building(
                1, 126.960, 37.558, "본관", "학교의 행정 업무를 총괄하는 중심 건물입니다.",
                new String[]{"101호 총장실", "201호 행정실", "301호 회의실"} // String[]로 변경
            ),
            new Building(
                2, 126.961, 37.559, "중앙도서관", "24시간 열람실과 다양한 장서를 보유한 학교의 핵심 시설입니다.",
                new String[]{"1층 일반열람실", "2층 자료실", "3층 그룹스터디룸"}
            ),
            new Building(
                3, 126.959, 37.557, "학생회관", "학생들의 휴식과 교류의 공간으로 다양한 편의시설이 있는 건물입니다.",
                new String[]{"1층 학생식당", "2층 동아리방", "3층 학생회 사무실"}
            ),
            new Building(
                4, 126.962, 37.558, "공학관", "공과대학 학생들의 실습과 강의가 이루어지는 건물입니다.",
                new String[]{"101호 실습실A", "201호 강의실B", "301호 컴퓨터실"}
            ),
            new Building(
                5, 126.958, 37.558, "체육관", "다양한 체육 시설과 수영장을 갖춘 종합 체육 시설입니다.",
                new String[]{"1층 수영장", "2층 헬스장", "3층 농구장"}
            ),
            new Building(
                6, 126.957, 37.559, "기숙사", "재학생들을 위한 기숙사 시설로 생활관과 식당이 있습니다.",
                new String[]{"1층 식당", "2층-5층 생활관"}
            ),
             new Building(
                7, 126.960, 37.556, "예술관", "음악과 미술 관련 전공 학생들의 전용 공간입니다.",
                new String[]{"1층 전시실", "2층 연습실", "3층 작업실"}
            ),
             new Building(
                8, 126.961, 37.557, "강의동 A", "일반 강의실과 대형 강의실이 있는 건물입니다.",
                new String[]{"101호 대형강의실", "201호 중형강의실", "301호 소형강의실"}
            )
        };
        return Arrays.asList(initialBuildings);
    }

    /**
     * @brief Facility 초기 데이터를 생성합니다.
     */
    private List<Facility> createInitialFacilities() {
        Facility[] initialFacilities = new Facility[] {
            new Facility(
                101, 126.959, 37.557, 1, "학생 식당 (학식)", "학생회관",
                "학생회관 1층에 위치한 메인 식당입니다.",
                "저렴한 가격에 다양한 메뉴를 제공합니다.",
                "키오스크에서 식권을 구매하세요.",
                "점심: 11:30 - 14:00, 저녁: 17:30 - 19:00",
                new String[]{"점심 피크 시간(12:00-12:30)은 매우 혼잡합니다.", "오늘의 메뉴는 앱에서 확인 가능합니다."}
            ),
            new Facility(
                102, 126.962, 37.558, 3, "컴퓨터실 A", "공학관",
                "공학관 3층에 위치한 공용 컴퓨터실입니다.",
                "프로그래밍 및 과제 수행을 위한 공간입니다.",
                "학생증을 태그해야 입실 가능합니다.",
                "평일 09:00 - 21:00",
                new String[]{"프린터 사용 가능 (유료)", "음식물 반입 금지."}
            ),
            new Facility(
                103, 126.961, 37.559, 1, "카페테리아 (도서관점)", "중앙도서관",
                "중앙도서관 1층 로비에 위치한 카페입니다.",
                "커피와 간단한 음료, 베이커리를 판매합니다.",
                "시험 기간에는 24시간 운영합니다.",
                "평시: 08:00 - 20:00",
                new String[]{"텀블러 할인 300원", "팀플하기 좋은 좌석이 많습니다."}
            )
        };
        return Arrays.asList(initialFacilities);
    }
}