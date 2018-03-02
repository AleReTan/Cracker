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
        }),
        // Создадим панель маршрутизации.
        /*routePanelControl = new ymaps.control.RoutePanel({
            options: {
                // Добавим заголовок панели.
                showHeader: true,
                title: 'Расчёт доставки'
            }
        });*/
        zoomControl = new ymaps.control.ZoomControl({
            options: {
                size: 'small',
                float: 'none',
                position: {
                    bottom: 145,
                    right: 10
                }
            }
        });

    // Пользователь сможет построить только автомобильный маршрут.
   /* routePanelControl.routePanel.options.set({
        types: {auto: true},
        allowSwitch: false,
        //превращение координат в адреса
        reverseGeocoding: false
    });*/

    // Если вы хотите задать неизменяемую точку "откуда", раскомментируйте код ниже.
    /*routePanelControl.routePanel.state.set({
        fromEnabled: false,
        from: 'Москва, Льва Толстого 16'
     });*/

    //myMap.controls.add(routePanelControl).add(zoomControl);
    myMap.controls.add(zoomControl);

    var geoObjects;


    $.ajax({
        url: "json"
    }).done(function(data) {
        geoObjects = ymaps.geoQuery(data)
            .addToMap(myMap)
            .applyBoundsToMap(myMap, {
                checkZoomRange: true
            });
        console.log(geoObjects)
    });
 console.log(geoObjects)
    // Получим ссылку на маршрут.
    /*routePanelControl.routePanel.getRouteAsync().then(function (route) {

        // Зададим максимально допустимое число маршрутов, возвращаемых мультимаршрутизатором.
        route.model.setParams({results: 1}, true);

        // Повесим обработчик на событие построения маршрута.
        route.model.events.add('requestsuccess', function () {

            var activeRoute = route.getActiveRoute();
            if (activeRoute) {
                // Получим протяженность маршрута.
                var length = route.getActiveRoute().properties.get("distance"),
                    // Вычислим стоимость доставки.
                    price = calculate(Math.round(length.value / 1000)),
                    // Создадим макет содержимого балуна маршрута.
                    balloonContentLayout = ymaps.templateLayoutFactory.createClass(
                        '<span>Расстояние: ' + length.text + '.</span><br/>' +
                        '<span style="font-weight: bold; font-style: italic">Стоимость доставки: ' + price + ' р.</span>');
                // Зададим этот макет для содержимого балуна.
                route.options.set('routeBalloonContentLayout', balloonContentLayout);
                // Откроем балун.
                activeRoute.balloon.open();
            }
        });

    });*/

    // Функция, вычисляющая стоимость доставки.
    function calculate(routeLength) {
        return Math.max(routeLength * DELIVERY_TARIFF, MINIMUM_COST);
    }

    document.getElementById('uploadButton').onclick = function () {
        // Для уничтожения используется метод destroy.
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