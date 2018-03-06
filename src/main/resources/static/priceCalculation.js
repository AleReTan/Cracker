ymaps.ready(init);

function init() {
    // Стоимость за километр.
    var DELIVERY_TARIFF = 20,
        // Минимальная стоимость.
        MINIMUM_COST = 500,
        myMap = new ymaps.Map('map', {
            center: [51.66149636, 39.20038956],
            zoom: 12,
            controls: []
        });
    var geoObjects;//вначале инициализировать данные, а в аджаксе обновлять

    function uploadData() {
        $.ajax({
            url: "json"
        }).done(function (data) {
            geoObjects = ymaps.geoQuery(data)
                .addToMap(myMap)
                .applyBoundsToMap(myMap, {
                    checkZoomRange: true
                });

        });
    }

    uploadData();
    console.log(geoObjects);

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
        var multiRoute = new ymaps.multiRouter.MultiRoute({
            // Описание опорных точек мультимаршрута.
            referencePoints: [
                myPlacemark.geometry.getCoordinates(),
                myPlacemark1.geometry.getCoordinates()
            ],
            // Параметры маршрутизации.
            params: {
                // Ограничение на максимальное количество маршрутов, возвращаемое маршрутизатором.
                results: 2
            }
        }, {
            // Автоматически устанавливать границы карты так, чтобы маршрут был виден целиком.
            boundsAutoApply: true
        });
        // Добавляем мультимаршрут на карту.
        myMap.geoObjects.add(multiRoute);
    };

}