pragma solidity ^0.4.25;
contract CardManagementInterface{
    //get Card id
    function getPriceOf(uint cardId)external returns(uint256);
    function getCardOwner(uint cardId)external returns(address);
    function setCardOwner(uint cardId, address owner)external;
    function setCardOnSale(uint cardId, bool onSale)external;
}

contract AccountManagementInterface{
    function getBalanceOf(address addr)external returns(uint256);
    function removeCard(address owner, uint cardId)external;
    function addCard(address who, uint cardId)external;
    //get balance of 
    //set balance of 
}

contract MarketContract{
    uint[] cardsOnSale;
    AccountManagementInterface accountManagementInterface;
    CardManagementInterface cardManagementInterface;
    function recharge(address addr, uint256 ammount){
        // accountManagementContract.recharge(addr, ammount);
    }
    
    function setAccountManagementContract(address addr){
        // accountManagementContract = AccountManagementContract(addr);
    }
    
    function buyDrawCards(address add, int32 times){
        //get balance
        //...
        //set balance
        // accountManagementContract.buyDrawCards(add, times);
    }
    
    function pushCard(address who, uint cardId, uint price){
        
        //get cardId
        //cardsOnSale++
    }
    
    function pullCard(uint cardId){
        //cardsOnSale--
    }
    
    function buyCard(address who, uint cardId){
        uint256 balance = accountManagementInterface.getBalanceOf(who);
        uint256 price = cardManagementInterface.getPriceOf(cardId);
        address oldOwner = cardManagementInterface.getCardOwner(cardId);
        cardManagementInterface.setCardOwner(who);
        accountManagementInterface.removeCard(oldOwner, cardId);
        accountManagementInterface.addCard(who, cardId);
        _removeCardsOnSale(cardId);
        
        //get cardId
        //set card owner
        //set owner card
        //cardsOnSale--
    }
    
    uint nonce = 0;
    function drawCard(string wish)external returns(int8, uint){
        uint cardId = uint(keccak256(abi.encodePacked(wish))) + 
                uint(keccak256(now, msg.sender, nonce));
        uint rand = cashId % 10000;
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
        }else(rand < 10000){
            level = 6;
        }
        return (level, cardId);
    }
    
    function giveCardTo(address who, uint cardId, string url, int8 level){
        
    }
    
    function _removeCardsOnSale(uint cardId)private{
        //todo
        for(;;){
            
        }
        cardManagementInterface.setCardOnSale(cardId, false);
    }
    
    //function changeCardOwner(string hashId, uint256 price, address _from, address _to) external returns(bool){
    //     int index = -1;
    //     for (uint i = 0; i<accounts[_from].cardsId.length; i++){
    //         if(keccak256(accounts[_from].cardsId[i]) == keccak256(hashId) && price <= accounts[_to].balance){
    //             index = int(i);
    //             accounts[_to].balance = accounts[_to].balance - price;
    //             accounts[_from].balance = accounts[_from].balance + price;
    //             break;
    //         }
    //     }
        
    //     if(index != -1){
    //         for(i = uint(index); i < accounts[_from].cardsId.length - 1; i++){
    //             accounts[_from].cardsId[i] = accounts[_from].cardsId[i + 1];
    //         }
    //         accounts[_from].cardsId.length--;
    //         accounts[_to].cardsId.push(hashId);
    //         return true;
    //     }
    //     return false;
    // }
}
