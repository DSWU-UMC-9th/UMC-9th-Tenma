package com.example.umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{

    // 공통
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400_1", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH401_1", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH403_1", "요청이 거부되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404_1", "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500_1", "예기치 않은 서버 에러가 발생했습니다."),

    // Store
    STORE_REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "존재하지 않는 지역입니다."),
    STORE_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_2", "존재하지 않는 카테고리입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_3", "존재하지 않는 가게입니다."),
    STORE_CREATE_FAILED(HttpStatus.BAD_REQUEST, "STORE400_1", "가게 등록에 실패했습니다."),
    STORE_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "STORE400_2", "가게 수정에 실패했습니다."),
    STORE_DELETE_FAILED(HttpStatus.BAD_REQUEST, "STORE400_3", "가게 삭제에 실패했습니다."),

    // Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    REVIEW_CREATE_FAILED(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 작성에 실패했습니다."),
    REVIEW_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "REVIEW400_2", "리뷰 수정에 실패했습니다."),
    REVIEW_DELETE_FAILED(HttpStatus.BAD_REQUEST, "REVIEW400_3", "리뷰 삭제에 실패했습니다."),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "사용자를 찾을 수 없습니다."),
    USER_CREATE_FAILED(HttpStatus.BAD_REQUEST, "USER400_1", "회원 등록에 실패했습니다."),
    USER_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "USER400_2", "회원 정보 수정에 실패했습니다."),
    USER_DELETE_FAILED(HttpStatus.BAD_REQUEST, "USER400_3", "회원 삭제에 실패했습니다."),

    // Mission
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "요청한 미션을 찾을 수 없습니다."),
    MISSION_CREATE_FAILED(HttpStatus.BAD_REQUEST, "MISSION400_1", "미션 생성에 실패했습니다."),
    MISSION_READ_FAILED(HttpStatus.BAD_REQUEST, "MISSION400_2", "미션 조회에 실패했습니다."),
    MISSION_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "MISSION400_3", "미션 수정에 실패했습니다."),
    MISSION_DELETE_FAILED(HttpStatus.BAD_REQUEST, "MISSION400_4", "미션 삭제에 실패했습니다."),

    // UserMission
    USER_MISSION_CREATE_FAILED(HttpStatus.BAD_REQUEST, "USERMISSION400_1", "미션 도전에 실패했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}