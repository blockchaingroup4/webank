package org.fisco.bcos.controllers;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.beans.ReverseInfo;
import org.fisco.bcos.clients.ReverseContractClient;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReverseController {
    @Autowired
    Credentials credentials;
    @Autowired
    HttpServletRequest request;
    //input:{transaction_id:, reason:}
    @RequestMapping(value = "/apply_for_reverse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String applyForReverse(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        String transactionId = (String)jsonObject.get("transaction_id");
        String reason = (String)jsonObject.get("reason");
        client.applyForReverse(transactionId, reason);
        ret.put("status", "ok");
        return ret.toJSONString();
    }

    //input:{}
    //output:
    // onSuccess:{status: "ok", reverses:[{...}, {...},....]}
    // onError: {status: "ok", error_type: "not_manager"}
    @RequestMapping(value = "/get_reverse_applies", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getReverseApplies(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object inputCredentialsObj = request.getSession().getAttribute("credentials");
        if(inputCredentialsObj == null || !((Credentials)inputCredentialsObj).getEcKeyPair().getPrivateKey().equals(credentials.getEcKeyPair().getPrivateKey())){
            ret.put("status", "error");
            ret.put("error_type", "not_manager");
            return ret.toJSONString();
        }
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        List<ReverseInfo> applies = client.getReverseApplies();
        ret.put("status", "ok");
        ret.put("reverses", applies);
        return ret.toJSONString();
    }

    //input:{reverse_id:}
    //output:
    // onSuccess:{status: "ok", info:{....}};
    // onError: {status: "error", error_type: "not_manager"}
    @RequestMapping(value = "/get_reverse_info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getReverseInfo(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        String reverseId = (String)jsonObject.get("reverse_id");
        ReverseInfo info = client.getReverseInfo(reverseId);
        ret.put("status", "ok");
        ret.put("info", info);
        return ret.toJSONString();
    }

    //input:{reverse_id:}
    //output:
    // onSuccess:{status: "ok"};
    // onError: {status: "error", error_type: "not_manager"}
    @RequestMapping(value = "/send_reverse", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendReverse(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object inputCredentialsObj = request.getSession().getAttribute("credentials");
        if(inputCredentialsObj == null || !((Credentials)inputCredentialsObj).getEcKeyPair().getPrivateKey().equals(credentials.getEcKeyPair().getPrivateKey())){
            ret.put("error_type", "not_manager");
            ret.put("status", "error");
            return ret.toJSONString();
        }
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        String reverseId = (String)jsonObject.get("reverse_id");
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
    public String setReverseResult(@RequestBody JSONObject jsonObject){
        JSONObject ret = new JSONObject();
        Object clientObj = request.getSession().getAttribute("reverse_contract_client");
        ReverseContractClient client = (ReverseContractClient)clientObj;
        String reverseId = (String)jsonObject.get("reverse_id");
        Boolean result = jsonObject.getBoolean("result");
        client.setReverseResult(reverseId, result);
        ret.put("status", "ok");
        return ret.toJSONString();
    }
}
