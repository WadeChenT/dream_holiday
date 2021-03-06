package com.hotel.holiday.dream.controller.dto.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.holiday.dream.enums.CodeMsg;
import com.hotel.holiday.dream.exception.DreamHotelException;
import com.hotel.holiday.dream.utils.DateUtils;
import com.hotel.holiday.dream.utils.JsonUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@ToString
@Component
public class ResObject {

//    @Hidden
//    @JsonIgnore
//    public final static ObjectMapper oMapper =
//            new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                              //.setDateFormat(new StdDateFormat().withColonInTimeZone(true))
//                              .registerModule(new Jdk8Module())
//                              .registerModule(new JavaTimeModule());

    private LocalDateTime time;
    private long timeEpoch;
    private String rtnCode;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errCause;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, @NonNull Object> result;

    private ResObject() {
        super();
        this.time = DateUtils.getNowLocal();
        this.timeEpoch = Instant.now().toEpochMilli();
        this.result = new LinkedHashMap<>();
    }

    public static ResObject of(CodeMsg codeEnum) {
        ResObject resultObj = new ResObject();
        resultObj.message = codeEnum.getMsg();
        resultObj.rtnCode = codeEnum.getCode();
        return resultObj;
    }

    public static ResObject of(DreamHotelException exception) {
        ResObject resultObj = new ResObject();
        resultObj.message = exception.getErrMsg();
        resultObj.rtnCode = exception.getErrCode();

        if (StringUtils.isNotBlank(exception.getMessage())) {
            resultObj.errCause = exception.getMessage();
        }

        return resultObj;
    }

    public static ResObject of(Exception exception) {
        ResObject resultObj = new ResObject();
        resultObj.message = DreamHotelException.ErrorEnum.COMMON_ERROR.getErrMsg();
        resultObj.rtnCode = DreamHotelException.ErrorEnum.COMMON_ERROR.getErrCode();

        if (StringUtils.isNotBlank(exception.getMessage())) {
            resultObj.errCause = exception.getMessage();
        }

        return resultObj;
    }

    /**
     * ????????????
     */
    public ResObject addResult(@NonNull String key, Object obj) {
        result.put(key, obj);
        return this;
    }

    public ResObject addResult(@NonNull Object obj) {

        if (obj instanceof Iterable || obj.getClass().isArray()) {
            return this.addResult("list", obj);
        }

        Map<String, Object> map = JsonUtil.convertValue(obj);
        map.forEach(this::addResult);
        return this;
    }
}
