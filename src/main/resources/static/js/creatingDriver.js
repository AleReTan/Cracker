window.onload = function () {
    init();
};

function init() {

}

function createDriver() {
    var dataUser = {
        login: $('#login').val(),
        password: $('#password').val(),
        role: 'DRIVER'
    };
    var dataDriver = {
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        phoneNumber: $('#phoneNumber').val(),
        carId: $('#carSelect').val(),
        login: $('#login').val()
    };

    $.ajax({
        data: dataUser,
        url: "http://localhost:8080/admin/users/createUser",
        type: 'POST'
    }).done(
        function () {
            console.log("successful user creating");
            $.ajax({
                data: dataDriver,
                url: "http://localhost:8080/drivers/create",
                type: 'POST'
            }).done(
                function () {
                    showPopUp("succes");
                    hidePopUp(2000);
                    window.location.replace("http://localhost:8080/admin");
                }
            ).fail(function (dataIn) {
                console.log("fail");
                console.log(dataIn.responseText);
                showPopUp(dataIn.responseText);
                hidePopUp(2000);
                $.ajax({
                    url: "http://localhost:8080/admin/users/" + $('#login').val(),
                    type: 'DELETE'
                });
            });
        }
    ).fail(
        function (dataIn) {
            console.log("fail");
            console.log(dataIn.responseText);
            showPopUp(dataIn.responseText);
            hidePopUp(2000);

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