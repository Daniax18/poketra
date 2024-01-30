<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page import="mapping.BddObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Poketra> poketras = BddObject.find(new Poketra(), null);
    HashMap<String, Double> result = (HashMap<String, Double>) request.getAttribute("matiere_manque");
%>
<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
    <div class="bg-light rounded h-100 p-4">
       <h6 class="mb-4"> Fabrication Poketra : </h6>
       <form method="POST" action="Fabrication_poketra_servelet">
                                                            
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
                <input type="text" name="quantiter" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Quantiter ... </label>
            </div>                               
            <button type="submit" class="btn btn-primary mt-4"> Save </button>
        </form>
    </div>

    <% if(result != null){ %>
        <div class="h-100 bg-light rounded p-4">
            <h6> Erreur manque </h6>
            <% if(result.size() > 0) { %>
             <table border="1" class="table table-bordered">
                <thead>
                    <tr>
                        <th>Matiere</th>
                        <th>Quantiter</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map.Entry<String, Double> entry : result.entrySet()) { %>
                         <tr>
                            <td><%= entry.getKey() %></td>
                            <td><%= entry.getValue() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            <% }%>
        </div>
      <% } %>
</div>