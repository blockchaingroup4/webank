package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.ContractAddr;
import org.fisco.bcos.clients.*;
import org.fisco.bcos.util.KeyUtil;
import org.fisco.bcos.util.PhoneDB;
import org.fisco.bcos.util.SendMessageUtil;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
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
    @Autowired
    Web3j web3j;
    //get home page
    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    //get check code
    //cooldown time > 60 second
    //input: {phone_number: phoneNumber}
    //output:
    //  onSuccess: {status: "ok"}
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
        request.getSession().setAttribute("code", code);
        return ret.toJSONString();
    }

    //sign up
    //input: {code: codeIn, name: "..."}
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
        Credentials credentials = GenCredential.create(key.getPrivateKey());
        AccountContractClient client = new AccountContractClient(credentials, contractAddr.getAccountContractAddress(), web3j);
        request.getSession().setAttribute("account_contract_client", new AccountContractClient(credentials, contractAddr.getAccountContractAddress(), web3j));
        String name = request.getParameter("name");
        client.addAccount(name);
        return ret.toJSONString();
    }

    //sign in
    //input: {private_key : "..."}
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String signIn(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        String privateKey = request.getParameter("private_key");
        Credentials credentials = GenCredential.create(privateKey);
        //todo: check credentials
        request.getSession().setAttribute("credentials", credentials);
        Object client = request.getSession().getAttribute("account_contract_client");
        if(client == null)
            request.getSession().setAttribute("account_contract_client", new AccountContractClient(credentials, contractAddr.getAccountContractAddress(), web3j));
        request.getSession().setAttribute("market_contract_client", new MarketContractClient(credentials, contractAddr.getMarketContractAddress(), web3j));
        request.getSession().setAttribute("card_contract_client", new CardContractClient(credentials, contractAddr.getCardContractAddress(), web3j));
        request.getSession().setAttribute("transaction_contract_client", new TransactionContractClient(credentials, contractAddr.getTransactionContractAddress(), web3j));
        request.getSession().setAttribute("reverse_contract_client", new ReverseContractClient(credentials, contractAddr.getReverseContractAddress(), web3j));
        ret.put("status", "ok");
        return ret.toJSONString();
    }

}

