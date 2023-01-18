package com.groupeisi.securityweb.controller;

import java.io.IOException;
import java.util.List;

import com.groupeisi.securityweb.dao.Droitdao;
import com.groupeisi.securityweb.dto.Comptedto;
import com.groupeisi.securityweb.dto.Droitdto;
import com.groupeisi.securityweb.entities.Droit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/droit/list", "/droit/add"})
public class DroitServlet extends HttpServlet {
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
			case "/droit/add":
				req.getRequestDispatcher("../WEB-INF/view/droits/add.jsp").forward(req, resp);
				break;
			case "/droit/logout":
				RequestDispatcher dispatcher = req.getRequestDispatcher("/LogoutServlet");
				dispatcher.forward(req, resp);
	
				break;
			default:
				List<Droitdto> listDroit  = droitdao.ListDriotToListDriotDto(droitdao.getAll());
				req.setAttribute("listDroit", listDroit);
	
				req.getRequestDispatcher("../WEB-INF/view/droits/list.jsp").forward(req, resp);
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
			String name = req.getParameter("name");
			
			Droit droit = new Droit();
			droit.setName(name);
			droitdao.save(droit);
			List<Droitdto> listDroit  = droitdao.ListDriotToListDriotDto(droitdao.getAll());
			req.setAttribute("listDroit", listDroit);
	
			req.getRequestDispatcher("../WEB-INF/view/droits/list.jsp").forward(req, resp);
        }
	
	}

}
