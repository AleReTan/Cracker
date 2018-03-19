ymaps.ready(init);
var myMap;
var geoObjects;

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
    // При возникновении такого события откроем балун.
    myMap.events.add('click', function (e) {
        if (!myMap.balloon.isOpen()) {
            var coords = e.get('coords');
            myMap.balloon.open(coords, {
                contentHeader: 'Местоположение клиента',
                contentBody:
                '<p>Координаты: ' + [
                    coords[0].toPrecision(6),
                    coords[1].toPrecision(6)
                ].join(', ') + '</p>',
                contentFooter: '<sup>Щелкните еще раз</sup>'
            });

            //$('#maps').value(coords, null);
            $("#geo").val(coords[0].toPrecision(6) + "," + coords[1].toPrecision(6))
        }
        else {
            myMap.balloon.close();
        }
    });

    // Обработка события, возникающего при щелчке
    // правой кнопки мыши в любой точке карты.
    // При возникновении такого события покажем всплывающую подсказку
    // в точке щелчка.
    myMap.events.add('contextmenu', function (e) {
        myMap.hint.open(e.get('coords'), 'Кто-то щелкнул правой кнопкой');
    });

    // Скрываем хинт при открытии балуна.
    myMap.events.add('balloonopen', function (e) {
        myMap.hint.close();
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
            console.log(closestObject);
            // Если ответ пуст, то ближайший объект не найдется.
            if (closestObject) {
                //newContent = closestObject.properties.get('balloonContent') + " Mth";
                //closestObject.properties.set('balloonContent', newContent);
                closestObject.balloon.open();
                $("driverId").val(closestObject.properties.get('driverId'));//ставим водителя на заказ
                var multiRoute = new ymaps.multiRouter.MultiRoute({
                    // Описание опорных точек мультимаршрута.
                    referencePoints: [
                        closestObject.geometry.getCoordinates(),
                        orderGeo//эта точка - место заказа
                    ],
                    params: {
                        // Ограничение на максимальное количество маршрутов, возвращаемое маршрутизатором.
                        results: 1
                    }
                });
                //myMap.geoObjects.add(multiRoute);

                multiRoute.events.once('activeroutechange', function () {
                    distance = multiRoute.getRoutes().get(0).properties.get('distance').value;
                    console.log(distance);
                    $("#price").val((500 + distance / 1000 * 50).toFixed(0))
                });
            }

        });

    };
    // };
    document.getElementById('driverSelect').onchange = function () {
        //ищем в коллекции geoObject выбранного водилу, он теперь клозестОбжект, дальше по накатанной
        var targetObject;
        var orderGeoArr = document.getElementById('geo').value.split(',');
        var orderGeo = [parseFloat(orderGeoArr[0]), parseFloat(orderGeoArr[1])];

        geoObjects.each(function (geoObject) {
            if (geoObject.properties.get('driverId') == document.getElementById('driverSelect').value) {
                targetObject = geoObject;
            }
        })
            .then(function () {
                console.log(targetObject.properties.get('driverId'));
                targetObject.balloon.open();
                $("driverId").val(targetObject.properties.get('driverId'));//ставим водителя на заказ
                var multiRoute = new ymaps.multiRouter.MultiRoute({
                    // Описание опорных точек мультимаршрута.
                    referencePoints: [
                        targetObject.geometry.getCoordinates(),
                        orderGeo//эта точка - место заказа
                    ],
                    params: {
                        // Ограничение на максимальное количество маршрутов, возвращаемое маршрутизатором.
                        results: 1
                    }
                });
                //myMap.geoObjects.add(multiRoute);
                multiRoute.events.once('activeroutechange', function () {
                    distance = multiRoute.getRoutes().get(0).properties.get('distance').value;
                    console.log(distance);
                    $("#price").val((500 + distance / 1000 * 50).toFixed(0))
                });
            });
    };
}