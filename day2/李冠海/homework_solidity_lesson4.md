## Lesson 4 Zombie Battle System
### Chapter 1 Payable - 使用payable修饰符赚以太币
Tips:   
1. 修饰符总结   
  - 可见性修饰符    
  **private: **只能内部调用，子类无法调用    
  **internal: **只能内部调用，子类可以调用   
  **public: **可以内部调用也可以外部调用   
  **external: **只能外部调用    

  - 状态修饰符   
  **view: **只读取区块链上数据，不修改数据   
  **pure: **不读取区块链上数据，也不修改数据

  - 自定义修饰符    
2. payable修饰符   
表示这个函数可以接收以太币   
ether关键字是内置的以太币单位   
msg.value表示接收的以太币数量   
对没有payable修饰的函数发送以太币会被拒绝    
```solidity
function func() external payable{
  require(msg.value == 0.001 ether);
  ....
}
```  

Content:    
定义升级费用变量levelUpFee    
创建升级僵尸的函数   

### Chapter 2 Withdraws - 将以太币从合约帐户转到外部帐户   
Tips:     
```solidity
//从合约向合约调用者转账
address _owner = owner();//使用了Ownable合约
_owner.transfer(address(this).balance);
//或者返回多出的以太币
msg.sender.transfer(msg.value - xxx);
```
Content:    
创建withdraw函数，从合约取款    
创建setLevelUpFee，设置升级所需要的以太币   

### Chapter 3 僵尸攻击合约
Tips: 无   
Content: 创建新的合约ZombieAttack

### Chapter 4 Random Numbers
Tips: 使用keccak256生成随机数（存在安全隐患）    
Content:    
定义randNonce变量，每次生成随机数就加1    
定义randMod函数，用来生成随机数   

### Chapter 5 僵尸攻击
Tips: 无   
Content:    
定义一个变量attackVictoryProbability，表示胜利的可能性   
创建一个函数attack，表示一只僵尸攻击另一只僵尸    

### Chapter 6 消除重复代码    
Tips: 无   
Content:
创建修饰符ownerOf判断当前合约的调用者是否是僵尸的拥有者   
用ownerOf修饰feedAndMultiply函数   

### Chapter 7 继续消除重复代码    
Tips: 无
Content:    
更新changeName函数    
更新changeDna函数   

### Chapter 8 完善僵尸攻击    
Tips: 无   
Content:    
给attack函数添加ownerOf修饰符   
获取攻击僵尸的引用和被攻击僵尸的引用    
使用随机数来决定攻击的结果   

### Chapter 9 处理僵尸攻击的胜利和失败    
Tips: 无   
Content:    
给Zombie结构体添加winCount和lossCount两个属性    
更新_createZombie()函数   

### Chapter 10 判断攻击的胜负    
Tips: 无   
Content:    
在attack方法添加if语句判断攻击的胜负    
如果胜利，那么攻击僵尸升级，胜利次数加1，被攻击僵尸失败次数加1，给僵尸吃dna    

### Chapter 11 补充判断   
Tips: 无   
Content:    
补充attack函数中的if判断，增加else情况   
处理被攻击僵尸胜利的情况    

### Chapter 12 Conclusion   
Tips: 无   
Content: 选择一只僵尸来攻击别的僵尸    
