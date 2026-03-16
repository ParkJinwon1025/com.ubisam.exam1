package com.ubisam.exam1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "exam_address")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phone;
    private String address;

}
