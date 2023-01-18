package com.groupeisi.securityweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.groupeisi.securityweb.config.HibernateUtil;
import com.groupeisi.securityweb.dto.Comptedto;
import com.groupeisi.securityweb.entities.Comptes;
import com.groupeisi.securityweb.entities.Droit;

public class Comptedao implements IComptedao {

	private Session session;
	private Transaction transaction;
	
	public Comptedao () {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public int save(Comptes compte) {
		try {
			transaction = session.beginTransaction();
			session.save(compte);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Comptes> getAll() {
		return session.createQuery("select comptes from Comptes comptes").getResultList();
		
	}

	public boolean login(String userName, String password) {

		Comptes user = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			user = (Comptes) session.createQuery("FROM Comptes U WHERE U.userName = :userName").setParameter("userName", userName)
					.uniqueResult();
			
			if(user != null && user.getPassword().equals(password)) {
				return true;
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}
	

	public Comptes getByUserName(String userName) {
		
		//return (AppUser) session.get(AppUser.class, email); //A revoir
		return (Comptes) session.createQuery("FROM Comptes c WHERE c.userName = :c_userName")
				.setParameter("c_userName", userName).uniqueResult();
	}
	

	public Comptes CompteDtoToCompteEntity (Comptedto comptedto) {
		Comptes compte = new Comptes();
		compte.setId(comptedto.getId());
		compte.setUserName(comptedto.getUserName());
		compte.setPassword(comptedto.getPassword());
		if (comptedto.getDroits() != null) {
			List<Droit> droits = new ArrayList<Droit>();
			comptedto.getDroits().forEach(userName->{
				Droit droit = new Droitdao().getByNom(userName);
				droits.add(droit);
			});
			compte.setDroits(droits);
		}
		
		return compte;
	}

	public Comptedto CompteEntityToCompteDto (Comptes compte) {
		Comptedto comptedto = new Comptedto();
		comptedto.setId(compte.getId());
		comptedto.setUserName(compte.getUserName());
		comptedto.setPassword(compte.getPassword());
		if (compte.getDroits() != null) {
			List<String> droits = new ArrayList<String>();
			compte.getDroits().forEach(droit->{
				droits.add(droit.getName());
			});
			comptedto.setDroits(droits);
		}
		
		return comptedto;
	}

	public List<Comptedto> ListCompteToListCompteDto (List<Comptes> comptes) {
		List<Comptedto> comptesdto = new ArrayList<Comptedto>();
		if (comptes != null) {
			comptes.forEach(compte->
				comptesdto.add(CompteEntityToCompteDto(compte))
			);
		}
		
		return comptesdto;
	}
	
}
