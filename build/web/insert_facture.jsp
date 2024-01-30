

<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        List<Remise> remises = BddObject.find(new Remise(), null); 
        List<Client> clients = BddObject.find(new Client(), null); 
%>


<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
    <div class="bg-light rounded h-100 p-4">
        <h6 class="mb-4"> Creation nouvelle facture : </h6>
        <form method="POST" action="InsertionFactureServlet">
            
            <div class="form-floating mt-3 mb-3">
                <select class="form-select" id="floatingSelect" name="client"
                    aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(Client client : clients){ %>
                            <option value="<%= client.getIdClient() %>">
                               <%= client.getNom() %>
                            </option>
                        <% }
                    %>
                </select>
                <label for="floatingSelect"> Choisir client </label>
            </div>
                
            <div class="form-floating mt-3 mb-3">
                <select class="form-select" id="floatingSelect" name="remise"
                    aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(Remise remise : remises){ %>
                            <option value="<%= remise.getIdRemise() %>">
                               <%= remise.getNomRemise() %>
                            </option>
                        <% }
                    %>
                </select>
                <label for="floatingSelect"> Choisir remise </label>
            </div>
            
            <div class="form-floating mb-3">
                <input type="date" name="date" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Date de la facture : </label>
            </div>
            <button type="submit" class="btn btn-primary"> Enregistrer </button>
        </form>
    </div>
</div>