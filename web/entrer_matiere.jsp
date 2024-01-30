<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="mapping.BddObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<MatierePremiere> mps = BddObject.find(new MatierePremiere(), null);
    List<Fournisseur> frns = BddObject.find(new Fournisseur(), null);    
%>
<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
     <div class="bg-light rounded h-100 p-4">
       <h6 class="mb-4"> Entrer matiere : </h6>
       <form method="POST" action="Entrer_matiere_servelet">
                                                            
            <div class="form-floating mb-3">
                <select class="form-select" id="floatingSelect" name="matiere"
                    aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(MatierePremiere ls : mps){ %>
                            <option value="<%= ls.getIdmatiere() %>">
                                <%= ls.getNom_matiere() %>
                            </option>
                        <% }
                    %>
                   
                </select>
                    <label for="floatingSelect"> Matiere premiere : </label>
            </div>
                    
            <div class="form-floating mb-3">
                <select class="form-select" id="floatingSelect" name="frn" aria-label="Floating label select example">
                    <option selected>  </option>
                    <%
                        for(Fournisseur frn : frns){ %>
                            <option value="<%= frn.getIdFournisseur() %>">
                                <%= frn.getNomFournisseur() %>
                            </option>
                        <% }
                    %>
                </select>
                <label for="floatingSelect"> Fournisseur : </label>
            </div>
                    
            <div class="form-floating mb-3">
                <input type="text" name="quantiter" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Quantiter ... </label>
            </div>
                    
            
            <button type="submit" class="btn btn-primary mt-4"> Save </button>
        </form>
    </div>
</div>