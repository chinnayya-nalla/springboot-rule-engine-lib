package com.github.chinnayyanalla.sampleapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payload {

    @JsonProperty("mandatoryProperty")
    private String mandatoryProperty;

    @JsonProperty("optionalProperty")
    private String optionalProperty;

    @JsonProperty("conditionalProperty")
    private String conditionalProperty;

    @JsonProperty("businessValidationProperty")
    private String businessValidationProperty;

}
