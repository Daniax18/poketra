

<div class="col-sm-12 col-xl-6 mt-5 mb-5" style="margin-left: auto; margin-right: auto">
   <div class="bg-light rounded h-100 p-4">
        <h6 class="mb-4">Insertion Type de sac  </h6>
        <form action="InsertionTypeServlet" method="POST">
            <div class="form-floating mb-3">
                <input 
                    type="text" 
                    name="name" 
                    class="form-control" 
                    id="floatingInput"
                >
                <label for="floatingInput"> Type ... </label>
            </div>
            <div class="form-floating mb-3">
                <input type="number" name="valeur" class="form-control" id="floatingInput"
                    placeholder="name@example.com">
                <label for="floatingInput"> valeur : </label>
            </div>
            <button type="submit" class="btn btn-primary"> Enregistrer </button>
        </form>
    </div>
</div>