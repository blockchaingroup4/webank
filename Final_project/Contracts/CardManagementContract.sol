pragma solidity ^0.4.25;

contract CardManagementContract{
    struct Card{
        string name;
        int8 level;
        uint cardId;
        string url;
        bool isOnSale;
        uint price;
        address owner;
    }
    mapping (uint => Card) cards;
    
    function setCardPrice(uint cardId, uint256 price)public{
        // require(cards[cardId].owner == msg.sender);
        cards[cardId].price = price;
    }
    
    function getCardInfo(uint cardId)external view returns(string, int8, uint, string, bool, uint, address){
        Card storage card = cards[cardId];
        return (card.name, card.level, card.cardId, card.url, card.isOnSale, card.price, card.owner);
    }
    
    function getPriceOf(uint cardId)external view returns(uint){
        // require(cards[cardId].owner == msg.sender);
        return cards[cardId].price;
    }
    
    function getCardOwner(uint cardId)external view returns(address){
        address ownerAddr = cards[cardId].owner;
        return ownerAddr;
    }
    
    function getCardName(uint cardId)external view returns(string){
        return cards[cardId].name;
    }
    
    function setCardOwner(uint cardId, address owner)external{
        cards[cardId].owner = owner;
    }
    
    function setCardOnSale(uint cardId, bool onSale)external{
        cards[cardId].isOnSale = onSale;
    }
    
    function createCard(string name, uint cardId, string url, int8 level, address owner)external{
        cards[cardId] = Card(name, level, cardId, url, false, 99999, owner);
    }

}
