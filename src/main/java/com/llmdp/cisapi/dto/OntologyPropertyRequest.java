package com.llmdp.cisapi.dto;

import lombok.Data;

@Data
public class OntologyPropertyRequest {
    private String localName;
    private String label;
    private String domainClassLocalName;
    private String rangeClassLocalName;
    private boolean datatypeProperty;
}
