// window.onload = function () {
//init();
// };
ymaps.ready(init);

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
    //преобразуем координаты из бд в форму для работы с мапами
    var startPointString = startPoint.split(',');
    var startPointArr = [parseFloat(startPointString[0]), parseFloat(startPointString[1])];

    var destinationPointString = destinationPoint.split(',');
    var destinationPointArr = [parseFloat(destinationPointString[0]), parseFloat(destinationPointString[1])];

    createRoute(startPointArr, destinationPointArr);
    //если нижеперечисленные статусы - блочим выбор
    if (statusOrderFMVariable === "Заказ завершен" ||
        statusOrderFMVariable === "Заказ отменен" ||
        statusOrderFMVariable === "Водитель с клиентом") {
        document.getElementById('driverSelect').disabled = true;
    }
    //если поиск водителя, то даем возможность брать любого
    if (statusOrderFMVariable === "Поиск водителя") {
        document.getElementById('setDriver').disabled = false;
    }
    //если переключились на уже назначенного водителя, то блочим возможность выбрать его
    document.getElementById('driverSelect').onchange = function () {
        if (document.getElementById('driverSelect').value !== selectedDriverId) {
            console.log(selectedDriverId + "selDrId");
            console.log(document.getElementById('driverSelect').value);
            document.getElementById('setDriver').disabled = false;
        }
        else {
            document.getElementById('setDriver').disabled = true;
        }
    };
    if (document.getElementById('chooseDriver') != null) {
        document.getElementById('chooseDriver').onclick = function () {
            // Дождемся ответа от сервера и получим объект, ближайший к точке.
            geoObjects.then(function () {
                var closestObject = geoObjects.getClosestTo(startPointArr);
                // Если ответ пуст, то ближайший объект не найдется.
                if (closestObject) {
                    document.getElementById('driverSelect').value = closestObject.properties.get('driverId');
                }

            });

        };
    }

    function createRoute(start, destination) {
        multiRoute = new ymaps.multiRouter.MultiRoute({
            // Описание опорных точек мультимаршрута.
            referencePoints: [
                start,
                destination],
            params: {
                // Ограничение на максимальное количество маршрутов, возвращаемое маршрутизатором.
                results: 1
            }
        });
        myMap.geoObjects.add(multiRoute);
    }

}