pragma solidity ^0.4.25;
contract Transactioninterface{
    function getTransaction_re(bool role, uint transactionId) returns(address, string, uint);
}
contract ReverseManagementContract{
    //public the address of managers
    // address managerAddress = ...;
    
    struct ReverseApplication{
        uint re_applicationId;
        uint transactionId;
        bool role;
        string discribe;
        bool dealed;
        
        //0:seller
        //1:buyer
    }
    mapping(uint => ReverseApplication) reverseApplications;
    Transactioninterface transactioninterface;
    
    function setReverseApplies(bool role, uint transactionId, uint re_applicationId,string discribe){
        //create re_applicationId
        //reverseApplications ++
    }
    
    function getReverseApplies() external{
        // require(managerAddress == msg.sender);
        //reverseApplications;
    }
    
    //click the button to deal with the reversapplications.
    function ManageReApplictions(bool role, uint transactionId) external returns(bool){
         
         address aim;
         string memory cardname;
         uint timestamp;
         (aim, cardname, timestamp)=transactioninterface.getTransaction_re(role, transactionId);
         return sendReverseInform(aim, cardname, timestamp);
    }
    
    
    function sendReverseInform(address aim, string cardname, uint timestamp) private returns(bool){
        //send massage to buyer and seller
        //0£ºunsuccessful
        //1£ºsuccessful
        
    }
    
}