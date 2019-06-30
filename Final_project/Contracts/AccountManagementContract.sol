pragma solidity ^0.4.25;
contract AccountManagementContract{
    struct Account{
        string name;
        uint balance;
        uint32 drawCount;
        address[] cardsId;
        uint[] transactionsId;
        uint[] requestionsId;   //reverseapplications(requestions) the user get
    }
    mapping(address => Account)accounts;
    
    function addAccount(address addr, string name)public{
        accounts[addr] = Account(name, 0, 5, new address[](0), new uint[](0), new uint[](0));
    }
    
    function getAccountInfo(address who)external view returns(string, uint, uint32, uint, uint, uint){
        require(msg.sender == who);
        Account storage account = accounts[who];
        return (account.name, account.balance, account.drawCount, account.cardsId.length, account.transactionsId.length, account.requestionsId.length);
    }
    
    function getBalanceOf(address addr)external view returns(uint){
        return accounts[addr].balance;
    }
    
    function setBalanceOf(address addr, uint balance)external{
        accounts[addr].balance = balance;
    }
    
    function removeCard(address owner, address cardId)external{
        address[] storage ids = accounts[owner].cardsId;
        for(uint i = 0; i < ids.length; i++){
            if(ids[i] == cardId){
                for(uint j = i; j < ids.length - 1; j++){
                    ids[j] = ids[j+1];
                }
                ids.length--;
                return;
            }
        }
    }
    
    function addCard(address who, address cardId)external{
        accounts[who].cardsId.push(cardId);
    }
    
    function getCardsNum(address who)external view returns(uint){
        return accounts[who].cardsId.length;
    }
    
    function getCardId(address who, uint index)external view returns(address){
        return accounts[who].cardsId[index];
    }
    
    function getDrawCountOf(address addr)external view returns(uint32){
        return accounts[addr].drawCount;
    }
    
    function setDrawCountOf(address addr, uint32 count)external{
        accounts[addr].drawCount = count;
    }
    
    function addTransaction(address who, uint transactionId)external{
        accounts[who].transactionsId.push(transactionId);
    } 
    
    function getTransactionsNum(address who)external view returns(uint){
        return accounts[who].transactionsId.length;
    }
    
    function getTransactionId(address who, uint index)public view returns(uint){
        return accounts[who].transactionsId[index];
    }
   
    function addRequestions(address who, uint requestionId)external{
        accounts[who].requestionsId.push(requestionId);    
    }
    
    function getRequestionsNum(address who)external view returns(uint){
        return accounts[who].requestionsId.length;
    }
    
    function getRequestionId(address who, uint index)public view returns(uint){
        return accounts[who].requestionsId[index];
    }
}
