package no.hvl.dat107.entity;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prosjekt", schema = "oblig3")

public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @OneToMany(mappedBy="prosjekt")
    private List<Prosjektdeltagelse> deltagelser;

    public void skrivUt(String innrykk) {
        System.out.printf("%sProsjekt nr %d: %s", innrykk, id, navn, beskrivelse);
    }
   
    public void skrivUtMedAnsatte() {
        System.out.println();
        skrivUt("");
        deltagelser.forEach(a -> a.skrivUt("\n   "));
    }

	String navn;
	String beskrivelse;
	
	public Prosjekt() {}
	
	public Prosjekt(String navn, String beskrivelse) {
		super();
		this.navn = navn;
		this.beskrivelse = beskrivelse;
	}
	
	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
	        deltagelser.add(prosjektdeltagelse);
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	@Override
	public String toString() {
		return "Prosjekt [id=" + id + ", navn=" + navn + ", beskrivelse=" + beskrivelse
				+ "]";
	}
	
	public Prosjekt lagreNyttProsjekt() {
		Scanner tast = new Scanner(System.in);
		
		System.out.println("Skriv inn nytt prosjekt navn: ");
		String navn = tast.nextLine();
		
		System.out.println("Legg til en beskrivelse: ");
		String beskrivelse = tast.nextLine();
		
		Prosjekt p = new Prosjekt(navn, beskrivelse);
		System.out.println(p);
		
		return p;
	}
	
	
}
