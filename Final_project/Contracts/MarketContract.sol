pragma solidity ^0.4.25;
contract CardManagementInterface{
    function getPriceOf(uint cardId)external returns(uint);
    function getCardOwner(uint cardId)external returns(address);
    function getCardName(uint cardId)external returns(string);
    function setCardOwner(uint cardId, address owner)external;
    function setCardOnSale(uint cardId, bool onSale)external;
    function setCardPrice(uint cardId, uint price)external;
    function createCard(uint cardId, string url, int8 level, address owner);
}

contract AccountManagementInterface{
    function getBalanceOf(address addr)external returns(uint);
    function setBalanceOf(address addr, uint balance)external;
    function removeCard(address owner, uint cardId)external;
    function addCard(address who, uint cardId)external;
    function addTransaction(address who, uint transactionId)external;
    function getDrawCountOf(address addr)external returns(uint32);
    function setDrawCountOf(address addr, uint count)external;
}
contract TransactionManagementInterface{
    function createTransaction(uint transactionId,  uint timestamp, uint cardId, 
    string cardname, uint price, address sellerAddress, address buyerAddress)external returns(uint);
}
contract MarketContract{
    uint[] cardsOnSale;
    AccountManagementInterface accountManagementInterface;
    CardManagementInterface cardManagementInterface;
    TransactionManagementInterface transactionManagementInterface;
    
    function setAccoundManagementInterface(address addr){
        accountManagementInterface = AccountManagementInterface(addr);
    }
    
    function setCardManagementInterface(address addr){
        cardManagementInterface = CardManagementInterface(addr);
    }
    
    function recharge(address addr, uint amount){
        uint balance = accountManagementInterface.getBalanceOf(addr);
        accountManagementInterface.setBalanceOf(addr, balance + amount);
    }
    
    //0:success
    //1:balance not enough
    function buyDrawCards(address addr, uint times)returns(uint){
        uint amountPerTime = 10;
        uint balance = accountManagementInterface.getBalanceOf(addr);
        uint needBalance = times*amountPerTime;
        if(needBalance > balance){
            return 1;
        }
        accountManagementInterface.setBalanceOf(addr, balance - needBalance);
        uint32 count = accountManagementInterface.getDrawCountOf(addr);
        accountManagementInterface.setDrawCountOf(addr, count + times);
        return 0;
    }
    
    function pushCard(address who, uint cardId, uint price)returns(uint){
        cardsOnSale.push(cardId);
        cardManagementInterface.setCardOnSale(cardId, true);
        cardManagementInterface.setCardPrice(cardId, price);
        return 0;
    }
    
    //0:success
    //1:card is not on sale
    function pullCard(uint cardId)returns(uint){
        cardManagementInterface.setCardOnSale(cardId, false);
        return _removeCardOnSale(cardId);
    }
    
    function buyCard(address buyer, uint cardId){
        uint balanceOfBuyer = accountManagementInterface.getBalanceOf(buyer);
        address seller = cardManagementInterface.getCardOwner(cardId);
        uint balanceOfSeller = accountManagementInterface.getBalanceOf(seller);
        uint price = cardManagementInterface.getPriceOf(cardId);
        string memory name = cardManagementInterface.getCardName(cardId);
        uint timestamp = now;
        
        cardManagementInterface.setCardOwner(cardId, buyer);
        cardManagementInterface.setCardOnSale(cardId, false);
        
        address buyeraddress = buyer;
        uint transactionId = transactionManagementInterface.createTransaction(0, now, cardId, name, price, seller, buyeraddress);
        
        accountManagementInterface.removeCard(seller, cardId);
        accountManagementInterface.addCard(buyer, cardId);
        
        accountManagementInterface.addTransaction(buyer, transactionId);
        accountManagementInterface.addTransaction(seller, transactionId);
        
        accountManagementInterface.setBalanceOf(buyer, balanceOfBuyer -  price);
        accountManagementInterface.setBalanceOf(seller, balanceOfSeller + price);
        
        _removeCardOnSale(cardId);
    }
    
    //0:success
    //1:card is not on sale
    function _removeCardOnSale(uint cardId)private returns(uint){
        for(uint i = 0; i < cardsOnSale.length; i++){
            if(cardsOnSale[i] == cardId){
                for(uint j = i; j < cardsOnSale.length-1; j++){
                    cardsOnSale[j] = cardsOnSale[j+1];
                }
                cardsOnSale.length--;
                return 0;
            }
        }
        return 1;
    }
    
    uint nonce = 0;
    function drawCard(string wish)external returns(int8, uint){
        uint cardId = uint(keccak256(abi.encodePacked(wish))) + 
                uint(keccak256(now, msg.sender, nonce));
        uint rand = cardId % 10000;
        nonce++;
        int8 level = 0;
        if(rand < 5000){
            level = 1;
        }else if(rand < 7500){
            level = 2;
        }else if(rand < 8550){
            level = 3;
        }else if(rand < 9390){
            level = 4;
        }else if(rand <9890){
            level = 5;
        }else{
            level = 6;
        }
        return (level, cardId);
    }
    
    function createCardAndGiveTo(address who, uint cardId, string url, int8 level){
        cardManagementInterface.createCard(cardId, url, level, who);
        accountManagementInterface.addCard(who, cardId);
    }
}