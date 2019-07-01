pragma solidity ^0.4.24;

contract LAG{
    string name="LAGC";
    string symbol="LAG";
    uint256 totalSupply;
    
    mapping(address=>uint256) private balances;
    event transferEvent(address from, address to, uint256 value);
    
    constructor(uint256 initialSupply, string CreditName, string CreditSymbol) public{
        totalSupply=initialSupply;
        balances[msg.sender]=totalSupply;
        name=CreditName;
        symbol=CreditSymbol;
    }
    function getTotalSupply() constant public returns (uint256){
        return totalSupply;
    }
    function _transfer(address _from, address _to, uint256 _value) internal{
        require(_to!=0x0);
        require(balances[_from]>=_value);
        require(balances[_from]+_value>=balances[_from]);
        uint previousBalances=balances[_from]+balances[_to];
        
        balances[_from]-=_value;
        balances[_to]+=_value;
        
        emit transferEvent(_from,_to,_value);
        assert(balances[_from]+balances[_to]==previousBalances);
    }
    function transfer(address _to, uint256 _value) public {
		_transfer(msg.sender, _to, _value);
	}
    function balanceOf(address _owner) constant public returns (uint256){
        return balances[_owner];
    }
}