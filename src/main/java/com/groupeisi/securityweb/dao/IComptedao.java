package com.groupeisi.securityweb.dao;

import java.util.List;

import com.groupeisi.securityweb.entities.Comptes;

public interface IComptedao {

	public int save (Comptes compte);
	public List<Comptes> getAll();
	public boolean login(String userName, String password);

}
