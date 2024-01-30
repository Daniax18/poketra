 

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
        Entrer personnel informations
      </p>
      <form class="forms-sample" method="post" action="Employer_serv">
        <div class="form-group mt-3">
          <label for="exampleInputUsername1">Nom</label>
          <input type="text" class="form-control" name="name" placeholder="Username">
        </div>
        <div class="form-group mt-3">
          <label for="exampleInputUsername2">Prenom</label>
          <input type="text" class="form-control" name="prenom" placeholder="Username">
        </div>
        <div class="form-group mt-3">
          <label for="exampleInputUsername2">Date_naissance</label>
          <input type="date" class="form-control" name="dtn" placeholder="Username">
        </div>
        <div class="form-group mt-3">
          <label for="exampleInputUsername2">Date embauche</label>
          <input type="date" class="form-control" name="date_embauche" placeholder="Username">
        </div>

       

        <button type="submit" class="btn btn-primary mr-2">Submit</button>
        <button class="btn btn-light">Cancel</button>
      </form>
    </div>
  </div>
</div>

