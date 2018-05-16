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
        url: front_url + "/admin/users/createUser",
        type: 'POST'
    }).done(
        function () {
            console.log("successful user creating");
            $.ajax({
                data: dataDriver,
                url: front_url + "/drivers/create",
                type: 'POST'
            }).done(
                function () {
                    showPopUp("succes");
                    hidePopUp(0);
                    window.location.replace(front_url + "/admin");
                }
            ).fail(function (dataIn) {
                console.log("fail");
                console.log(dataIn.responseText);
                showPopUp(dataIn.responseText);
                hidePopUp(0);
                $.ajax({
                    url: front_url + "/admin/users/" + $('#login').val(),
                    type: 'DELETE'
                });
            });
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