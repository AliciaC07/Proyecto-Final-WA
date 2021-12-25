package com.event.eventmicroservice.services;

import com.event.eventmicroservice.models.Bill;
import com.event.eventmicroservice.models.Product;
import com.event.eventmicroservice.models.dtos.EmailSend;
import com.event.eventmicroservice.models.dtos.Order;
import com.event.eventmicroservice.models.dtos.OrderInfoEmployee;
import com.event.eventmicroservice.models.dtos.UserDTO;
import com.event.eventmicroservice.repositories.BillRepository;
import com.event.eventmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;

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
        bill.setDate(LocalDate.now());
        Order order = new Order();
        order.setUserName(bill.getUserName());
        order.setOrderNumber("OTE-"+bill.getOrderTransaction());
        order.setDate(LocalDate.now().toString());
        order.setEventSelected(bill.getEventSelected().getName());
        order.setEmail(bill.getEmail());
        order.getProductsSelected().addAll(bill.getAllNameProducts());
        order.setTotalAmount(bill.getTotalAmount());
        String status = notificationOrder(order);
        UserDTO[] userDTOS = retrieveClients();
        System.out.println(userDTOS.length);
        for (UserDTO userDTO : userDTOS){
            OrderInfoEmployee orderInfoEmployee = new OrderInfoEmployee();
            orderInfoEmployee.setEmployee(userDTO.getName()+"  "+ userDTO.getLastName());
            orderInfoEmployee.setClient(order.getUserName());
            orderInfoEmployee.setEvent(order.getEventSelected());
            orderInfoEmployee.setEmail(userDTO.getEmail());
            notificationOrderEmployee(orderInfoEmployee);
        }
        System.out.print(status);
        return billRepository.save(bill);
    }

    public Bill findBillById(Integer id){
        return billRepository.findByIdAndFinishedFalse(id)
                .orElseThrow(()-> new EntityNotFoundException("This Bill was not found"));

    }

    public Iterable<Bill> findBillByUsername(String userName){
        return billRepository.findAllByUserName(userName);
    }

    public Iterable<Bill> findAllBills(){
        return billRepository.findAll();
    }

    @Transactional
    public Bill changeStatus(Integer id, String status){
        Bill bill = findBillById(id);
        bill.setStatus(status);
        billRepository.save(bill);
        return bill;

    }

    public String notificationSender(EmailSend emailSend) {

        HttpEntity<EmailSend> bodyRequest = new HttpEntity<>(emailSend);
        return restTemplate.exchange("http://NOTIFICATION-MICROSERVICE/api/user-notification",
                HttpMethod.POST,
                bodyRequest,
                String.class).getBody();
    }
    public String notificationOrder(Order order) {

        HttpEntity<Order> bodyRequest = new HttpEntity<>(order);
        return restTemplate.exchange("http://NOTIFICATION-MICROSERVICE/api/order-notification",
                HttpMethod.POST,
                bodyRequest,
                String.class).getBody();
    }

    public UserDTO[] retrieveClients() {

        return restTemplate.exchange("http://USER-MICROSERVICE/api/users-employee",
                HttpMethod.GET,
                null,
                UserDTO[].class).getBody();
    }

    public void notificationOrderEmployee(OrderInfoEmployee order) {

        HttpEntity<OrderInfoEmployee> bodyRequest = new HttpEntity<>(order);
        restTemplate.exchange("http://NOTIFICATION-MICROSERVICE/api/employee-notification",
                HttpMethod.POST,
                bodyRequest,
                String.class).getBody();
    }




    public Iterable<Bill> getAllBill(){
        return billRepository.findAll();
    }

}
