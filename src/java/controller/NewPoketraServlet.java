/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Poketra;

/**
 *
 * @author aram
 */
@WebServlet(name = "NewPoketraServlet", urlPatterns = {"/NewPoketraServlet"})
public class NewPoketraServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
          
        
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String look = request.getParameter("look");
        String taille = request.getParameter("taille");
        
        String[] valeursChoisies = request.getParameterValues("matiere");
        
        try {
            Poketra poketra = new Poketra(name, type, look, taille);
            poketra.savePoketraWithMatiere(valeursChoisies);
//              out.println("Name is "+ poketra.getNomPoketra());
//              out.println("type is "+ poketra.getIdTypeSac());
//              out.println("look is "+ poketra.getIdLookSac());
//              out.println("taille is "+ poketra.getIdTailleSac());
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        } finally{
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp?page=new_poketra");
            rd.forward(request, response);
        }
        
        
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
