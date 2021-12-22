package com.event.eventmicroservice.controller;

import com.event.eventmicroservice.models.Bill;
import com.event.eventmicroservice.services.BillService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/order")
    public Bill order(@RequestBody Bill bill){
        return billService.save(bill);
    }


}
