pragma solidity ^0.4.25;
contract Transactioninterface{
    function getTransaction_re(bool role, uint transactionId) returns(address);
    function getRole(address who, uint transactionId)returns (bool);
    function setReversingTrue(uint transactionId)external;
}
contract AccountManagementInterface{
    function addRequestions(address who, uint requestionId);
}
contract ReverseManagementContract{
    //public the address of managers
    // address managerAddress = ...;
    
    struct ReverseApplication{
        uint reverseApplyId;
        uint transactionId;
        bool role;  
        string discribe;
        bool dealed; //whether dealed by manager
        
        //role:  0:seller
        //       1:buyer
    }
    
    
    ReverseApplication[] reverseApplications;
    Transactioninterface transactioninterface;
    AccountManagementInterface accountManagementInterface;
    
    function createReverseApplies(address who, uint transactionId, string discribe)external{
        bool role = transactioninterface.getRole(who, transactionId);
        transactioninterface.setReversingTrue(transactionId);
        uint reverseApplyId = reverseApplications.push(ReverseApplication(0, transactionId, role, discribe, false))-1;
        reverseApplications[reverseApplyId].reverseApplyId = reverseApplyId;
        //create re_applicationId
        //reverseApplications ++
    }
    
    function getReverseAppliesNum() external returns (uint){
        return reverseApplications.length;
    }
    
    function getReverseApply(uint index)external returns(uint, uint, bool, string, bool){
        return (reverseApplications[index].reverseApplyId, reverseApplications[index].transactionId, reverseApplications[index].role, 
        reverseApplications[index].discribe, reverseApplications[index].dealed );
    }
    
    //click the button to deal with the reverseapplications.
    // function ManageReverseApply(bool role, uint transactionId) external returns(bool){
    //      address aim;
    //      string memory cardname;
    //      uint timestamp;
    //      (aim, cardname, timestamp)=transactioninterface.getTransaction_re(role, transactionId);
    //      return sendReverseInform(aim, cardname, timestamp);
    // }
    
   
    
    
    function sendReverseInform(uint reverseApplyId)external{
        uint transactionId = reverseApplications[reverseApplyId].transactionId;
        bool role = reverseApplications[reverseApplyId].role;
        address aim = transactioninterface.getTransaction_re(role, transactionId);
    
        accountManagementInterface.addRequestions(aim, reverseApplyId);
        
        
    }
    
}