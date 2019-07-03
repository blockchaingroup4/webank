// 获取弹窗
var modal = document.getElementById('popupElement');

// 打开弹窗的按钮对象
var btn = document.getElementById("button_register");

// 获取 <span> 元素，用于关闭弹窗 that closes the modal
var span = document.getElementsByClassName("close")[0];

// // 点击按钮打开弹窗
// btn.onclick = function() {
//     modal.style.display = "block";
// }
//
// 点击 <span> (x), 关闭弹窗
span.onclick = function() {
    modal.style.display = "none";
}

// 在用户点击其他地方时，关闭弹窗
// window.onclick = function(event) {
//     if (event.target == modal) {
//         modal.style.display = "none";
//     }
// }

// 响应登录功能
// var btn_login = document.getElementById("Login");
// btn_login.onclick = function() {
//     window.location.href="index.html";
// }

$("#button_login").on('click', function () {
    $.ajax({
        async:false,
        url: "/sign_in",
        data:JSON.stringify({"private_key":$('#input_private_key').val()}),
        dataType:'json',
        type:'post',
        contentType:'application/json',
        success: function (result) {
            console.log(result);
            if(result["type"] == "user"){
                window.location.href = "index.html";
            }
            else if(result["type"]=="manager"){
                window.location.href="adaministractor.html";
            }
        } ,
        error: function (result) {
            console.log(result);
        }
    });
});

$('#button_register').on('click', function () {
    $.ajax({
        async: false,
        url: '/sign_up',
        data:JSON.stringify({"code":$('#input_code').val(), "name":$("#input_name").val()}),
        dataType: 'json',
        type:'post',
        contentType: 'application/json',
        success: function (result) {
            console.log(result);
            var info = "你的地址：" + result["address"] + "\n" +
                "你的公钥：" + result["public_key"] + "\n" +
                "你的私钥：" + result["private_key"] + "\n" +
                "请妥善保管，丢失将无法找回！";
            var priKey = result["private_key"];
            var pubKey = result["public_key"];
            var addr = result["address"];
            $('#textarea_addr').text(addr);
            $('#textarea_pubkey').text(pubKey);
            $('#textare_prikey').text(priKey);
            modal.style.display = "block";
        },
        error: function (result) {
            console.log(result);
        }
    }) ;
});