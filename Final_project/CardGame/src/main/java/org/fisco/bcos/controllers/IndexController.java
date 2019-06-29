package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.ContractAddr;
import org.fisco.bcos.clients.*;
import org.fisco.bcos.util.KeyUtil;
import org.fisco.bcos.util.PhoneDB;
import org.fisco.bcos.util.SendMessageUtil;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    ContractAddr contractAddr;
    //get home page
    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    //get check code
    //cooldown time > 60 second
    //input: {phone_number: phoneNumber}
    //output:
    //  onSuccess: {status: "ok", code: "...."}
    //  lackPhoneError: {status: "error", error_type: "lack_phone"}
    //  cooldownError: {status: "error",  error_type: "cooldown"}
    //  repeatError: {status: "error", error_type: "repeat"}
    @RequestMapping(value = "/send_check_code", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendCheckCode(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        String phoneNumber = request.getParameter("phone_number");
        if(phoneNumber == null){
            ret.put("status", "error");
            ret.put("error_type", "lack_phone");
            return ret.toJSONString();
        }
        if(PhoneDB.contains(phoneNumber)){
            //todo
        }
        String code = SendMessageUtil.getRandomCode(6);
        if(SendMessageUtil.sendCode(code)!=1){
            //todo
        }
        ret.put("status", "ok");
        ret.put("code", code);
        request.getSession().setAttribute("code", code);
        return ret.toJSONString();
    }

    //sign up
    //input: {code: codeIn}
    //output:
    //  success: {status: "ok", public_key: "0x....", address: "0x....", private_key: "...."}
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String signUp(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        //todo : check code
        KeyUtil.UserKey key = KeyUtil.createUserKey();
        ret.put("status", "ok");
        ret.put("public_key", key.getPublicKey());
        ret.put("address", key.getAddress());
        ret.put("private_key", key.getPrivateKey());
        return ret.toJSONString();
    }

    //sign in
    //input: {private_key : "..."}
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String signIn(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        String privateKey = request.getParameter("private_key");
        Credentials credentials = Credentials.create(privateKey);
        //todo: check credentials
        request.getSession().setAttribute("credentials", credentials);
        request.getSession().setAttribute("account_contract_client", new AccountContractClient(credentials, contractAddr.getAccountContractAddress()));
        request.getSession().setAttribute("market_contract_client", new MarketContractClient(credentials, contractAddr.getMarketContractAddress()));
        request.getSession().setAttribute("card_contract_client", new CardContractClient(credentials, contractAddr.getCardContractAddress()));
        request.getSession().setAttribute("transaction_contract_client", new TransactionContractClient(credentials, contractAddr.getTransactionContractAddress()));
        request.getSession().setAttribute("reverse_contract_client", new ReverseContractClient(credentials, contractAddr.getReverseContractAddress()));
        ret.put("status", "ok");
        return ret.toJSONString();
    }

//    @RequestMapping(value = "/send_check_code", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String sendCheckCode(HttpServletRequest request) {
//        System.out.println(request);
//        System.out.println(request.getParameter("phone"));
//
////        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////        System.out.println(request);
//        String code = SendMessageUtil.getRandomCode(6);
//        System.out.println(code);
//        JSONObject obj = new JSONObject();
//        obj.put("code", code);
//        return obj.toJSONString();
//    }

//    @RequestMapping(value = "/test", method= RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String test(){
//        JSONObject obj = new JSONObject();
//        obj.put("first", "haha");
//        obj.put("second", 20);
//        return obj.toJSONString();
//    }
}

