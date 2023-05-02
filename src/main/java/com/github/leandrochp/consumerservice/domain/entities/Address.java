package com.github.leandrochp.consumerservice.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String street;
    private Integer number;
    private String city;
    private String country;
    private String portalCode;
}
