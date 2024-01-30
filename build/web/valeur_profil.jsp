<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        List<Poste> postes = BddObject.find(new Poste(), null);  
%>
<div class="col-md-12 grid-margin stretch-card mt-5 mx-auto ml-auto">
  <div class="card">
    <div class="card-body">
      <p class="card-description">
        Entrer personnel profil
      </p>
      <form class="forms-sample" method="post" action="ValeurProfilServelet">
        <div class="form-floating mt-3 mb-3">
            <select class="form-select" id="floatingSelect" name="poste"
                aria-label="Floating label select example">
                <option selected>  </option>
                <%
                    for(Poste poste : postes){ %>
                        <option value="<%= poste.getIdPoste() %>">
                           <%= poste.getNomPoste() %>
                        </option>
                    <% }
                %>
            </select>
            <label for="floatingSelect"> Choisir profil ... </label>
        </div>

       <div class="form-floating mb-3">
                <input type="number" name="coeficient" class="form-control" id="coeficient"
                    placeholder="name@example.com">
                <label for="floatingInput"> Coefficient  </label>
        </div>
        <div class="form-floating mb-3">
                <input type="number" name="ancien" class="form-control" id="ancient"
                    placeholder="name@example.com">
                <label for="floatingInput"> Anciennete  </label>
        </div>

        <button type="submit" class="btn btn-primary mr-2">Submit</button>
        <button class="btn btn-light">Cancel</button>
      </form>
    </div>
  </div>
</div>
