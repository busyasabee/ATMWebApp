
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
                $("#atmTable #tableData").empty();
                
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Error deleting data: ", jqXHR.responseText);
            }
        });

    });

    $("#showBtn").on("click", function(){

        $.ajax({
            url: "/atmapp/show",
            type: "GET",

            success: function(data, textStatus, jqXHR) {
                console.log("GET SUCCESS");
                console.log(data);

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
                        "<div> <button class='updateBtn' name='updateBtn'>Обновить</button></div>" +
                        "</div>");

                });

                $("#gridMainDiv").css("display", "grid");

            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Error with showing data: ", jqXHR.responseText);
            }
        });

    });

    $("#gridDataDiv").on("click", "button", function () {
       console.log("Button parent");
       console.log($(this).parent());
       console.log($(this).parent().parent());
       var divGridRow = $(this).parent().parent();
       var atmId = divGridRow.find('.idDiv').val();
       var atmName = divGridRow.find('[name=atmName]').val();
       var beginDate = divGridRow.find('[name=beginDate]').val();
       var endDate = divGridRow.find('[name=endDate]').val();
       var workingStatus = divGridRow.find('[name=workingStatus]').val();
       var workingCost = divGridRow.find('[name=workCost]').val();
        console.log(atmId);
        console.log(atmName);
        console.log(beginDate);
        console.log(endDate);
        console.log(workingStatus);
        console.log(workingCost);
       // console.log($(this).parent().find('input[name=atmRepair]').val());
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
            // data: sendData,

            success: function(data, textStatus, jqXHR) {
                $("#resultDiv").html("<p> Данные были успешно обновлены </p> ");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $("#resultDiv").html(" <p> Произошла ошибка с изменением данных. Возможно введены неправильные данные </p> ");
                console.log("ERROR : ", jqXHR.responseText);

            }
        });

        console.log(id);
        console.log("Name " + atrName);
        console.log("Input");
        console.log($(this).val());
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
