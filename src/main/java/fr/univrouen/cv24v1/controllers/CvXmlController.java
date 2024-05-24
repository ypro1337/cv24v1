package fr.univrouen.cv24v1.controllers;

import fr.univrouen.cv24v1.model.*;
import fr.univrouen.cv24v1.repository.CvRepository;
import fr.univrouen.cv24v1.services.CvService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Controller
public class CvXmlController {

    @Autowired
    private CvRepository cvRepository;

    @Autowired
    private CvService cvService;

    private static final String XML_DEFAULT_SCHEMA = "xml/cv24.xsd";

    @GetMapping(value = "/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> showCvResumeXml() throws JAXBException, IllegalAnnotationException {
        List<Cv> cvs = cvRepository.findAll();
        List<CvResume> cvResumes = cvService.transformCvsToResumes(cvs);
        return marshalAndRespond(cvResumes);
    }


    @GetMapping(value = "/cv24/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> showCvXml(@RequestParam("id") Long id) {
        Optional<Cv> cvOpt = cvRepository.findById(id);

        if (cvOpt.isPresent()) {
            Cv cv = cvOpt.get();
            try {
                JAXBContext context = JAXBContext.newInstance(Cv.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                URL schemaURL = getClass().getClassLoader().getResource(XML_DEFAULT_SCHEMA);
                if (schemaURL == null) {
                    throw new SAXException("Schema file not found.");
                }
                Schema schema = schemaFactory.newSchema(schemaURL);
                marshaller.setSchema(schema);

                StringWriter sw = new StringWriter();
                marshaller.marshal(cv, sw);
                return new ResponseEntity<>(sw.toString(), HttpStatus.OK);
            } catch (JAXBException e) {
                e.printStackTrace();
                return new ResponseEntity<>("<error>Error generating XML</error>", HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (SAXException e) {
                return new ResponseEntity<>("<error>Error Finding XSD</error>", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            String errorXml = String.format("<error><id>%d</id><status>ERROR</status></error>", id);
            return new ResponseEntity<>(errorXml, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/cv24/search", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> searchCvs(@RequestParam(value = "nom", required = false) String nom,
                                            @RequestParam(value = "prenom", required = false) String prenom,
                                            @RequestParam(value = "objectif", required = false) String objectif) {
        try {
            // Filter Cvs and transform them to resumes
            List<Cv> filteredCvs = cvRepository.findByNomOrPrenomOrObjectifValueContaining(nom,prenom,objectif);
            List<CvResume> cvResumes = cvService.transformCvsToResumes(filteredCvs);
            return marshalAndRespond(cvResumes);
        } catch (JAXBException e) {
            return ResponseEntity.internalServerError().body("<response><status>ERROR</status><detail>No Such Field</detail></response>");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("<response><status>ERROR</status><detail>Unknown error occurred.</detail></response>");
        }
    }

    private ResponseEntity<String> marshalAndRespond(List<CvResume> cvResumes) throws JAXBException {
        CvResumeWrapper wrapper = new CvResumeWrapper();
        wrapper.setCvResumes(cvResumes);

        JAXBContext context = JAXBContext.newInstance(CvResumeWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(wrapper, sw);
        return ResponseEntity.ok(sw.toString());
    }
}
