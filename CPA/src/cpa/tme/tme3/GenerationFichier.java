package cpa.tme.tme3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerationFichier {
	public static void main(String[] args) {
		//Generation du graphe
		double p =0.3, q = 0.0001;
		double r;
		int nbNode = 1000000;
		try(FileOutputStream fOs= new FileOutputStream(new File("I:/test.txt"))){
			String line;
			for(int i=0; i < nbNode; i++) {
				for(int j=0; j < nbNode; j++) {
					if(i != j) {
						r = Math.random();
						
						if(i/20000==j/20000) {
							//same cluster
							if(r < p) {
//								sb.append(i+"\t" + j + "\n");
								line = i+"\t" + j + "\n";
								fOs.write(line.getBytes());
							}
						} else {
							// different cluster
							if(r < q) {
//								sb.append(i+"\t" + j + "\n");
								line = i+"\t" + j + "\n";
								fOs.write(line.getBytes());
							}
						}
					}
				}
			}
//			fOs.write(sb.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
