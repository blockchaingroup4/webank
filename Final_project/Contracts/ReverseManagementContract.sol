pragma solidity ^0.4.25;
contract TransactionInterface{
    function getTransaction_re(bool role, uint transactionId)external view returns(address);
    function getRole(address who, uint transactionId)external view returns (bool);
    function getCardId(uint transactionId)external view returns (uint);
    function getPriceOf(uint transactionId)external view returns(uint);
    function setReversingTrue(uint transactionId)external;
    function dealWithRequestions(uint transactionId, bool result)external;
    function getBuyer(uint transactionId)external view returns(address);
    function getSeller(uint transactionId)external view returns(address);
}

contract AccountManagementInterface{
    function addRequestions(address who, uint requestionId)external;
    function getBalanceOf(address addr)external view returns(uint);
    function setBalanceOf(address addr, uint balance)external;
    function removeCard(address owner, uint cardId)external;
    function addCard(address who, uint cardId)external;
}

contract CardManagementInterface{
    function getCardOwner(uint cardId)external view returns(address);
    function setCardOwner(uint cardId, address owner)external;
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
    TransactionInterface transactionInterface;
    AccountManagementInterface accountManagementInterface;
    CardManagementInterface cardManagementInterface;
    function setACTInterfaces(address accountAddr, address cardAddr, address transactionAddr)external{
        accountManagementInterface = AccountManagementInterface(accountAddr);
        cardManagementInterface = CardManagementInterface(cardAddr);
        transactionInterface = TransactionInterface(transactionAddr);
    }
    
    function getReverseInfo(uint reverseId)external view returns(uint, uint, bool, string, bool){
        ReverseApplication storage reverse = reverseApplications[reverseId];
        return (reverse.reverseApplyId, reverse.transactionId, reverse.role, reverse.discribe, reverse.dealed);
    }
    
    function createReverseApplies(address who, uint transactionId, string discribe)external{
        uint cardId = transactionInterface.getCardId(transactionId);
        require(cardManagementInterface.getCardOwner(cardId) == who);
        
        bool role = transactionInterface.getRole(who, transactionId);
        transactionInterface.setReversingTrue(transactionId);
        uint reverseApplyId = reverseApplications.push(ReverseApplication(0, transactionId, role, discribe, false))-1;
        reverseApplications[reverseApplyId].reverseApplyId = reverseApplyId;
        //create re_applicationId
        //reverseApplications ++
    }
    
    function getReverseAppliesNum() external view returns (uint){
        return reverseApplications.length;
    }
    
    function getReverseApply(uint index)external view returns(uint, uint, bool, string, bool){
        return (reverseApplications[index].reverseApplyId, reverseApplications[index].transactionId, reverseApplications[index].role, 
        reverseApplications[index].discribe, reverseApplications[index].dealed );
    }
    
    function sendReverseInform(uint reverseApplyId)external{
        uint transactionId = reverseApplications[reverseApplyId].transactionId;
        bool role = reverseApplications[reverseApplyId].role;
        address aim = transactionInterface.getTransaction_re(role, transactionId);
    
        accountManagementInterface.addRequestions(aim, reverseApplyId);
    }
    
    function setReverseResult(uint reverseApplyId,bool result)external{
        reverseApplications[reverseApplyId].dealed = true;
        uint transactionId = reverseApplications[reverseApplyId].transactionId;
        bool anotherRole = reverseApplications[reverseApplyId].role; //the user who give me the requestion
        address who = transactionInterface.getTransaction_re(anotherRole, transactionId); //who is the user who use this function
        
        
        transactionInterface.dealWithRequestions(transactionId, result);
        if (result){
            uint cardId = transactionInterface.getCardId(transactionId);
            uint price = transactionInterface.getPriceOf(transactionId);
            address buyer = transactionInterface.getBuyer(transactionId);
            address seller = transactionInterface.getSeller(transactionId);
            uint buyerBalance = accountManagementInterface.getBalanceOf(buyer);
            uint sellerBalance = accountManagementInterface.getBalanceOf(seller);
            
            accountManagementInterface.setBalanceOf(buyer, buyerBalance + price);
            accountManagementInterface.setBalanceOf(seller, sellerBalance - price);
            address aim;
            if(who == seller)aim = buyer;
            else aim = seller;
            cardManagementInterface.setCardOwner(cardId,aim);
            accountManagementInterface.addCard(seller, cardId);
            accountManagementInterface.removeCard(buyer, cardId);
            
        }
        
    }
    
}
