

<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="utilities.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Facture> factures = BddObject.findByOrder(new Facture(), "date", Ordering.DESC, null);
%>


<div class="col-sm-12 col-md-8 col-xl-8 mt-5 mx-auto ml-auto">
    <div class="h-100 bg-light rounded p-4">
        <div class="d-flex flex-row justify-content-between mb-4">
            
            <h6 class="mb-0"> La liste des factures enregistrees : </h6>
           
        </div>
       
        <div style="width: 100%">
            <table border="1" class="table table-lg table-bordered">
                <thead>
                    <tr>
                        <th> Date </th>
                        <th> Facture N' </th>
                        <th> Client </th>
                        <th> Montant </th>
                        <th> Status </th>
                        <th>  </th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Facture p : factures){ %>
                    <tr>
                        <td> <%= p.getDate() %> </td>
                        <td>  <%= p.getIdFacture() %> </td>
                        <td> <%= p.getClient().getNom() %> </td>
                        <td> Ar <b><%= p.getMontantTotal() %></b>  </td>
                        <th> <%= p.myStatus() %> </th>
                        <td class="text-center">
                            <a href="home.jsp?page=detail_paiement&&id=<%= p.getIdFacture() %>">
                                <i class="fa fa-eye me-2"> </i>
                            </a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>