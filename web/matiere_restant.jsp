<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="mapping.BddObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<MatierePremiere> mps = BddObject.find(new MatierePremiere(), null);
%>
<div class="h-100 bg-light rounded p-4">
    <table border="1" class="table table-bordered">
            <thead>
                <tr>
                    <th>Matiere</th>
                    <th>Quantiter</th>
                </tr>
            </thead>
            <tbody>
                <% for(MatierePremiere p : mps){ %>
                <tr>
                    <td> <%= p.getNom_matiere() %> </td>
                    <td> <%= p.myStock(null)  %> </td>
                    </tr>
                <% } %>
            </tbody>
    </table>
</div>