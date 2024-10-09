package com.ikucuk.BankManagementProject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")  //generator = "native" =>mevcut db destekledigi kimlik ve sıra sutunlarını kullanacak deme
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}
