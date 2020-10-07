function add_basket(num) {
    var data = {
        consumerId: $('#consumerId' + num).val(),
        menuId: $('#menuId' + num).val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/basket',
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function() {
        alert('메뉴 추가');
    }).fail(function (error) {
        alert('오류 발생');
    });
}