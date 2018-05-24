/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JONATHAN
 */
@WebServlet(name = "serv_autor", urlPatterns = {"/serv_autor"})
public class serv_autor extends HttpServlet {

    String ls_mensaje = "";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String is_pantalla = "";
        ls_mensaje = "imprime algo";
        String is_boton = "";
        String ls_codigo = "";
        String ls_nombre = "";
        String ls_apellifo = "";
        is_boton = request.getParameter("boton");
        ls_codigo = request.getParameter("codigo");
        ls_nombre = request.getParameter("nombre");
        ls_apellifo = request.getParameter("apellido");
        if (is_boton == null || is_boton == "") {
            is_pantalla = desplegar_pantalla("", "", "");
        }

        if (is_boton != null && is_boton != "") {
            if (is_boton.equals("Insertar")) {                
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebaPU");
                EntityManager em1 = factory.createEntityManager();
                Autor pr = new Autor(ls_codigo);
                pr.setAuNombre(ls_nombre);
                pr.setAuApellido(ls_apellifo);
                //try {
                em1.getTransaction().begin();
                em1.persist(pr);
                em1.getTransaction().commit();
                is_pantalla = desplegar_pantalla("", "", "");
                ls_mensaje = "Inserción correcta";
                /* } catch (Exception ex) {
                    ls_mensaje = "El error es: " + ex.getMessage();
                    is_pantalla = desplegar_pantalla("", "", "");
                    ls_mensaje = "Inserción Incorrecta";
                }*/
                em1.close();
                factory.close();
            }

            if (is_boton.equals("Buscar")) {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebaPU");
                EntityManager em1 = factory.createEntityManager();
                Autor pr = new Autor();
                try {
                    pr = em1.find(Autor.class, ls_codigo);
                    ls_nombre = pr.getAuNombre();
                    ls_apellifo = pr.getAuApellido();
                    is_pantalla = desplegar_pantalla(ls_codigo, ls_nombre, ls_apellifo);
                    ls_mensaje = "dato encontrado";
                } catch (Exception ex) {
                    ls_nombre = null;
                    ls_apellifo = null;
                    is_pantalla = desplegar_pantalla(ls_codigo, ls_nombre, ls_apellifo);
                    ls_mensaje = "dato no encontrado";
                }
                em1.close();
                factory.close();
            }
        }
        is_pantalla += ls_mensaje;
        out.println(is_pantalla);
    }

    public String desplegar_pantalla(String as_codigo, String as_nombre, String as_apellido) {
        String ls_pantalla = "";
        ls_pantalla += "<!DOCTYPE html>";
        ls_pantalla += "<html>";
        ls_pantalla += "<head>";
        ls_pantalla += "<title>Servlet serv_menu_biblioteca</title>";
        ls_pantalla += "</head>";

        ls_pantalla += "<form action='serv_autor' method='post'>";
        ls_pantalla += "<h1>Tabla de Autores</h1>";
        ls_pantalla += "<header>";
        ls_pantalla += "<nav>";
        ls_pantalla += "<ul>";
        ls_pantalla += "<li><a href='#'>Inicio</a></li>";
        ls_pantalla += "<li><a href='#'>Autores</a></li>";
        ls_pantalla += "<li><a href='#'>Libros</a></li>";
        ls_pantalla += "<li><a href='#'>Prestamos</a></li>";
        ls_pantalla += "<li><a href='#'>Regresar al menu principal</a></li>";
        ls_pantalla += "</ul>";
        ls_pantalla += "</nav>";
        ls_pantalla += "</header>";
        ls_pantalla += "<table width='25%' border='0' align='center'>";
        ls_pantalla += "<tr>";
        ls_pantalla += "<td>Código del Cliente</td>";
        ls_pantalla += "<td><label for='codigo'></label>";
        ls_pantalla += "<input type='text' name='codigo'" + "value='" + as_codigo + "'></td>";
        ls_pantalla += "</tr>";
        ls_pantalla += "<tr>";
        ls_pantalla += "<td>Nombre del Cliente</td>";
        ls_pantalla += "<td><label for='nombre_cliente'></label>";
        ls_pantalla += "<input type='text' name='nombre' class='centrado'" + "value='" + as_nombre + "'></td>";
        ls_pantalla += "</tr>";
        ls_pantalla += "<tr>";
        ls_pantalla += "<td>Apellido del Cliente</td>";
        ls_pantalla += "<td><label for='apellido'></label>";
        ls_pantalla += "<input type='text' name='apellido' class='centrado'" + "value='" + as_apellido + "'></td>";
        ls_pantalla += "</tr>";
        ls_pantalla += "<tr>";
        ls_pantalla += "<td><br><br></td>";
        ls_pantalla += "<td colspan='1'><input type='submit' value='Insertar' name='boton'></td>";
        ls_pantalla += "<td colspan='1'><input type='submit' value='Buscar' name='boton'></td>";
        ls_pantalla += "</tr>";
        ls_pantalla += "</table>";
        ls_pantalla += "</form>";
        ls_pantalla += "</body>";
        ls_pantalla += "</html>";
        return ls_pantalla;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
