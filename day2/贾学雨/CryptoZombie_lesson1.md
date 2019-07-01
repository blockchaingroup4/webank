# CryptoZombie游戏学习

## Lesson 1 搭建僵尸工厂

### 第1章 课程概述

### 第2章 合约

+ #### 合约模板

  ``` 
  contract HelloWorld {
  
  }
  ```

+ #### 版本指令

  ##### 所有的 Solidity 源码都必须冠以 "version pragma" — 标明 Solidity 编译器的版本. 以避免将来新的编译器可能破坏你的代码。例如: `pragma solidity ^0.4.19;` (当前 Solidity 的最新版本是 0.4.19).

### 第3章 状态变量和整数

#### **状态变量**是被永久地保存在合约中。也就是说它们被写入以太币区块链中. 想象成写入一个数据库。

### 第4章 数学运行

#### 在 Solidity 中，数学运算很直观明了，与其它程序设计语言相同。Solidity 还支持 **乘方操作** (如：x 的 y次方） // 例如： 5 ** 2 = 25

### 第5章 结构体

#### Solidity 提供了 **结构体**：

``` 
struct Person {
  uint age;
  string name;
}
```

### 第6章 数组

+ #### 如果你想建立一个集合，可以用 **_数组_**这样的数据类型. Solidity 支持两种数组: **_静态_** 数组和**_动态_** 数组:

  ``` 
  // 固定长度为2的静态数组:
  uint[2] fixedArray;
  // 固定长度为5的string类型的静态数组:
  string[5] stringArray;
  // 动态数组，长度不固定，可以动态添加元素:
  uint[] dynamicArray;
  ```

+ #### 公共数组：你可以定义 `public` 数组, Solidity 会自动创建 **getter** 方法. 语法如下:

  ``` 
  Person[] public people;
  ```

### 第7章 定义函数

#### 在 Solidity 中函数定义的句法如下:

``` 
function eatHamburgers(string _name, uint _amount) {

}
```

### 第8章 使用结构体和数组

+ #### 创建新的结构体

  ``` 
  struct Person {
    uint age;
    string name;
  }
  
  Person[] public people;
  ```

+ #### 添加进数组

  ``` 
  // 创建一个新的Person:
  Person satoshi = Person(172, "Satoshi");
  
  // 将新创建的satoshi添加进people数组:
  people.push(satoshi);
  ```

### 第9章 私有/公有函数

#### Solidity 定义的函数的属性默认为`公共`。 这就意味着任何一方 (或其它合约) 都可以调用你合约里的函数。将自己的函数定义为`私有`是一个好的编程习惯，只有当你需要外部世界调用它时才将它设置为`公共`。

#### 定义私有函数：

``` 
uint[] numbers;

function _addToArray(uint _number) private {
  numbers.push(_number);
}
```

### 第10章 函数的更多属性

+ #### 返回值

  ``` 
  string greeting = "What's up dog";
  
  function sayHello() public returns (string) {
    return greeting;
  }
  ```

+ #### 函数的修饰符

  ##### 上面的函数实际上没有改变 Solidity 里的状态，即，它没有改变任何值或者写任何东西。这种情况下我们可以把函数定义为 **view**, 意味着它只能读取数据不能更改数据:

  ``` 
  function sayHello() public view returns (string) {
  }
  ```

  ##### Solidity 还支持 **pure** 函数, 表明这个函数甚至都不访问应用里的数据，例如：

  ``` 
  function _multiply(uint a, uint b) private pure returns (uint) {
    return a * b;
  }
  ```

### 第11章 Keccak256和类型转换

+ #### Keccak256

  ```
  //6e91ec6b618bb462a4a6ee5aa2cb0e9cf30f7a052bb467b0ba58b8748c00d2e5
  keccak256("aaaab");
  //b1f078126895a1424524de5321b339ab00408010b7cf0e6ed451514981e58aa9
  keccak256("aaaac");
  ```

+ #### 类型转换

  ``` 
  uint8 a = 5;
  uint b = 6;
  // 将会抛出错误，因为 a * b 返回 uint, 而不是 uint8:
  uint8 c = a * b;
  // 我们需要将 b 转换为 uint8:
  uint8 c = a * uint8(b);
  ```

### 第12章 放在一起

### 第13章 事件

``` 
// 这里建立事件
event IntegersAdded(uint x, uint y, uint result);

function add(uint _x, uint _y) public {
  uint result = _x + _y;
  //触发事件，通知app
  IntegersAdded(_x, _y, result);
  return result;
}
```

### 第14章 Web3.js

``` 
// 下面是调用合约的方式:
var abi = /* abi是由编译器生成的 */
var ZombieFactoryContract = web3.eth.contract(abi)
var contractAddress = /* 发布之后在以太坊上生成的合约地址 */
var ZombieFactory = ZombieFactoryContract.at(contractAddress)
// `ZombieFactory` 能访问公共的函数以及事件

// 某个监听文本输入的监听器:
$("#ourButton").click(function(e) {
  var name = $("#nameInput").val()
  //调用合约的 `createRandomZombie` 函数:
  ZombieFactory.createRandomZombie(name)
})

// 监听 `NewZombie` 事件, 并且更新UI
var event = ZombieFactory.NewZombie(function(error, result) {
  if (error) return
  generateZombie(result.zombieId, result.name, result.dna)
})

// 获取 Zombie 的 dna, 更新图像
function generateZombie(id, name, dna) {
  let dnaStr = String(dna)
  // 如果dna少于16位,在它前面用0补上
  while (dnaStr.length < 16)
    dnaStr = "0" + dnaStr

  let zombieDetails = {
    // 前两位数构成头部.我们可能有7种头部, 所以 % 7
    // 得到的数在0-6,再加上1,数的范围变成1-7
    // 通过这样计算：
    headChoice: dnaStr.substring(0, 2) % 7 + 1，
    // 我们得到的图片名称从head1.png 到 head7.png

    // 接下来的两位数构成眼睛, 眼睛变化就对11取模:
    eyeChoice: dnaStr.substring(2, 4) % 11 + 1,
    // 再接下来的两位数构成衣服，衣服变化就对6取模:
    shirtChoice: dnaStr.substring(4, 6) % 6 + 1,
    //最后6位控制颜色. 用css选择器: hue-rotate来更新
    // 360度:
    skinColorChoice: parseInt(dnaStr.substring(6, 8) / 100 * 360),
    eyeColorChoice: parseInt(dnaStr.substring(8, 10) / 100 * 360),
    clothesColorChoice: parseInt(dnaStr.substring(10, 12) / 100 * 360),
    zombieName: name,
    zombieDescription: "A Level 1 CryptoZombie",
  }
  return zombieDetails
}
```

![zombies1.JPG](https://github.com/blockchaingroup4/webank/blob/master/day2/%E8%B4%BE%E5%AD%A6%E9%9B%A8/images/zombies1.JPG?raw=true)