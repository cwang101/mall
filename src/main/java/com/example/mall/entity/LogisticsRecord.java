package com.example.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class LogisticsRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String logisticsStatus;
    private String outboundTime;
    private String signedTime;
    private String deliveryMan;

    @JsonIgnore
    @OneToOne(targetEntity = OrderInfo.class)
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private OrderInfo order;

    public LogisticsRecord(Long orderId, String  logisticsStatus) {
        this.orderId=orderId;
        this.logisticsStatus=logisticsStatus;
    }
}
