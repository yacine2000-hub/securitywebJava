package com.groupeisi.securityweb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Droit implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	public int getId() {
		return id;
	}
	@Column (name = "name", nullable = false, length = 200, unique = true)
	private String name;
	@ManyToMany(mappedBy = "droits")
	private List<Comptes> comptes = new ArrayList<Comptes>();
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setComptes(List<Comptes> comptes) {
		this.comptes = comptes;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Comptes> getComptes() {
		return comptes;
	}
	public void setUsers(List<Comptes> comptes) {
		this.comptes = comptes;
	}

}
