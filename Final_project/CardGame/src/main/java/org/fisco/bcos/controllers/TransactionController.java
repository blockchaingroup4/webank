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
        TransactionContractClient client = (TransactionContractClient)clientObj;
        String transactionId = request.getParameter("transaction_id");
        TransactionInfo info = client.getTransactionInfo(transactionId);
        ret.put("status", "ok");
        ret.put("info", info);
        return ret.toJSONString();
    }
}
