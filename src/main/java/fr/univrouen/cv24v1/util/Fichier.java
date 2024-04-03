package fr.univrouen.cv24v1.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
public class Fichier {

public static String loadFileXML() {
	Resource resource = new DefaultResourceLoader().getResource("xml/smallCV.xml");
	try {
		String result = resource.getContentAsString(Charset.forName("UTF-8"));
		return result ;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "";
	
}
	
}
