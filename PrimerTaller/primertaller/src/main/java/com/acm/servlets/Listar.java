package com.acm.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.acm.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "listar", value = "/listar")
public class Listar extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must log in first.");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        // El contenido de la respeusta será HTML, con codificación de caracteres UTF-8. 
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><USER DATA</title></head>");
        out.println("<body>");
        out.println("<p>THE INFORMATION IS</p>");
        out.println("<p>Username: " + usuario.getUsername() + "</p>");
        out.println("</body></html>");
    }
}
