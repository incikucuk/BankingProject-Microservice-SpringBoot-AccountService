package com.ikucuk.BankManagementProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass    //Bu classları genellikle ortak state ve mapping bilgisi olan entitylerimiz olduğunda kullanırız.
@Getter
@Setter
@ToString
public class BaseEntity {
    @Column(updatable = false)    //db UPDATE islemi yapılmamasi icin insertable=false
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)   //db INSERT islemi yapılmamasi icin insertable=false
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private String updatedBy;
}
