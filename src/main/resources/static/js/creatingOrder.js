ymaps.ready(init);
var myMap;
var geoObjects;
var startCoords;
var destinationCoords;
var multiRoute;

function init() {
    myMap = new ymaps.Map("map", {
        center: [51.66149636, 39.20038956],
        zoom: 12,
        controls: []
    });

    //Создание поля для поиска обьекта
    // Создаем экземпляр класса ymaps.control.SearchControl
    mySearchControl = new ymaps.control.SearchControl({
        options: {
            noPlacemark: true
        }
    }),
        // Результаты поиска будем помещать в коллекцию.
        mySearchResults = new ymaps.GeoObjectCollection(null, {
            hintContentLayout: ymaps.templateLayoutFactory.createClass('$[properties.name]')
        });
    myMap.controls.add(mySearchControl);
    

    $.ajax({
        url: "http://localhost:8081/availableDriversJson"
    }).done(function (data) {
        geoObjects = ymaps.geoQuery(data)
            .addToMap(myMap);
    });
    // Обработка события, возникающего при щелчке
    // левой кнопкой мыши в любой точке карты.
    // пишем в поле местоположение клиента
    myMap.events.add('click', function (e) {
        startCoords = e.get('coords');
        $("#geoData").val(startCoords[0] + "," + startCoords[1]);
        var myGeocoder = ymaps.geocode(startCoords);
        myGeocoder.then(function (res) {
            $("#address").val(res.geoObjects.get(0).getAddressLine());//геокодируем координаты в адрес
        });
        if (destinationCoords) {
            myMap.geoObjects.remove(multiRoute);
            calculatePrice();
        }
    });


    // Обработка события, возникающего при щелчке
    // правой кнопки мыши в любой точке карты.
    // пишем в поле точку назначения
    myMap.events.add('contextmenu', function (e) {
        destinationCoords = e.get('coords');
        $("#destinationGeoData").val(destinationCoords[0] + "," + destinationCoords[1]);
        myMap.geoObjects.remove(multiRoute);
        calculatePrice();
    });
    document.getElementById('chooseDriver').onclick = function () {
        // Дождемся ответа от сервера и получим объект, ближайший к точке.
        geoObjects.then(function () {
            var closestObject = geoObjects.getClosestTo(startCoords);
            // Если ответ пуст, то ближайший объект не найдется.
            if (closestObject) {
                document.getElementById('driverSelect').value = closestObject.properties.get('driverId');
                $("driverId").val(closestObject.properties.get('driverId'));//ставим водителя на заказ
            }

        });

    };
    // };
    document.getElementById('driverSelect').onchange = function () {
        //ищем в коллекции geoObject выбранного водилу, он теперь клозестОбжект, дальше по накатанной
        var targetObject;
        geoObjects.each(function (geoObject) {
            if (geoObject.properties.get('driverId') == document.getElementById('driverSelect').value) {
                targetObject = geoObject;
            }
        })
            .then(function () {
                $("driverId").val(targetObject.properties.get('driverId'));//ставим водителя на заказ
                //calculatePrice();
            });
    };

    function calculatePrice() {
        multiRoute = new ymaps.multiRouter.MultiRoute({
            // Описание опорных точек мультимаршрута.
            referencePoints: [
                startCoords,
                destinationCoords
            ],
            params: {
                // Ограничение на максимальное количество маршрутов, возвращаемое маршрутизатором.
                results: 1
            }
        });
        myMap.geoObjects.add(multiRoute);

        multiRoute.events.once('activeroutechange', function () {
            distance = multiRoute.getRoutes().get(0).properties.get('distance').value;
            console.log(distance);
            $("#orderCost").val((500 + distance / 1000 * 50).toFixed(0))
        });
    }
}

function catcher() {
    var data = {
        clientFirstName: $('#clientFirstName').val(),
        clientLastName: $('#clientLastName').val(),
        clientPhoneNumber: $('#clientPhoneNumber').val(),
        orderCost: $('#orderCost').val(),
        address: $('#address').val(),
        geoData: $('#geoData').val(),
        destinationGeoData: $('#destinationGeoData').val(),
        driverId: $('#driverSelect').val()
    };
    $.ajax({
        data: data,
        url: "http://localhost:8081/orders/create",
        type: 'POST'
    }).done(
        function () {
            console.log("succes");
            showPopUp("succes");
            hidePopUp(2000);
            window.location.replace("http://localhost:8081/orders");
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