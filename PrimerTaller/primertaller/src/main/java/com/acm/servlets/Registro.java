package com.acm.servlets;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.acm.model.Usuario;


@WebServlet(name = "registro", value = "/registro")
public class Registro extends HttpServlet {

    // crea una copia fresca de la lista subyacente cada vez que se modifica, es decir, cada vez que se añade, modifica o elimina un elemento.
    private static List<Usuario> usuarios = new CopyOnWriteArrayList<>();

     // Método estático para acceder a la lista de usuarios desde otros servlets
     public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El nombre de usuario y la contraseña son obligatorios.");
            return;
        }   

        
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                resp.sendError(HttpServletResponse.SC_CONFLICT, "El usuario ya existe.");
                return;
            }
        }

        usuarios.add(new Usuario(username, password)); 
        // 201 created
        resp.setStatus(HttpServletResponse.SC_CREATED);
        //resp.getWriter().write("Usuario registrado con éxito.");
        resp.sendRedirect(req.getContextPath() + "/inicioSesion.html");
        
    }

    
}

