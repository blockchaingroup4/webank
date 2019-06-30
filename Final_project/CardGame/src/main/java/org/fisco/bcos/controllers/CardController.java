package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.AccountInfo;
import org.fisco.bcos.beans.CardInfo;
import org.fisco.bcos.clients.AccountContractClient;
import org.fisco.bcos.clients.CardContractClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CardController {
    //获取卡片信息
    //output:
    // onSuccess: {status: "ok", info{name:"", level:"", card_id:"", url:"", is_on_sale:"", price:"", owner:""}}
    @RequestMapping(value = "/get_card_info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getCardInfo(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("card_contract_client");
        if(clientObj == null){
            ret.put("status", "error");
            ret.put("error_type", "client_null");
            return ret.toJSONString();
        }

        CardContractClient client = (CardContractClient)clientObj;
        String cardId = request.getParameter("card_id");
        CardInfo info = client.getCardInfo(cardId);
        ret.put("status", "ok");
        ret.put("info", info);
        return ret.toJSONString();
    }

    //input:{card_id:,price:}
    @RequestMapping(value = "/set_card_price", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String setCardPrice(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        CardContractClient client = (CardContractClient)clientObj;
        String cardId = request.getParameter("card_id");
        String price = request.getParameter("price");
        client.setCardPrice(cardId, price);
        ret.put("status", "ok");
        return ret.toJSONString();
    }
}
