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
}
