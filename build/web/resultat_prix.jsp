

<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String prix1 = (String) request.getParameter("min");
    String prix2 = (String) request.getParameter("max");
    List<Poketra> poketras = Poketra.all_poketra_between(prix1, prix2, null);
%>


<div class="col-sm-12 col-md-6 col-xl-4 mt-5 mx-auto ml-auto">
    <div class="h-100 bg-light rounded p-4">
        <div class="d-flex flex-row justify-content-between mb-4">
             <div class="d-flex align-items-center justify-content-between mb-2">
            <h6 class="mb-0">Prix min : <%= prix1 %></h6>
            
            </div>
            <div class="d-flex align-items-center justify-content-between mb-2">
                <h6 class="mb-0">Prix max : <%= prix2 %></h6>
            </div>
        </div>
       
        <table border="1" class="table table-bordered">
            <thead>
                <tr>
                    <th>Poketra</th>
                    <th> Prix de fabrication </th>
                </tr>
            </thead>
            <tbody>
                <% for(Poketra p : poketras){ %>
                <tr>
                    <td> <%= p.getNomPoketra() %> </td>
                    <td> Ar <%= p.totalPriceFabrication(null) %> </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

    </div>
</div>