package com.event.eventmicroservice.controller;

import com.event.eventmicroservice.services.BillService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }


}
