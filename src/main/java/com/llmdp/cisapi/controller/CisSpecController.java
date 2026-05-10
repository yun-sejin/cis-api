package com.llmdp.cisapi.controller;

import com.llmdp.cisapi.dto.CisSpecRequest;
import com.llmdp.cisapi.dto.CisSpecResponse;
import com.llmdp.cisapi.service.CisSpecService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "CIS Spec", description = "CIS 원천 시스템 스펙 데이터 제공 API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CisSpecController {

    private final CisSpecService cisSpecService;

    @Operation(summary = "CIS 스펙 목록 조회", description = "조건에 맞는 ifr_cis_spec 데이터를 조회합니다. 조건을 비워서 보내면 전체 조회됩니다.")
    @PostMapping("/specs")
    public ResponseEntity<List<CisSpecResponse>> getSpecs(@RequestBody CisSpecRequest request) {
        return ResponseEntity.ok(cisSpecService.getCisSpecList(request));
    }
}
