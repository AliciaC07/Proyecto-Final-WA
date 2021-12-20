package com.notification.notificationmicroservice.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Order {

    private String userName;
    private String orderNumber;
    private String email;
    private String eventSelected;
    private Float totalAmount;
    private LocalDate date;
    private List<String> productsSelected;


}
