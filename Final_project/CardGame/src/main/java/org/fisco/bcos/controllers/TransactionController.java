package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.ReverseInfo;
import org.fisco.bcos.beans.TransactionInfo;
import org.fisco.bcos.clients.ReverseContractClient;
import org.fisco.bcos.clients.TransactionContractClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TransactionController {
    //input:{transaction_id:}
    //output:
    // onSuccess:{status: "ok", info:{....}};
    // onError: todo
    @RequestMapping(value = "/get_transaction_info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getTransactionInfo(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("transaction_contract_client");
        //未登录
        if(clientObj == null){
            ret.put("status", "error");
            ret.put("error_type", "client_null");
            return ret.toJSONString();
        }
        TransactionContractClient client = (TransactionContractClient)clientObj;
        String transactionId = request.getParameter("transaction_id");
        //缺少transaction_id参数
        if(transactionId == null){
            ret.put("status", "error");
            ret.put("error_type", "lack_transaction_id");
            return ret.toJSONString();
        }
        TransactionInfo info = client.getTransactionInfo(transactionId);
        //获取交易信息失败
        if(info == null){
            ret.put("status", "error");
            ret.put("error_type", "info_null");
            return ret.toJSONString();
        }
        ret.put("info", info);
        ret.put("status", "ok");
        return ret.toJSONString();
    }
}
