package com.pp.cs.sales.fulfillment.service;

import com.pp.cs.sales.fulfillment.dto.OrderFulfillmentReqDto;
import com.pp.cs.sales.fulfillment.entity.OrderFulfillmentEntity;

public interface OrderFulfillmentsService {

    public OrderFulfillmentEntity createOrderFulfillment(OrderFulfillmentReqDto reqDto);
}
