package com.llmdp.cisapi.controller;

import com.llmdp.cisapi.dto.OntologyClassRequest;
import com.llmdp.cisapi.dto.OntologyExportResponse;
import com.llmdp.cisapi.dto.OntologyIndividualRequest;
import com.llmdp.cisapi.dto.OntologyPropertyRequest;
import com.llmdp.cisapi.dto.OntologyRelationRequest;
import com.llmdp.cisapi.service.OntologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ontology", description = "온톨로지(RDF/OWL) 클래스, 프로퍼티, 개체 관리 API")
@RestController
@RequestMapping("/api/v1/ontology")
@RequiredArgsConstructor
public class OntologyController {

    private final OntologyService ontologyService;

    @Operation(summary = "클래스 정의", description = "OWL 클래스를 추가합니다. superClassLocalName을 지정하면 상위 클래스로 연결됩니다.")
    @PostMapping("/classes")
    public ResponseEntity<Void> addClass(@RequestBody OntologyClassRequest request) {
        ontologyService.addClass(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "프로퍼티 정의", description = "OWL Object/Datatype 프로퍼티를 추가합니다.")
    @PostMapping("/properties")
    public ResponseEntity<Void> addProperty(@RequestBody OntologyPropertyRequest request) {
        ontologyService.addProperty(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "개체 정의", description = "특정 클래스의 인스턴스(Individual)를 추가합니다.")
    @PostMapping("/individuals")
    public ResponseEntity<Void> addIndividual(@RequestBody OntologyIndividualRequest request) {
        ontologyService.addIndividual(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "관계 정의", description = "개체 간의 관계(트리플)를 추가합니다.")
    @PostMapping("/relations")
    public ResponseEntity<Void> addRelation(@RequestBody OntologyRelationRequest request) {
        ontologyService.addRelation(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "온톨로지 내보내기", description = "현재까지 정의된 온톨로지를 TURTLE 또는 RDF/XML 형식으로 내보냅니다.")
    @GetMapping("/export")
    public ResponseEntity<OntologyExportResponse> export(
            @RequestParam(defaultValue = "TURTLE") String format) {
        return ResponseEntity.ok(ontologyService.export(format));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
