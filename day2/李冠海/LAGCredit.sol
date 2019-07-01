pragma solidity ^0.4.25;
//积分系统
//功能：
//1、初始化积分
//2、总积分查询
//3、积分转账
//4、积分查询
//5、积分转账明细记录
contract LAGCredit{
    string name = "LAGC";
    string symbol = "LAG";
    uint256 totalSupply;
    mapping (address => uint256) private balances;
    event transferEvent(address from, address to, uint256 value);
    
	//构造函数：
	//参数：
	//initialSupply: 初始积分
	//creditName：积分名称
	//creditSymbol：积分简称
	constructor(uint256 initialSupply, string creditName, string creditSymbol)public{
		//初始化积分
        totalSupply = initialSupply;
		//合约部署者拥有所有积分
        balances[msg.sender] = totalSupply;
        name = creditName;
        symbol = creditSymbol;
    }
    
	//总积分查询
    function getTotalSupply()public view returns (uint256){
        return totalSupply;
    }
    
	//积分转账
    function _transfer(address _from, address _to, uint _value)internal{
        require(_to!=0x0);
        require(balances[_from]>=_value);
        require(_value>=0);
        
        uint previousBalances = balances[_from] + balances[_to];
        balances[_from] -=_value;
        balances[_to] += _value;
        emit transferEvent(_from, _to, _value);
        assert(balances[_from] + balances[_to] == previousBalances);
    }
    
	//积分转账
    function transfer(address _to, uint256 _value)public{
        _transfer(msg.sender, _to, _value);
    }   
    
	//积分查询
    function balanceOf(address _owner)public view returns(uint256){
        return balances[_owner];
    }
}