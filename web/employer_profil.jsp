 

<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        List<Poste> postes = BddObject.find(new Poste(), null); 
        List<Employer> emps = BddObject.find(new Employer(), null); 
%>


<div class="col-md-12 grid-margin stretch-card mt-5 mx-auto ml-auto">
  <div class="card">
    <div class="card-body">
      <p class="card-description">
        Entrer personnel profil
      </p>
      <form class="forms-sample" method="post" action="EmpProfilServelet">
        <div class="form-floating mt-3 mb-3">
            <select class="form-select" id="floatingSelect" name="employer"
                aria-label="Floating label select example">
                <option selected>  </option>
                <%
                    for(Employer emp : emps){ %>
                        <option value="<%= emp.getId() %>">
                           <%= emp.getNom() %>
                        </option>
                    <% }
                %>
            </select>
            <label for="floatingSelect"> Choisir employer ... </label>
        </div>

       <div class="form-floating mt-3 mb-3">
            <select class="form-select" id="floatingSelect" name="profil"
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

        <button type="submit" class="btn btn-primary mr-2">Submit</button>
        <button class="btn btn-light">Cancel</button>
      </form>
    </div>
  </div>
</div>

