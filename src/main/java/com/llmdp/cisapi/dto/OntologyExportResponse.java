package com.llmdp.cisapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OntologyExportResponse {
    private String format;
    private String content;
}
