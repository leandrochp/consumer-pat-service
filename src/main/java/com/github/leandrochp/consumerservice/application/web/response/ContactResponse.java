package com.github.leandrochp.consumerservice.application.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactResponse {
    @JsonProperty("mobile_phone_number")
    private String mobilePhoneNumber;
    @JsonProperty("residence_phone_number")
    private String residencePhoneNumber;
    @JsonProperty("work_phone_number")
    private String workPhoneNumber;

    private String email;
}
