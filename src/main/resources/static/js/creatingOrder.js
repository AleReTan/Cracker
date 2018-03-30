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

    $.ajax({
        url: "http://localhost:8080/availableDriversJson"
    }).done(function (data) {
        geoObjects = ymaps.geoQuery(data)
            .addToMap(myMap);
    });

    // Обработка события, возникающего при щелчке
    // левой кнопкой мыши в любой точке карты.
    // пишем в поле местоположение клиента
    myMap.events.add('click', function (e) {
        startCoords = e.get('coords');
        $("#geo").val(startCoords[0] + "," + startCoords[1]);
        var myGeocoder = ymaps.geocode(startCoords);
        myGeocoder.then(function (res) {
            $("#address").val(res.geoObjects.get(0).getAddressLine());//геокодируем координаты в адрес
        });

    });

    // Обработка события, возникающего при щелчке
    // правой кнопки мыши в любой точке карты.
    // пишем в поле точку назначения
    myMap.events.add('contextmenu', function (e) {
        destinationCoords = e.get('coords');
        $("#destinationGeo").val(destinationCoords[0] + "," + destinationCoords[1]);
        myMap.geoObjects.remove(multiRoute);
        calculatePrice();
    });

    document.getElementById('chooseDriver').onclick = function () {
        var orderGeoArr = document.getElementById('geo').value.split(',');
        var orderGeo = [parseFloat(orderGeoArr[0]), parseFloat(orderGeoArr[1])];
        /*
                $.ajax({
                    url: "json"
                }).done(findDriver);

                function findDriver(data) {
                    geoObjects = ymaps.geoQuery(data)
                        .addToMap(myMap);*/
        // Дождемся ответа от сервера и получим объект, ближайший к точке.
        geoObjects.then(function () {
            var closestObject = geoObjects.getClosestTo(orderGeo);
            // Если ответ пуст, то ближайший объект не найдется.
            if (closestObject) {
                document.getElementById('driverSelect').value = closestObject.properties.get('driverId');
                //newContent = closestObject.properties.get('balloonContent') + " Mth";
                //closestObject.properties.set('balloonContent', newContent);
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
                calculatePrice();
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
            $("#price").val((500 + distance / 1000 * 50).toFixed(0))
        });
    }
}