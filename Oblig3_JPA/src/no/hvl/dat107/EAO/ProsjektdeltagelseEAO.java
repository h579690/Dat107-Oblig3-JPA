package no.hvl.dat107.EAO;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.*;

public class ProsjektdeltagelseEAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ansatt");

	public void lagreNyProsjektD(Prosjektdeltagelse nyttP) {
		
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
	
    public void oppdaterTimer(int aID, int pID) {
    	
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Scanner tast = new Scanner(System.in);
		
		Prosjektdeltagelse pd;
		
		try {
			tx.begin();
			
			ProsjektdeltagelsePK key = new ProsjektdeltagelsePK(aID, pID);
			
			pd = em.find(Prosjektdeltagelse.class, key);
		
			System.out.println("Skriv inn antall timer: ");
			float timer = tast.nextInt();
			tast.nextLine();
			
			pd.setTimer(timer);
			
			em.merge(pd);
			System.out.println(pd);
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
    
    public double totalTimer(int pID) {
    	EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
    	double antallTimer = 0;
    	Prosjekt p;
    	
    	String queryString = ("SELECT SUM(p.timer) FROM Prosjektdeltagelse p WHERE p.prosjekt = :pID");
    	
		try {
			tx.begin();
			
			p = em.find(Prosjekt.class, pID);
			Query query = em.createQuery(queryString);
			query.setParameter("pID", p);
			
			antallTimer =(double) query.getSingleResult();
			
			
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
    	
    	return antallTimer;
    }
	
}
