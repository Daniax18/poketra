

<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        List<Type_sac> types = BddObject.find(new Type_sac(), null); 
%>




<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
    <div class="bg-light rounded h-100 p-4">
        <h6 class="mb-4"> Nombre d'heure par type de sac </h6>
        <form method="POST" action="HeureSacServelet">
            
            <div class="form-floating mt-3 mb-3">
                <select class="form-select" id="floatingSelect" name="idtype"
                    aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(Type_sac ts : types){ %>
                            <option value="<%= ts.getId() %>">
                               <%= ts.getNom() %>
                            </option>
                        <% }
                    %>
                </select>
                <label for="floatingSelect"> Choisir type de sac ... </label>
            </div>
            
            <div class="form-floating mb-3">
                <input type="number" name="nombre" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Nombre : </label>
            </div>
            <button type="submit" class="btn btn-primary"> Enregistrer </button>
        </form>
    </div>
</div>