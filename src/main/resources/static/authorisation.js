$(document).ready(function () {
    $("#auth").on("click", function (e) {
        e.preventDefault();
        getAuth();
    });
    });

    function getAuth(){
        var data = {
            login : $('#login').val(),
            password :  $('#password').val(),
            role :  $('#role').val()
            };

        $.ajax(
            {
                data: JSON.stringify(data),
                contentType: "application/json",
                type: 'POST',
                url: '/login',
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true
            }
        ).done(
            function(data, textStatus, jqXHR) {
                var Url ="";
                if (data == "USER"){
                    Url = "/orders";
                }
                else if(data == "ADMIN") {
                    Url = "/orders";
                }

                window.location = Url;
            }
        ).fail(
            function(jqXHR, textStatus, errorThrown) {
                alert('Booh! Wrong credentials, try again!');
            }
        );

    }