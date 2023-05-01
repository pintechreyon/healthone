package com.secui.healthone.meal;

import com.secui.healthone.customfood.CustomFoodSteps;
import com.secui.healthone.util.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class MealApiTest extends ApiTest {

    @Test
    @DisplayName("식단 데이터 단일 요청 (일반, 사용자  음식 데이터)")
    @Transactional
    void getMealInfo() {

    }

    @Test
    @DisplayName("식단 데이터 리스트 요청 시간 요청 (일반, 사용자  음식 데이터)")
    @Transactional
    void getMealInfoList() {

    }

    @Test
    @DisplayName("식단 등록 요청 (일반 음식 데이터)")
    @Transactional
    void insertNormalMeal() {
        //given
        MealSteps.식단_등록요청(MealSteps.일반_식단_등록요청_생성1());
        //when
        final ExtractableResponse<Response> response1 = MealSteps.식단_단일조회요청(1);
        log.info(response1.body().asString());
        //then
        assertThat(response1.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("식단 등록 요청 (사용자 음식 데이터)")
    @Transactional
    void insertCustomMeal() {
        //given
        CustomFoodSteps.사용자_음식데이터_등록요청(CustomFoodSteps.사용자_음식데이터_등록요청_생성());
        MealSteps.식단_등록요청(MealSteps.사용자_식단_등록요청_생성1());
        //when
        final ExtractableResponse<Response> response1 = MealSteps.식단_단일조회요청(1);
        log.info(response1.body().asString());
        //then
        assertThat(response1.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("식단 수정 요청")
    @Transactional
    void updateMeal() {
        
    }
    
    @Test
    @DisplayName("식단 삭제 요청")
    @Transactional
    void deleteMeal() {

    }
    
}
