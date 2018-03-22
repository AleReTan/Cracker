window.onload = function () {
    init();
};

function init () {
    selectItemByValue(document.getElementById('status'),statusOrderFMVariable);
}

function selectItemByValue(elmnt, value) {
    for (var i = 0; i < elmnt.options.length; i++) {
        if (elmnt.options[i].value == value)
            elmnt.selectedIndex = i;
    }
}