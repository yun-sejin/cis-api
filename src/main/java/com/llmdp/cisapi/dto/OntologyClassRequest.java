package com.llmdp.cisapi.dto;

import lombok.Data;

@Data
public class OntologyClassRequest {
    private String localName;
    private String label;
    private String superClassLocalName;
}
