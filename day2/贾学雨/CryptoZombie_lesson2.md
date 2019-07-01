# CryptoZombie游戏学习

## Lesson 2 僵尸攻击人类

### 第1章 概述

### 第2章 Mapping和Address

+ #### Address

  ##### 以太坊区块链由 **_ account _** (账户)组成，你可以把它想象成银行账户。一个帐户的余额是 **_以太_** （在以太坊区块链上使用的币种），你可以和其他帐户之间支付和接受以太币，就像你的银行帐户可以电汇资金到其他银行帐户一样。

+ #### Mapping

  ```  
  //对于金融应用程序，将用户的余额保存在一个 uint类型的变量中：
  mapping (address => uint) public accountBalance;
  //或者可以用来通过userId 存储/查找的用户名
  mapping (uint => string) userIdToName;
  
  ```

### 第3章 Msg.sender

#### 在 Solidity 中，有一些全局变量可以被所有函数调用。 其中一个就是 `msg.sender`，它指的是当前调用者（或智能合约）的 `address`。

``` 
mapping (address => uint) favoriteNumber;

function setMyNumber(uint _myNumber) public {
  // 更新我们的 `favoriteNumber` 映射来将 `_myNumber`存储在 `msg.sender`名下
  favoriteNumber[msg.sender] = _myNumber;
  // 存储数据至映射的方法和将数据存储在数组相似
}

function whatIsMyNumber() public view returns (uint) {
  // 拿到存储在调用者地址名下的值
  // 若调用者还没调用 setMyNumber， 则值为 `0`
  return favoriteNumber[msg.sender];
}
```

### 第4章 Require

####  `require`使得函数在执行过程中，当不满足某些条件时抛出错误，并停止执行

### 第5章 继承

``` 
contract Doge {
  function catchphrase() public returns (string) {
    return "So Wow CryptoDoge";
  }
}

contract BabyDoge is Doge {
  function anotherCatchphrase() public returns (string) {
    return "Such Moon BabyDoge";
  }
}
```

### 第6章 Import

#### 在 Solidity 中，当你有多个文件并且想把一个文件导入另一个文件时，可以使用 `import` 语句。

### 第7章 Storage和Memory

#### **Storage** 变量是指永久存储在区块链中的变量。 **Memory** 变量则是临时的，当外部函数对某合约调用完成时，内存型变量即被移除。 你可以把它想象成存储在你电脑的硬盘或是RAM中数据的关系。

### 第8章 僵尸的DNA

### 第9章 更多关于函数可见性

#### 除 `public` 和 `private` 属性之外，Solidity 还使用了另外两个描述函数可见性的修饰词：`internal`（内部） 和 `external`（外部）。

#### `internal` 和 `private` 类似，不过， 如果某个合约继承自其父合约，这个合约即可以访问父合约中定义的“内部”函数。（嘿，这听起来正是我们想要的那样！）。

#### `external` 与`public` 类似，只不过这些函数只能在合约之外调用 - 它们不能被合约内的其他函数调用。

### 第10章 僵尸吃什么？

### 第11章 使用接口

``` 
contract NumberInterface {
  function getNum(address _myAddress) public view returns (uint);
}
```

### 第12章 处理多返回值

``` 
function multipleReturns() internal returns(uint a, uint b, uint c) {
  return (1, 2, 3);
}
```

### 第13章 奖励：Kitty基因

### 第14章 放在一起

![zombies2.JPG](https://github.com/blockchaingroup4/webank/blob/master/day2/%E8%B4%BE%E5%AD%A6%E9%9B%A8/images/zombies2.JPG?raw=true)