<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        List<Poste> postes = BddObject.find(new Poste(), null); 
        List<Employer> emp = BddObject.find(new Employer(), null); 
%>
<div class="bg-light rounded h-100 p-4">
    <h6 class="mb-4">Liste Employer</h6>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Employer</th>
                <th scope="col">Embauche</th>
                <th scope="col">Profil</th>
                <th scope="col">Taux horaire</th>                                      
            </tr>
            </thead>
            <tbody>  
                <%
                    for(Employer emps : emp){ %>
                        <tr>
                            
                            <th><%= emps.getId() %></th>
                            <th><%= emps.getNom() %></th>
                            <th scope="row"><%= emps.getDateEmbauche() %></th>
                            <td><%= emps.getMyPoste(null).getNomPoste() %></td>
                            <td> Ar <%= emps.getMyPoste(null).getMyTauxHoraire(null) %></td>
                        </tr>
                    <% }
                %>                                   
            </tbody>
    </table>
</div>