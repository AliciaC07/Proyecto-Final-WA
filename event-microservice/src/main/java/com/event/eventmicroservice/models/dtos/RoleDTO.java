package com.event.eventmicroservice.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
public class RoleDTO {

    private Integer id;


    private String name;


    private Boolean active = true;
}
