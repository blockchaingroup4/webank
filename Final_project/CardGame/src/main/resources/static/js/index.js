document.querySelector('.img__btn').addEventListener('click', function() {
    document.querySelector('.cont').classList.toggle('s--signup');
});


$("#send_check_code_btn").on('click', function () {
    $.ajax({
       type: "post",
       url:"/send_check_code",
       data:{
           "phone_number":"123456"
       },
       dataType: "json",
       success: function (result) {
          console.log("ajax success");
          console.log(result);
       },
       error: function (result) {
          console.log("ajax error");
          console.log(result);
       }
   });
});

$("#sign_up_btn").on('click', function () {
   $.ajax({
      type:'post',
      url:'/sign_up',
      data:{
         "code" : "123456"
      },
      dataType:'json',
      success:function (result) {
         console.log("success");
         console.log(result);
      },
      error:function (result) {
         console.log(result);
      }
   });
});

$("#sign_in_btn").on('click', function () {
   // console.log($("#key_input").val());
   $.ajax({
      type:'post',
      dataType:'json',
      url:'/sign_in',
      data:{
         "private_key":$("#key_input").val()
      },
      success:function (result) {
        console.log(result);
      },
      error:function (result) {
         console.log(result);
      }
   });
});

$("#get_account_info_btn").on('click', function () {
   $.ajax({
      type:'post',
      dataType:'json',
      url:'/get_account_info',
      data:{

      },
      success:function (result) {
         console.log(result);
      },
      error: function (result) {
         console.log(result);
      }
   });
});

$("#get_card_info_btn").on('click', function () {
   $.ajax({
      type:'post',
      dataType: 'json',
      url:'/get_card_info',
      data:{
         "card_id":"xxxx"
      },
      success: function (o) {
         console.log(o);
      },
      error: function (o) {
         console.log(o);
      }
   });
});

$('#get_cards_on_sale_btn').on('click', function () {
   $.ajax({
      type:'post',
      dataType:'json',
      url:'/get_cards_on_sale',
      data:{},
      success: function (o) {
         console.log(o);
      },
      error:function (o) {
         console.log(o);
      }
   })
});

$('#buy_card_btn').on('click', function () {
   $.ajax({
      type:'post',
      dataType: 'json',
      url:'/buy_card',
      data:{
         "card_id":"xxxx"
      },
      success: function (o) {
         console.log(o);
      },
      error: function (o) {
         console.log(o);
      }
   });
})

