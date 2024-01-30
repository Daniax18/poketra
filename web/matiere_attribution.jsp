<%-- 
    Document   : matiere_attribution
    Created on : 12 déc. 2023, 12:37:24
    Author     : ITU
--%>

<%@page import="java.util.List"%>
<%@page import="model.Look_sac"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Look_sac> ls = (List<Look_sac>) request.getAttribute("looks");
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
                width: 50%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
            }

            button {
                width: 20%;
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
        <h2>Attribution look to matiere</h2>
        <form method="GET" action="MatiereForLookServlet">
            <select name = "idlook">
                <% for(Look_sac l : ls){ %>
                <option value="<%= l.getIdlook() %>">
                    <%= l.getNom_look() %>
                </option>
                <% } %>
                
            </select>
            <button type="submit" > Recherche </button>
        </form>
       <p><a href="index.html"> Acceuil </a></p>
    </body>
</html>
