$(document).ready(function add_cart() {

    var menuArray = new Array();
    var totalCount = 0;
    for(var i = 0; i < sessionStorage.length; i++){
            menuArray[i] = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
            totalCount += menuArray[i].count * menuArray[i].price;
    }

    for(var i = 0; i < sessionStorage.length; i++){
             menuArray[i] = sessionStorage.getItem(sessionStorage.key(i));
        }

    var data = {
        menus: menuArray,
        total: totalCount
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/orderMenu',
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function(data) {
        sessionStorage.setItem("id", data);
        window.location.href = '/result/' + data;
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
});