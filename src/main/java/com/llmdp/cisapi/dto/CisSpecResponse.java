package com.llmdp.cisapi.dto;

import lombok.Data;

@Data
public class CisSpecResponse {
    private Long specId;
    private String specCode;
    private String specName;
    private String specValue;
    private String specType;
    private String useYn;
    private String createdAt;
    private String updatedAt;
}
