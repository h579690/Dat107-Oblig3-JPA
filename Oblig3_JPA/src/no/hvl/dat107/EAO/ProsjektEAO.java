package no.hvl.dat107.EAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import no.hvl.dat107.*;
import no.hvl.dat107.entity.*;

public class ProsjektEAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ansatt");
	
	  public Prosjekt finnProsjektMedId(int id) {

	        EntityManager em = emf.createEntityManager();

	        Prosjekt prosjekt;
	        try {
	            prosjekt = em.find(Prosjekt.class, id);
	        } finally {
	            em.close();
	        }
	        return prosjekt;
	    }
	  
		
		public void lagreNyttProsjekt(Prosjekt nyttP) {
			
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			try {
				tx.begin();
				em.persist(nyttP);
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
		
		public void sum(int total) {
			EntityManager em = emf.createEntityManager();
			
			Prosjektdeltagelse pd = new Prosjektdeltagelse();
			Prosjekt prosjekt;
			int antall;
			
			
			
			
		}

}
