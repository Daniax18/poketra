

<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="mapping.BddObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String idpoketra = (String) request.getParameter("idpoketra");
    Poketra poketra = new Poketra();
    poketra.setIdPoketra(idpoketra);
    poketra = BddObject.findById(poketra, null);
    double[] result = poketra.mydata(null);
    String jsonDataString = new Gson().toJson(result);
%>

<div class="container-fluid pt-4 px-4">
    <div class="row g-4">
        <div class="col-sm-12 col-xl-12">                       
            <div class="bg-light rounded h-100 p-4">
                <h6 class="mb-4">Statistique par genre du poketra " <%= poketra.getNomPoketra() %> " : " <%= poketra.toString() %> "</h6>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Women</th>
                            <th scope="col">Men</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">Nombre</th>
                            <td><%= result[0] %></td>
                            <td><%= result[1] %></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <canvas id="myDonutChart"></canvas>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var ctx = document.getElementById('myDonutChart').getContext('2d');
        var myDonutChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['Femme', 'Homme'],
                datasets: [{
                    label: '# of Ventes',
                    data: <%= jsonDataString %>,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Ratio vente par genre'
                    }
                }
            }
        });
    });
</script>