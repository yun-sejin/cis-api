package com.llmdp.cisapi.dto;

import lombok.Data;

@Data
public class CisSpecRequest {
    private String specCode;
    private String specName;
    private String specType;
    private String useYn;
}
