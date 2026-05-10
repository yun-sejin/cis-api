package com.llmdp.cisapi.mapper;

import com.llmdp.cisapi.dto.CisSpecRequest;
import com.llmdp.cisapi.dto.CisSpecResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CisSpecMapper {
    List<CisSpecResponse> selectCisSpecList(CisSpecRequest request);
}
