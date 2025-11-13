package com.skhu.tips.model.service;

import java.util.List;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;

/**
 * @interface DataService
 * @brief 데이터 접근 및 상태 관리를 위한 계약(Contract) 인터페이스.
 * 컨트롤러는 이 인터페이스에 의존하여 DataService의 구체적인 구현과 분리됩니다.
 */
public interface DataService {

    // --- 데이터 접근 API ---

    /** @brief 저장된 모든 건물 리스트를 반환합니다. */
    List<Building> getBuildings();

    /** @brief 저장된 모든 시설 리스트를 반환합니다. */
    List<Facility> getFacilities();

    /** @brief ID로 특정 건물을 찾습니다. */
    Building getBuildingById(int id);

    /** @brief ID로 특정 시설을 찾습니다. */
    Facility getFacilityById(int id);


    // --- 상태 관리 API ---

    /** @brief 현재 선택된 건물을 반환합니다. */
    Building getSelectedBuilding();

    /** @brief 현재 선택된 시설을 반환합니다. */
    Facility getSelectedFacility();

    /** @brief 선택된 건물 상태를 업데이트합니다. */
    void setSelectedBuilding(Building building);

    /** @brief 선택된 시설 상태를 업데이트합니다. */
    void setSelectedFacility(Facility facility);
}