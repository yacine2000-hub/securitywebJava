package com.groupeisi.securityweb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Comptes implements Serializable {
	

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
	public List<Droit> getDroits() {
		return droits;
	}
	public void setDroits(List<Droit> droits) {
		this.droits = droits;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	@Column (name = "userName", nullable = false, length = 200)
	private String userName;
	@Column (name = "password", nullable = false, length = 200)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Droit> droits = new ArrayList<Droit>();

}
