

<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="mapping.BddObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<MatierePremiere> mps = BddObject.find(new MatierePremiere(), null);
    List<Taille_sac> tailles = BddObject.find(new Taille_sac(), null);
    List<Type_sac> types = BddObject.find(new Type_sac(), null);
    List<Look_sac> looks = BddObject.find(new Look_sac(), null);
    
%>

<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
     <div class="bg-light rounded h-100 p-4">
       <h6 class="mb-4"> Nouveau model de Poketra : </h6>
       <form method="POST" action="NewPoketraServlet">
           
             <div class="form-floating mb-3">
                <input type="text" name="name" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Nom ... </label>
            </div>
           
                   
            <div class="form-floating mb-3">
                <select class="form-select" id="floatingSelect" name="type"
                    aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(Type_sac type : types){ %>
                            <option value="<%= type.getId() %>">
                                <%= type.getNom() %>
                            </option>
                        <% }
                    %>
                   
                </select>
                <label for="floatingSelect"> Choisir le type : </label>
            </div>
                    
            <div class="form-floating mb-3">
                <select class="form-select" id="floatingSelect" name="look"
                    aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(Look_sac ls : looks){ %>
                            <option value="<%= ls.getIdlook() %>">
                                <%= ls.getNom_look() %>
                            </option>
                        <% }
                    %>
                   
                </select>
                <label for="floatingSelect"> Choisir un look ici </label>
            </div>
                    
            <div class="form-floating mb-3">
                <select class="form-select" id="floatingSelect" name="taille"
                    aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(Taille_sac taille : tailles){ %>
                            <option value="<%= taille.getId() %>">
                                <%= taille.getNom() %>
                            </option>
                        <% }
                    %>
                   
                </select>
                <label for="floatingSelect"> Choisir sa taille </label>
            </div>
                    
            <div>
                <p>Les matieres a choisir </p>
                <%
                    for(MatierePremiere mp : mps){ %>
                      <div class="form-check">
                          <input class="form-check-input" name="matiere" type="checkbox" value="<%= mp.getIdmatiere() %>" id="flexCheckDefault">
                            <label class="form-check-label">
                                <%= mp.getNom_matiere() %>
                            </label>
                        </div>
                    <% }
                %>
                
            </div>
                    
            
            <button type="submit" class="btn btn-primary mt-4"> Attribuer </button>
        </form>
    </div>
</div>