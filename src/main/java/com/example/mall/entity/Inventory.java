package com.example.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Inventory {
    @Id
    private Long id;
    private Integer count;
    private Integer lockedCount;

    @JsonIgnore
    @OneToOne(targetEntity = Product.class)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Product product;


    public Inventory(Long id, Integer count, Integer lockedCount) {
        this.id = id;
        this.count = count;
        this.lockedCount = lockedCount;
    }

}
