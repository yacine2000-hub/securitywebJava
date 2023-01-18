package com.groupeisi.securityweb.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import com.groupeisi.securityweb.entities.Comptes;

public class Droitdto {

public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getComptes() {
		return comptes;
	}
	public void setComptes(List<String> comptes) {
		this.comptes = comptes;
	}
private int id;
private String name;
private List<String> comptes = new ArrayList<String>();
}
