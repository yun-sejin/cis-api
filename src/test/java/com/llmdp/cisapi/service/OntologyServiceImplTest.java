package com.llmdp.cisapi.service;

import com.llmdp.cisapi.dto.OntologyClassRequest;
import com.llmdp.cisapi.dto.OntologyExportResponse;
import com.llmdp.cisapi.dto.OntologyIndividualRequest;
import com.llmdp.cisapi.dto.OntologyPropertyRequest;
import com.llmdp.cisapi.dto.OntologyRelationRequest;
import com.llmdp.cisapi.service.impl.OntologyServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OntologyServiceImplTest {

    private final OntologyService ontologyService = new OntologyServiceImpl();

    @Test
    void addClass_addProperty_addIndividual_addRelation_export() {
        OntologyClassRequest specClass = new OntologyClassRequest();
        specClass.setLocalName("CisSpec");
        specClass.setLabel("CIS 스펙");
        ontologyService.addClass(specClass);

        OntologyClassRequest typeClass = new OntologyClassRequest();
        typeClass.setLocalName("SpecType");
        typeClass.setLabel("스펙 유형");
        ontologyService.addClass(typeClass);

        OntologyPropertyRequest hasType = new OntologyPropertyRequest();
        hasType.setLocalName("hasSpecType");
        hasType.setLabel("스펙 유형을 가진다");
        hasType.setDomainClassLocalName("CisSpec");
        hasType.setRangeClassLocalName("SpecType");
        hasType.setDatatypeProperty(false);
        ontologyService.addProperty(hasType);

        OntologyIndividualRequest spec = new OntologyIndividualRequest();
        spec.setLocalName("spec1");
        spec.setClassLocalName("CisSpec");
        spec.setLabel("스펙1");
        ontologyService.addIndividual(spec);

        OntologyIndividualRequest type = new OntologyIndividualRequest();
        type.setLocalName("typeA");
        type.setClassLocalName("SpecType");
        type.setLabel("유형A");
        ontologyService.addIndividual(type);

        OntologyRelationRequest relation = new OntologyRelationRequest();
        relation.setSubjectLocalName("spec1");
        relation.setPropertyLocalName("hasSpecType");
        relation.setObjectLocalName("typeA");
        ontologyService.addRelation(relation);

        OntologyExportResponse export = ontologyService.export("TURTLE");
        assertThat(export.getContent()).contains("spec1");
        assertThat(export.getContent()).contains("hasSpecType");
    }

    @Test
    void addIndividual_withUnknownClass_throws() {
        OntologyIndividualRequest request = new OntologyIndividualRequest();
        request.setLocalName("ghost");
        request.setClassLocalName("NotExisting");

        assertThatThrownBy(() -> ontologyService.addIndividual(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
