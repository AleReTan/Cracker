ymaps.ready(init);
var geoObjects;

function init() {
    myMap = new ymaps.Map('map', {
        center: [51.66149636, 39.20038956],
        zoom: 12,
        controls: []
    });

    $.ajax({
        url: front_url + "/allDriversJson"
    }).done(function (data) {
        geoObjects = ymaps.geoQuery(data)
            .addToMap(myMap);
    });

}