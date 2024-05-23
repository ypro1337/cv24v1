package fr.univrouen.cv24v1.controllers;

import fr.univrouen.cv24v1.model.Cv;
import fr.univrouen.cv24v1.repository.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Comparator;

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

    @GetMapping("/cv24/resume")
    public String showCvResume(Model model) {
        List<Cv> cvs = cvRepository.findAll();

        // le cas ou il y a aucun cv
        if (cvs.isEmpty()) {
            model.addAttribute("status", "ERROR");
            model.addAttribute("message", "No CVs found");
            return "error";
        }

        // convertir les cvs en xml
        StringBuilder xml = new StringBuilder();
        xml.append("<cvs>");
        for (Cv cv : cvs) {
            xml.append("<cv>");
            xml.append("<id>").append(cv.getId()).append("</id>");
            xml.append("<identite>");
            xml.append("<genre>").append(cv.getIdentite().getGenre()).append("</genre>");
            xml.append("<nom>").append(cv.getIdentite().getNom()).append("</nom>");
            xml.append("<prenom>").append(cv.getIdentite().getPrenom()).append("</prenom>");
            xml.append("</identite>");
            xml.append("<objectif statut=\"").append(cv.getObjectif().getStatut()).append("\">");
            xml.append(cv.getObjectif().getValue()).append("</objectif>");


            cv.getCompetences().getDiplomes().stream()
                    .max(Comparator.comparing(diplome -> diplome.getDate()))
                    .ifPresent(diplome -> {
                        xml.append("<diplome>");
                        xml.append("<titre>").append(diplome.getTitres().get(0)).append("</titre>");
                        xml.append("</diplome>");
                    });

            xml.append("</cv>");
        }
        xml.append("</cvs>");

        //transformation xslt
        try (InputStream xsltStream = getClass().getResourceAsStream("/xslt/cv.xslt")) {
            if (xsltStream == null) {
                throw new RuntimeException("XSLT file not found");
            }
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(xsltStream);
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new StringReader(xml.toString()));
            StringWriter writer = new StringWriter();
            transformer.transform(text, new StreamResult(writer));

            // Add the transformed HTML to the model
            model.addAttribute("htmlContent", writer.toString());
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
            model.addAttribute("status", "ERROR");
            model.addAttribute("message", "Error processing CVs");
            return "error";
        }

        model.addAttribute("currentPage", "resume");
        return "resume";
    }

    @GetMapping(value = "/cv24/html")
    public String showCvDetails(@RequestParam Long id, Model model) {
        // Fetch the CV by id from the repository
        Optional<Cv> cvOptional = cvRepository.findById(id);

        if (!cvOptional.isPresent()) {
            // Handle the case where the CV is not found
            model.addAttribute("status", "ERROR");
            model.addAttribute("message", "CV not found");
            return "error";
        }

        // Convert the found CV to XML format
        Cv cv = cvOptional.get();
        StringBuilder xml = new StringBuilder();
        xml.append("<cv>");
        xml.append("<id>").append(cv.getId()).append("</id>");
        xml.append("<identite>");
        xml.append("<genre>").append(cv.getIdentite().getGenre()).append("</genre>");
        xml.append("<nom>").append(cv.getIdentite().getNom()).append("</nom>");
        xml.append("<prenom>").append(cv.getIdentite().getPrenom()).append("</prenom>");
        xml.append("<tel>").append(cv.getIdentite().getTel()).append("</tel>");
        xml.append("<mel>").append(cv.getIdentite().getMel()).append("</mel>");
        xml.append("</identite>");
        xml.append("<objectif>");
        xml.append("<value>").append(cv.getObjectif().getValue()).append("</value>");
        xml.append("<statut>").append(cv.getObjectif().getStatut()).append("</statut>");
        xml.append("</objectif>");
        xml.append("<prof>");
        xml.append("<details>");
        cv.getProf().getDetails().forEach(detail -> {
            xml.append("<detail>");
            xml.append("<datedeb>").append(detail.getDatedeb()).append("</datedeb>");
            xml.append("<datefin>").append(detail.getDatefin()).append("</datefin>");
            xml.append("<titre>").append(detail.getTitre()).append("</titre>");
            xml.append("</detail>");
        });
        xml.append("</details>");
        xml.append("</prof>");
        xml.append("<competences>");
        xml.append("<diplomes>");
        cv.getCompetences().getDiplomes().forEach(diplome -> {
            xml.append("<diplome>");
            xml.append("<date>").append(diplome.getDate()).append("</date>");
            xml.append("<institut>").append(diplome.getInstitut()).append("</institut>");
            xml.append("<titres>");
            diplome.getTitres().forEach(titre -> {
                xml.append("<titre>").append(titre).append("</titre>");
            });
            xml.append("</titres>");
            xml.append("<niveau>").append(diplome.getNiveau()).append("</niveau>");
            xml.append("</diplome>");
        });
        xml.append("</diplomes>");
        xml.append("<certifs>");
        cv.getCompetences().getCertifs().forEach(certif -> {
            xml.append("<certif>");
            xml.append("<datedeb>").append(certif.getDatedeb()).append("</datedeb>");
            xml.append("<datefin>").append(certif.getDatefin()).append("</datefin>");
            xml.append("<titre>").append(certif.getTitre()).append("</titre>");
            xml.append("</certif>");
        });
        xml.append("</certifs>");
        xml.append("</competences>");
        xml.append("<divers>");
        xml.append("<lv>");
        cv.getDivers().getLv().forEach(lv -> {
            xml.append("<lv>");
            xml.append("<lang>").append(lv.getLang()).append("</lang>");
            xml.append("<cert>").append(lv.getCert()).append("</cert>");
            xml.append("<nivs>").append(lv.getNivs()).append("</nivs>");
            xml.append("<nivi>").append(lv.getNivi()).append("</nivi>");
            xml.append("</lv>");
        });
        xml.append("</lv>");
        xml.append("<autre>");
        cv.getDivers().getAutre().forEach(autre -> {
            xml.append("<autre>");
            xml.append("<titre>").append(autre.getTitre()).append("</titre>");
            xml.append("<comment>").append(autre.getComment()).append("</comment>");
            xml.append("</autre>");
        });
        xml.append("</autre>");
        xml.append("</divers>");
        xml.append("</cv>");

        // Perform XSLT transformation
        try (InputStream xsltStream = getClass().getResourceAsStream("/xslt/cvDetails.xslt")) {
            if (xsltStream == null) {
                throw new RuntimeException("XSLT file not found");
            }
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(xsltStream);
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new StringReader(xml.toString()));
            StringWriter writer = new StringWriter();
            transformer.transform(text, new StreamResult(writer));

            // Add the transformed HTML to the model
            model.addAttribute("htmlContent", writer.toString());
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
            model.addAttribute("status", "ERROR");
            model.addAttribute("message", "Error processing CV");
            return "error";
        }


        return "cvDetails";
    }
}
