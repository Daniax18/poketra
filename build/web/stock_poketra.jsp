<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="mapping.BddObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Poketra> poketras = BddObject.find(new Poketra(), null);
    
%>

<div class="col-sm-12 col-md-12 col-xl-12 mt-5 mx-auto ml-auto">
    
    <div class="bg-light rounded p-4 mt-3">
        <div>
            <h6> Etat de stock des poketra : </h6>
        </div>
        <table border="1" class="table table-bordered" style="width: 50%">
            <thead class="text-center">
                    <tr>
                        <th> Matiere </th>
                        <th> Reste en stock </th>
                        <th> Valeur </th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Poketra p : poketras){ 
                         double[] data = p.myStockAndValue(null);
                    %>
                    <tr>
                        <td> <%= p.getNomPoketra() %> </td>
                        <td class="text-end"> <%= data[0] %> </td>
                        <td class="text-end"> Ar <%= data[1] %> </td>
                        </tr>
                    <% } %>
                </tbody>
        </table>
    </div>
</div>