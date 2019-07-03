// 　//图片加载
// $("#imageFile").change(function () {
//     var file = this.files[0];
//     if (window.FileReader) {
//         var reader = new FileReader();
//         reader.readAsDataURL(file);
//         //监听文件读取结束后事件
//         reader.onloadend = function (e) {
//             //e.target.result就是最后的路径地址
//             $("#image").attr("src",e.target.result);
//         };
//     }
// });


// 全局变量
var cardsID = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]; // 卡片id，用于发上架、下架的post请求
var cardsName = ['n1', 'n2', 'name3', 'n4', 'n5', 'n6', 'n7', 'n8', 'n9', 'n10']; // 卡片名字
var cardsLevel = [6, 5, 5, 4, 4, 3, 3, 2, 2, 1]; // 卡片等级
var isOnSale = ['true', 'false', 'true', 'false', 'true', 'false', 'true', 'false', 'true', 'false']; // 卡片是否正在出售（上架）
var cardNum = 10; // 用户的卡片数量
// 加入图片
// 第一种方法
// 获取的是一个 如果要获取多个
// document.querySelectorAll(selector)
// document.querySelectorAll("#btn-get_card_info-Json").onclick = function () {
// 	var ajax = new XMLHttpRequest();

// 	ajax.open('get','./get_card_info');

// 	ajax.send();

// 	ajax.onreadystatechange = function () {
// 		if (ajax.readyState==4&&ajax.status==200) {
// 			// json 字符串 是字符串 所以我们可以 通过  responseText获取
// 			console.log(ajax.responseText);

// 			// 转化为 js对象
// 			var jsObj = JSON.parse(ajax.responseText);

// 			console.log(jsObj);

// 			// 拼接ul s
// 			var str = '';

// 			str+='<ul>';
// 			str+='<li>'+jsObj.name+'</li>';
// 			str+='<li>'+jsObj.skill+'</li>';
// 			str+='<li>'+jsObj.friend+'</li>';
// 			str+='</ul>';

// 			// 设置到界面上

// 			document.body.innerHTML = str;
// 		}
// 	}
// }

// 页面加载完后自动调用
window.onload = function() {
    addImage();
    // bb();
}

// 加入图片
// 第二种方法
function addImage() {
    //定义变量sendData
    // this.cardsID ={'card_id':[1,2,3]};   //  假设用户都有ID为1,2,3的卡片
    var imgNum = 0; //  这个用户的卡片张数
    var urlImg = ['./Assets/1.png', './Assets/2.png', './Assets/3.png', './Assets/4.png', './Assets/5.png', './Assets/6.png', './Assets/7.png', './Assets/8.png', './Assets/9.png', './Assets/10.png']; //  这个用户的卡片的url
    var i = 0;
    // alert('here');

    // 请求当前用户拥有的卡片cardsId
	$.ajax({
		url: '/get_account_info',
		type:'post',
		dataType:'json',
		contentType:"application/json",
		async:true,//异步请求
		cache:false,
		// data:JSON.stringify(sendData),//没有要发的数据
		//执行成功的回调函数
		success:function(data) {
			// alert(data.info);
			var jsonData = JSON.parse(data);
			this.cardsID=jsonData.info.cardsId;  //  获取这个用户所有卡片的id
			// 获取卡片的张数
			 for(var aItem in jsonData){
				imgNum++;
			 }    
			 this.cardNum=imgNum;
		},
		//执行失败或错误的回调函数
		error:function(data) {
			alert("未能拿到用户的卡片id信息！");
		}
		});
		for (i = 0; i < imgNum; i++) { 
			// 请求图片url
			$.ajax({
					url: '/get_card_info',
					type:'post',
					dataType:'json',
					contentType:"application/json",
					async:true,//异步请求
					cache:false,
					data:JSON.stringify({"card_id":this.cardsID[i]}),//使用变量sendData
					//执行成功的回调函数
					success:function(data) {
						// alert(data.url);
						var jsonData = JSON.parse(data);
						urlImg[i]=jsonData.info.url;   
						this.cardsName[i]=jsonData.info.name;
						this.isOnSale[i]=jsonData.info.isOnSale;
						this.cardsLevel[i]=jsonData.info.level;
					},
					//执行失败或错误的回调函数
					error:function(data) {
						alert("没有成功拿到卡片的url！");
					}
			});
			}


    // 动态添加图片
    // 第一种方法   appendChild
    // 获取位置元素
    var dynamicImg = document.getElementById('dynamicImg');
    // // 把图片一张张插入页面
    for (i = 0; i < imgNum; i++) {
        var temp = i + 1;
        var adiv = document.createElement('div');
		var aImg = document.createElement('img');
		// var imgID = document.createElement('a'); // 第几张图片，从1开始
        var imgId = document.createTextNode("卡号：" + temp); // 第几张图片，从1开始
        var imgName = document.createTextNode("卡名：" + this.cardsName[i]);
        var imgLever = document.createTextNode("卡等级：" + this.cardsLevel[i]);
		var imgIsSale = document.createTextNode("卡是否在售：" + this.isOnSale[i]);
		// imgID.value="卡号：" + temp;
		// imgID.style.align = "center";
        aImg.src = urlImg[i];
		aImg.style.marginLeft = "100px";
		// adiv.appendChild(imgID);
        // adiv.appendChild(document.createElement('br'));
        adiv.appendChild(imgId);
        adiv.appendChild(document.createElement('br'));
        adiv.appendChild(imgName);
        adiv.appendChild(document.createElement('br'));
        adiv.appendChild(imgLever);
        adiv.appendChild(document.createElement('br'));
        adiv.appendChild(imgIsSale);
        adiv.appendChild(document.createElement('br'));
        adiv.appendChild(aImg);
        adiv.appendChild(document.createElement('br'));
        dynamicImg.appendChild(adiv);
        // if(i%4==0)
        // dynamicImg.appendChild(document.createElement('br'));  // 换行
        // 	alert("插入一张图片");
    }


    // 第二种方法 动态加入图片

    var list = "";
    for (i = 0; i < imgNum; i++) {
        // list += "<li><a href=\"\"><img src=" + urlImg[i] +" alt=\"商品图片\"></a></li>";
        list += "<div class=\"item\"" + ">" +
            "<div class=\"imgContainer\" >" +
            "<img src=\"" + urlImg[i] + "\" alt=\"个人拥有的卡片图片\">" +
            "</div>" +
            "<div class=\"info\" >" +
            "<p class = \"cardInfo\" >" +
            "<span class=\"cardNo\">" +
            "卡序号：" + (parseInt(i) + 1) +
            "</span>" +
            "<span class=\"name\">" +
            "名字：" + this.cardsName[i] +
            "</span>" +
            "<span class=\"level\">" +
            "等级：" + this.cardsLevel[i] +
            "</span>" +
            "<span class=\"isOnSale\">" +
            "是否已上架：" + this.isOnSale[i] +
            "</span>" +
            "</p>" +
            "</div>" +
            "</div>";
        if (i % 4 == 0)
            list += "<br>"; // 换行
    }
    // alert("插入 ");
    console.log(list);
    // var imgClass = document.getElementsByClassName("imgClass");
    var imgClass = document.getElementsById("imgClass");
    imgClass.innerHTML += list;

}

var goSale = document.getElementById('upMarket');
goSale.onclick = function() {
    // 上架
    var price = document.getElementById('price').value;
    var cardNo = document.getElementById('cardNo').value;
    var sendData = { 'card_id': this.cardsID[parseInt(cardNo) - 1], 'price': price }; // parseInt: get int from string
    $.ajax({
        url: '/push_card',
        type: 'post',
        dataType: 'json',
        contentType: "application/json",
        async: true, //异步请求
        cache: false,
        data: JSON.stringify(sendData),
        //执行成功的回调函数
        success: function(data) {
            // alert(data.info);
            var jsonData = JSON.parse(data);
            if (jsonData.status == "OK")
                alert("上架成功！");
        },
        //执行失败或错误的回调函数
        error: function(data) {
            alert("上架失败！");
        }
    });
};
// 下架
var downSale = document.getElementById('downMarket');
downSale.onclick = function() {

    var cardNo = document.getElementById('cardNo').value;
    $.ajax({
        url: '/pull_card',
        type: 'post',
        dataType: 'json',
        contentType: "application/json",
        async: true, //异步请求
        cache: false,
        data: JSON.stringify({ "card_id": this.cardsID[parseInt(cardNo) - 1] }),
        //执行成功的回调函数
        success: function(data) {
            // alert(data.info);
            var jsonData = JSON.parse(data);
            if (jsonData.status == "OK")
                alert("下架成功！");
        },
        //执行失败或错误的回调函数
        error: function(data) {
            alert("下架失败！");
        }
    });


};
// goSale.click();  //模拟点击事件，页面刷新，出发点击事件