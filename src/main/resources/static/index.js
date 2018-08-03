
var fileFormData;

jQuery(document).ready(function ($) {

    $("#uploadBtn").on("click", function () {
        event.preventDefault();
        $("input[type=file]").trigger("click");
    });

    $('input[type=file]').on('change', function () {
        if ($(this).val() != null ){
            var formElement = $("#fileForm")[0];
            fileFormData = new FormData(formElement);
            sendData();
            $(this).val(null);
        }
    });


    $("#deleteBtn").on("click", function(){

        $.ajax({
            url: "/atmapp/delete",
            type: "DELETE",

            success: function(data, textStatus, jqXHR) {
                $("#resultDiv").html(" <p> Данные были успешно удалены </p> ");
                
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Error deleting data: ", jqXHR.responseText);
            }
        });

    });

});

function sendData(){
    $.ajax({
        url: "/atmapp/upload",
        type: "POST",
        data: fileFormData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,

        success: function(data, textStatus, jqXHR) {
            $("#resultDiv").html(" <p> Загружено записей " + data + "</p> ");
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $("#resultDiv").html(" <p> Произошла ошибка с загрузкой данных. Возможно загружаются данные из одного файла несколько раз </p> ");
            console.log("ERROR : ", jqXHR.responseText);

        }
    });
}
