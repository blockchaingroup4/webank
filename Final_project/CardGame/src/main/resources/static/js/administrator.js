
window.onload=function (){
  $.ajax({
      url:"/get_reverse_applies",
      type:"POST",
      data:{},
      dataType:"json",
      contentType:"application/json",
      success:function(result){
          var reverses=result["reverses"];
          // var reverses = [{role:true,reverseId:1, transactionId: 3,reason:"no reason"},{role:false,reverseId:3,transactionId: 3, reason:"reason"}];
          var body=$("#rev_body");
          body.empty();
          for(var i=0;i<reverses.length;i++){
              var row = $("<tr></tr>");
              var role = reverses[i].role? "卖家":"买家";
              var accountTd = $("<td>" + role + "</td>");
              var revId = $("<td>" + reverses[i].reverseId + "</td>");
              var tranId = $("<td>"+ reverses[i].transactionId + "</td>")
              var reason=$("<td>" + reverses[i].reason + "</td>");
              var button=$("<td><button>通过</button></td>");
              row.append(accountTd,revId,tranId, reason,button);
              body.append(row);
          }
      }
  })
};