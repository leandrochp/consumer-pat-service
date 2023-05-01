package com.github.leandrochp.consumerpatservice.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Consumer {
    private Integer id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;
    private Contact contact;
    private Address address;
    private List<Card> cards;
}
