package com.llmdp.cisapi.dto;

import lombok.Data;

@Data
public class OntologyRelationRequest {
    private String subjectLocalName;
    private String propertyLocalName;
    private String objectLocalName;
}
