/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.BddObject;
import model.Look_matiere;

/**
 *
 * @author rango
 */
@WebServlet(name = "AttributionLookServlet", urlPatterns = {"/AttributionLookServlet"})
public class AttributionLookServlet extends HttpServlet {

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
        try {
            String idmatiere = request.getParameter("idmatiere");
            String[] valeursChoisies = request.getParameterValues("look");
//             out.println("La valeur est");
            if (valeursChoisies != null) {
                for (String valeur : valeursChoisies) {
//                    System.out.println("Valeur choisie : " + valeur);
//                    out.println("La valeur est " + valeur);
                    Look_matiere lm = new Look_matiere();
                    lm.setIdmatiere(idmatiere);
                    lm.setIdlook(valeur);
              

                    BddObject.insertInDatabase(lm, null);
                }
            } else {
                // Aucune case cochée
                request.setAttribute("error", "You must set a value");
            }
        } catch (Exception e) {
             request.setAttribute("error", e.getMessage());
        } finally{
            RequestDispatcher rd = request.getRequestDispatcher("AjoutLook");
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
