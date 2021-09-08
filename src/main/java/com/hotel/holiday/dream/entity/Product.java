package com.hotel.holiday.dream.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "files")
    private byte[] files;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @LastModifiedDate
    @Column(name = "modify_at", nullable = false)
    private LocalDateTime modifyAt;

    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    
}