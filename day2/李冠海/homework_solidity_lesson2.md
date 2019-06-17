# Lesson 2 Attack Their Victims   
## Chapter 1 Overview   
略   
## Chapter 2 Mapping & Address - 映射和地址    
Tips:   
solidity中有两个数据类型：mapping和address    
```solidity
//mapping用法   
mapping(address => uint) public acountBalance;
```   
Content:    
创建一个从僵尸到僵尸拥有者的映射zombieToOwner   
创建一个拥有僵尸数量的映射ownerZombieCount   
## Chapter 3 msg.sender - 使用内置的全局变量msg    
Tips:   
```solidity
address senderAddress = msg.sender;
```   
Content:    
建立msg.sender和僵尸id的映射    
每创建一只僵尸，ownerZombieCount对应的键的值就要加1    
## Chapter 4 require - 使用require函数    
Tips:   
```solidity
//require相当于assert
require(cond1 == cond2);
//如果满足条件则继续运行，否则停止执行    
```   
Content:    
在createRandomZombie一开始加上require来保证每个用户只能创建一只僵尸    
## Chapter 5 inheritance - 继承   
Tips:   
```solidity
//在solidity中通过is来继承合约   
//继承合约的同时会继承数据成员和成员函数   
contract Base{
}
contract Derived is Base{
}
```   
Content:    
创建一个合约ZombieFeeding并使它继承自ZombieFactory    
## Chapter 6 import - 导入    
Tips:
```solidity
//使用import来导入其它文件
import "./otherContract.sol";
```   
Content:    
将zombiefactory.sol导入zombiefeeding.sol   
## Chapter 7 storage vs memory    
Tips:   
```solidity   
//storage表示变量永久存储在区块链上
//memory表示变量是临时的，之后会被清除
//storage还可以用来表示引用
//在和结构体数组变互时有时需要使用指定是storage还是memory，一般情况不需要指定   
struct TestStruct{
}
TestStruct[] arr;
//obj1是arr[0]的一个指针/引用
//改变obj1的属性会改变arr[0]的属性
TestStruct storage obj1 = arr[0];
//obj2是arr[1]的一个副本
//改变obj1的属性不会改变arr[0]的属性
TestStruct memory obj2 = arr[1];
```
Content:    
定义一个函数feedAndMultiply用来喂养僵尸   
在feedAndMultiply函数中调用require函数来保证调用者是僵尸的持有者   
定义一个局部storage变量myZombie指向调用者的僵尸   
## Chapter 8    
Tips: 无   
Content:    
对_targetDna取余   
定义一个newDna，它是myZombie的dna和_targetDna的平均值    
调用_createZombie来创建僵尸    
## Chapter 9 Visibility - 可见性   
Tips:   
external表示只有外部可以调用    
internal和private相似，但继承的子类也可以调用父类的函数   
Content:    
将_createZombie()函数的可见性由private改为internal    
## Chapter 10 Interacting - 和其它合约交互   
Tips:   
```solidity
//和其它合约交互需要定义其它合约的接口
contract OtherContractInterface{
  function func();
}
```   
Content:    
创建一个合约接口KittyInterface    
## Chapter 11 Interacting - 使用其它合约    
Tips:   
```solidity
//根据其它合约的地址来创建接口实例
address addr = 0xabcd....;
OtherContractInterface interface = OtherContractInterface(addr);
```   
Content:    
定义一个变量ckAddress用来存储其它合约的地址    
根据合约地址创建KittyInterface的一个实例   
## Chapter 12 Multiple return values - 处理多个返回值    
Tips:   
```solidity
//返回多个值
function func()returns (uint,uint){
  return (1,2);
}
//获取返回值
uint retV1;
uint retV2;
(retV1, retV2)=func();
//省略返回值
(,retV2)=func();
```   
Content:    
定义一个函数feedOnKitty   
在这个函数中调用kittyContract.getKitty方法来获取kitty    
调用feedAndMultiply来喂养僵尸    
## Chapter 13 If - 使用if语句   
Tips:   
```solidity   
//if的用法和c++一样
if(....){
  ....
}
```   
Content:    
给feedAndMultiply函数添加一个参数_species用来表示物种    
判断物种是否是kitty    
如果是kitty，将新dna后两位改为99   
调用feedAndMultiply来喂养僵尸    
