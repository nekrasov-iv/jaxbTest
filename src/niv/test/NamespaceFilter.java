package niv.test;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceFilter extends XMLFilterImpl {
 
    private static final String NAMESPACE1 = "http://www.example.org/ProductPurchaseRequest";
    private static final String NAMESPACE2 = "http://www.example.org/Product";
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	if("purchase-request".equals(qName)) {
    		super.endElement(NAMESPACE1, localName, qName);
    	} else {
    		super.endElement(NAMESPACE2, localName, qName);
    	}
    }
 
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    	if("purchase-request".equals(qName)) {
            super.startElement(NAMESPACE1, localName, qName, atts);
        } else {
            super.startElement(NAMESPACE2, localName, qName, atts);
        }    	
    }
 
}