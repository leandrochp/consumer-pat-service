package com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities;

import com.github.leandrochp.consumerpatservice.domain.entities.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
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
        Address address = new Address();
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setCity(this.city);
        address.setCountry(this.country);
        address.setPortalCode(this.portalCode);

        return address;
    }
}
