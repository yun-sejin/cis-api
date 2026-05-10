package com.llmdp.cisapi.service.impl;

import com.llmdp.cisapi.dto.CisSpecRequest;
import com.llmdp.cisapi.dto.CisSpecResponse;
import com.llmdp.cisapi.mapper.CisSpecMapper;
import com.llmdp.cisapi.service.CisSpecService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CisSpecServiceImpl implements CisSpecService {

    private final CisSpecMapper cisSpecMapper;

    @Override
    public List<CisSpecResponse> getCisSpecList(CisSpecRequest request) {
        return cisSpecMapper.selectCisSpecList(request);
    }
}
