var webSocket = new WebSocket("ws://localhost:8080/ws");

webSocket.onopen = function(message) {
    var mess = "판매자_등록";
    webSocket.send(mess);
};
webSocket.onclose = function(message) {

};
webSocket.onerror = function(message) {
    alert("소켓 에러");
};
webSocket.onmessage = function(message) {
alert("주문 추가");
        $.ajax({
                type : "GET",
                url : "sales",
                async : true,
                dataType : "html",
                cache: false,
                error : function() {
                    alert('통신실패!!');
                },
                success : function(data) {
                    location.reload(true);
                    //$('body').children().remove();
                    //$('body').html(data);
                }
            });
};
function cancellation(id) {
    var message = "판매자_주문취소 " + id;
    webSocket.send(message);
}
function complete(id) {
    var message = "판매자_조리완료 " + id;
    webSocket.send(message);
}
// Disconnect 버튼을 누르면 호출되는 함수
    function disconnect() {
// WebSocket 접속 해제
    webSocket.close();
}
