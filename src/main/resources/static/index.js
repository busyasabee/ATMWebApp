
var fileFormData;

jQuery(document).ready(function ($) {

    $("#uploadBtn").on("click", function () {
        event.preventDefault();
        $("input[type=file]").trigger("click");
    });

    $('input[type=file]').on('change', function () {
        var formElement = $("#fileForm")[0];
        fileFormData = new FormData(formElement);
        console.log("prepared Form");
        console.log(fileFormData.get("file"));

        $.ajax({
            url: "/atmapp/upload",
            type: "POST",
            data: fileFormData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,

            success: function(data, textStatus, jqXHR) {

                var entriesCount = data;

                if (data != 0){
                    $("#resultDiv").html(" <p> Загружено записей " + entriesCount + "</p> ");
                } else {
                    $("#resultDiv").html(" <p> Произошла ошибка с загрузкой данных. Возможно загружаются данные из одного файла несколько раз </p> ");
                }

                console.log(data);

            },
            error: function(jqXHR, textStatus, errorThrown) {

                console.log("ERROR : ", jqXHR.responseText);

            }
        });
    });

    function prepareForm(event)
    {
        var files = event.target.files;
        console.log(files);
        var formElement = $("#fileForm")[0];
        fileFormData = new FormData(formElement);

        console.log(fileFormData.get("file"));

    }
});
