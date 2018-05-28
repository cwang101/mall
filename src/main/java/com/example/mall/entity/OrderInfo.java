package com.example.mall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    private String status;
    private String createTime;
    private String finishTime;
    private String paidTime;
    private String withdrawnTime;
    private Long userId;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "order")
    List<Snapshot>  purchaseItemList=new ArrayList<>();



    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "order")
    private LogisticsRecord logisticsRecord;

    public OrderInfo() {
        this.totalPrice=0.0d;
        this.status="unPaid";
        this.createTime=String.valueOf(new Date(System.currentTimeMillis()));
    }
}
