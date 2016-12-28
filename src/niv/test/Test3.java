package niv.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.example.product.Product;
import org.example.productpurchaserequest.PurchaseRequest;
import org.xml.sax.InputSource;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

public class Test3 {
	public static void main(String[] args) throws Exception {
        // Create the JAXBContext
        JAXBContext jc = JAXBContext.newInstance(PurchaseRequest.class);
 
        // Create the XMLFilter
        XMLFilter filter = new NamespaceFilter();
 
        // Set the parent XMLReader on the XMLFilter
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();
        filter.setParent(xr);
 
        // Set UnmarshallerHandler as ContentHandler on XMLFilter
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        UnmarshallerHandler unmarshallerHandler = unmarshaller
                .getUnmarshallerHandler();
        filter.setContentHandler(unmarshallerHandler);
 
        // Parse the XML
        InputSource xml = new InputSource("test3.xml");
        filter.parse(xml);
        PurchaseRequest purchaseRequest = (PurchaseRequest) unmarshallerHandler.getResult();        
		
		for (Product product : purchaseRequest.getProduct()) {
			System.out.println(product.getId() + " " + product.getName());
		}
		
		 // Marshal the Customer object back to XML
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(purchaseRequest, System.out);
	}

}
