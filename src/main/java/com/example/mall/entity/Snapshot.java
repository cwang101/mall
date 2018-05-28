package com.example.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Setter
@Getter
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snapId;
    private Long productId;
    private Long orderId;
    private String productName;
    private String productDescription;
    private Double purchasePrice;
    private Integer purchaseCount;

    @JsonIgnore
    @ManyToOne(targetEntity = OrderInfo.class)
    @JoinColumn(name ="orderId", insertable = false, updatable = false)
    private OrderInfo order;

    public void update(Long orderId, String name, String description, Double price) {
        this.orderId=orderId;
        this.productName=name;
        this.productDescription=description;
        this.purchasePrice=price;
    }
}
