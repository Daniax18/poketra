

<%@page import="model.Look_sac"%>
<%@page import="java.util.List"%>
<%@page import="model.MatierePremiere"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<MatierePremiere> mps = (List<MatierePremiere>) request.getAttribute("matieres");
    List<Look_sac> looks = (List<Look_sac>) request.getAttribute("look");

%>

<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
     <div class="bg-light rounded h-100 p-4">
       <h6 class="mb-4"> Attribution style pour matiere : </h6>
       <form method="POST" action="AttributionLookServlet">
            <div class="form-floating mb-3">
                <select class="form-select" id="floatingSelect" name="idmatiere"
                    aria-label="Floating label select example">
                    <option selected> Liste des matieres </option>
                    <%
                        for(MatierePremiere mp : mps){ %>
                            <option value="<%= mp.getIdmatiere() %>">
                                <%= mp.getNom_matiere() %>
                            </option>
                        <% }
                    %>
                   
                </select>
                <label for="floatingSelect"> Choisir une matiere ici </label>
            </div>
            <div>
                <p>Les styles a choisir </p>
                <%
                    for(Look_sac ls : looks){ %>
                      <div class="form-check">
                          <input class="form-check-input" name="look" type="checkbox" value="<%= ls.getIdlook() %>" id="flexCheckDefault">
                            <label class="form-check-label">
                                <%= ls.getNom_look() %>
                            </label>
                        </div>
                    <% }
                %>
                
            </div>
            <button type="submit" class="btn btn-primary mt-4"> Attribuer </button>
        </form>
    </div>
</div>