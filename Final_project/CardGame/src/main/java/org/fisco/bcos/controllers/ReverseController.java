package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.ReverseInfo;
import org.fisco.bcos.clients.ReverseContractClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReverseController {
    //input:{transaction_id:, reason:}
    @RequestMapping(value = "/apply_for_reverse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyForReverse(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        String transactionId = request.getParameter("transaction_id");
        String reason = request.getParameter("reason");
        client.applyForReverse(transactionId, reason);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{}
    //output:
    // onSuccess:{status: "ok", reverses_id:[....]}
    // onError: todo
    @RequestMapping(value = "/get_reverse_applies", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getReverseApplies(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        List<String> applies = client.getReverseApplies();
        ret.put("status", "ok");
        ret.put("reverses_id", applies);
        return ret.toJSONString();
    }

    //input:{reverse_id:}
    //output:
    // onSuccess:{status: "ok", info:{....}};
    // onError: todo
    @RequestMapping(value = "/get_reverse_info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getReverseInfo(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        String reverseId = request.getParameter("reverse_id");
        ReverseInfo info = client.getReverseInfo(reverseId);
        ret.put("status", "ok");
        ret.put("info", info);
        return ret.toJSONString();
    }

    //input:{reverse_id:}
    //output:
    // onSuccess:{status: "ok"};
    // onError: todo
    @RequestMapping(value = "/send_reverse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendReverse(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        String reverseId = request.getParameter("reverse_id");
        client.sendReverse(reverseId);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{reverse_id:, result: true/false}
    //output:
    // onSuccess:{status: "ok"};
    // onError: todo
    @RequestMapping(value = "/set_reverse_result", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String setReverseResult(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        String reverseId = request.getParameter("reverse_id");
        Boolean result = Boolean.valueOf(request.getParameter("result"));
        client.setReverseResult(reverseId, result);
        ret.put("status", "ok");
        return ret.toJSONString();
    }
}
