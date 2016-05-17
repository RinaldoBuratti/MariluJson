package main;

import java.util.Scanner;

import data.DataGetter;
import data.DataSetter;
import downloader.DownloaderHttp;



public class Main {

	public static void main(String[] args) throws Exception {
		DownloaderHttp downloader = new DownloaderHttp();
		DataSetter ds = new DataSetter();
		final String PATH;
		String[] links;
		boolean nextResource = true;

		Scanner reader = new Scanner(System.in);
		System.out.println("digitare il percorso per il file: ");
		PATH = reader.nextLine();

		do {
			try{
				ds.getDataFromInput(reader);
				links = DataGetter.getLinks(ds.getSite(), ds.getResource(), PATH);
				downloader.getData(ds.getSite(), ds.getResource(), ds.getRule(), links);

				System.out.println("Vuoi processare un'altra risorsa? [S/N]");
				char validate = reader.next().charAt(0);
				if(validate == ('N') || validate ==('n')) {
					nextResource = false;	
				}
			}catch(Exception e) {
				System.out.println("Errore nei parametri immessi");
			}

		} while(nextResource);

		if(!nextResource) 
			reader.close();
	}
}
