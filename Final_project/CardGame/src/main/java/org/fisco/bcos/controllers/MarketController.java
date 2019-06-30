package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.CardInfo;
import org.fisco.bcos.clients.MarketContractClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MarketController {
    //get cards on sale
    //output:
    //  onSuccess: {status: "ok", cards_on_sale: [xxx, yyy, zzz....]}
    @RequestMapping(value = "/get_cards_on_sale", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getCardsOnSale(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        ret.put("status", "ok");
        ret.put("cards_on_sale", client.getCardsOnSale());
        return ret.toJSONString();
    }


    //buy card
    //input:{card_id: ""}
    //output:
    //  onSuccess:{status: "ok"}
    @RequestMapping(value = "/buy_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String buyCard(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String cardId = request.getParameter("card_id");
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{card_id:}
    @RequestMapping(value = "/pull_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String pullCard(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String cardId = request.getParameter("card_id");
        client.pullCard(cardId);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{card_id:}
    @RequestMapping(value = "/push_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String pushCard(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String cardId = request.getParameter("card_id");
        client.pushCard(cardId);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{card_id:,price:}
    @RequestMapping(value = "/set_card_price", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String setCardPrice(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String cardId = request.getParameter("card_id");
        String price = request.getParameter("price");
        client.setCardPrice(cardId, price);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{wish: "..."}
    //output:
    //  onSUccess:{status: "ok", card_info:{....}}
    @RequestMapping(value = "/draw_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String drawCard(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String wish = request.getParameter("wish");
        CardInfo info = client.drawCard(wish);
        ret.put("status", "ok");
        ret.put("card_info", info);
        return ret.toJSONString();
    }

    //input: {amount:}
    @RequestMapping(value = "/recharge", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String recharge(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String amount = request.getParameter("amount");
        client.recharge(amount);
        ret.put("status", "ok");
        return ret.toJSONString();
    }
}
