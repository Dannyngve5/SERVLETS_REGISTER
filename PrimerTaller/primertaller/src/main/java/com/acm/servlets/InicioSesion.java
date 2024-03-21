package com.acm.servlets;

import com.acm.model.Usuario;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;



@WebServlet(name = "inicioSesion", value = "/iniciosesion")
public class InicioSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El nombre de usuario y la contraseña son obligatorios.");
            return;
        }

        List<Usuario> usuarios = Registro.getUsuarios(); 

        Usuario usuarioEncontrado = usuarios.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (usuarioEncontrado == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Nombre de usuario o contraseña incorrecta.");
            return;
        }

        // INICIO EXITOSO

        HttpSession session = req.getSession();
        // int tiempoDeVidaEnSegundos = 30; 
        // session.setMaxInactiveInterval(tiempoDeVidaEnSegundos);
        
        session.setAttribute("usuario", usuarioEncontrado);
        //resp.getWriter().write("Inicio de sesión exitoso.");

        //req.getRequestDispatcher("/listar").forward(req, resp);        
        //RequestDispatcher dispatcher = req.getRequestDispatcher("/listar");
        //dispatcher.forward(req, resp);

        resp.sendRedirect(req.getContextPath() + "/lobby.html");
        


    }

    
}


// Poner un dispatcher para listar  req.getRequestDispatcher(path:"/listar").forward(req, resp);