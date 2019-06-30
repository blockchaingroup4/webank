pragma solidity ^0.4.25;
contract CardManagementInterface{
    function getPriceOf(address cardId)external view returns(uint);
    function getCardOwner(address cardId)external view returns(address);
    function getCardName(address cardId)external view returns(string);
    function setCardOwner(address cardId, address owner)external;
    function setCardOnSale(address cardId, bool onSale)external;
    function setCardPrice(address cardId, uint price)external;
    function createCard(string name, address cardId, string url, int8 level, address owner)external;
}

contract AccountManagementInterface{
    function getBalanceOf(address addr)external view returns(uint);
    function setBalanceOf(address addr, uint balance)external;
    function removeCard(address owner, address cardId)external;
    function addCard(address who, address cardId)external;
    function addTransaction(address who, uint transactionId)external;
    function getDrawCountOf(address addr)external view returns(uint32);
    function setDrawCountOf(address addr, uint32 count)external;
}
contract TransactionManagementInterface{
    function createTransaction(uint timestamp, address cardId, string cardname, uint price, address sellerAddress, address buyerAddress)external returns(uint);
}
contract MarketContract{
    address[] cardsOnSale;
    event DrawCardEvent(int8 level, address cardId);
    AccountManagementInterface accountManagementInterface;
    CardManagementInterface cardManagementInterface;
    TransactionManagementInterface transactionManagementInterface;
    
    function setACTInterfaces(address accountAddr, address cardAddr, address transactionAddr)external{
        accountManagementInterface = AccountManagementInterface(accountAddr);
        cardManagementInterface = CardManagementInterface(cardAddr);
        transactionManagementInterface = TransactionManagementInterface(transactionAddr);
    }
    
    function setAccoundManagementInterface(address addr)external{
        accountManagementInterface = AccountManagementInterface(addr);
    }
    
    function setCardManagementInterface(address addr)external{
        cardManagementInterface = CardManagementInterface(addr);
    }
    
    function setTransactionManagementInterface(address addr)external{
        transactionManagementInterface = TransactionManagementInterface(addr);
    }
    
    function recharge(address addr, uint amount)external{
        uint balance = accountManagementInterface.getBalanceOf(addr);
        accountManagementInterface.setBalanceOf(addr, balance + amount);
    }
    
    //0:success
    //1:balance not enough
    function buyDrawCards(address addr, uint times)external returns(uint){
        uint amountPerTime = 10;
        uint balance = accountManagementInterface.getBalanceOf(addr);
        uint needBalance = times*amountPerTime;
        if(needBalance > balance){
            return 1;
        }
        accountManagementInterface.setBalanceOf(addr, balance - needBalance);
        uint32 count = accountManagementInterface.getDrawCountOf(addr);
        accountManagementInterface.setDrawCountOf(addr, uint32(count + times));
        return 0;
    }
    
    function pushCard(address cardId, uint price)external returns(uint){
        cardsOnSale.push(cardId);
        cardManagementInterface.setCardOnSale(cardId, true);
        cardManagementInterface.setCardPrice(cardId, price);
        return 0;
    }
    
    //0:success
    //1:card is not on sale
    function pullCard(address cardId)external returns(uint){
        cardManagementInterface.setCardOnSale(cardId, false);
        return _removeCardOnSale(cardId);
    }
    
    function buyCard(address buyer, address cardId)external{
        uint balanceOfBuyer = accountManagementInterface.getBalanceOf(buyer);
        address seller = cardManagementInterface.getCardOwner(cardId);
        uint balanceOfSeller = accountManagementInterface.getBalanceOf(seller);
        uint price = cardManagementInterface.getPriceOf(cardId);
        string memory name = cardManagementInterface.getCardName(cardId);
        cardManagementInterface.setCardOwner(cardId, buyer);
        cardManagementInterface.setCardOnSale(cardId, false);
        
        address buyeraddress = buyer;
        uint transactionId = transactionManagementInterface.createTransaction(now, cardId, name, price, seller, buyeraddress);
        
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
    function _removeCardOnSale(address cardId)private returns(uint){
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
    function drawCard(address who, string wish)external{
        require(accountManagementInterface.getDrawCountOf(who) > 0);
        uint cardIdInt = uint(keccak256(abi.encodePacked(wish))) + 
                uint(keccak256(abi.encodePacked(now, msg.sender, nonce)));
        uint rand = cardIdInt % 10000;
        address cardId = address(cardIdInt);
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
        emit DrawCardEvent(level, cardId);
    }
    
    function createCardAndGiveTo(string name, address who, address cardId, string url, int8 level)external{
        cardManagementInterface.createCard(name, cardId, url, level, who);
        accountManagementInterface.addCard(who, cardId);
        accountManagementInterface.setDrawCountOf(who, accountManagementInterface.getDrawCountOf(who) - 1);
    }
}
