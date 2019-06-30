pragma solidity ^0.4.25;

contract TransactionManagementContract{
    struct Transaction{
        uint timestamp;
        address cardId;
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
    function createTransaction(uint timestamp, address cardId, string cardname, uint price, address sellerAddress, address buyerAddress)external returns (uint){
        uint transactionId = transactions.push(Transaction(timestamp, cardId, cardname, price, sellerAddress, buyerAddress, 0, false, false, false))-1;
        transactions[transactionId].transactionId = transactionId;
        return transactionId;
    }
    
    function getTransactionInfo(uint transactionId)external view returns(uint, address, string, uint, address, address, uint, bool, bool, bool){
        Transaction storage transaction = transactions[transactionId];
        return (transaction.timestamp, transaction.cardId, transaction.cardname, transaction.price, transaction.sellerAddress, transaction.buyerAddress,
                transaction.transactionId, transaction.isReversing, transaction.isReversed, transaction.reverseResult);
    }
    
    function getTransaction(uint transactionId)external view returns(uint, address, string, uint){
        Transaction storage transaction = transactions[transactionId];
        return (transaction.timestamp, transaction.cardId, transaction.cardname, transaction.price);
    }
    
    function getTransaction_re(bool role, uint transactionId)external view returns(address)
    {
        Transaction storage transaction = transactions[transactionId];
        if (role){
            return (transaction.sellerAddress);
        }
        else{
            return (transaction.buyerAddress);
        }
        //role:  0:buyer
        //       1:seller
    }
    
    function getRole(address who, uint transactionId)external view returns (bool){
        Transaction storage transaction = transactions[transactionId];
        if(transaction.sellerAddress == who){
            return false;
        }
        else{
            return true;
        }
    }
    
    function getCardId(uint transactionId)external view returns(address){
        Transaction storage transaction = transactions[transactionId];
        return transaction.cardId;
    }
    
    function getPriceOf(uint transactionId)external view returns(uint){
        Transaction storage transaction = transactions[transactionId];
        return transaction.price;
    }
    
    function setReversingTrue(uint transactionId)external{
        transactions[transactionId].isReversing = true;
    }
    
    function dealWithRequestions(uint transactionId, bool result)external{
        transactions[transactionId].isReversed = true;
        transactions[transactionId].isReversing = false;
        transactions[transactionId].reverseResult = result;
        
        //true:exchange the card and delete the reverseApplication
        //false: delete the reverseApplication
    }
    
    function getBuyer(uint transactionId)external view returns(address){
        return transactions[transactionId].buyerAddress;
    }
    
    function getSeller(uint transactionId)external view returns(address){
        return transactions[transactionId].sellerAddress;
    }
}
