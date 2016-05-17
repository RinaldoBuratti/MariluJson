package data;

import java.util.Scanner;

public class DataSetter {
	private String site;
	private String resource;
	private String product;
	private String rule;
	private String page_id;
	private String attribute_name;
	
	public DataSetter() {
		
	}

	public void getDataFromInput(Scanner reader) {
		reader.nextLine();
		System.out.println("Scrivi il nome del sito: ");
		this.setSite(reader.nextLine());
		
		System.out.println("Scrivi il nome della risorsa: ");
		this.setResource(reader.nextLine());
		
		System.out.println("Scrivi la regola: ");
		this.setRule(reader.nextLine());
		
	}
	
	
	
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	public String getRule() {
		return rule;
	}

	public String getAttribute_name() {
		return attribute_name;
	}

	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	
}
