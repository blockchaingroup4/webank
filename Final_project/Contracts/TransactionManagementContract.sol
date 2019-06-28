pragma solidity ^0.4.25;

contract TransactionManagementContract{
    struct Transaction{
        uint transactionId;
        uint timestamp;
        uint cardId;
        string cardname;
        uint price;
        address sellerAddress;
        address buyerAddress;
    }
    
    mapping (uint => Transaction) transactions;
    
    function createTransaction(uint transactionId, uint timestamp, uint cardId, string cardname, uint price, address sellerAddress, address buyerAddress){
        transactions[transactionId] = Transaction(transactionId, timestamp, cardId, cardname, price, sellerAddress, buyerAddress);
    }
    
    function getTransaction(uint transactionId)returns(uint, uint, string, uint){
        Transaction transaction = transactions[transactionId];
        return (transaction.timestamp, transaction.cardId, transaction.cardname, transaction.price);
    }
    
    function getTransaction_re(bool role, uint transactionId) returns(address, string, uint)
    {
        Transaction transaction = transactions[transactionId];
        if (role){
            return (transaction.sellerAddress, transaction.cardname, transaction.timestamp);
        }
        else{
            return (transaction.buyerAddress, transaction.cardname, transaction.timestamp);
        }
        //role:  0:buyer
        //       1:seller
    }
}
