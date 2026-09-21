package com.llmdp.cisapi.service.impl;

import com.llmdp.cisapi.dto.OntologyClassRequest;
import com.llmdp.cisapi.dto.OntologyExportResponse;
import com.llmdp.cisapi.dto.OntologyIndividualRequest;
import com.llmdp.cisapi.dto.OntologyPropertyRequest;
import com.llmdp.cisapi.dto.OntologyRelationRequest;
import com.llmdp.cisapi.service.OntologyService;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
public class OntologyServiceImpl implements OntologyService {

    private static final String NS = "http://cis-api.llmdp.com/ontology#";

    private final OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

    @Override
    public synchronized void addClass(OntologyClassRequest request) {
        OntClass ontClass = model.createClass(NS + request.getLocalName());
        if (request.getLabel() != null) {
            ontClass.addLabel(request.getLabel(), null);
        }
        if (request.getSuperClassLocalName() != null) {
            ontClass.addSuperClass(getRequiredClass(request.getSuperClassLocalName()));
        }
    }

    @Override
    public synchronized void addProperty(OntologyPropertyRequest request) {
        OntProperty property = request.isDatatypeProperty()
                ? model.createDatatypeProperty(NS + request.getLocalName())
                : model.createObjectProperty(NS + request.getLocalName());

        if (request.getLabel() != null) {
            property.addLabel(request.getLabel(), null);
        }
        if (request.getDomainClassLocalName() != null) {
            property.addDomain(getRequiredClass(request.getDomainClassLocalName()));
        }
        if (request.getRangeClassLocalName() != null && !request.isDatatypeProperty()) {
            property.addRange(getRequiredClass(request.getRangeClassLocalName()));
        }
    }

    @Override
    public synchronized void addIndividual(OntologyIndividualRequest request) {
        OntClass ontClass = getRequiredClass(request.getClassLocalName());
        Individual individual = model.createIndividual(NS + request.getLocalName(), ontClass);
        if (request.getLabel() != null) {
            individual.addLabel(request.getLabel(), null);
        }
    }

    @Override
    public synchronized void addRelation(OntologyRelationRequest request) {
        Resource subject = model.getResource(NS + request.getSubjectLocalName());
        Property property = model.getProperty(NS + request.getPropertyLocalName());
        if (property == null) {
            throw new IllegalArgumentException("존재하지 않는 property 입니다: " + request.getPropertyLocalName());
        }
        Resource object = model.getResource(NS + request.getObjectLocalName());
        model.add(subject, property, object);
    }

    @Override
    public synchronized OntologyExportResponse export(String format) {
        Lang lang = "RDF/XML".equalsIgnoreCase(format) ? Lang.RDFXML : Lang.TURTLE;
        StringWriter writer = new StringWriter();
        model.write(writer, lang.getName());
        return new OntologyExportResponse(lang.getName(), writer.toString());
    }

    private OntClass getRequiredClass(String localName) {
        OntClass ontClass = model.getOntClass(NS + localName);
        if (ontClass == null) {
            throw new IllegalArgumentException("존재하지 않는 class 입니다: " + localName);
        }
        return ontClass;
    }
}
