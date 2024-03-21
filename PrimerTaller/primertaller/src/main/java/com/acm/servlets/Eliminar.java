package com.acm.servlets;
import java.io.IOException;
import java.util.List;
import com.acm.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;


@WebServlet(name = "eliminar", value = "/eliminar")
public class Eliminar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must log in first.");
            return;
        }

        Usuario usuarioASerEliminado = (Usuario) session.getAttribute("usuario");
        List<Usuario> usuarios = Registro.getUsuarios();
        synchronized (usuarios) { 
            usuarios.removeIf(usuario -> usuario.getUsername().equals(usuarioASerEliminado.getUsername()));
        }

        session.invalidate(); 
        resp.getWriter().write("User information has been successfully deleted.");
    }
}