package com.hotel.holiday.dream;

import com.hotel.holiday.dream.bean.ConfigConstant;
import com.hotel.holiday.dream.service.Impl.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class DreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamApplication.class, args);
	}

	@Bean
	@DependsOn("configConstant")
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(ConfigConstant.mailHost);
		mailSender.setPort(ConfigConstant.mailPort);

		mailSender.setUsername(ConfigConstant.username);
		mailSender.setPassword(ConfigConstant.mailPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", ConfigConstant.mailSmtpAuth);
		props.put("mail.smtp.starttls.enable", ConfigConstant.startTlsEnable);
		props.put("mail.smtp.starttls.required", ConfigConstant.startTlsRequired);
		props.put("mail.debug", "true");

		return mailSender;
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
}
