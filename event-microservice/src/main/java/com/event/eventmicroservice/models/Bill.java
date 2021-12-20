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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> productsSelected = new ArrayList<>();

    @Column
    private Boolean finished=false;
}
