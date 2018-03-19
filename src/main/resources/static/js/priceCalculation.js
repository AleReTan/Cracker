ymaps.ready(init);

function init() {
    myMap = new ymaps.Map('map', {
        center: [51.66149636, 39.20038956],
        zoom: 12,
        controls: []
    });
    var geoObjects;//вначале инициализировать данные, а в аджаксе обновлять

    $.ajax({
        url: "json"
    }).done(addRoutes);

    document.getElementById('uploadButton').onclick = function () {
        var myPlacemark = new ymaps.Placemark([51.67079450, 39.18878232],
            {
                balloonContent: "Машина x111xx36",
                hintContent: "x111xx36"
            },
            {
                preset: 'islands#circleIcon',
                iconColor: '#3caa3c'
            });
        var myPlacemark1 = new ymaps.Placemark([51.67143491, 39.20929585],
            {
                balloonContent: "Машина x123xx36",
                hintContent: "x123xx36"
            },
            {
                preset: 'islands#circleIcon',
                iconColor: '#3caa8c'
            });
        myMap.geoObjects.add(myPlacemark);
        myMap.geoObjects.add(myPlacemark1);
        var route = new ymaps.route(
            [
                myPlacemark.geometry.getCoordinates(),
                myPlacemark1.geometry.getCoordinates()
            ]
        ).then(function (router) {
            route = router;
            myMap.geoObjects.add(route);
            console.log(route.getLength())
        }, function (err) {
            console.log('Ошибка: ' + err);
        });
    };
    var distance;//дистанция кратчайшая

    function addRoutes(data) {
        geoObjects = ymaps.geoQuery(data)
            .addToMap(myMap);


        // Дождемся ответа от сервера и получим объект, ближайший к точке.
        geoObjects.then(function () {
            var closestObject = geoObjects.getClosestTo([51.324523452, 39.4435324523]);
            // Если ответ пуст, то ближайший объект не найдется.
            if (closestObject) {
                newContent = closestObject.properties.get('balloonContent') + " Mth";
                closestObject.properties.set('balloonContent', newContent);
                closestObject.balloon.open();

                var multiRoute = new ymaps.multiRouter.MultiRoute({
                    // Описание опорных точек мультимаршрута.
                    referencePoints: [
                        closestObject.geometry.getCoordinates(),
                        [51.324523452, 39.4435324523]//эта точка - место заказа
                    ],
                    params: {
                        // Ограничение на максимальное количество маршрутов, возвращаемое маршрутизатором.
                        results: 1
                    }
                });

                multiRoute.events.once('activeroutechange', function () {
                    distance = multiRoute.getRoutes().get(0).properties.get('distance').value;
                    console.log(distance);
                });
            }

        });

        /*geoObjects.each(function (geoObject, index) {
            var multiRoute = new ymaps.multiRouter.MultiRoute({
                // Описание опорных точек мультимаршрута.
                referencePoints: [
                    geoObject.geometry.getCoordinates(),
                    [51.324523452, 39.4435324523]
                ],
                params: {
                    // Ограничение на максимальное количество маршрутов, возвращаемое маршрутизатором.
                    results: 1
                }
            });
            //multiRoute.setActiveRoute(multiRoute.getRoutes().get(0));
            console.log(multiRoute.getRoutes());
        });*/

    }

    /*   function addRoutes(data) {
           geoObjects = ymaps.geoQuery(data)
               .addToMap(myMap);
           var minimalPath = 9999999999999;

               geoObjects.each(function (geoObject, index) {
                   var route = new ymaps.route(
                       [
                           geoObject.geometry.getCoordinates(),
                           [51.324523452, 39.4435324523]
                       ]
                   ).then(function (router) {
                       route = router;
                       //myMap.geoObjects.add(route);
                       //console.log(route.getLength());
                       if (minimalPath > route.getLength()) {
                           minimalPath = route.getLength();
                       }
                       console.log(minimalPath + " в самом маршруте")
                   });
                   console.log(minimalPath + " после создания маршрута")
               });
       }*/
    /*
        function addRoutes(data) {
            geoObjects = ymaps.geoQuery(data)
                .addToMap(myMap);
            var arr = [];
            geoObjects
                .then(function (value) {
                    geoObjects.each(function (geoObject, index) {
                        var route = new ymaps.route(
                            [
                                geoObject.geometry.getCoordinates(),
                                [51.324523452, 39.4435324523]
                            ]
                        ).then(function (router) {
                            //route = router;
                            arr.push(router);

                        });
                    });
                })
                .then(function (value) {
                    console.log(arr)
                    var minRoute = arr[0];
                    for (var i = 0; i < arr.length; i++) {
                        if (minRoute.getLength() > arr[i].getLength()) {
                            //minimalPath = route.getLength();
                            minRoute = arr[i];
                        }
                    }
                    minimalPath = minRoute.getLength();
                    console.log(minimalPath);

                })
        }*/
}