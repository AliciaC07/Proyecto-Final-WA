package com.event.eventmicroservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {

    private String userName;
    private String orderNumber;
    private String email;
    private String eventSelected;
    private Float totalAmount;
    private String date;
    private List<String> productsSelected = new ArrayList<>();


}
