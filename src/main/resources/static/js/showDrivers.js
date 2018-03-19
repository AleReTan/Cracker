ymaps.ready(init);
var geoObjects;
function init() {
    myMap = new ymaps.Map('map', {
        center: [51.66149636, 39.20038956],
        zoom: 12,
        controls: []
    });

    $.ajax({
        url: "http://localhost:8080/allCarsJson"
    }).done(function (data) {
        geoObjects = ymaps.geoQuery(data)
            .addToMap(myMap);
    });

}