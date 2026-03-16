package com.pp.cs.sales.fulfillment.api;

import com.pp.cs.sales.fulfillment.dto.OrderFulfillmentReqDto;
import com.pp.cs.sales.fulfillment.entity.OrderFulfillmentEntity;
import com.pp.cs.sales.fulfillment.service.OrderFulfillmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/sales/quotes/v1/", produces = "application/json")
public class OrderFulfillmentsController {

    @Autowired
    private OrderFulfillmentsService orderFulfillmentsService;

    @PostMapping(path = "/create/orderfulfillments")
    public ResponseEntity<OrderFulfillmentEntity> createOrderFulfillment(OrderFulfillmentReqDto reqDto){
        return ResponseEntity.ok(this.orderFulfillmentsService.createOrderFulfillment(reqDto));
    }
}
