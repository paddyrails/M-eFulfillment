package com.pp.cs.sales.fulfillment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OrderFulfillments")
@Getter
@Setter
@NoArgsConstructor
public class OrderFulfillmentEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "order_id")
    private String orderId;
}
