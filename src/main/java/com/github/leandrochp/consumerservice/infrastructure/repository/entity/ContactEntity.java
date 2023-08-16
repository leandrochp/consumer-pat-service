package com.github.leandrochp.consumerservice.infrastructure.repository.entity;

import com.github.leandrochp.consumerservice.domain.consumer.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "contact")
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
        val contact = new Contact();
        contact.setMobilePhoneNumber(this.mobilePhoneNumber);
        contact.setResidencePhoneNumber(this.residencePhoneNumber);
        contact.setWorkPhoneNumber(this.workPhoneNumber);
        contact.setEmail(this.email);

        return contact;
    }
}
