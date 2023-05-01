package com.github.leandrochp.consumerpatservice.application.web.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressResponse {
    private String street;
    private Integer number;
    private String city;
    private String country;
    @JsonProperty("portal_code")
    private String portalCode;
}
