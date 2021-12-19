package com.event.eventmicroservice.services;

import com.event.eventmicroservice.models.Bill;
import com.event.eventmicroservice.repositories.BillRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Transactional
    public Bill save(Bill bill){
        return billRepository.save(bill);
    }


    public Iterable<Bill> getAllBill(){
        return billRepository.findAll();
    }

}
