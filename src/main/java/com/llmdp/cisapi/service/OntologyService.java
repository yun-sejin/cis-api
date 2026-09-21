package com.llmdp.cisapi.service;

import com.llmdp.cisapi.dto.OntologyClassRequest;
import com.llmdp.cisapi.dto.OntologyExportResponse;
import com.llmdp.cisapi.dto.OntologyIndividualRequest;
import com.llmdp.cisapi.dto.OntologyPropertyRequest;
import com.llmdp.cisapi.dto.OntologyRelationRequest;

public interface OntologyService {
    void addClass(OntologyClassRequest request);

    void addProperty(OntologyPropertyRequest request);

    void addIndividual(OntologyIndividualRequest request);

    void addRelation(OntologyRelationRequest request);

    OntologyExportResponse export(String format);
}
