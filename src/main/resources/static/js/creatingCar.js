window.onload = function () {
    init();
};

function init() {

}

function createCar() {
    var data = {
        number: $('#number').val(),
        model: $('#model').val(),
        color: $('#color').val(),
        type: $('#type').val()
    };

    $.ajax({
        data: data,
        url: front_url + "/cars/create",
        type: 'POST'
    }).done(
        function () {
            console.log("successful car creating");
            showPopUp("succes");
            hidePopUp(0);
            window.location.replace(front_url + "/cars");
        }
    ).fail(
        function (dataIn) {
            console.log("fail");
            console.log(dataIn.responseText);
            showPopUp(dataIn.responseText);
            hidePopUp(0);

        }
    );
}

function showPopUp(content) {
    $('#popup-content').text(content);
    $('#popup').show();


}

function hidePopUp(time) {
    setTimeout(function () {
        $('#popup').hide(time)
    }, 5000);

}