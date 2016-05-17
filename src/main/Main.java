package main;

import java.util.Scanner;

import data.DataGetter;
import data.DataSetter;
import downloader.DownloaderHttp;
import json.JsonCreatorXpath;


public class Main {

	public static void main(String[] args) throws Exception {
		DownloaderHttp downloader = new DownloaderHttp();
		DataSetter ds = new DataSetter();
		JsonCreatorXpath js = new JsonCreatorXpath();
		final String PATH;
		String[] links;
		boolean nextResource = true;
		
		Scanner reader = new Scanner(System.in);
		System.out.println("digitare il percorso per il file: ");
		PATH = reader.nextLine();
		
		do {
			ds.getDataFromInput();
			links = DataGetter.getLinks(ds.getSite(), ds.getResource(), PATH);
			downloader.getData(ds.getSite(), ds.getResource(), ds.getRule(), links);
			
			System.out.println("I codici sono validi? [S/N]: ");
			char validate = reader.next().charAt(0);
			if(validate == ('S') || validate ==('s')) {
				System.out.println("ciao");
			}
			
			System.out.println("vuoi processare una nuova risorsa? [S/N]: ");
			 char continua= reader.next().charAt(0);
			if(continua == ('N') || continua ==('n')) {
				nextResource = false;
			}
			
		} while(nextResource);
		
		reader.close();
	}
}
