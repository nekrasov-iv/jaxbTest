package niv.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.example.product.Product;
import org.example.productpurchaserequest.PurchaseRequest;

public class Test {
	public static void main(String[] args) throws Exception {
		File file = new File("test.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseRequest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		PurchaseRequest purchaseRequest = (PurchaseRequest) jaxbUnmarshaller.unmarshal(file);
		
		for (Product product : purchaseRequest.getProduct()) {
			System.out.println(product.getId() + " " + product.getName());
		}
	}

}
