package downloader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class DownloaderHttp {

	public void getData(String site, String resource, String rule, String links[]) throws Exception {

		for(int i=0; i<links.length; i++) {
			System.out.println(i+1+") Link: "+ links[i]);
			applyXPath(links[i],rule);
		}
	}

	public static void applyXPath(String urlToRead, String xpath) throws Exception {
		try{
			StringBuilder result = new StringBuilder();
			URL url = new URL(urlToRead);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
			Document d = cleanHtml(result.toString());
			//System.out.println(getStringFromDoc(d));			//HTML PULITO

			// effettua query
			//String query = "//div[@id='descItemNumber']";				//esempio di query che funzion su chrome
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList pageNode = (NodeList)xPath.evaluate(xpath,d,XPathConstants.NODESET);

			//Stampa i valori
			for (int i = 0; i < pageNode.getLength(); i++) {
				System.out.println("Valore estratto dall'Xpath '" +xpath +"'");
				System.out.println("Id: " + pageNode.item(i).getFirstChild().getNodeValue());
			}
		}catch(Exception e){
			System.out.println("---------ERRORE regola errata o pagina mancante---------");
		}
	}

	private static Document cleanHtml(String html){

		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode rootNode = cleaner.clean(html);
			DomSerializer domSerializer = new DomSerializer(new CleanerProperties());
			return domSerializer.createDOM(rootNode);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getStringFromDoc(org.w3c.dom.Document doc)    {
		DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
		LSSerializer lsSerializer = domImplementation.createLSSerializer();
		return lsSerializer.writeToString(doc);   
	}

}