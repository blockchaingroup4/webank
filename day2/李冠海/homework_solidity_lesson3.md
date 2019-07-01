# Solidity 笔记
## Lesson 3 Advanced Solidity Concepts
### Chapter 1 Immutability of Contracts   
Tips: 合约在部署之后就无法修改和更新   
Content: 创建函数setKittyContractAddress用来修改Kitty合约的地址    

### Chapter 2 Ownable - 使用OpenZeppelin库提高安全性    
Tips: 通过继承Ownable来管理合约的持有者信息    
Content: 导入ownable.sol，使ZombieFactory继承Ownable   

### Chapter 3 Modifier - 如何使用Modifier    
Tips: 通过modifier关键可以自定义函数修饰符（function modifier）：
```solidity
//定义函数修饰符
modifier funcModifier(){
  require(XXXX);
  _;
}
//用自定义函数修饰符修饰函数（放在可见性修饰符后面）
//每次调用func时都会先执行funcModifier，funcModifier执行到_;就会返回
function func() public funcModifier{
}
```
Content: 给setKittyContractAddres添加onlyOwner修饰符，保证只有合约所有者可以调用这个函数。
### Chpater 4 Gas - 定义struct时使用合适的类型以节省gas
Tips: 结构体越小越节省gas，将同一类型的数据放一一起可以减小结构体大小    
Content：给Zombie结构体增加了两个数据成员:level（等级）和readyTime（下一次可以吃东西的时间）
### Chapter 5 Time uints - 内置变量和时间单位的使用
Tips:   
1. now: 当前unix的时间戳（从1970年1月1日起，单位是秒，类型是uint256）
2. seconds, minutes, hours, days, weeks, years
3. 1 minutes = 60, 1 hours = 3600, 1days = 86400....    

Content:    
定义冷却时间cooldownTime为1天   
更新_createZombie方法，level = 1, readyTime = uint32(now + cooldownTime)   
### Chapter 6 Storage - 传递引用/指针    
Tips: 通过storage关键字传递引用：
```Solidity
function func(Type storage sym){
    sym.attr = xxx;
    sym.call(...);
}
```
Content:    
定义_triggerCooldown方法来更新冷却时间   
定义_isReady方法来判断冷却是否结束   
### Chapter 7 Internal - 修改可见性以提高安全性
Tips: 无   
Content:    
将feedAndMultiply修改为internal   
调用_isReady()判断是否可以吃   
调用_triggerCooldown()来触发冷却时间   
### Chapter 8 Modifier - 带参数的modifier
Tips: modifier可以带参数：
```solidity
modifier funcModifier(Type1 arg1, Type2 arg2){
  require(....);
  _;
}
//arg1、arg2可以是常数
function func(Type1 arg1, Type2 arg2) public funcModifier(arg1, arg2){
  ....;
}
```
Content: 创建aboveLevel函数修饰符，用来判断僵尸等级是否满足条件    
### Chapter 9
Tips: 无   
Content:    
创建更名函数，用来更改僵尸名字，使用aboveLevel修饰符（2级及以上）   
创建changeDna函数，用来更改僵尸dna，使用aboveLevel修饰符（20级及以上）
### Chapter 10 Gas - 使用view关键字以节省gas
Tips:   
用view修饰的函数在被外部调用执行时不消耗gas，非view函数调用view函数仍然需要消耗gas    
Content:    
创建函数getZombiesByOwner   
### Chapter 11 Memory/New - 使用memory避免将数据写到storage    
Tips:   
storage的成本很高，所有节点都要维持写入storage的数据，所以应尽量避免storage    
Content:    
完善函数getZombiesByOwner，主要是通过memory创建临时数组（使用new运算符），并返回
### Chapter 12 For Loops - 在view或者pure函数中使用for循环    
Tips: 和C++for循环一样:    
```solidity
for(uint i = 0; i < 10; i++){
  ....
}
```
数组的长度:    
```solidity
arr.length
```
Content:    
在getZombiesByOwner方法中使用for循环找到所有僵尸    
