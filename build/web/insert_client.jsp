


<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
    <div class="bg-light rounded h-100 p-4">
        <h6 class="mb-4">Insert Nouveau client </h6>
        <form method="POST" action="InsertionClientServlet">
            <div class="form-floating mb-3">
                <input type="text" name="name" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Nom  </label>
            </div>
            
             <div class="form-floating mb-3">
                <input type="text" name="prenom" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Prenom  </label>
            </div>
            
             <div class="form-floating mb-3">
                <input type="date" name="dtn" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> Date de naissance  </label>
            </div>
            
             <div class="form-floating mt-3 mb-3">
                <select class="form-select" id="floatingSelect" name="genre"
                    aria-label="Floating label select example">
                    <option value="0"> Femme </option>
                    <option value="1"> Homme </option>
                </select>
                <label for="floatingSelect"> Choisir genre </label>
            </div>
            
            <button type="submit" class="btn btn-primary"> Enregistrer </button>
        </form>
    </div>
</div>