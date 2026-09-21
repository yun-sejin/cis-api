package com.llmdp.cisapi.dto;

import lombok.Data;

@Data
public class OntologyIndividualRequest {
    private String localName;
    private String classLocalName;
    private String label;
}
