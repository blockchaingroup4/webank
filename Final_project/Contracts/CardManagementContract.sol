pragma solidity ^0.4.25;

contract CardManagementContract{
    struct Card{
        string name;
        int8 level;
        address cardId;
        string url;
        bool isOnSale;
        uint price;
        address owner;
    }
    mapping (address => Card) cards;

    function setCardPrice(address cardId, uint price)external{
        // require(cards[cardId].owner == msg.sender);
        cards[cardId].price = price;
    }
    
    function getCardInfo(address cardId)external view returns(string, int8, address, string, bool, uint, address){
        Card storage card = cards[cardId];
        return (card.name, card.level, card.cardId, card.url, card.isOnSale, card.price, card.owner);
    }
    
    function getPriceOf(address cardId)external view returns(uint){
        // require(cards[cardId].owner == msg.sender);
        return cards[cardId].price;
    }
    
    function getCardOwner(address cardId)external view returns(address){
        address ownerAddr = cards[cardId].owner;
        return ownerAddr;
    }
    
    function getCardName(address cardId)external view returns(string){
        return cards[cardId].name;
    }
    
    function setCardOwner(address cardId, address owner)external{
        cards[cardId].owner = owner;
    }
    
    function setCardOnSale(address cardId, bool onSale)external{
        cards[cardId].isOnSale = onSale;
    }

    function createCard(string name, address cardId, string url, int8 level, address owner)external{
        cards[cardId] = Card(name, level, cardId, url, false, 99999, owner);
    }

}
