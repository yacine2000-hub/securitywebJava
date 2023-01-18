package com.groupeisi.securityweb.controller;

import java.io.IOException;

import com.groupeisi.securityweb.dao.Comptedao;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(value = "/login", name = "login")
public class LoginServlet extends HttpServlet {
		
	private Comptedao loginDao;

	@Override
	public void init() {
		loginDao = new Comptedao();
		//super.init(config);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("error", "email ou mot de passe incorrect !!!");
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username").toString();
		String password = req.getParameter("password").toString();
		
		if (loginDao.login(username, password)) {
	        HttpSession session = req.getSession();
	        session.setAttribute("username", username);
			resp.sendRedirect("compte/list");
			
		}else {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		
	}
	
}
