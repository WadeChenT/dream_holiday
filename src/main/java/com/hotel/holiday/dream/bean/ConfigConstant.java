package com.hotel.holiday.dream.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@ConfigurationProperties(prefix = "constant")
public class ConfigConstant {

    public static String defaultEncoding;
    public static String mailHost;
    public static Integer mailPort;
    public static String username;
    public static String mailPassword;
    public static String mailSmtpAuth;
    public static String startTlsEnable;
    public static String startTlsRequired;

    @Value("${constant.mailSender.defaultEncoding}")
    public void setDefaultEncoding(String defaultEncoding) {
        ConfigConstant.defaultEncoding = defaultEncoding;
    }

    @Value("${constant.mailSender.mailHost}")
    public void setMailHost(String mailHost) {
        ConfigConstant.mailHost = mailHost;
    }

    @Value("${constant.mailSender.mailPort}")
    public void setMailPort(Integer mailPort) {
        ConfigConstant.mailPort = mailPort;
    }

    @Value("${constant.mailSender.username}")
    public void setUsername(String username) {
        ConfigConstant.username = username;
    }

    @Value("${constant.mailSender.mailPassword}")
    public void setMailPassword(String mailPassword) {
        ConfigConstant.mailPassword = mailPassword;
    }

    @Value("${constant.mailSender.mailSmtpAuth}")
    public void setMailSmtpAuth(String mailSmtpAuth) {
        ConfigConstant.mailSmtpAuth = mailSmtpAuth;
    }

    @Value("${constant.mailSender.startTlsEnable}")
    public void setStartTlsEnable(String startTlsEnable) {
        ConfigConstant.startTlsEnable = startTlsEnable;
    }

    @Value("${constant.mailSender.startTlsRequired}")
    public void setStartTlsRequired(String startTlsRequired) {
        ConfigConstant.startTlsRequired = startTlsRequired;
    }

    @PostConstruct
    public void sOut() {
        log.info("constant.mailSender.defaultEncoding: {}", defaultEncoding);
        log.info("constant.mailSender.mailHost: {}", mailHost);
        log.info("constant.mailSender.mailPort: {}", mailPort);
        log.info("constant.mailSender.username: {}", username);
        log.info("constant.mailSender.mailPassword: {}", mailPassword);
        log.info("constant.mailSender.mailSmtpAuth: {}", mailSmtpAuth);
        log.info("constant.mailSender.startTlsEnable: {}", startTlsEnable);
        log.info("constant.mailSender.startTlsRequired: {}", startTlsRequired);
    }
}
