package com.example.umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{

    // 공통
    OK(HttpStatus.OK, "COMMON200", "요청이 성공적으로 처리되었습니다."),

    // Store
    STORE_CREATED(HttpStatus.CREATED, "STORE201_1", "가게가 성공적으로 등록되었습니다."),
    STORE_READ_SUCCESS(HttpStatus.OK, "STORE200_1", "가게 정보가 성공적으로 조회되었습니다."),
    STORE_UPDATED(HttpStatus.OK, "STORE200_2", "가게 정보가 성공적으로 수정되었습니다."),
    STORE_DELETED(HttpStatus.OK, "STORE200_3", "가게가 성공적으로 삭제되었습니다."),

    // Review
    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 작성되었습니다."),
    REVIEW_READ_SUCCESS(HttpStatus.OK, "REVIEW200_1", "리뷰가 성공적으로 조회되었습니다."),
    REVIEW_UPDATED(HttpStatus.OK, "REVIEW200_2", "리뷰가 성공적으로 수정되었습니다."),
    REVIEW_DELETED(HttpStatus.OK, "REVIEW200_3", "리뷰가 성공적으로 삭제되었습니다."),

    // User
    USER_CREATED(HttpStatus.CREATED, "USER201_1", "회원이 성공적으로 등록되었습니다."),
    USER_READ_SUCCESS(HttpStatus.OK, "USER200_1", "회원 정보가 성공적으로 조회되었습니다."),
    USER_UPDATED(HttpStatus.OK, "USER200_2", "회원 정보가 성공적으로 수정되었습니다."),
    USER_DELETED(HttpStatus.OK, "USER200_3", "회원이 성공적으로 삭제되었습니다."),

    // Mission
    MISSION_CREATED(HttpStatus.CREATED, "MISSION201_1", "미션이 성공적으로 생성되었습니다."),
    MISSION_READ_SUCCESS(HttpStatus.OK, "MISSION200_1", "미션이 성공적으로 조회되었습니다."),
    MISSION_UPDATED(HttpStatus.OK, "MISSION200_2", "미션이 성공적으로 수정되었습니다."),
    MISSION_DELETED(HttpStatus.OK, "MISSION200_3", "미션이 성공적으로 삭제되었습니다."),

    // UserMission
    USER_MISSION_CREATED(HttpStatus.CREATED, "USERMISSION201_1", "미션 도전이 성공적으로 시작되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}