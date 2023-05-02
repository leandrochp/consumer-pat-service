package com.github.leandrochp.consumerservice.infrastructure.repositories.entities;

import com.github.leandrochp.consumerservice.domain.entities.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;
    @Column(name = "residence_phone_number")
    private String residencePhoneNumber;
    @Column(name = "work_phone_number")
    private String workPhoneNumber;

    private String email;

    @OneToOne
    private ConsumerEntity consumer;

    public Contact toModel() {
        Contact contact = new Contact();
        contact.setMobilePhoneNumber(this.mobilePhoneNumber);
        contact.setResidencePhoneNumber(this.residencePhoneNumber);
        contact.setWorkPhoneNumber(this.workPhoneNumber);
        contact.setEmail(this.email);

        return contact;
    }
}
