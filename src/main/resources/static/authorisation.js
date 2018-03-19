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
                    Url = "/drivers";
                }
                else if(data == "ADMIN") {
                    Url = "/admin";
                }

                window.location = Url;
            }
        ).fail(
            function(jqXHR, textStatus, errorThrown) {
                alert('Booh! Wrong credentials, try again!');
            }
        );
        if (!confirm("Are you sure continue?")) {
            e.preventDefault();
        }
    }