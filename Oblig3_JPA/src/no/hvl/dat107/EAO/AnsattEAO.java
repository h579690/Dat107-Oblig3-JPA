package no.hvl.dat107.EAO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.*;
import no.hvl.dat107.entity.*;

public class AnsattEAO {
	
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ansatt");
	
	public Ansatt finnAnsattMedId(int id) {
		
		EntityManager em = emf.createEntityManager();

		Ansatt ansatt;

		try {
			ansatt = em.find(Ansatt.class, id);

		} finally {
			em.close();
		}
		return ansatt;
	}
	
	public Ansatt finnAnsattMedBnavn(String bnavn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
	
		
		Ansatt ansatt = null;
		
		try {
			//https://docs.oracle.com/javaee/6/tutorial/doc/bnbtg.html
			tx.begin();
			TypedQuery<Ansatt> query = em.createQuery(
					"SELECT a FROM Ansatt a WHERE a.bnavn LIKE :bnavn", Ansatt.class);
			query.setParameter("bnavn", bnavn);
			ansatt = query.getSingleResult();
			tx.commit();
		}catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		}finally {
			em.close();
		}
		return ansatt;
		
	}
	
	public List<Ansatt> finnAlleAnsatte() {
		
		EntityManager em = emf.createEntityManager();
		
		List<Ansatt> ansatt;
		
		try {
			TypedQuery<Ansatt> query = em.createQuery(
					"SELECT t FROM Ansatt t", Ansatt.class);
			ansatt = query.getResultList();
		
		} finally {
			em.close();
		}
		return ansatt;
	}
	
	public void lagreNyAnsatt(Ansatt ansattny) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(ansattny);
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
	
	public void oppdaterAnsatt(Ansatt ansatt) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Scanner tast = new Scanner(System.in);
		int tall;
		String inn;
		
		try {
			tx.begin();
			
			System.out.println("Oppdater lønn? j/n");
			if(tast.nextLine().equals("j")) {
				System.out.println("Skriv inn ny lønn: ");
				tall = tast.nextInt();
				tast.nextLine();
				ansatt.setMaanedslonn(tall);
			} 
			System.out.println("Oppdater stilling? j/n");
			if(tast.nextLine().equals("j")) {
				System.out.println("Skriv inn ny stilling: ");
				inn = tast.nextLine();
				ansatt.setStilling(inn);
			}
			
			System.out.println(ansatt);
			
			em.merge(ansatt);
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
	
	public void oppdaterAvdeling(Ansatt ansatt) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Scanner tast = new Scanner(System.in);
		
		AvdelingEAO avd = new AvdelingEAO();
		
		try {
			tx.begin();
			
			System.out.println("Skriv inn ny avdeling id: ");
			int avd_Id = tast.nextInt();
			
			if(ansatt.getAvdeling().getSjef_Id().equals(ansatt)) {
				System.out.println("Funker ikkje, ansatt er allerede sjef.");
			}else {
				ansatt.setAvdeling(avd.finnAvdeling(avd_Id));
				System.out.println(ansatt);
			}

			em.merge(ansatt);
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
	
    public void registrerProsjektdeltagelse(Ansatt a, Prosjekt p) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            a = em.merge(a);
            p = em.merge(p);
            
            Prosjektdeltagelse pd = new Prosjektdeltagelse(a, p, 0, "");
            
            a.leggTilProsjektdeltagelse(pd);
            p.leggTilProsjektdeltagelse(pd);
            
            em.persist(pd);
            
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


    public Prosjektdeltagelse finnProsjektdeltagelse(int ansattId, int prosjektId) {
        
        String queryString = "SELECT pd FROM Prosjektdeltagelse pd " 
                + "WHERE pd.ansatt.id = :ansattId AND pd.prosjekt.id = :prosjektId";

        EntityManager em = emf.createEntityManager();

        Prosjektdeltagelse pd = null;
        try {
            TypedQuery<Prosjektdeltagelse> query 
                    = em.createQuery(queryString, Prosjektdeltagelse.class);
            query.setParameter("ansattId", ansattId);
            query.setParameter("prosjektId", prosjektId);
            pd = query.getSingleResult();
            
        } catch (NoResultException e) {
            // e.printStackTrace();
        } finally {
            em.close();
        }
        return pd;
    }
}
