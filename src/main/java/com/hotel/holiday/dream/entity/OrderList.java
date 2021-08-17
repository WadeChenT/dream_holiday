package com.hotel.holiday.dream.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "order_list")
@Accessors(chain = true)
public class OrderList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "poduct_id", nullable = false)
    private String productId;

    @Column(name = "email_n_a", nullable = false)
    private String emailNoneAuth;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "payed", nullable = false)
    private Boolean payed;

    @Column(name = "actived", nullable = false)
    private String actived;

    @Column(name = "modify_at", nullable = false)
    private LocalDateTime modifyAt;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

}