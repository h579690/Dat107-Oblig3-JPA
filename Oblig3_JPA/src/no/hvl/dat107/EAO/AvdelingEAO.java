package no.hvl.dat107.EAO;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import no.hvl.dat107.*;
import no.hvl.dat107.entity.*;

public class AvdelingEAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ansatt");
	
	AnsattEAO ansattEAO = new AnsattEAO();
	
	public Avdeling finnAvdeling(int id) {
		
		EntityManager em = emf.createEntityManager();

		Avdeling avdeling;

		try {
			avdeling = em.find(Avdeling.class, id);

		} finally {
			em.close();
		}
		return avdeling;
	}
	
	public void leggTilAvdeling(Avdeling nyAvd) {
			
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			try {
				tx.begin();
				em.persist(nyAvd);
				tx.commit();

			} catch (Throwable e) {
				e.printStackTrace();
				if (tx.isActive()) {
					tx.rollback();
				}
			} finally {
				em.close();
			}
		}
	
	
}
