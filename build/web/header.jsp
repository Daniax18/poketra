<%-- 
    Document   : header
    Created on : 15 mars 2023, 01:45:12
    Author     : rango
--%>
<%@page import="mapping.*"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        List<Poketra> poketras = BddObject.find(new Poketra(), null);
        List<MatierePremiere> mps = BddObject.find(new MatierePremiere(), null);  
%>

<div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
        <span class="sr-only">Loading...</span>
    </div>
</div>
        <!-- Spinner End -->


<!-- Sidebar Start -->
<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-light navbar-light">
        <a href="home.jsp" class="navbar-brand mx-4 mb-3">
            <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>PROJET</h3>
        </a>
        <div class="navbar-nav w-100">
            <a href="#" class="nav-item nav-link active"><i class="fa fa-folder me-2"></i>Dashboard</a>
            
            <!-- ACHAT -->
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-shopping-cart me-2"></i>Vente</a>
                <div class="dropdown-menu bg-transparent border-0">
                    <a 
                        href="home.jsp?page=insert_client" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Inserer Client
                    </a>
                    
                    <a 
                        href="home.jsp?page=insert_facture" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Nouvelle facture
                    </a>        
                    <a 
                        href="home.jsp?page=liste_factures" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Facturation
                    </a> 
                    
                    <a 
                        href="home.jsp?page=liste_paiement" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Paiements
                    </a> 
                    
                    <a 
                        data-bs-toggle="modal" 
                        data-bs-target="#genre" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Par genre
                    </a>
                </div>
            
             <!-- ACHAT -->
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-get-pocket me-2"></i>Achat</a>
                <div class="dropdown-menu bg-transparent border-0">
                    <a 
                        href="home.jsp?page=insert_fournisseur" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Inserer Fournisseur
                    </a>
                    
                    <a 
                        href="home.jsp?page=achat_matiere" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Achat Matiere Premiere
                    </a>               
                </div>
                
                  <!-- STOCK -->            
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-dashboard me-2"></i>Etat de stock</a>
                <div class="dropdown-menu bg-transparent border-0">                   
                    <a 
                        href="home.jsp?page=entrer_matiere" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Entrer de MP
                    </a>
                    <a 
                        href="home.jsp?page=fabrication_poketra" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i>
                        Fabrication poketra 
                    </a>
                    <a 
                        href="home.jsp?page=matiere_restant" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Stock mp 
                    </a>
                    <a 
                        href="home.jsp?page=stock_poketra" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Stock poketra 
                    </a>
                </div>
            </div>
            
                  <br>
                  
                  
            <!-- MATIERE PREMIERE -->
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-chain me-2"></i>Matiere Premiere</a>
                <div class="dropdown-menu bg-transparent border-0">
                    <a 
                        href="home.jsp?page=insert_matiere" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Insertion
                    </a>
                    
                    <a 
                        href="AjoutLook" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i>
                        Attribution -> Style 
                    </a>
                    
                    <a 
                        data-bs-toggle="modal" 
                        data-bs-target="#matiere" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        MP for Poketra
                    </a>

                </div>
            </div>
            
            <!-- POKETRA -->
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-product-hunt me-2"></i>Produits</a>
                <div class="dropdown-menu bg-transparent border-0">
                    <a 
                        href="home.jsp?page=new_poketra" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Nouveau poketra
                    </a>
                    
                    <a 
                        data-bs-toggle="modal" 
                        data-bs-target="#produire" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Mode fabrication
                    </a>
                    <a 
                        href="home.jsp?page=prix_vente" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Prix de vente
                    </a>
                    <a 
                        href="home.jsp?page=benefice_rate" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Taux benefice
                    </a>
                    
                </div>
            </div>
            
          
            
            
            <!-- EMPLOYE -->
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-users me-2"></i>Employee</a>
                <div class="dropdown-menu bg-transparent border-0">
                    <a 
                        href="home.jsp?page=insert_employer" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> Insertion
                    </a>
                    <a 
                        href="home.jsp?page=insert_poste" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> Nouveau poste
                    </a>
                    <a 
                        href="home.jsp?page=minimum_emp" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Insert emp / taille
                    </a>
                    <a 
                        href="home.jsp?page=heure_sac" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Insert horaire / type
                    </a>
                    <a 
                        href="home.jsp?page=taux_horiare" 
                        class="dropdown-item"
                    > <i class="fa fa-chevron-right me-2"></i> 
                        Insert taux horaire
                    </a>
                </div>
            </div>
            
            <!-- Recherche -->
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-search me-2"></i>Recherche</a>
                <div class="dropdown-menu bg-transparent border-0">
                    <a 
                        data-bs-toggle="modal" 
                        data-bs-target="#benefice" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Benefice entre 2 prix
                    </a>     
                     <a 
                        data-bs-toggle="modal" 
                        data-bs-target="#prix_revient" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        P.Revient entre 2 prix
                    </a>     
                </div>
            </div>
            
            <!-- Employer profil -->            
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-users me-2"></i>Employe profil</a>
                <div class="dropdown-menu bg-transparent border-0">                  
                    <a 
                        href="home.jsp?page=employer_profil" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Entrer le profil de l'employer
                    </a>
                    <a 
                        href="home.jsp?page=th_minimum" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i>
                        Taux d'horaire minimum
                    </a>
                    <a 
                        href="home.jsp?page=liste_employer" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Liste des employer 
                    </a>
                    <a 
                        href="home.jsp?page=valeur_profil" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Valeur profil 
                    </a>
                </div>
            </div>
                  
            <!-- PARAMETRE -->
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-file me-2"></i>Parametre</a>
                <div class="dropdown-menu bg-transparent border-0">
                    <a 
                        href="home.jsp?page=insert_look_sac" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Inserer Look Sac
                    </a>
                    
                    <a 
                        href="home.jsp?page=insert_type_sac" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Inserer Type de sac 
                    </a>
                    
                    <a 
                        href="home.jsp?page=insert_taille" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Inserer Taille
                    </a>
                    
                    <a 
                        href="home.jsp?page=insert_remise" 
                        class="dropdown-item"
                    > 
                        <i class="fa fa-chevron-right me-2"></i> 
                        Inserer Remise
                    </a>
                </div>
            </div>
        </div>
    </nav>
</div>
<!-- Sidebar End -->


<!-- Content Start -->
<div class="content">
    <!-- Navbar Start -->
    <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
        <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
            <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
        </a>
        <a href="#" class="sidebar-toggler flex-shrink-0">
            <i class="fa fa-bars"></i>
        </a>
        <form class="d-none d-md-flex ms-4">
            <input class="form-control border-0" type="search" placeholder="Search">
        </form>
        <div class="navbar-nav align-items-center ms-auto">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                    <img class="rounded-circle me-lg-2" src="assets/img/user.jpg" alt="" style="width: 40px; height: 40px;">
                    <span class="d-none d-lg-inline-flex"> Antema </span>
                </a>
                <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                    <a href="#" class="dropdown-item">Settings</a>
                    <a href="#" class="dropdown-item">Log Out</a>
                </div>
            </div>
        </div>
    </nav>
    <!-- Navbar End -->
    
   <!-- MODAL MATIERE PREMIERE FOR POKETRA -->
    <div class="modal fade" id="matiere" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content" >
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalCenterTitle"> Choisir parmi matiere_premieres : <b> </b>  </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="btn-close"></button>
            </div>
              
            <form action="home.jsp" method="GET">
                <input type="hidden" name="page" value="poketra_by_matiere">
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <select class="form-select" id="floatingSelect" name="idmatiere"
                            aria-label="Floating label select example">
                            <option selected>  </option>
                            <%
                                for(MatierePremiere mp : mps){ %>
                                    <option value="<%= mp.getIdmatiere() %>">
                                        <%= mp.getNom_matiere() %>
                                    </option>
                                <% }
                            %>
                        </select>
                        <label for="floatingSelect"> Choisir ici ... </label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"> Recherche </button>
                </div>
            </form>
        </div>
      </div>
    </div>

    
   <!-- MODAL MODE FABRICATION PRODUIT -->
    <div class="modal fade" id="produire" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content" >
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalCenterTitle"> Choisir parmi les poketra : <b> </b>  </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="btn-close"></button>
            </div>
              
            <form action="home.jsp" method="GET">
                <input type="hidden" name="page" value="detail_poketra">
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <select class="form-select" id="floatingSelect" name="idpoketra"
                            aria-label="Floating label select example">
                            <option selected>  </option>
                            <%
                                for(Poketra poketra : poketras){ %>
                                    <option value="<%= poketra.getIdPoketra() %>">
                                       <%= poketra.getNomPoketra() %> ( <%= poketra.toString() %> )
                                    </option>
                                <% }
                            %>
                        </select>
                        <label for="floatingSelect"> Choisir ici ... </label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"> Detail </button>
                </div>
            </form>
        </div>
      </div>
    </div>
      
    <!-- MODAL RECHERCHE BENEFICE ENTRE 2 PRIX -->
    <div class="modal fade" id="benefice" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content" >
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalCenterTitle"> Recherche benefice entre : <b> </b>  </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="btn-close"></button>
            </div>
              
            <form method="GET">
                <input type="hidden" name="page" value="resultat_benefice">
                    <div class="modal-body">

                        <div class="form-floating mb-3">
                                   <input type="text" name="min" class="form-control" id="floatingInput"
                                       placeholder="name@example.com">
                                   <label for="floatingInput"> Prix min </label>
                        </div>
                        <div class="form-floating mb-3">
                                   <input type="text" name="max" class="form-control" id="floatingInput"
                                       placeholder="name@example.com">
                                   <label for="floatingInput"> Prix max </label>
                        </div>
                    </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"> Recherche </button>
                </div>
            </form>
        </div>
      </div>
    </div>
    
    <!-- MODAL RECHERCHE PRIX DE REVIENT ENTRE 2 PRIX -->
    <div class="modal fade" id="prix_revient" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content" >
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalCenterTitle"> Recherche Prix de revient entre : <b> </b>  </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="btn-close"></button>
            </div>
              
            <form method="GET">
                <input type="hidden" name="page" value="resultat_prix">
                    <div class="modal-body">

                        <div class="form-floating mb-3">
                                   <input type="text" name="min" class="form-control" id="floatingInput"
                                       placeholder="name@example.com">
                                   <label for="floatingInput"> Prix min </label>
                        </div>
                        <div class="form-floating mb-3">
                                   <input type="text" name="max" class="form-control" id="floatingInput"
                                       placeholder="name@example.com">
                                   <label for="floatingInput"> Prix max </label>
                        </div>
                    </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"> Recherche </button>
                </div>
            </form>
        </div>
      </div>
    </div>
    
    <!-- MODAL GENRE -->
    <div class="modal fade" id="genre" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content" >
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalCenterTitle"> Choisir parmi les poketra : <b> </b>  </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="btn-close"></button>
            </div>
              
            <form action="home.jsp" method="GET">
                <input type="hidden" name="page" value="genre">
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <select class="form-select" id="floatingSelect" name="idpoketra"
                            aria-label="Floating label select example">
                            <option selected>  </option>
                            <%
                                for(Poketra poketra : poketras){ %>
                                    <option value="<%= poketra.getIdPoketra() %>">
                                       <%= poketra.getNomPoketra() %> ( <%= poketra.toString() %> )
                                    </option>
                                <% }
                            %>
                        </select>
                        <label for="floatingSelect"> Choisir ici ... </label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"> Detail </button>
                </div>
            </form>
        </div>
      </div>
    </div>
      
    