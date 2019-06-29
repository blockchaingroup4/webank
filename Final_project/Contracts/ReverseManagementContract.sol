pragma solidity ^0.4.25;
contract Transactioninterface{
    function getTransaction_re(bool role, uint transactionId) returns(address);
    function getRole(address who, uint transactionId)returns (bool);
    function getCardId(uint transactionId)external returns (uint);
    function getPriceOf(uint transactionId)external returns(uint);
    function setReversingTrue(uint transactionId)external;
    function dealWithRequestions(uint transactionId, bool result);
}

contract AccountManagementInterface{
    function addRequestions(address who, uint requestionId);
    function getBalanceOf(address addr)external returns(uint);
    function setBalanceOf(address addr, uint balance)external;
    function removeCard(address owner, uint cardId)external;
    function addCard(address who, uint cardId)external;
    function addTransaction(address who, uint transactionId)external;
    function getDrawCountOf(address addr)external returns(uint32);
    function setDrawCountOf(address addr, uint count)external;
}

contract CardManagementInterface{
    function getPriceOf(uint cardId)external returns(uint);
    function getCardOwner(uint cardId)external returns(address);
    function getCardName(uint cardId)external returns(string);
    function setCardOwner(uint cardId, address owner)external;
    function setCardOnSale(uint cardId, bool onSale)external;
    function setCardPrice(uint cardId, uint price)external;
    function createCard(uint cardId, string url, int8 level, address owner);
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
    CardManagementInterface cardManagementInterface;
    
    function createReverseApplies(address who, uint transactionId, string discribe)external{
        uint cardId = transactionId.getCardId(transactionId);
        require(cardManagementInterface.getCardOwner(cardId) == who);
        
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
    
    function setReverseResult(uint reverseApplyId,bool result)external{
        reverseApplications[reverseApplyId].dealed = true;
        uint transactionId = reverseApplications[reverseApplyId].transactionId;
        bool anotherRole = reverseApplications[reverseApplyId].role; //the user who give me the requestion
        address who = transactioninterface.getTransaction_re(anotherRole, transactionId); //who is the user who use this function
        
        
        transactioninterface.dealWithRequestions(transactionId, result);
        if (result){
            uint cardId = transactioninterface.getCardId(transactionId);
            uint price = transactioninterface.getPriceOf(transactionId);
            address buyer = transactioninterface.getBuyer(transactionId);
            address seller = transactioninterface.getSeller(transactionId);a
            uint buyerBalance = accountManagementInterface.getBalanceOf(buyer);
            uint sellerBalance = accountManagementInterface.getBalanceOf(seller);
            
            accountManagementInterface.setBalanceOf(buyer, buyerBalance + price);
            accountManagementInterface.setBalanceOf(seller, seller - price);
           
            if(who == seller)aim = buyer;
            else aim = seller;
            cardManagementInterface.setCardOwner(cardId,aim);
            accountManagementInterface.addCard(seller, cardId);
            accountManagementInterface.removeCard(buyer, cardId);
            
        }
        
    }
    
}