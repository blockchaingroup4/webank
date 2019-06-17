# Lesson 1 Making the Zombie Factory
## Chapter 1 Overview   
略   
## Chapter 2 Contract - 定义合约、声明版本    
Tips:   
用contract关键字来定义合约    
用pragma solidity ^0.4.25来声明版本   
```solidity
//版本
pragma solidity ^0.4.25;
//定义合约
contract ContractName{
}
```
Content:    
声明合约使用的solidity版本为0.4.25    
创建ZombieFactory合约   
## Chapter 3 Variables & Inters - 变量    
Tips:   
```solidity
//定义一个变量
DataType varName = varValue;
```   
uint即uint256，除了uint256，还有uint8，uint16，uint32等   
Content:    
定义一个变量dnaDigits，初始值为16    
## Chapter 4 Math - 数学运算    
Tips:   
加法: a+b     
减法: a-b     
乘法: a\*b     
除法: a/b     
取余: a%b     
求幂: a\**b    
Content:    
定义dnaModulus=10\**dnaDigits   
## Chapter 5 Structs - 使用结构体    
Tips: 定义结构体的方式和C++类似    
```solidity
struct Struct{
  Type attr;
  ....
}
```   
Content:    
定义一个叫Zombie的结构体   
Zombie结构体有两个属性：名字和DNA   
## Chapter 6 Arrays - 使用数组    
Tips: 数组可以是定长的也可以是变长的   
```solidity
//定长数组
uint[3] fixedArray
//变长数组  
uint[] dynamicArray
```   
Content:    
创建一个僵尸数组zombies   
## Chapter 7 Function - 定义和使用函数   
Tips: 定义和使用函数的方法如下
```solidity
//定义函数
function func(Type1 arg1, Type2 arg2){
}
//使用函数
func(par1, par2);
```   
Content: 定义一个根据名字和dna来创建僵尸的函数createZombie   
## Chapter 8 Array & Struct - 给变长数组添加元素，创建结构体对象    
Tips: 通过push给变长数组添加元素   
```solidity
uint[] arr;
//给数组添加元素
arr.push(1);
//定义结构体
struct Object{
  Type1 attr1;
  Type2 attr2;
  ....
}
//创建结构体对象
Object obj = Object(par1, par2);
```   
Content:    
在createZombie函数中创建一个僵尸对象，并将它加入到僵尸数组中    
## Chapter 9 Private/Public - 可见性修饰符    
Tips:   
函数可以是public的也可以是private的    
public表示所有人都可以调用    
private表示只有合约内部可以调用   
solidity的函数默认是public的   
private函数命名通常以下划线开头   
```solidity
//将函数声明为public
function func() public{
}
```
Content:    
将createZombie修改为private，同时修改名字使其符合规范    
## Chapter 10 Return & Modifier - 返回值和修饰符    
Tips:   
```solidity
//通过returns来说明返回的类型
//通过return(..., ..., ...)来返回数据
function func() returns (uint, uint){
  return (1, 2);
}
//view表示只读不修改
//pure表示不读不修改
contract Contract{
  uint variable;
  //只读不修改
  function func1() view returns (uint){
    return variable;
  }
  //不读不修改
  function func2() pure returns (uint){
    return 10;
  }
}
```   
Content:    
创建一个private函数_generateRandomDna   
## Chapter 11 Keccak256 & Typecasting - 使用keccak256函数和类型转换    
Tips:   
keccak256(bytes memory) returns (bytes32)的输入类型是bytes，返回值类型是bytes32    
它对字符串进行映射   
通常需要使用abi.encodePacked函数将字符串转为bytes   
```solidity
//使用keccak256并进行类型转换
uint res = uint(keccak256(abi.encodePacked("xxx")));
```   
Content:    
使用keccak256根据僵尸名字生成dna，再对dna进行取余操作    
## Chapter 12
Tips: 无   
Content:    
创建一个public函数createRandomZombie用来创建僵尸，这个函数主要通过调用_generateRandomDna来生成随机dna，然后再调用_createZombie来创建僵尸   
## Chapter 13 Event - 定义和使用事件   
Tips:   
```solidity
//定义事件
event DoSomethingEvent(Type1 arg1, Type2 arg2);
//触发事件
function doSomething(){
  ....
  emit DoSomethingEvent(par1, par2);
}
```
Content:    
定义一个事件NewZombie，当调用_createZombie方法时，触发这个事件    
