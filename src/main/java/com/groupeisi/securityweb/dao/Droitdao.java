package com.groupeisi.securityweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.groupeisi.securityweb.config.HibernateUtil;
import com.groupeisi.securityweb.dto.Comptedto;
import com.groupeisi.securityweb.dto.Droitdto;
import com.groupeisi.securityweb.entities.Comptes;
import com.groupeisi.securityweb.entities.Droit;

public class Droitdao implements IDroitdao {
	private Session session;
	private Transaction transaction;
	
	public Droitdao () {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public Droit getByNom(String nom) {
		
		return (Droit) session.createQuery("FROM Droit d WHERE d.nom = :d_nom")
				.setParameter("d_nom", nom).uniqueResult();
	}
	
	public int save(Droit droit) {
		try {
			transaction = session.beginTransaction();
			session.save(droit);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}


	@SuppressWarnings("unchecked")
	public List<Droit> getAll() {
		return session.createQuery("select droit from Droit droit").getResultList();
		
	}

	@Override
	public Droit getOne(int l) {
		return session.get(Droit.class, l);
	}
	

	

	public Droit DriotDtoToDriotEntity (Droitdto droitdto) {
		Droit droit = new Droit();
		droit.setId(droitdto.getId());
		droit.setName(droitdto.getName());
		if (droitdto.getComptes() != null) {
			List<Comptes> comptes = new ArrayList<Comptes>();
			droitdto.getComptes().forEach(userName->{
				Comptes compte = new Comptedao().getByUserName(userName);
				comptes.add(compte);
			});
			droit.setComptes(comptes);
		}
		
		return droit;
	}

	public Droitdto DroitEntityToDroitDto (Droit droit) {
		Droitdto droitdto = new Droitdto();
		droitdto.setId(droit.getId());
		droitdto.setName(droit.getName());
		if (droit.getComptes() != null) {
			List<String> comptes = new ArrayList<String>();
			droit.getComptes().forEach(compte->{
				comptes.add(compte.getUserName());
			});
			droitdto.setComptes(comptes);
		}
		
		return droitdto;
	}

	public List<Droitdto> ListDriotToListDriotDto (List<Droit> droits) {
		List<Droitdto> droitsdto = new ArrayList<Droitdto>();
		if (droits != null) {
			droits.forEach(droit->
			droitsdto.add(DroitEntityToDroitDto(droit))
			);
		}
		
		return droitsdto;
	}
	
}
