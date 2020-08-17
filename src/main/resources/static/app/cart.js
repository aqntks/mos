$("#input-count").on('mouseover', function(){
    console.log("Saving value " + $(this).val());
    $(this).data('val', $(this).val());

});

$("#input-count").on('change', function(){
    var prev = $(this).data('val');
    var current = $(this).val();

    var ajaxOption = {
        url : '/cart',
        async : true,
        type : "POST",
        dataType : "html",
        cache : false
    };

    // + 버튼
    if(prev < current){
    var data = {
            consumerId: $('#consumerId7').val(),
            menuId: $('#menuId7').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/basket',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {

        }).fail(function (error) {
           //오류 처리
        });
    }
    else if(prev > current){
        var id = $('#id7').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/basket/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
    else{}
});