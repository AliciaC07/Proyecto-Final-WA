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

    @PostMapping("/change-status/{id}/{status}")
    public Bill changeStatus(@PathVariable Integer id, @PathVariable String status){
        return billService.changeStatus(id, status);
    }

    @PostMapping("/order")
    public Bill order(@RequestBody Bill bill){
        return billService.save(bill);
    }

    @GetMapping("/orders")
    public Iterable<Bill> findAll(){
        return billService.findAllBills();
    }

    @GetMapping("/client/orders/{userName}")
    public Iterable<Bill> findBillsByClient(@PathVariable String userName){
        return billService.findBillByUsername(userName);
    }


}
