package fr.univrouen.cv24v1.controllers;

import fr.univrouen.cv24v1.model.Cv;
import fr.univrouen.cv24v1.repository.CvRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.net.URL;
import java.util.Optional;

@Controller
public class CvRestController {

    @Autowired
    CvRepository cvRepository;
    private static final String XML_DEFAULT_SCHEMA = "xml/cv24.xsd";

    @PostMapping("/cv24/insert")
    public ResponseEntity<String> insertCv(@RequestBody String xmlData) {
        try {
            JAXBContext context = JAXBContext.newInstance(Cv.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL schemaURL = getClass().getClassLoader().getResource(XML_DEFAULT_SCHEMA);
            if (schemaURL == null) {
                throw new SAXException("Schema file not found.");
            }
            Schema schema = schemaFactory.newSchema(schemaURL);
            unmarshaller.setSchema(schema);

            Cv cv = (Cv) unmarshaller.unmarshal(new StringReader(xmlData));

            Optional<Cv> existingCv = cvRepository.findByIdentite_GenreAndIdentite_NomAndIdentite_PrenomAndIdentite_Tel(
                    cv.getIdentite().getGenre(),
                    cv.getIdentite().getNom(),
                    cv.getIdentite().getPrenom(),
                    cv.getIdentite().getTel()
            );
            if (existingCv.isPresent()) {
                // Duplicate CV found
                return ResponseEntity.status(HttpStatus.CONFLICT).body("<response><status>ERROR</status><detail>DUPLICATED</detail></response>");
            }

            // Save the new CV to the database
            Cv savedCv = cvRepository.save(cv);

            // Return success response + ID
            return ResponseEntity.ok("<response><id>" + savedCv.getId() + "</id><status>INSERTED</status></response>");
        } catch (JAXBException | SAXException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("<response><status>ERROR</status><detail>INVALID</detail></response>");
        } catch (Exception e) {
            // Catch any other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("<response><status>ERROR</status><detail>UNKNOWN_ERROR</detail></response>");
        }
    }

    @DeleteMapping("/cv24/delete")
    public ResponseEntity<String> deldeteCv(@RequestParam Long id) {
        Optional<Cv> cvOptional = cvRepository.findById(id);

        if (!cvOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("<response><status>ERROR</status><detail>NOT_FOUND</detail></response>");
        }

        cvRepository.deleteById(id);
        return ResponseEntity.ok("<response><status>DELETED</status></response>");
    }
}
