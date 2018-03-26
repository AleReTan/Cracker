window.onload = function () {
    init();
};

function init() {
    //если нижеперечисленные статусы - блочим выбор
    if (statusOrderFMVariable === "Заказ завершен" ||
        statusOrderFMVariable === "Заказ отменен" ||
        statusOrderFMVariable === "Водитель с клиентом") {
        document.getElementById('driverId').disabled = true;
    }
    //если поиск водителя, то даем возможность брать любого
    if (statusOrderFMVariable === "Поиск водителя") {
        document.getElementById('setDriver').disabled = false;
    }
    //если переключились на уже назначенного водителя, то блочим возможность выбрать его
    document.getElementById('driverId').onchange = function () {
        if (document.getElementById('driverId').value !== selectedDriverId) {
            document.getElementById('setDriver').disabled = false;
        }
        else {
            document.getElementById('setDriver').disabled = true;
        }
    }
}