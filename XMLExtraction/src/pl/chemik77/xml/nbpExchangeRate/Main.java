package pl.chemik77.xml.nbpExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<NbpExchange> nbp = getNbp("http://rss.nbp.pl/kursy/xml2/2017/a/17a059.xml");
		
		System.out.println("NBP - Tabela A kursów średnich walut obcych");
		System.out.println("-------------------------------------------");
		
		System.out.println("Nazwa waluty\t    Przelicznik\t    Kod waluty\t    Kurs średni");
		for(NbpExchange n : nbp) {
			System.out.printf("%-10s\t\t%s\t\t%3s\t\t%.4f\n", n.getNazwa(), n.getPrzelicznik(), n.getKod(), n.getKurs());
		}
		
	}

	public static ArrayList<NbpExchange> getNbp(String url) {
		ArrayList<NbpExchange> nbp = new ArrayList<NbpExchange>();
		
		try {
			URL nbpUrl = new URL(url);
			URLConnection conn = nbpUrl.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			int responseCode = httpConn.getResponseCode();
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				InputStream in = httpConn.getInputStream();
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(in);
				Element docElement = doc.getDocumentElement();
				NodeList nList = docElement.getElementsByTagName("pozycja");
				
				if(nList != null && nList.getLength() > 0) {
					for(int i=0; i<nList.getLength(); i++) {
						Element position = (Element) nList.item(i);
						Element name = (Element) position.getElementsByTagName("nazwa_waluty").item(0);
						Element convers = (Element) position.getElementsByTagName("przelicznik").item(0);
						Element code = (Element) position.getElementsByTagName("kod_waluty").item(0);
						Element rate = (Element) position.getElementsByTagName("kurs_sredni").item(0);
						
						String sName = name.getFirstChild().getNodeValue();
						if(sName.length() > 15)
							sName = sName.substring(0, 14) + ".";
						
						String sConvers = convers.getFirstChild().getNodeValue();
						int iConvers = Integer.parseInt(sConvers);
						
						String sCode = code.getFirstChild().getNodeValue();
						
						String sRate = rate.getFirstChild().getNodeValue().replaceAll(",", ".");
						double dRate = Double.parseDouble(sRate);
						
						
						NbpExchange n = new NbpExchange(sName, iConvers, sCode, dRate);
						
						nbp.add(n);
						
					}
				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return nbp;
	}

}
