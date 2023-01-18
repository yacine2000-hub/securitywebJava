package com.groupeisi.securityweb.dao;

import java.util.List;

import com.groupeisi.securityweb.entities.Droit;

public interface IDroitdao {

	public int save (Droit droit);
	public List<Droit> getAll();
	public Droit getOne(int id);

}
