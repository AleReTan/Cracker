$(document).ready(function () {
    $("#dlt").on("click", function (e) {
        if (!confirm("Are you sure continue?")) {
            e.preventDefault();
        }
    });
});