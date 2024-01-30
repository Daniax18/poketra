

<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="mapping.BddObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String idmatiere = (String) request.getParameter("idmatiere");
    MatierePremiere mp = new MatierePremiere();
    mp.setIdmatiere(idmatiere);
    mp = BddObject.findById(mp, null);
    
    List<Poketra_matiere> pms = mp.getPoketraByMatiere();
%>

<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto;" >
     <div class="bg-light rounded h-100 p-4">
       <h6 class="mb-4"> Les poketra pour matiere : <b> <%= mp.getNom_matiere() %> </b></h6>
        
       <div>
           <table class="table table-bordered">
               <thead class="text-center">
                    <th> Poketra </th>
                    <th> Qty </th>
               </thead>
               
                <% for(Poketra_matiere pm : pms){ %>
                <tbody>
                    <td> <%= pm.getMy_poketra().getNomPoketra()  %> </td>
                    <td> <%= pm.getQty()  %> </td>
                </tbody>
                <% } %>
               
           </table>
       </div>
    </div>
</div>