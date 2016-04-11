$(document).ready(function () {
    $("#numberOfThread").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            $("#numberOfThreadErrorMessage").html("<font color='red'>Digits Only</font>").show().fadeOut("slow");
            return false;
        }
    });

    $("#batchSize").keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            $("#batchSizeErrorMessage").html("<font color='red'>Digits Only</font>").show().fadeOut("slow");
            return false;
        }
    });


});

function fullReindexing(){
    var numberOfThread = $('#numberOfThread').val();
    var batchSize = $('#batchSize').val();
    if (numberOfThread !== '' && batchSize !== '' && batchSize%1000 ===0 && batchSize <= 1000) {
        var requestObject = {};
        requestObject['numberOfThread'] = numberOfThread;
        requestObject['batchSize'] = batchSize;
        $("#numberOfThread").attr('disabled', 'disabled');
        $("#batchSize").attr('disabled', 'disabled');
        $("#fullReIndexingSubmit").attr('disabled', 'disabled');
        $.ajax({
            type: "POST",
            url: "rest/index/fullReIndex",
            contentType: 'application/json',
            data: JSON.stringify(requestObject),
            dataType: "json",
            success: function (data) {
                $("#numberOfThread").removeAttr('disabled');
                $("#batchSize").removeAttr('disabled');
                $("#fullReIndexingSubmit").removeAttr('disabled');
                alert("Success");
            }
        });
    } else {
        alert("Please enter value for batch size and number of thread. Number of thread should be multiple of 1000 and should not exceed 10000");
    }
}