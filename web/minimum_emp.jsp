
<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        List<Poste> postes = BddObject.find(new Poste(), null); 
%>


<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
    <div class="bg-light rounded h-100 p-4">
        <h6 class="mb-4"> Regle de gestion Nbr employe / Taille  </h6>
        
        <form method="POST" action="MinEmpServlet">
            <%
                for(Poste poste : postes){ %>
                <div class="form-floating mb-3">
                    <input type="number" name="<%= poste.getIdPoste() %>" class="form-control" id="floatingInput"
                        placeholder="name@example.com">
                    <label for="floatingInput"> <%= poste.getNomPoste() %> : </label>
                </div>
                <% }
            %>
            <button type="submit" class="btn btn-primary"> Enregistrer </button>
        </form>
    </div>
</div>