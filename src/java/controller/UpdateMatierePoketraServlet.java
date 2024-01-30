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
import java.util.List;
import mapping.BddObject;
import model.Poketra;
import model.Poketra_matiere;

/**
 *
 * @author aram
 */
@WebServlet(name = "UpdateMatierePoketraServlet", urlPatterns = {"/UpdateMatierePoketraServlet"})
public class UpdateMatierePoketraServlet extends HttpServlet {

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
        
        
        String getPoketra = request.getParameter("poketra");
        Poketra poketra = new Poketra();
        poketra.setIdPoketra(getPoketra);
        
        try {
            poketra = BddObject.findById(poketra, null);
            List<Poketra_matiere> pms = poketra.getMy_matiere();
            for(Poketra_matiere pm : pms){
                pm.setQty(request.getParameter(pm.getIdMatierePremiere()));
            }
            Poketra_matiere.updatePms(pms);
        } catch (Exception e)  {
            request.setAttribute("error", e.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp?page=detail_poketra&idpoketra="+poketra.getIdPoketra());
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
