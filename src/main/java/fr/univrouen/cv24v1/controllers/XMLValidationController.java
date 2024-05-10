package fr.univrouen.cv24v1.controllers;

import fr.univrouen.cv24v1.model.Cv;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.UnmarshalException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.net.URL;

@RestController
public class XMLValidationController {

    @PostMapping("/validate-xml")
    public ResponseEntity<String> validateXml(@RequestBody String xmlData) {
        try {
            // Create JAXB context for the root element class
            JAXBContext context = JAXBContext.newInstance(Cv.class);

            // Create Unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Set schema for validation

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL schemaURL = getClass().getClassLoader().getResource("xml/cv24.xsd");
            if (schemaURL == null) {
                throw new SAXException("Schema file not found.");
            }
            Schema schema = schemaFactory.newSchema(schemaURL);
            unmarshaller.setSchema(schema);

            // Unmarshal XML document (this also performs validation)
            Cv cv = (Cv) unmarshaller.unmarshal(new StringReader(xmlData));
            return ResponseEntity.ok("XML document is valid.");
        }catch (UnmarshalException e) {
            // Specific catch for unmarshalling errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unmarshalling error: " + e.getMessage());
        } catch (JAXBException | SAXException e) {
            // Handle JAXBException or SAXException (validation error)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }
}