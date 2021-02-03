package no.hvl.dat107;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import no.hvl.dat107.EAO.*;
import no.hvl.dat107.entity.*;

public class Main {

	public static void main(String[] args) {
		
		AnsattEAO ansattEAO = new AnsattEAO();
		AvdelingEAO avdelingEAO = new AvdelingEAO();
		Avdeling avdeling = new Avdeling();
		Prosjekt prosjekt = new Prosjekt();
		ProsjektEAO prosjektEAO = new ProsjektEAO();
		Prosjektdeltagelse prosjektD = new Prosjektdeltagelse();
		ProsjektdeltagelseEAO prosjektDEAO = new ProsjektdeltagelseEAO();
		
		String valg = 
				"Tast inn nr for å: \n" 
				+ "0. avslutte programmet\n" 
				+ "1. søke etter ansatt med id\n"
				+ "2. søke etter ansatt med brukernavn\n"
				+ "3. skriv ut alle ansatte\n"
				+ "4. oppdatere en ansatt sin stilling/lønn\n"
				+ "5. legge inn en ny ansatt\n"
				+ "6. søke etter avdeling med id\n" 
				+ "7. oversikt over alle ansatte på en avdeling\n"
				+ "8. oppdatere en ansatt sin avdeling\n"
				+ "9. legge inn en ny avdeling\n"
				+ "10. legge til et prosjekt\n"
				+ "11. registrere prosjektdeltagelse\n"
				+ "12. føre timer for en ansatt\n"
				+ "13. skrive ut all info om eit prosjekt\n";

		Scanner tast = new Scanner(System.in);
		
		
		System.out.println(valg);
		int input = 0;
		do {
			input = tast.nextInt();
			tast.nextLine();
			
			Ansatt ansatt = new Ansatt();
			
			String inn = "";
			int tall = input;
			int aid = 0;
			int pid = 0;
			
			if(tall == 2 || tall == 9) {
		
				System.out.println("Skriv inn brukernavn: ");
				inn = tast.nextLine();
			}
			
			if(input == 1 || tall == 4 || input == 8) {
				System.out.println("Skriv inn ansatt ID: ");
				tall = tast.nextInt();
				tast.nextLine();
			}
			if(input == 12) {
					System.out.println("Skriv inn ansatt ID: ");
					aid = tast.nextInt();
					tast.nextLine();
					
					System.out.println("Skriv inn prosjekt ID: ");
					pid = tast.nextInt();
					tast.nextLine();
			}
			
			if(input == 13) {
				System.out.println("Skriv inn prosjekt ID: ");
				tall = tast.nextInt();
				tast.nextLine();
			}
			
			if(input == 6 || input == 7) {
				System.out.println("Skriv inn avdeling ID: ");
				tall = tast.nextInt();
				tast.nextLine();
			}

			
		switch(input) {
		case 1: 
			ansatt = ansattEAO.finnAnsattMedId(tall);
			System.out.println(ansatt);
			break;
		case 2:
			//ansatt = em.find(Ansatt.class, input);
			System.out.println(ansattEAO.finnAnsattMedBnavn(inn));
			break;
		case 3:
			System.out.println("Alle ansatte: " + ansattEAO.finnAlleAnsatte());
			break;
		case 4:
			ansattEAO.oppdaterAnsatt(ansattEAO.finnAnsattMedId(tall));
			break;
		case 5:	
			ansattEAO.lagreNyAnsatt(ansatt.lesAnsatt());
			break;
		case 6:
			avdeling = avdelingEAO.finnAvdeling(tall);
			System.out.println(avdeling);
			break;
		case 7:
			System.out.println(avdelingEAO.finnAvdeling(tall).getAnsatte());
			System.out.println("Sjefen for avdelingen er: " + avdelingEAO.finnAvdeling(tall).getSjef_Id());
			break;
		case 8:
			ansattEAO.oppdaterAvdeling(ansattEAO.finnAnsattMedId(tall));
			break;
		case 9:
			avdelingEAO.leggTilAvdeling(avdeling.lesAvdeling());
			break;
		case 10:
			//legge inn et nytt prosjekt
			prosjektEAO.lagreNyttProsjekt(prosjekt.lagreNyttProsjekt());
			break;
		case 11:
			//registrere prosjektdeltagelse
			prosjektDEAO.lagreNyProsjektD(prosjektD.lesPD());
			break;
		case 12:
			//føre timer for en ansatt på et prosjekt
			prosjektDEAO.oppdaterTimer(aid, pid);
			break;
		case 13:
			//utskrift av info om prosjekt: liste av deltagere, med roller og timer også totaletimer
			prosjektEAO.finnProsjektMedId(tall).skrivUtMedAnsatte();
			System.out.println("\nTotale timer for prosjektet: " + prosjektDEAO.totalTimer(tall));
			break;
		} 
	}while(input != 0);

		}
}
