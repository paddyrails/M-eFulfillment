package com.pp.cs.sales.fulfillment.service;

import com.pp.cs.sales.fulfillment.dao.OrderFulfillmentsDao;
import com.pp.cs.sales.fulfillment.dto.OrderFulfillmentReqDto;
import com.pp.cs.sales.fulfillment.entity.OrderFulfillmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderFulfillmentsServiceImpl implements OrderFulfillmentsService{

    @Autowired
    private OrderFulfillmentsDao orderFulfillmentsDao;

    @Override
    public OrderFulfillmentEntity createOrderFulfillment(OrderFulfillmentReqDto reqDto) {
        OrderFulfillmentEntity entity = new OrderFulfillmentEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setOrderId(reqDto.getOrderId());
        this.orderFulfillmentsDao.save(entity);

        return entity;
    }
}
