package pl.chemik77.xml.plantCatalog;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class PlantCatalog {

	public static void main(String[] args) {
		try {
			
			File file = new File("src/pl/chemik77/xml/plantCatalog.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
			//System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			//System.out.println("------------------------");
			System.out.println("Plants catalog");
			
			NodeList nList = doc.getElementsByTagName("PLANT");
			for(int i=0; i<nList.getLength(); i++) {
				Node nNode = nList.item(i);
				
				//System.out.println("\nCurrent element : " + nNode.getNodeName());
				System.out.println("");
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					System.out.println("Name : " + eElement.getElementsByTagName("COMMON").item(0).getTextContent());
					System.out.println("Latin name : " + eElement.getElementsByTagName("BOTANICAL").item(0).getTextContent());
					System.out.println("Zone : " + eElement.getElementsByTagName("ZONE").item(0).getTextContent());
					System.out.println("Light : " + eElement.getElementsByTagName("LIGHT").item(0).getTextContent());
					System.out.println("Price : " + eElement.getElementsByTagName("PRICE").item(0).getTextContent());
					System.out.println("Availability : " + eElement.getElementsByTagName("AVAILABILITY").item(0).getTextContent());
					
				}
				
				
			}
			
		} catch (IOException e) {
			e.getStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
