package utils.handlers;

import java.util.List;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ParametrsHandler extends DefaultHandler {
	
	public ParametrsHandler(List<PropertyParameter> parametrs) {
		this.parametrs = parametrs;
	}
	
	private static enum ParametrsXMLEnum {
		PARAMETERS, PARAMETER, ID, NAME;
	}
	
	List<PropertyParameter> parametrs;
	private ParametrsXMLEnum currentEnum = null;
	private int currentId;
	private String currentName;
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
	   currentEnum = ParametrsXMLEnum.valueOf(qName.toUpperCase());
	}
	
	public void characters(char[] ch, int start, int length) {
		if(currentEnum == ParametrsXMLEnum.ID) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentId = Integer.parseInt(s);
			}
		}
		
		if(currentEnum == ParametrsXMLEnum.NAME) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentName = s;
			}
		}
	}
	
	public void endElement(String uri, 
			String localName, String qName) { 
			currentEnum = ParametrsXMLEnum.valueOf(qName.toUpperCase());
			if(currentEnum == ParametrsXMLEnum.PARAMETER) {
				parametrs.add(new PropertyParameter(currentId, currentName));
			}
	} 
}



