package com.github.leandrochp.consumerservice.infrastructure.repository.entity;

import com.github.leandrochp.consumerservice.domain.consumer.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String street;
    private Integer number;
    private String city;
    private String country;

    @Column(name = "portal_code")
    private String portalCode;

    @OneToOne
    private ConsumerEntity consumer;

    public Address toModel() {
        val address = new Address();
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setCity(this.city);
        address.setCountry(this.country);
        address.setPortalCode(this.portalCode);

        return address;
    }
}
