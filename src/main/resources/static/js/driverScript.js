window.onload = function () {
    init();
};

function init() {
    //проверка на то, что водител. не назначена машина
    if (selectedCarId == "") {
        document.getElementById('saveButton').disabled = false;
    };

    //если переключились на уже назначенную машину, то блочим возможность выбрать ее
    document.getElementById('carId').onchange = function () {

        if (document.getElementById('carId').value !== selectedCarId) {
            document.getElementById('saveButton').disabled = false;
        }
        else {
            document.getElementById('saveButton').disabled = true;
        }
    }
}