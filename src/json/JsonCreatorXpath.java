package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonCreatorXpath{
	
	public boolean addToXpathJsonFile(String site, String product, String rule, String attribute, String page_id) throws IOException {
		JSONParser parser = new JSONParser();
		JSONObject json = null;

		try {
			FileReader fr = new FileReader("xpath.avolio.json");
			/*Object obj = parser.parse(new FileReader("xpath.avolio.json"));*/
			Object obj = parser.parse(fr);
			json = (JSONObject) obj;
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (json.containsKey(site)){				//se il sito è già stato inserito
			JSONObject temp = (JSONObject) json.get(site);
			if(temp.containsKey(product)) {			//Se il prodotto è già stato inserito
				JSONArray temp1 = (JSONArray) temp.get(product);
				/*temp1.add("rule:"+rule);
				temp1.add("'attribute_name':"+attribute+"'");
				temp1.add("'page_id':"+page_id+"'");
				System.out.println(temp.toString());
				temp.put(product, temp1);	*/	
				//HashMap<String,String> map = new HashMap<>();
				JSONObject jobject = new JSONObject();
				jobject.put("rule", rule);
				jobject.put("attribute_name", attribute);
				jobject.put("page_id", page_id);
				//map.put("rule",rule);
				//map.put("attribute_name", attribute);
				//map.put("page_id", page_id);
				temp1.add(jobject);
				System.out.println(temp.toString());
			} else {								//Se il prodotto non è già stato inserito
				
			}
			
			FileWriter fw = new FileWriter("xpath.avolio.json",true);
			try {
				fw.write(json.toJSONString());
			} catch (Exception e) {}
			finally {
				fw.flush();
				fw.close();
			}
			
		} else {									//se il sito non è mai stato inserito prima
			
		}
		
		
		
		/*try (FileWriter file = new FileWriter("xpath.avolio.json")) {
				file.write(json.toJSONString());
				System.out.println("Successfully Copied JSON Object to File...");
				System.out.println("\nJSON Object: " + json);
		}*/

		
		
		
		
		return true;
	}

}
