package no.hvl.dat107.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import no.hvl.dat107.EAO.*;

@Entity
@Table(name = "ansatt", schema = "oblig3")
//@NamedQuery(name = "hentAllePersoner", query ="SELECT p FROM Person p")


public class Ansatt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "avd_Id", referencedColumnName = "id")
	private Avdeling avd_Id;


    @OneToMany(mappedBy="ansatt")
    private List<Prosjektdeltagelse> deltagelser;

	private String bnavn;
	private String fnavn;
	private String enavn;
	private LocalDate ansettelsesdato;
	private String stilling;
	private int maanedslonn;
	
	public Ansatt() {}
	
	public Ansatt(String bnavn, String fnavn, String enavn, LocalDate ansettelsesdato, String stilling, int maanedslonn, Avdeling avdeling) {
		super();
		this.bnavn = bnavn;
		this.fnavn = fnavn;
		this.enavn = enavn;
		this.ansettelsesdato = ansettelsesdato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avd_Id = avdeling;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getBnavn() {
		return bnavn;
	}
	public void setBnavn(String bnavn) {
		this.bnavn = bnavn;
	}
	public String getFnavn() {
		return fnavn;
	}
	public void setFnavn(String fnavn) {
		this.fnavn = fnavn;
	}
	public String getEnavn() {
		return enavn;
	}
	public void setEnavn(String enavn) {
		this.enavn = enavn;
	}
	
	public LocalDate getAnsettelsesdato() {
		return ansettelsesdato;
	}
	public void setAnsettelsesdato(LocalDate ansettelsesdato) {
		this.ansettelsesdato = ansettelsesdato;
	}
	public String getStilling() {
		return stilling;
	}
	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	public int getMaanedslonn() {
		return maanedslonn;
	}
	public void setMaanedslonn(int maanedslonn) {
		this.maanedslonn = maanedslonn;
	}
	
	public Avdeling getAvdeling() {
		return avd_Id;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avd_Id = avdeling;
	}
/*
	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}
  */  

	@Override
	public String toString() {
		return "Ansatt [bnavn= " + bnavn + ", fnavn= " + fnavn + ", enavn= " + enavn + ", ansettelsesdato= "
				+ ansettelsesdato + ", stilling= " + stilling + ", maanedslonn= " + maanedslonn + "\n" + "avd_Id= " + avd_Id.getId();
	}
	
	  public void skrivUt(String innrykk) {
	        System.out.printf("%sAnsatt nr %d: %s %s", innrykk, id, fnavn, enavn, ansettelsesdato, stilling, maanedslonn);
	    }
	
	public Ansatt lesAnsatt() {
		Scanner tast = new Scanner(System.in);
		String inn;
		AvdelingEAO a = new AvdelingEAO();
		
		System.out.println("Skriv inn brukernavnet: ");
		String bnavn = tast.nextLine();
		tast.nextLine();
		
		System.out.println("Skriv inn fornavn: ");
		String fnavn = tast.nextLine();
		
		System.out.println("Skriv inn etternavn: ");
		String enavn = tast.nextLine();
		
		System.out.println("Skriv inn ansettelsesdato: ");
		LocalDate dato = LocalDate.parse(tast.nextLine());
		
		System.out.println("Skriv inn stilling: ");
		String stilling = tast.nextLine();
		
		System.out.println("Skriv inn lønn: ");
		int lonn = tast.nextInt();
		
		System.out.println("Skriv inn avd_Id: ");
		int avd_Id = tast.nextInt();
		
		Avdeling avdeling = a.finnAvdeling(avd_Id);
		
		Ansatt ansattny = new Ansatt(bnavn, fnavn, enavn, dato, stilling, lonn, avdeling);
		System.out.println(ansattny);
		return ansattny;
	}

	 public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
	        deltagelser.add(prosjektdeltagelse);
	    }

	    public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
	        deltagelser.remove(prosjektdeltagelse);
	    }
	
}
