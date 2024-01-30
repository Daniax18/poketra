

<%@page import="mapping.BddObject"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Remise> remises = BddObject.find(new Remise(), null);
%>


<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
    <div class="bg-light rounded h-100 p-4">
        <h6 class="mb-4">Insertion Remise </h6>
        <form method="POST" action="InsertionRemiseServlet">
            <div class="form-floating mb-3">
                <input type="text" name="name" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Nom Remise </label>
            </div>
            <button type="submit" class="btn btn-primary"> Enregistrer </button>
        </form>
    </div>
    
    <div class="mt-3">
        <h5 class="mb-3"> La liste de nos remises </h5>
         <table border="1" class="table table-bordered">
            <thead>
                <tr>
                    <th> Nom de la remise </th>
                    <th> Valeur </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if(remises != null){
                        for(Remise p : remises){ %>
                            <tr>
                                <td> <%= p.getNomRemise() %> </td>
                                <td class="text-end"> <%= p.myValueRemise(null) %> % </td>
                                <td class="text-center" style="width: 10%"> 
                                    <a 
                                        data-bs-toggle="modal" 
                                        data-bs-target="#modify<%= p.getIdRemise() %>" 
                                        <i class="fa fa-pencil me-2"></i>
                                    </a>
                                </td>
                             </tr>
                                 <!-- MODAL MODIFICATION REMISE -->
                                <div class="modal fade" id="modify<%= p.getIdRemise() %>" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                  <div class="modal-dialog modal-dialog-centered">
                                      <div class="modal-content" >
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalCenterTitle"> Modifier la valeur de la remise "<b> <%= p.getNomRemise() %>" </b>  </h5>
                                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="btn-close"></button>
                                        </div>

                                          <form method="POST" action="UpdateRemiseServlet">
                                            <input type="hidden" name="idremise" value="<%= p.getIdRemise() %>">
                                                <div class="modal-body">

                                                    <div class="form-floating mb-3">
                                                        <input type="text" name="valeur" class="form-control" id="floatingInput"
                                                            placeholder="name@example.com">
                                                        <label for="floatingInput"> Taux </label>
                                                    </div>

                                                </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-primary"> Recherche </button>
                                            </div>
                                        </form>
                                    </div>
                                  </div>
                                </div>
                        <% } 
                    } %>
            </tbody>
        </table>
    </div>
    
</div>