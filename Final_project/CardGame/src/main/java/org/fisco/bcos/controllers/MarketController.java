package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.CardInfo;
import org.fisco.bcos.clients.CardContractClient;
import org.fisco.bcos.clients.MarketContractClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MarketController {
    @Autowired
    HttpServletRequest request;
    //get cards on sale
    //output:
    //  onSuccess: {status: "ok", cards_on_sale: [{...}, {...}, {...},....]}
    @RequestMapping(value = "/get_cards_on_sale", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getCardsOnSale(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        Object cardClientObj = request.getSession().getAttribute("card_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        CardContractClient cardClient = (CardContractClient)cardClientObj;
        ret.put("status", "ok");
        List<String>addrs = client.getCardsOnSale();
        List<CardInfo>cards = new ArrayList<>();
        for(String addr : addrs){
            cards.add(cardClient.getCardInfo(addr));
        }
        ret.put("cards_on_sale", cards);
        return ret.toJSONString();
    }


    //buy card
    //input:{card_id: ""}
    //output:
    //  onSuccess:{status: "ok"}
    @RequestMapping(value = "/buy_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String buyCard(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String cardId = (String)jsonObject.get("card_id");
        client.buyCard(cardId);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{card_id:}
    @RequestMapping(value = "/pull_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String pullCard(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String cardId = (String)jsonObject.get("card_id");
        System.out.println(cardId);
        client.pullCard(cardId);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{card_id:, amount: ""}
    @RequestMapping(value = "/push_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String pushCard(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String cardId = (String)jsonObject.get("card_id");
        String amount = (String)jsonObject.get("amount");
        System.out.println(cardId);
        client.pushCard(cardId, new BigInteger(amount));
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{wish: "..."}
    //output:
    //  onSUccess:{status: "ok", card_info:{....}}
    @RequestMapping(value = "/draw_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String drawCard(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String wish = (String)jsonObject.get("wish");
        CardInfo info = client.drawCard(wish);
        ret.put("status", "ok");
        ret.put("card_info", info);
        return ret.toJSONString();
    }

    //input: {amount:}
    @RequestMapping(value = "/recharge", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String recharge(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String amount = (String)jsonObject.get("amount");
        client.recharge(amount);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //todo
    //input:{times:""}
    @RequestMapping(value = "/buy_draw_card", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String buyDrawCard(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("market_contract_client");
        MarketContractClient client = (MarketContractClient)clientObj;
        String times = (String)jsonObject.get("times");
        client.buyDrawCard(times);
        ret.put("status", "ok");
        return ret.toJSONString();
    }
}
