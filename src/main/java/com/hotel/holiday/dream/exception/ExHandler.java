package com.hotel.holiday.dream.exception;

import com.hotel.holiday.dream.controller.dto.common.ResObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExHandler extends ResponseEntityExceptionHandler {

    protected static String ERROR_MSG_TITLE = "DreamHotel Server Error:";
//    private final ActionLogService logService;

    @ResponseBody
    @ExceptionHandler(DreamHotelException.class)
    public ResponseEntity<ResObject> dreamHotelExceptionHandling(HttpServletRequest req,
                                                                 HttpServletResponse res,
                                                                 DreamHotelException e) {

        return getResObjectResponseEntity(e, ResObject.of(e));
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResObject> dreamHotelExceptionHandling(HttpServletRequest req,
                                                                HttpServletResponse res,
                                                                RuntimeException e) {

        return getResObjectResponseEntity(e, ResObject.of(e));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResObject> handleMaxSizeException(MaxUploadSizeExceededException e) {
        return getResObjectResponseEntity(e, ResObject.of(e));
    }

    private ResponseEntity<ResObject> getResObjectResponseEntity(RuntimeException e, ResObject of) {
        log.error(ERROR_MSG_TITLE, e);

        ResponseEntity<ResObject> resEntity = ResponseEntity.ok()
                                                            .body(of);

//        var actionLog = ActionLogThreadLocal.get()
//                                            .setResHeader(JsonUtil.toJsonUnFormattedLog(resEntity.getHeaders()))
//                                            .setResBody(JsonUtil.toJsonUnFormattedLog(resEntity.getBody()));
//
//        logService.save(actionLog);

        return resEntity;
    }
}
