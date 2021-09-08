package com.hotel.holiday.dream.entity;

import com.hotel.holiday.dream.controller.dto.OrderCreateDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;


@Data
@Entity
@Table(name = "order_list")
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
public class OrderList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "email_n_a", nullable = false)
    private String emailNoneAuth;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "count")
    private String count;

    @Column(name = "remark")
    private String remark;

    @Column(name = "payed", nullable = false)
    private Boolean payed;

    @Column(name = "actived", nullable = false)
    private String actived;

    @LastModifiedDate
    @Column(name = "modify_at", nullable = false)
    private LocalDateTime modifyAt;

    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    public OrderList(OrderCreateDto orderCreateDto) {
        this.productId = orderCreateDto.getProductId();
        this.emailNoneAuth = orderCreateDto.getEmail();
        if (!Objects.isNull(orderCreateDto.getStartAt()))
            this.startAt = LocalDateTime.ofInstant(
                    orderCreateDto.getStartAt().toInstant(), ZoneId.systemDefault());
        if (!Objects.isNull(orderCreateDto.getEndAt()))
            this.endAt = LocalDateTime.ofInstant(
                orderCreateDto.getEndAt().toInstant(), ZoneId.systemDefault());
        this.price = orderCreateDto.getPrice();
        this.remark = orderCreateDto.getRemark();
        this.payed = false;
        this.actived = "1";
    }
}