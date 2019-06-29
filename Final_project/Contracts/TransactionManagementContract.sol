pragma solidity ^0.4.25;

contract TransactionManagementContract{
    struct Transaction{
        uint timestamp;
        uint cardId;
        string cardname;
        uint price;
        address sellerAddress;
        address buyerAddress;
        uint transactionId;
        bool isReversing; //the transaction is under reversing
        bool isReversed; // the trasaction has been reversed(one trasaction can only be reversed once)
        bool reverseResult; //true:   successful
                            //false:  unsuccessful
    }
    
    Transaction[] transactions;
    function createTransaction(uint timestamp, uint cardId, string cardname, uint price, address sellerAddress, address buyerAddress) returns (uint){
        uint transactionId = transactions.push(Transaction(timestamp, cardId, cardname, price, sellerAddress, buyerAddress, 0, false, false, false))-1;
        transactions[transactionId].transactionId = transactionId;
        return transactionId;
    }
    
    function getTransaction(uint transactionId)returns(uint, uint, string, uint){
        Transaction transaction = transactions[transactionId];
        return (transaction.timestamp, transaction.cardId, transaction.cardname, transaction.price);
    }
    
    function getTransaction_re(bool role, uint transactionId) returns(address)
    {
        Transaction transaction = transactions[transactionId];
        if (role){
            return (transaction.sellerAddress);
        }
        else{
            return (transaction.buyerAddress);
        }
        //role:  0:buyer
        //       1:seller
    }
    
    function getRole(address who, uint transactionId)returns (bool){
        Transaction transaction = transactions[transactionId];
        if(transaction.sellerAddress == who){
            return false;
        }
        else{
            return true;
        }
    }
    
    function getCardId(uint transactionId)external returns(uint){
        Transaction transaction = transactions[transactionId];
        return transaction.cardId;
    }
    
    function getPriceOf(uint transactionId)external returns(uint){
        Transaction transaction = transactions[transactionId];
        return transaction.price;
    }
    
    function setReversingTrue(uint transactionId)external{
        transactions[transactionId].isReversing = true;
    }
    
    function dealWithRequestions(uint transactionId, bool result){
        transactions[transactionId].isReversed = true;
        transactions[transactionId].isReversing = false;
        transactions[transactionId].reverseResult = result;
        
        //true:exchange the card and delete the reverseApplication
        //false: delete the reverseApplication
    }
}