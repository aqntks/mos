function save_menu() {
    var data = {
        sellerId: $('#sellerId').val(),
        menuName: $('#menuName').val(),
        menuPrice: $('#menuPrice').val(),
        menuDescription: $('#menuDescription').val(),
        menuImg: $('#menuImg').val(),
        menuType: $('#menuType').val()
    };

    $.ajax({
                type: 'POST',
                url: '/api/v1/menu',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('메뉴 등록 완료');
                window.location.href = '/menu_management';
            }).fail(function (error) {
                alert('오류 발생');
            });
}

function edit_menu(menuId) {
    var data = {
                sellerId: $('#sellerId').val(),
                menuName: $('#menuName').val(),
                menuPrice: $('#menuPrice').val(),
                menuDescription: $('#menuDescription').val(),
                menuImg: $('#menuImg').val(),
                menuType: $('#menuType').val()
            };

            $.ajax({
                type: 'PUT',
                url: '/api/v1/menu/'+menuId,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('메뉴가 수정되었습니다.');
                window.location.href = '/menu_management';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
}

function delete_menu(menuId) {
            $.ajax({
                type: 'DELETE',
                url: '/api/v1/menu/'+menuId,
                dataType: 'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                alert('메뉴가 삭제되었습니다.');
                window.location.href = '/menu_management';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
}