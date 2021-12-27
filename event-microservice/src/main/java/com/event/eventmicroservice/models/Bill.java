package com.event.eventmicroservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String userName;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event eventSelected;

    @Column(nullable = false)
    private Float totalAmount;

    @Column
    private LocalDate date;

    @Column
    private String orderTransaction;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> productsSelected = new ArrayList<>();

    @Column
    private String status;

    @Column
    private String employee;

    public List<String> getAllNameProducts(){
        List<String> list = new ArrayList<>();
        for (Product p : this.productsSelected){
            list.add(p.getName());
        }
        return list;
    }
}
