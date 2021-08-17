package com.hotel.holiday.dream.exception;

import com.hotel.holiday.dream.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DreamHotelException extends RuntimeException {
    private static final long serialVersionUID = -6917587535657688263L;
    private LocalDateTime errTime;
    private String errMsg;
    private String errCode;

    private DreamHotelException() {
        super();
        this.errTime = DateUtils.getNowLocal();
    }

    public static DreamHotelException occur() {
        return new DreamHotelException();
    }

    public static DreamHotelException occur(String errCode, String errMsg) {
        return occur().setErrCode(errCode)
                      .setErrMsg(errMsg);
    }

    public static DreamHotelException occur(ErrorEnum error) {
        return occur(error.getErrCode(), error.getErrMsg());
    }

    @Getter
    public enum ErrorEnum {
        COMMON_ERROR("9900", "Server Error."),
        NOT_FOUND("9901", "Resource not found."),
        URL_NOT_FOUND("9902", "https url not found."),
        HTTP_CONNECTION_ISSUE("9903", "http connection issue."),
        OBJECT_FORMAT_ISSUE("9904", "Object format issue."),
        JSON_FORMAT_ISSUE("9905", "Json format issue."),
        JSON_FORMAT_ISSUE_CREQ("9905", "Json format issue. (CReq)"),

        MESSAGE_TYPE_LENGTH_ISSUE("9906", "Message Type length issue."),
        RESPONSE_NOT_FOUND("9907", "Response not found."),
        NO_LOGS("9908", "no logs."),


        UNAUTHORIZED("99999", "unauthorized."),
        TOKEN_EXPIRED("99998", "token expired."),
        TOKEN_ERROR("99997", "token error."),
        TOKEN_ERROR_TYPE("99996", "token error."),
        ADMIN_USER_NOT_FOUND("99990", "user not found.");

        private String errMsg;
        private String errCode;

        ErrorEnum(String errCode, String errMsg) {
            this.errCode = errCode;
            this.errMsg = errMsg;
        }
    }

}
