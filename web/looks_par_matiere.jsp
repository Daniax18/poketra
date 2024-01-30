<%-- 
    Document   : looks_par_matiere
    Created on : 12 déc. 2023, 12:50:57
    Author     : ITU
--%>

<%@page import="model.Look_sac"%>
<%@page import="java.util.List"%>
<%@page import="model.MatierePremiere"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<MatierePremiere> mts = (List<MatierePremiere>) request.getAttribute("matieres");
    List<Look_sac> looks = (List<Look_sac>) request.getAttribute("look");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }

            h1 {
                color: #333;
            }

            form {
                max-width: 400px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            label {
                display: block;
                margin-bottom: 8px;
                color: #333;
            }

            select, input[type="checkbox"], button {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
            }
            
            button {
                background-color: #4caf50;
                color: #fff;
                cursor: pointer;
            }

            button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <h1>Matieres pour chaque look</h1>
        <form action="AttributionLookServlet" method="post">
            <label for="categorie">Look:</label>
            <select name="idmatiere" id="categorie">
                <% for(MatierePremiere mp : mts){ %>
                <option value="<%= mp.getIdmatiere() %>">
                    <%= mp.getNom_matiere() %>
                </option>
                <% } %>
            </select>

            <% for(Look_sac ls : looks){ %>
                <label for="look"><%= ls.getNom_look() %></label>
               <input type="checkbox" name="look" value="<%= ls.getIdlook()%>">
            <% } %>
              <button type="submit">Attribuer</button>
        </form>
            <p><a href="index.html"> Acceuil </a></p>
    </body>
</html>

