function delete_order(id) {
            $.ajax({
                type: 'DELETE',
                url: '/api/v1/orderMenu/'+id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                alert('주문이 취소되었습니다.');
                window.location.href = '/sales';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
}

function finish_order(id) {
            $.ajax({
                type: 'DELETE',
                url: '/api/v1/orderMenu/'+id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                alert('조리가 완료되었습니다.');
                window.location.href = '/sales';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
}

function statusOn(id) {
    var data = {
                seller_id: '1',
                on_off: 1
            };

            $.ajax({
                type: 'PUT',
                url: '/api/v1/status/'+ id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('판매 시작');
                window.location.href = '/sales';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
}
function statusOff(id) {
    var data = {
                seller_id: '1',
                on_off: 0
            };

            $.ajax({
                type: 'PUT',
                url: '/api/v1/status/'+ id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('판매 종료');
                window.location.href = '/sales';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
}