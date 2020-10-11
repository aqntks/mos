var source = $("#template").html();
    var template = Handlebars.compile(source);
    source = $("#totalTem").html();
    var totalTem = Handlebars.compile(source);


    var menuArray = new Array();
    var totalCount = 0;

    for(var i = 0; i < sessionStorage.length; i++){
        menuArray[i] = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
        menuArray[i].allCount = parseInt(menuArray[i].price) * parseInt(menuArray[i].count);
        menuArray[i].key = sessionStorage.key(i);
        totalCount += menuArray[i].allCount;
    }

     var data = {
        menu: menuArray
    }
    var tot = {
        total: totalCount
    }

    $('#basket').append(template(data));
    $('#total').append(totalTem(tot));

    function save_array(){
        for(var i = 0; i < sessionStorage.length; i++){
            menuArray[i] = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
            menuArray[i].allCount = parseInt(menuArray[i].price) * parseInt(menuArray[i].count);
            menuArray[i].key = sessionStorage.key(i);
        }
    }

    function delete_menu(id){
        var temp = JSON.parse(sessionStorage.getItem(id));
        sessionStorage.removeItem(id);
        totalCount -= temp.price * temp.count;
        tot = {
            total: totalCount
        }
        var newArray2 = new Array();
        for(var i = 0; i < sessionStorage.length; i++){
            newArray2[i] = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
            newArray2[i].allCount = parseInt(newArray2[i].price) * parseInt(newArray2[i].count);
            newArray2[i].key = sessionStorage.key(i);
        }
        data3 = {
            menu: newArray2
        }

        $('#basket').children().remove();
        $('#total').children().remove();
        $('#basket').html(template(data3));
        $('#total').html(totalTem(tot));
    }

    function go_result(){
        var id = sessionStorage.getItem("id");
        if(id == null)
            alert("주문내역이 없습니다.");
        else{
        location.href="/result/" + id;
        }
    }

    function up_count(key){
        var temp = JSON.parse(sessionStorage.getItem(key));
        temp.count += 1;
        sessionStorage.setItem(key, JSON.stringify(temp));

        save_array();
        totalCount += temp.price;
        tot = {
            total: totalCount
        }
        var newArray = new Array();
                for(var i = 0; i < sessionStorage.length; i++){
                    newArray[i] = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
                    newArray[i].allCount = parseInt(newArray[i].price) * parseInt(newArray[i].count);
                    newArray[i].key = sessionStorage.key(i);
                }
                data2 = {
                    menu: newArray
                }

        $('#basket').children().remove();
        $('#total').children().remove();
        $('#basket').html(template(data2));
        $('#total').html(totalTem(tot));
    }
    function down_count(key){
        var temp = JSON.parse(sessionStorage.getItem(key));
        if(temp.count != 1){
            temp.count -= 1;
            sessionStorage.setItem(key, JSON.stringify(temp));
        }
        else{
            sessionStorage.removeItem(key);
        }

        save_array();
        totalCount -= temp.price;
        tot = {
            total: totalCount
        }
        var newArray = new Array();
        for(var i = 0; i < sessionStorage.length; i++){
            newArray[i] = JSON.parse(sessionStorage.getItem(sessionStorage.key(i)));
            newArray[i].allCount = parseInt(newArray[i].price) * parseInt(newArray[i].count);
            newArray[i].key = sessionStorage.key(i);
        }
        data2 = {
            menu: newArray
        }

        $('#basket').children().remove();
        $('#total').children().remove();
        $('#basket').html(template(data2));
        $('#total').html(totalTem(tot));
    }