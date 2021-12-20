package com.event.eventmicroservice.controller;

import com.event.eventmicroservice.models.Bill;
import com.event.eventmicroservice.services.BillService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/close-job/{id}")
    public Bill closeJob(@PathVariable Integer id){
        return billService.closeJob(id);
    }


}
