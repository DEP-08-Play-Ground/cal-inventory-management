package com.dulanga.cal.cal.entity;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Item implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String itemCode;
    private String itemName;
    @Column(nullable = false)
    private String itemCategory;
    private Integer itemAmount;
}
