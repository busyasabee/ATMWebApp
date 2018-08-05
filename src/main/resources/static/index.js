
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

            success: function() {
                $("#resultDiv").html(" <p> Данные были успешно удалены </p> ");
                $("#gridMainDiv").css("display", "none");
                
            }
        });

    });

    $("#showBtn").on("click", function(){

        $.ajax({
            url: "/atmapp/show",
            type: "GET",

            success: function(data) {
                console.log("GET SUCCESS");
                console.log(data);

                $("#resultDiv").html("<p></p> ");
                var gridDataDiv = $("#gridMainDiv #gridDataDiv");
                gridDataDiv.empty();

                $.each(data, function (key, atmRepair) {

                    var repairBeginDate = atmRepair.repairBeginDate;
                    if (repairBeginDate == null) {
                        repairBeginDate = "";
                    }

                    var repairEndDate = atmRepair.repairEndDate;
                    if (repairEndDate == null) {
                        repairEndDate = "";
                    }

                    gridDataDiv.append("<div class='gridRow'>" +
                        "<div class='idDiv' style='display: none'> " + atmRepair.id + "</div>" +
                        "<div> <input name='atm' type='text' value='" + atmRepair.atm + "'></div>" +
                        "<div> <input name='repairBeginDate' type='text' value='" + repairBeginDate + "'></div>" +
                        "<div> <input name='repairEndDate' type='text' value='" + repairEndDate + "'></div>" +
                        "<div> <input name='workingStatus' type='text' value='" + atmRepair.workingStatus + "'></div>" +
                        "<div> <input name='workCost' type='text' value='" + atmRepair.workCost + "'></div>" +
                        "</div>");

                });

                $("#gridMainDiv").css("display", "grid");

            },
            error:function () {
                $("#resultDiv").html(" <p> Нет данных для отображения </p> ");
            }
        });

    });

    $("#gridDataDiv").on("change", "input", function () {

        var divGridRow = $(this).parent().parent();
        var id = divGridRow.find('.idDiv').text();
        var atrName = $(this).attr('name');
        var atrValue = $(this).val();

        var sendData = {};
        sendData.id = id;
        sendData.atrName = atrName;
        sendData.atrValue = atrValue;

        $.ajax({
            url: "/atmapp/update?" + $.param(sendData),
            type: "POST",

            success: function() {
                $("#resultDiv").html("<p> Данные были успешно обновлены </p> ");
            },
            error: function() {
                $("#resultDiv").html(" <p> Произошла ошибка с изменением данных. Возможно введены неправильные данные </p> ");

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

        success: function(data) {
            $("#resultDiv").html(" <p> Загружено записей " + data + "</p> ");
        },
        error: function() {
            $("#resultDiv").html(" <p> Произошла ошибка с загрузкой данных. Возможно загружаются данные из одного файла несколько раз или данные в неправильном формате </p> ");
        }
    });
}
