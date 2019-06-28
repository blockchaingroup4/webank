pragma solidity ^0.4.25;
import "./Ownable.sol";

contract AccountManagementContract is Ownable{
    struct Account{
        string name;
        uint256 balance;
        uint32 drawCount;
        uint[] cardsId;
        uint[]transactionsId;
    }
    mapping(address => Account)accounts;
    function addAccount(address addr, string name)public{
        accounts[addr] = Account(name, 0, 5, new string[](0));
    }
    
    function getAccountInfo(address addr)external returns(string, uint256, uint32, uint256){
        //returns string name, uint256 balance, uint32 drawCount
        //for cardsLength
        
    }
    
    function getNumOfTransaction(address who)returns(uint){
        require(msg.sender == who);
        return accounts[who].transactionsId.length;
    }
    
    function getTransactionIdOf(uint index, address who)returns(uint){
        require(msg.sender == who);
        return accounts[who].transactionsId[index];
    }
    
    struct ApplicantInfo{
        address addr;
        string name;
    }
    mapping(address => ApplicantInfo) applicants;
    address[] applicantAddresses;
    
    function applyForAccount(address addr, string name)public{
        applicants[addr] = ApplicantInfo(addr, name);
        applicantAddresses.push(addr);
    }

    function getNumOfApplicants()public view returns(int256) {
        return int256(applicantAddresses.length);
    }
    
    function getApplicantInfo(uint index)public view returns(address, string name) {
        return (applicants[applicantAddresses[index]].addr, applicants[applicantAddresses[index]].name);
    }
    
    function agree(address addr) onlyOwner{
        addAccount(addr, applicants[addr].name);
        _deleteApplicant(addr);
    }
    
    function disagree(address addr) onlyOwner{
        _deleteApplicant(addr);
    }
    
    function _deleteApplicant(address addr)private{
        delete applicants[addr];
        int index = -1;
        for(uint i = 0; i < applicantAddresses.length; i++){
            if(applicantAddresses[i] == addr){
                index = int(i);
                break;
            }
        }
        if(index != -1){
            delete applicantAddresses[uint(index)];
            for(i = uint(index); i < applicantAddresses.length - 1; i++){
                applicantAddresses[i] = applicantAddresses[i+1];
            }
            applicantAddresses.length--;
        }
    }
    
    function changeAccountInfo(string name){
        //change name
    }
    

}
