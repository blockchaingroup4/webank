pragma solidity ^0.4.25;

contract CardManagementContract{
    struct Card{
        int8 level;
        uint cardId;
        string url;
        bool isOnSale;
        uint256 price;
        address owner;
    }
    mapping (uint => Card) cards;
    
    function setCardPrice(uint cardId, uint256 price)public{
        require(cards[cardId].owner == msg.sender);
        cards[cardId].price = price;
    }
    
    function getPriceOf(uint cardId)external returns(uint){
        require(cards[cardId].owner == msg.sender);
        uint price = cards[cardId].price;
        return price;
    }
    
    function getCardOwner(uint cardId)external returns(address){
        address ownerAddr = cards[cardId].owner;
        return ownerAddr;
    }
    
    function setCardOwner(uint cardId, address owner)external{
        cards[cardId].owner = owner;
    }
    
    function setCardOnSale(uint cardId, bool onSale)external{
        cards[cardId].isOnSale = onSale;
    }
    
    function createCard(uint cardId, string url, int8 level, address owner){
        Card card = Card(level, cardId, url, false, 99999, owner);
        cards[cardId] = card;
        
    }

}