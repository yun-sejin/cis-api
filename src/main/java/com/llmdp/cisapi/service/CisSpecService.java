package com.llmdp.cisapi.service;

import com.llmdp.cisapi.dto.CisSpecRequest;
import com.llmdp.cisapi.dto.CisSpecResponse;

import java.util.List;

public interface CisSpecService {
    List<CisSpecResponse> getCisSpecList(CisSpecRequest request);
}
