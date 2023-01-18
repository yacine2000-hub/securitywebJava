package com.groupeisi.securityweb.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.groupeisi.securityweb.entities.Droit;

public class Comptedto {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getDroits() {
		return droits;
	}
	public void setDroits(List<String> droits) {
		this.droits = droits;
	}
	private int id;
	private String userName;
	private String password;
	private List<String> droits = new ArrayList<String>();
}
