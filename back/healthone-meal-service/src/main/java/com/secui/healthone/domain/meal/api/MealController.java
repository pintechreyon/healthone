package com.secui.healthone.domain.meal.api;

import com.secui.healthone.domain.meal.dto.MealReqDto;
import com.secui.healthone.domain.meal.dto.MealResDto;
import com.secui.healthone.domain.meal.service.MealService;
import com.secui.healthone.global.error.response.ErrorResponse;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/meal")
@RequiredArgsConstructor
@Tag(name = "Meal", description = "식사 관련 컨트롤러")
public class MealController {

    private final MealService mealService;

    @Operation(summary = "식사 정보 단일 조회", description = "식사 정보 단일 조회 API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 정보 단일 조회 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MealResDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))), })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public RestApiResponse<MealResDto> getMeal(@RequestParam("no") Integer no) {
        return new RestApiResponse<>("식사 정보 단일 조회 성공", mealService.getMeal(no));
    }

    @Operation(summary = "식사 정보 리스트 조회", description = "식사 정보 리스트 조회 (날짜기반) API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 리스트 조회 성공", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MealResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    public RestApiResponse<List<MealResDto>> getMealList(@RequestParam("date") String date, @RequestParam("userno") Integer userNo) throws ParseException {
        return new RestApiResponse<>(date+ "식사 리스트 조회 성공", mealService.getMealList(date, userNo));
    }

    @Operation(summary = "식사 등록", description = "식사 등록 API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 등록 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MealResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public RestApiResponse<MealResDto> insertMeal(@RequestBody MealReqDto requestDto) {
        return new RestApiResponse<>("식사 등록 성공", mealService.insertMeal(requestDto));
    }

    @Operation(summary = "식사 수정", description = "식사 수정 API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MealResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping
    public RestApiResponse<MealResDto> updateMeal(@RequestBody MealReqDto requestDto) {
        return new RestApiResponse<>("식사 수정 성공", mealService.insertMeal(requestDto));
    }

    @Operation(summary = "식사 삭제", description = "식사 삭제 API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MealResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public RestApiResponse<Void> updateMeal(@RequestParam("no") Integer no) {
        mealService.deleteMeal(no);
        return new RestApiResponse<>("식사 삭제 성공", null);
    }
}