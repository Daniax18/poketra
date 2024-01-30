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
    List<Paiement> paiements = facture.getPaiements();
    double[] result = facture.paiementAndReste(null);
    String error = (String) request.getAttribute("error");
%>

<div class="col-sm-12 col-md-12 col-xl-12 mt-5 mx-auto ml-auto">
    
    <div class="d-flex flex-row justify-content-between">
        <div style="width: 49%">
            <div class="h-100 bg-light rounded p-4">
                <div class="d-flex flex-row justify-content-between mb-4">
                    <div>
                        <h4 class="mb-0"> Detail de la facture : <%= facture.getIdFacture() %>  </h4>
                        <h6 class="mb-0"> Status : <%= facture.myStatus() %>  </h6>
                    </div>
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
                </div>
            </div>
        </div>
        
        <div style="width: 49%">
            <div class="h-100 bg-light rounded p-4">
                <div class="d-flex flex-row justify-content-between mb-4">
                    <div>
                        <h4 class="mb-0"> Paiements recus :  </h4>
                    </div>
                    
                     <% if(facture.getStatus() == 1 ){ %>
                        <button 
                            data-bs-toggle="modal"
                            data-bs-target="#add" 
                            class="btn btn-primary"
                        >
                            Ajouter paiement
                        </button>
                    <% } %>
                </div>

                <div style="width: 100%">
                    <table border="1" class="table table-lg table-bordered">
                        <thead>
                            <tr>
                                <th> Date </th>
                                <th> Montant recu  </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                if(paiements != null){
                                 for(Paiement paiement : paiements){ %>
                                    <tr>
                                        <td> <%= paiement.getDatePaiement() %> </td>
                                        <td class="text-end"> Ar <b><%= paiement.getMontant() %></b>  </td>
                                    </tr>
                                    <% }
                                    }
                                %>
                            <tr class="text-end">
                                <td> Total recus </td>
                                <td> Ar <%= result[0] %> </td>
                            </tr>
                            <tr class="text-end">
                                <td> Reste a payer  </td>
                                <td> Ar <b> <%= result[1] %> </b> </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                    
                <% if(error != null){ %>
                    <div>
                        <span style="color: red"> <%= error %> </span>
                    </div>
                <% } %>
                <a href="home.jsp?page=detail_paiement&&id=<%= facture.getIdFacture() %>">
                    <button class="btn btn-outline-secondary">
                         Refresh
                    </button>
                </a>
            </div>
        </div>
    </div>
</div>
                    

<!-- MODAL AJOUT PAIEMENT -->
    <div class="modal fade" id="add" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content" >
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalCenterTitle"> Paiement de la facture : <b> </b>  </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="btn-close"></button>
            </div>
              
            <form action="InsertionPaiement" method="POST">
                <input type="hidden" name="id" value="<%= facture.getIdFacture() %>">
                <div class="modal-body">    
                    <div class="form-floating mb-3">
                       <input type="date" name="date" class="form-control" id="floatingInput"
                           placeholder="name@example.com">
                       <label for="floatingInput"> Date paiement </label>
                   </div>
                    <div class="form-floating mb-3">
                       <input type="text" name="montant" class="form-control" id="floatingInput"
                           placeholder="name@example.com">
                       <label for="floatingInput"> Montant </label>
                   </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"> Enregistrer </button>
                </div>
            </form>
        </div>
      </div>
    </div>