package no.hvl.dat107.entity;

import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import no.hvl.dat107.EAO.AnsattEAO;
import no.hvl.dat107.EAO.ProsjektEAO;

@Entity 
@Table(schema = "oblig3")
@IdClass(ProsjektdeltagelsePK.class)
public class Prosjektdeltagelse {
	    
	    private float timer;
	    private String rolle;
	    
	    @Id
	    @ManyToOne
	    @JoinColumn(name="ansatt_id")
	    private Ansatt ansatt;
	    
	    @Id
	    @ManyToOne
	    @JoinColumn(name="prosjekt_id")
	    private Prosjekt prosjekt;

	    public Prosjektdeltagelse() {}
	    
	    public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, float timer, String rolle) {
	        this.ansatt = ansatt;
	        this.prosjekt = prosjekt;
	        this.timer = timer;
	        this.rolle = rolle;
	       
	        ansatt.leggTilProsjektdeltagelse(this);
	        prosjekt.leggTilProsjektdeltagelse(this);
	    }
	    
	    public void skrivUt(String innrykk) {
	        System.out.printf("%sDeltagelse: %s %s, %s, %f timer", innrykk, 
	        		ansatt.getFnavn(), ansatt.getEnavn(), rolle, timer);
	    }

	    @Override
		public String toString() {
			return "Prosjektdeltagelse [timer=" + timer + ", rolle=" + rolle + ", ansatt=" + ansatt + ", prosjekt="
					+ prosjekt + "]";
		}

		public Prosjektdeltagelse lesPD() {
	    	Scanner tast = new Scanner(System.in);
	    	AnsattEAO ansattEAO = new AnsattEAO();
	    	Ansatt ansatt;
	    	Prosjekt p;
	    	ProsjektEAO pEAO = new ProsjektEAO();
	    	
	    	System.out.println("Skriv inn Id til den ansatte du vil registrere: ");
	    	int ans = tast.nextInt();
	    	ansatt = ansattEAO.finnAnsattMedId(ans);
	    	tast.nextLine();
	    	
	    	System.out.println("Skriv inn id til prosjektet du vil registrere han i: ");
	    	int pId = tast.nextInt();
	    	p = pEAO.finnProsjektMedId(pId);
	    	tast.nextLine();
	    	
	    	System.out.println("Skriv inn antall timer til den ansatte: ");
	    	float timer = tast.nextInt();
	    	tast.nextLine();
	    	
	    	System.out.println("Skriv inn rollen til den ansatte: ");
	    	String rolle = tast.nextLine();
	    	
	    	Prosjektdeltagelse pd = new Prosjektdeltagelse(ansatt, p, timer, rolle);
	    	System.out.println(pd);
	    	
	    	return pd;
	    }

		public float getTimer() {
			return timer;
		}

		public void setTimer(float timer) {
			this.timer = timer;
		}

		public String getRolle() {
			return rolle;
		}

		public void setRolle(String rolle) {
			this.rolle = rolle;
		}

		public Ansatt getAnsatt() {
			return ansatt;
		}

		public void setAnsatt(Ansatt ansatt) {
			this.ansatt = ansatt;
		}

		public Prosjekt getProsjekt() {
			return prosjekt;
		}

		public void setProsjekt(Prosjekt prosjekt) {
			this.prosjekt = prosjekt;
		}
	
}
