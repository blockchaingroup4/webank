package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.AccountInfo;
import org.fisco.bcos.clients.AccountContractClient;
import org.fisco.bcos.util.PhoneDB;
import org.fisco.bcos.util.SendMessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {
    //获取用户信息
    //output:
    // onSuccess: {status: "ok", info{name:"", balance:"", draw_count:"", cards_id:"", transactions_id:""}}
    @RequestMapping(value = "/get_account_info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAccountInfo(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("account_contract_client");
        if(clientObj == null){
            ret.put("status", "error");
            ret.put("error_type", "client_null");
            return ret.toJSONString();
        }
        AccountContractClient client = (AccountContractClient)clientObj;
        AccountInfo info = client.getAccountInfo();
        ret.put("status", "ok");
        ret.put("info", info);
        return ret.toJSONString();
    }
}
