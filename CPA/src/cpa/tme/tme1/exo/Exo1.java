package cpa.tme.tme1.exo;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;


public class Exo1 {

	public static void main(String[] args) {
		File file = new File(args[0]);

		Scanner lecteur = null;
		int edges = 0;
		HashSet<Integer> id = new HashSet<Integer>();
		try {
			lecteur = new Scanner(file);
			
			// Affiche et passe l'en-tete du fichier
			while(!lecteur.hasNextInt()) {
				System.out.println(lecteur.nextLine());
			}
			//compte les liens et les id du fichier
			while(lecteur.hasNext()) {
				id.add(lecteur.nextInt());
				edges++;
			}
			edges /= 2;
			lecteur.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("le fichier \"" + args[0] + "\" contient " + edges + " liens et " + id.size() + " noeuds.");
	}
}
