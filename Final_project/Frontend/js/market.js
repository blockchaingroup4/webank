var http_request;
var clearfix = document.getElementsByClassName("clearfix")
//获取市场中所有正在出售的卡片
function get_cards() {
    if(window.ActiveXObject){
        http_request = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else {
        http_request = new XMLHttpRequest()
    }

    if(http_request){
        var url = "/get_cards_on_sale";
        http_request.open("GET", url, true);
        http_request.onreadystatechange = cards_return;
        http_request.send()
    }
}
//获取市场中所有正在出售的卡片的回调函数
function cards_return() {
    if(http_request.readyState == 4){
        if(http_request.status == 200){
            var mes_data = http_request.responseText;
            var mes_value = eval("(" + mes_data + ")");
            if(mes_value.status == 'ok'){
                var list="";
                for(var i=0; i<mes_value.info.length; i++){
                    list += "<li><a href=\"\"><img src=\"" + mes_value.info[i].url +"\" alt=\"商品图片\"></a></li>";
                }
                clearfix.innerHTML = list;
            }
        }
    }
}

get_cards()