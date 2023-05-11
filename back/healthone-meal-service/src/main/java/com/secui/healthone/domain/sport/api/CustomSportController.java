package com.secui.healthone.domain.sport.api;

import com.secui.healthone.domain.sport.dto.CustomSportReqDto;
import com.secui.healthone.domain.sport.dto.CustomSportResDto;
import com.secui.healthone.domain.sport.service.CustomSportService;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customsport")
@RequiredArgsConstructor
@Tag(name = "CustomSport", description = "사용자 운동 데이터 관련 컨트롤러")
public class CustomSportController {
    private final CustomSportService customSportService;

    @Operation(summary = "사용자 운동 데이터 검색 정보 반환", description = "사용자 운동 데이터 검색 반환 API", tags = {"CustomSport"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 운동 데이터 검색 성공", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomSportResDto.class))),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "name", description = "사용자 운동 이름", example = "걷기")
    @GetMapping("/search")
    public RestApiResponse<List<CustomSportResDto>> searchCustomSport (@Valid @RequestParam("name") String name) {
        Integer userNo = 1;
        return new RestApiResponse<>("사용자 운동 데이터 검색 성공", customSportService.searchCustomSport(userNo, name));
    }

    @Operation(summary = "사용자 운동 데이터 조회 반환", description = "사용자 운동 데이터 조회 API", tags = {"CustomSport"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "운동 데이터 조회 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomSportResDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "no", description = "사용자 운동 식별번호", example = "1")
    @GetMapping
    public RestApiResponse<CustomSportResDto> getCustomSportData (@Valid @RequestParam("no") Integer no) {
        Integer userNo = 1;
        return new RestApiResponse<>("사용자 운동 데이터 조회 성공", customSportService.getCustomSport(userNo, no));
    }

    @Operation(summary = "사용자 운동 데이터 등록", description = "사용자 운동 데이터 등록 API", tags = {"CustomSport"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 운동 데이터 등록 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CustomSportResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "사용자 운등 등록 객체")
    @PostMapping
    public RestApiResponse<CustomSportResDto> insertCustomSportData (@Valid @RequestBody CustomSportReqDto reqDto) {
        return new RestApiResponse<>("사용자 운동 데이터 등록 성공", customSportService.insertCustomSport(reqDto));
    }

    @Operation(summary = "사용자 운동 데이터 수정", description = "사용자 운동 데이터 수정 API", tags = {"CustomSport"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 운동 데이터 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CustomSportResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "사용자 운동 수정 객체")
    @PatchMapping
    public RestApiResponse<CustomSportResDto> updateCustomSportData (@Valid @RequestBody CustomSportReqDto reqDto) {
        return new RestApiResponse<>("사용자 운동 데이터 수정 성공", customSportService.insertCustomSport(reqDto));
    }

    @Operation(summary = "사용자 운동 데이터 삭제", description = "사용자 운동 데이터 삭제 API", tags = {"CustomSport"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 운동 데이터 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "no", description = "사용자 운동 식별번호", example = "1")
    @DeleteMapping
    public RestApiResponse<Integer> deleteCustomSportData (@Valid @RequestParam("no") Integer no) {
        customSportService.deleteCustomSport(no);
        return new RestApiResponse<>("사용자 운동 데이터 삭제 성공", no);
    }
}
