package com.hotel.holiday.dream.service.Impl;

import com.hotel.holiday.dream.controller.dto.OrderCreateDto;
import com.hotel.holiday.dream.entity.OrderList;
import com.hotel.holiday.dream.entity.Product;
import com.hotel.holiday.dream.repo.OrderListRepository;
import com.hotel.holiday.dream.repo.ProductRepository;
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
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final JavaMailSender mailSender;
    private final OrderListRepository orderListRepository;
    private final ProductRepository productRepository;

    @Override
    public String createOrder(OrderCreateDto orderCreateDto) {
        OrderList orderList = new OrderList(orderCreateDto);
        OrderList entity = orderListRepository.save(orderList);
        orderCreateDto.setId(entity.getId());
        Product product = productRepository.getById(orderCreateDto.getProductId());

        sentEmail(orderCreateDto, product);

        return entity.getId();
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

    private void sentEmail(OrderCreateDto orderCreateDto, Product product) {
        doSendEmail(orderCreateDto, product);

//        Runnable runnable = new MyRunnable(orderCreateDto, product);
//        Thread thread = new Thread(runnable);
//        thread.start();
    }

    private void doSendEmail(OrderCreateDto orderCreateDto, Product product) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            message.setTo(orderCreateDto.getEmail());
            message.setSubject("Dream Hotel Invoice");
            String content = "";

            if (product.getType().equalsIgnoreCase("1")) {
                List<String> contentList = Files.readAllLines(ResourceUtils.getFile("classpath:templates/dream_hotel_invoice.html").toPath());
                content = rewriteEmailContentForRoom(contentList, orderCreateDto, product);
            } else if (product.getType().equalsIgnoreCase("2")) {
                List<String> contentList = Files.readAllLines(ResourceUtils.getFile("classpath:templates/dream_hotel_weddinghall.html").toPath());
                content = rewriteEmailContentForConference(contentList, orderCreateDto, product);
            } else if (product.getType().equalsIgnoreCase("3")) {
                List<String> contentList = Files.readAllLines(ResourceUtils.getFile("classpath:templates/dream_hotel_restaurant.html").toPath());
                content = rewriteEmailContentForDeal(contentList, orderCreateDto, product);
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

    private String rewriteEmailContentForRoom(List<String> contentList, OrderCreateDto orderCreateDto, Product product) {
        String content = "";
        for (String str : contentList) {
            str  = StringUtils.replace(str,"{{$name}}", orderCreateDto.getEmail());
            str  = StringUtils.replace(str,"{{$title}}", product.getTitle());
            str  = StringUtils.replace(str,"{{$orderId}}", orderCreateDto.getId());
//            str  = StringUtils.replace(str,"{{$files}}", product.getFiles().toString());
            str  = StringUtils.replace(str,"{{$startAt}}", orderCreateDto.getStartAt().toString());
            str  = StringUtils.replace(str,"{{$endAt}}", orderCreateDto.getEndAt().toString());
            content += str;
        }

        return content;
    }

    private String rewriteEmailContentForConference(List<String> contentList, OrderCreateDto orderCreateDto, Product product) {
        String content = "";
        for (String str : contentList) {
            str  = StringUtils.replace(str,"{{$name}}", orderCreateDto.getEmail());
            str  = StringUtils.replace(str,"{{$title}}", product.getTitle());
            str  = StringUtils.replace(str,"{{$orderId}}", orderCreateDto.getId());
            str  = StringUtils.replace(str,"{{$startAt}}", orderCreateDto.getStartAt().toString());
            str  = StringUtils.replace(str,"{{$remark}}", orderCreateDto.getRemark());
            content += str;
        }

        return content;
    }

    private String rewriteEmailContentForDeal(List<String> contentList, OrderCreateDto orderCreateDto, Product product) {
        String content = "";
        for (String str : contentList) {
            str  = StringUtils.replace(str,"{{$name}}", orderCreateDto.getEmail());
            str  = StringUtils.replace(str,"{{$title}}", product.getTitle());
            str  = StringUtils.replace(str,"{{$orderId}}", orderCreateDto.getId());
            str  = StringUtils.replace(str,"{{$count}}", orderCreateDto.getCount());
            content += str;
        }

        return content;
    }

    public class MyRunnable implements Runnable {
        OrderCreateDto orderCreateDto;
        Product product;

        public MyRunnable(OrderCreateDto orderCreateDto, Product product) {
            // store parameter for later user
            this.orderCreateDto = orderCreateDto;
            this.product = product;
        }

        public void run() {
            doSendEmail(orderCreateDto, product);
        }
    }
}
