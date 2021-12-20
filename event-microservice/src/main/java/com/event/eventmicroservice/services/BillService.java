package com.event.eventmicroservice.services;

import com.event.eventmicroservice.models.Bill;
import com.event.eventmicroservice.models.Product;
import com.event.eventmicroservice.models.dtos.EmailSend;
import com.event.eventmicroservice.repositories.BillRepository;
import com.event.eventmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    public BillService(BillRepository billRepository, ProductRepository productRepository) {
        this.billRepository = billRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Bill save(Bill bill){
        for (Product product : bill.getProductsSelected()) {
            product.setAvailability(false);
            productRepository.save(product);
        }
        return billRepository.save(bill);
    }

    public Bill findBillById(Integer id){
        return billRepository.findByIdAndStatusFalse(id)
                .orElseThrow(()-> new EntityNotFoundException("This Bill was not found"));

    }

    @Transactional
    public Bill closeJob(Integer id){
        Bill bill = findBillById(id);
        for (Product product : bill.getProductsSelected()) {
            product.setAvailability(true);
            productRepository.save(product);
        }
        bill.setFinished(true);
        billRepository.save(bill);
        return bill;

    }

    public String notificationSender(EmailSend emailSend) {

        HttpEntity<EmailSend> bodyRequest = new HttpEntity<>(emailSend);
        return restTemplate.exchange("http://NOTIFICATION-FACTORY/api/user-notification",
                HttpMethod.POST,
                bodyRequest,
                String.class).getBody();
    }




    public Iterable<Bill> getAllBill(){
        return billRepository.findAll();
    }

}
