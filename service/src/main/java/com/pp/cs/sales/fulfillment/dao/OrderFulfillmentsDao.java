package com.pp.cs.sales.fulfillment.dao;

import com.pp.cs.sales.fulfillment.entity.OrderFulfillmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFulfillmentsDao extends JpaRepository<OrderFulfillmentEntity, String> {

}
