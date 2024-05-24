package fr.univrouen.cv24v1.util;

import fr.univrouen.cv24v1.model.Cv;
import fr.univrouen.cv24v1.model.CvResume;
import fr.univrouen.cv24v1.model.CvResumeWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;

public class MarshallingHelper {
    // Step 2: Create a private static instance of the same class
    private static MarshallingHelper instance;

    // Step 1: Make the constructor private
    private MarshallingHelper() {
        // Private constructor to prevent instantiation
    }

    // Step 3: Provide a public static method to get the instance
    public static synchronized MarshallingHelper getInstance() {
        if (instance == null) {
            instance = new MarshallingHelper();
        }
        return instance;
    }

    public StringWriter marshalList(List<CvResume> cvResumes) throws JAXBException {
        CvResumeWrapper wrapper = new CvResumeWrapper();
        wrapper.setCvResumes(cvResumes);

        JAXBContext context = JAXBContext.newInstance(CvResumeWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(wrapper, sw);
        return sw ;
    }

    public StringWriter marshalCv(Cv cv , String default_schema) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(Cv.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        URL schemaURL = getClass().getClassLoader().getResource(default_schema);
        if (schemaURL == null) {
            throw new SAXException("Schema file not found.");
        }
        Schema schema = schemaFactory.newSchema(schemaURL);
        marshaller.setSchema(schema);

        StringWriter sw = new StringWriter();
        marshaller.marshal(cv, sw);
        return sw;
    }

}
