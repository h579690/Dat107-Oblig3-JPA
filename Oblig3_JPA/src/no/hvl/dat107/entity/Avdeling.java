package no.hvl.dat107.entity;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import no.hvl.dat107.EAO.AnsattEAO;


@Entity
@Table(name = "avdeling", schema = "oblig3")

public class Avdeling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "sjef_id", referencedColumnName = "id")
	private Ansatt sjef_id;
	
	@OneToMany(mappedBy = "avd_Id", fetch = FetchType.EAGER)
	private List<Ansatt> ansatte;
	
	private String navn;
	
	public Avdeling() {}
	
	public Avdeling(String navn, Ansatt sjef_id) {
		super();
		this.navn = navn;
		this.sjef_id = sjef_id;
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
	public Ansatt getSjef_Id() {
		return sjef_id;
	}
	public void setSjef_Id(Ansatt ansatt_sjef) {
		this.sjef_id = ansatt_sjef;
	}
	

	public List<Ansatt> getAnsatte() {
		return ansatte;
	}

	public void setAnsatte(List<Ansatt> ansatte) {
		this.ansatte = ansatte;
	}

	@Override
	public String toString() {
		return "Avdeling [id=" + id + ", navn=" + navn + ", sjef_Id=" + sjef_id + "]";
	}
	
	public Avdeling lesAvdeling() {
		AnsattEAO ansattEAO = new AnsattEAO();
		Scanner tast = new Scanner(System.in);
		
		System.out.println("Skriv inn navn: ");
		String navn = tast.nextLine();
		
		System.out.println("Skriv inn brukernavn for sjef: ");
		Ansatt sjef_id = ansattEAO.finnAnsattMedBnavn(tast.nextLine());

		Avdeling avd = new Avdeling(navn, sjef_id);
		sjef_id .setAvdeling(avd);
		System.out.println(avd);
		
		return avd;
	}
	
}
