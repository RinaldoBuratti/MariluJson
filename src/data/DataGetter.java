package data;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataGetter {
	
	/*Restituisce i link puliti in formato stringa */
	public static String[] getLinks(String site, String resource, String path) {
		JSONParser parser = new JSONParser();
		JSONObject middle = null;
		String[] arr = null;
		
		try {
			Object obj = parser.parse(new FileReader(path));
			JSONObject json = (JSONObject) obj;
			middle = (JSONObject) json.get(site);
			//JSONObject res = middle.get(resource);
			String var = middle.get(resource).toString();
			String values = var.replace("[", "").replace("]", "").replace("\"", "").replace("\\/", "/");
			//System.out.println(middle.values());
			//System.out.println(middle.entrySet());
			//System.out.println(s.size());
			arr = values.split(",");
			
		} catch (Exception e){
			System.out.println("Risorsa non presente per il sito specificato");
			System.out.println("Controllare sito internet e numero di risorsa");
		};
		
		
		return arr;
		
	}
	
}
