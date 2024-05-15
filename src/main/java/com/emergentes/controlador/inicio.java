package com.emergentes.controlador;

import com.emergentes.dao.EstudianteDAO;
import com.emergentes.dao.EstudianteDAOimpl;
import com.emergentes.modelo.Estudiante;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "inicio", urlPatterns = {"/inicio"})
public class inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EstudianteDAO dao = new EstudianteDAOimpl();
            int id;
            Estudiante est = new Estudiante();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch(action){
                case "add":
                    request.setAttribute("estudiante", est);
                    request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    est = dao.getById(id);
                    System.out.println(est);
                    request.setAttribute("estudiante", est);
                    request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                    break;
                case "delete":
                    id =Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/inicio");
                    break;
                case "view":
                    List<Estudiante> lista = dao.getAll();
                    request.setAttribute("estudiantes", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error "+ ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String seminario = request.getParameter("seminario");
        String fechaStr = request.getParameter("fecha");
        Date fecha = Date.valueOf(fechaStr);
        
        Estudiante est = new Estudiante();
        est.setId(id);
        est.setNombre(nombre);
        est.setApellido(apellido);
        est.setSeminario(seminario);
        est.setFecha(fecha);
        
        if (id==0) {
            EstudianteDAO dao = new EstudianteDAOimpl();
            try {
                dao.insert(est);
            } catch (Exception ex) {
                System.out.println("Error "+ex.getMessage());
            }
                response.sendRedirect(request.getContextPath()+"/inicio");
        } else {
            try {
                EstudianteDAO dao = new EstudianteDAOimpl();
                dao.update(est);
                response.sendRedirect(request.getContextPath()+"/inicio");
            } catch (Exception ex) {
                System.out.println("Error "+ex.getMessage());
            }
        }
    }

}
