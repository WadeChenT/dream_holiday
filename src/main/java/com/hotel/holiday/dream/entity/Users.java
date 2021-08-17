package com.hotel.holiday.dream.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Table(name = "users")
@Entity
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "actived", nullable = false)
    private String actived;

    @Column(name = "modify_at", nullable = false)
    private LocalDateTime modifyAt;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;


}