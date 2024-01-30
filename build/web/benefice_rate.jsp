

<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="mapping.BddObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Poketra> poketras = BddObject.find(new Poketra(), null);
    
%>

<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
     <div class="bg-light rounded h-100 p-4">
       <h6 class="mb-4"> Taux de benefice de Poketra : </h6>
       <form method="POST" action="RateServlet">
           <div class="form-floating mb-3">
                <select class="form-select" id="floatingSelect" name="poketra"
                    aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(Poketra poketra : poketras){ %>
                            <option value="<%= poketra.getIdPoketra() %>">
                               <%= poketra.getNomPoketra() %> ( <%= poketra.toString() %> )
                            </option>
                        <% }
                    %>
                   
                </select>
                <label for="floatingSelect"> Poketra : </label>
            </div>
                    
            <div class="form-floating mb-3">
                <input type="number" name="valeur" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Coefficient en % : </label>
            </div>
                    
            
            <button type="submit" class="btn btn-primary mt-4"> Enregistrer </button>
        </form>
    </div>
</div>