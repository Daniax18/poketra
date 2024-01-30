<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="utilities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String idfacture = (String) request.getParameter("id");
    Facture facture = new Facture();
    facture.setIdFacture(idfacture);
    facture = BddObject.findById(facture, null);
            
    List<FactureFille> filles = facture.getFilles();
    List<Poketra> poketras = BddObject.find(new Poketra(), null);
    String error = (String) request.getAttribute("error");
%>

<div class="col-sm-12 col-md-8 col-xl-8 mt-5 mx-auto ml-auto">
    <div class="h-100 bg-light rounded p-4">
        <div class="d-flex flex-row justify-content-between mb-4">
            <div>
                <h4 class="mb-0"> Detail de la facture : <%= facture.getIdFacture() %>  </h4>
                <h6 class="mb-0"> Status : <%= facture.myStatus() %>  </h6>
            </div>
              
            <% if(facture.getStatus() == 0 ){ %>
                <button 
                    data-bs-toggle="modal"
                    data-bs-target="#add" 
                    class="btn btn-primary"
                >
                    Ajouter produit
                </button>
            <% } %>
        </div>
       
        <div style="width: 100%">
            <table border="1" class="table table-lg table-bordered">
                <thead>
                    <tr>
                        <th> Poketra </th>
                        <th> PU </th>
                        <th> Qty </th>
                        <th class="text-end"> Total </th>
                    </tr>
                </thead>
                <tbody>
                    <% for(FactureFille p : filles){ %>
                    <tr>
                        <td> <%= p.getMy_poketra().getNomPoketra() %> </td>
                        <td>  <%= p.getPu() %> </td>
                        <td> <%= p.getQty() %> </td>
                        <td class="text-end"> Ar <b><%= p.getMontant() %></b>  </td>
                    </tr>
                    <% } %>
                    <tr class="text-end">
                        <td colspan="3"> Total remise </td>
                        <td> Ar <%= facture.getAccountGiven(null) %> </td>
                    </tr>
                    <tr class="text-end">
                        <td colspan="3"> Total facture </td>
                        <td> Ar <b> <%= facture.getMontantTotal() %> </b> </td>
                    </tr>
                </tbody>
            </table>
            
                    
            <% if(error != null){ %>
            <div class="mt-4 mb-4">
                <span style="color: red"> <%= error %> </span>
            </div>
            <% } %>        
           
                    
            <div class="d-flex flex-row justify-content-between mb-4 mt-4">
                 <% if(facture.getStatus() == 0 ){ %>
                    <form 
                        action="ValidateFactureServlet" 
                        method="POST"
                        style="width: 20%"
                    >
                       <input type="hidden" name="id" value="<%= facture.getIdFacture() %>">

                       <button 
                           class="btn btn-success"
                           type="submit"
                       >
                           Enregistrer
                       </button>
                    </form>
                <% } %>


                <a href="home.jsp?page=detail_facture&&id=<%= facture.getIdFacture() %>">
                    <button class="btn btn-outline-secondary">
                         Refresh
                     </button>
                </a>
            </div>
        </div>
    </div>
</div>
                    
    <!-- MODAL AJOUT PRODUIT -->
    <div class="modal fade" id="add" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content" >
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalCenterTitle"> Choisir parmi les poketra : <b> </b>  </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="btn-close"></button>
            </div>
              
            <form action="InsertionFactureFille" method="POST">
                <input type="hidden" name="id" value="<%= facture.getIdFacture() %>">
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <select class="form-select" id="floatingSelect" name="idpoketra"
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
                        <label for="floatingSelect"> Choisir ici ... </label>
                    </div>
                        
                    <div class="form-floating mb-3">
                       <input type="text" name="qty" class="form-control" id="floatingInput"
                           placeholder="name@example.com">
                       <label for="floatingInput"> Quantity </label>
                   </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"> Enregistrer </button>
                </div>
            </form>
        </div>
      </div>
    </div>