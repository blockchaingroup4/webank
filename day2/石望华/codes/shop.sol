pragma solidity ^0.4.25;
//import "./Ownable.sol";
//import "./AccountStruct.sol";

contract shop {
    // 注册事件
     event RegisterEvent(address account, uint256 asset_value);

    // 通知客户端积分交易发生
    event transferEvent(address _from, address _to, uint256 value);
    
    string ShopName = "南京先锋书店";
    string CreditName = "LAGC";  // 积分名称 LIBRAIRIE AVANT-GARDE CREDIT 
    string CreditSymbol = "LAG"; // 积分简称 
    uint256 TotalSupply = 100000;         // 积分发行量
    
    // 账户地址映射到积分余额
    // 地址包括商店地址和用户地址
    mapping (address => uint) private AddressToBalances;    
    
    // 构造函数，由积分创建者执行：书店 
    constructor (uint initialSupply, string creditName, string creditSymbol) public payable 
    { 
        TotalSupply = initialSupply;
        AddressToBalances[msg.sender] = TotalSupply; 
        CreditName = creditName;
        CreditSymbol = creditSymbol;
    }
    
    // 查询积分发放总额
    function getTotalSupply() public constant returns (uint256) { return TotalSupply;}
    
    // 查询账户余额
    function balanceOf(address _owner) public constant returns (uint256) { return AddressToBalances[_owner];}
    
    // 积分的发送函数，内部函数 
    function transfer(address _from, address _to, uint _value) public {
        require(_to != 0x0); 
        require(AddressToBalances[_from] >= _value); 
        require(AddressToBalances[_to] + _value > AddressToBalances[_to]); //_value大于0
        
        uint previousBalances = AddressToBalances[_from] + AddressToBalances[_to];
        // 开始转账
        AddressToBalances[_from] -= _value;
        AddressToBalances[_to] += _value;
        emit transferEvent(_from, _to, _value); // 记录转账并通知客户端发生积分交易 
        assert(AddressToBalances[_from] + AddressToBalances[_to] == previousBalances);
}
}
