package com.groupeisi.securityweb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.groupeisi.securityweb.dao.Comptedao;
import com.groupeisi.securityweb.dao.Droitdao;
import com.groupeisi.securityweb.dto.Comptedto;
import com.groupeisi.securityweb.entities.Comptes;
import com.groupeisi.securityweb.entities.Droit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/compte/list", "/compte/add"})
public class ComptesSevlet extends HttpServlet {
	private Comptedao comptedao = new Comptedao();
	private Droitdao droitdao = new Droitdao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
        	resp.sendRedirect("../index.jsp");
        } 

        if (session.getAttribute("username") == null) {
        	resp.sendRedirect("../index.jsp");
        } else {
		String action = req.getServletPath();

			switch (action) {
			case "/compte/add":
				List<Droit> listdroit = droitdao.getAll();
				req.setAttribute("listdroit", listdroit);
				req.getRequestDispatcher("../WEB-INF/view/comptes/add.jsp").forward(req, resp);
				break;
			case "/compte/logout":
				RequestDispatcher dispatcher = req.getRequestDispatcher("/LogoutServlet");
				dispatcher.forward(req, resp);
	
				break;
			default:
				List<Comptedto> listCompte = comptedao.ListCompteToListCompteDto(comptedao.getAll());
				req.setAttribute("listCompte", listCompte);
	
				req.getRequestDispatcher("../WEB-INF/view/comptes/list.jsp").forward(req, resp);
				break;
			}
        }
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
        	resp.sendRedirect("../index.jsp");
        } 

        if (session.getAttribute("username") == null) {
        	resp.sendRedirect("../index.jsp");
        } else {
			Comptes compte = new Comptes();
			String[] idDroits = req.getParameterValues("droit");
			List<Droit> listDroit = new ArrayList<>();
		    for (String idDroit : idDroits) {
		    	listDroit.add(droitdao.getOne(Integer.parseInt(idDroit)));
		      }
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			compte.setUserName(username);
			compte.setPassword(password);
			compte.setDroits(listDroit);
			comptedao.save(compte);
			
			List<Comptedto> listCompte = comptedao.ListCompteToListCompteDto(comptedao.getAll());
			req.setAttribute("listCompte", listCompte);
	
			req.getRequestDispatcher("../WEB-INF/view/comptes/list.jsp").forward(req, resp);
        }
		
	}	 

}
