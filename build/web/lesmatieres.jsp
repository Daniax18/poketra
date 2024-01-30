<%-- 
    Document   : lesmatieres
    Created on : 12 déc. 2023, 14:30:08
    Author     : rango
--%>



<%@page import="java.util.List"%>
<%@page import="model.MatierePremiere"%>
<%@page import="model.Look_sac"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Look_sac ls = (Look_sac) request.getAttribute("look");
    List<MatierePremiere> mat = (List<MatierePremiere>) request.getAttribute("matieres");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Les matieres pour: <%= ls.getNom_look() %> </h1>
        
        <%
            for(MatierePremiere mp : mat){ %>
                <p>- <%= mp.getNom_matiere() %></p>
            <% }
        %>
        <a href="index.html"> Acceuil </a>
    </body>
</html>
