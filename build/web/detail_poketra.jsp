

<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="mapping.BddObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String idpoketra = (String) request.getParameter("idpoketra");
    Poketra poketra = new Poketra();
    poketra.setIdPoketra(idpoketra);
    poketra = BddObject.findById(poketra, null);
    List<Poketra_matiere> pms = poketra.getMy_matiere();
%>

<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
     <div class="bg-light rounded h-100 p-4">
       <h6 class="mb-4"> Detail matieres : " <%= poketra.toString() %> " </h6>
       <form method="POST" action="UpdateMatierePoketraServlet">
           <input type="hidden" name="poketra" value="<%= poketra.getIdPoketra() %>">
           <% for(Poketra_matiere pm : pms) { %>
            <div class="form-floating mb-3">
                 <input 
                     type="number" 
                     name="<%= pm.getIdMatierePremiere() %>" 
                     value="<%= pm.getQty() %>"
                     class="form-control" 
                     id="floatingInput"
                     placeholder="name@example.com"
                 >
                 <label for="floatingInput"> <%= pm.getMy_mp().getNom_matiere() %>  </label>
             </div>    
           <% } %>
            <button type="submit" class="btn btn-primary mt-4"> Attribuer </button>
        </form>
    </div>
</div>