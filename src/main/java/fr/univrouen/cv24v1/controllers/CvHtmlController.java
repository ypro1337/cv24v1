package fr.univrouen.cv24v1.controllers;

import fr.univrouen.cv24v1.model.Cv;
import fr.univrouen.cv24v1.model.CvResume;
import fr.univrouen.cv24v1.repository.CvRepository;
import fr.univrouen.cv24v1.services.CvService;
import fr.univrouen.cv24v1.util.MarshallingHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import java.io.InputStream;
import java.io.IOException;

@Controller
public class CvHtmlController {

    @Autowired
    private CvRepository cvRepository;

    @Autowired
    private CvService cvService;

    private MarshallingHelper helper;

    private static final String XML_DEFAULT_SCHEMA = "xml/cv24.xsd";

    private final String XSLT_FILE = "/xslt/cvDetails.xslt";

    @SneakyThrows
    @GetMapping("/cv24/resume")
    public String showCvResume(Model model) {
        List<Cv> cvs = cvRepository.findAll();
        List<CvResume> cvResumes = cvService.transformCvsToResumes(cvs);
        helper = MarshallingHelper.getInstance();

        // No CVs
        if (cvs.isEmpty()) {
            model.addAttribute("status", "ERROR");
            model.addAttribute("message", "No CVs found");
            return "error";
        }

        // Transformation XSLT
        try (InputStream xsltStream = getClass().getResourceAsStream("/xslt/cv.xslt")) {
            if (xsltStream == null) {
                throw new RuntimeException("XSLT file not found");
            }
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(xsltStream);
            Transformer transformer = factory.newTransformer(xslt);

            StringWriter sw = helper.marshalList(cvResumes);
            Source text = new StreamSource(new StringReader(sw.toString()));
            StringWriter resultWriter = new StringWriter();
            transformer.transform(text, new StreamResult(resultWriter));

            model.addAttribute("htmlContent", resultWriter.toString());
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
            model.addAttribute("status", "ERROR");
            model.addAttribute("message", "Error processing CVs");
            return "error";
        }

        model.addAttribute("currentPage", "resume");
        return "resume";
    }

    @SneakyThrows
    @GetMapping(value = "/cv24/html")
    public String showCvDetails(@RequestParam Long id, Model model) {
        // Fetch the CV by id from the repository
        Optional<Cv> cvOptional = cvRepository.findById(id);
        helper = MarshallingHelper.getInstance();
        if (!cvOptional.isPresent()) {
            // Handle the case where the CV is not found
            model.addAttribute("status", "ERROR");
            model.addAttribute("message", "CV not found");
            return "error";
        }

        // Convert the found CV to XML format
        Cv cv = cvOptional.get();

        // Perform XSLT transformation
        try (InputStream xsltStream = getClass().getResourceAsStream(XSLT_FILE)) {
            if (xsltStream == null) {
                throw new RuntimeException("XSLT file not found");
            }
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(xsltStream);
            Transformer transformer = factory.newTransformer(xslt);

            StringWriter sw = helper.marshalCv(cv, XML_DEFAULT_SCHEMA);
            Source text = new StreamSource(new StringReader(sw.toString()));
            StringWriter resultWriter = new StringWriter();
            transformer.transform(text, new StreamResult(resultWriter));

            // Add the transformed HTML to the model
            model.addAttribute("htmlContent", resultWriter.toString());
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
            model.addAttribute("status", "ERROR");
            model.addAttribute("message", "Error processing CV");
            return "error";
        }

        return "cvDetails";
    }
}
