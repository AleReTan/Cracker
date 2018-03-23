window.onload = function () {
    init();
};

function init () {
    var selectStatusMenu = document.getElementById('status');
    selectItemByValue(selectStatusMenu,statusOrderFMVariable);
    if (statusOrderFMVariable === "Заказ завершен" || statusOrderFMVariable === "Заказ отменен"){
            selectStatusMenu.disabled = true;
            document.getElementById('driverId').disabled = true;
            document.getElementById('saveButton').disabled = true;
    }
}

function selectItemByValue(element, value) {
    for (var i = 0; i < element.options.length; i++) {
        if (element.options[i].value === value)
            element.selectedIndex = i;
    }
}