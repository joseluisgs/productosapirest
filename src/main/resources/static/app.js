/**
 *
 */

$(document).ready(function () {

    $.ajax({
        url: 'http://localhost:8080/api/productos/',
        type: 'GET',
        success: function (data) {
            var html = "";
            $.each(data, function (index, value) {
                html += '<tr>';
                html += '<td>' + value.id + '</td>';
                html += '<td>' + value.nombre + '</td>';
                html += '<td>' + value.categoriaNombre + '</td>';
                html += '<td><img src=' + value.imagen + ' class="img-rounded" style="width:150px"></td>';
                html += '</tr>';
            });
            $("#productos-table-body").append(html);
        },
        error: function (error) {
            console.log("Error: " + error);
        }
    });

});