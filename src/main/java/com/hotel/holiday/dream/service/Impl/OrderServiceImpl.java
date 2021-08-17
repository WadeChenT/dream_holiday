package com.hotel.holiday.dream.service.Impl;

import com.hotel.holiday.dream.controller.dto.OrderCreateDto;
import com.hotel.holiday.dream.entity.OrderList;
import com.hotel.holiday.dream.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final JavaMailSender mailSender;

    @Override
    public void createOrder(OrderCreateDto orderCreateDto) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
                                                              MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                                              StandardCharsets.UTF_8.name());
            message.setTo(orderCreateDto.getEmail());
            message.setSubject("Dream Hotel Invoice");
            List<String> contentList = Files.readAllLines(ResourceUtils.getFile("classpath:templates/dream_hotel_invoice.html").toPath());
            String content = "";
            for (String str : contentList) {
                str  = StringUtils.replace(str,"{{$name}}", orderCreateDto.getEmail());
                str  = StringUtils.replace(str,"{{$startAt}}", orderCreateDto.getStartAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                str  = StringUtils.replace(str,"{{$endAt}}", orderCreateDto.getEndAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                content += str;
            }

            message.setText(content, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderList get(String id) {
        return null;
    }

    @Override
    public List<OrderList> getList() {
        return null;
    }

    @Override
    public OrderList update() {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
