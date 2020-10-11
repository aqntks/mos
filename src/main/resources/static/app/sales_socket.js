var webSocket = new WebSocket("ws://ec2-15-164-44-102.ap-northeast-2.compute.amazonaws.com:8080//ws");

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
$('#orderSection').load(location.href + ' #orderSection');
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
