# 第三周周报
-------
# 1. 李冠海
## 周一
继续分析block_chain.sh源代码，并完善报告和ppt

## 周二
[学习solidity lesson1](../day2/李冠海/homework_solidity_lesson1.md)    

## 周三
[学习solidity lesson2](../day2/李冠海/homework_solidity_lesson2.md)    

## 周四
[学习solidity lesson3](../day2/李冠海/homework_solidity_lesson3.md)    

## 周五
[学习solidity lesson4](../day2/李冠海/homework_solidity_lesson4.md)    

-------
# 2. 贾学雨

## 周一 （2019/6/10）

### 继续研读FISCO-BCOS技术文档，并学习Web3SDK的内容。[Web3SDK](https://github.com/FISCO-BCOS/web3sdk)可以支持访问节点、查询节点状态、修改系统设置和发送交易等功能。

- ### 环境要求

  - #### java版本：要求 [JDK8或以上](https://openjdk.java.net/)。

  - #### FISCO BCOS区块链环境搭建：参考 [FISCO BCOS安装教程](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/installation.html)

  - #### 网络连通性：检查Web3SDK连接的FISCO BCOS节点channel_listen_port是否能telnet通，若telnet不通，需要检查网络连通性和安全策略。

- ## Java应用引入SDK

#### 通过gradle或maven引入SDK到java应用

#### gradle:

```
compile group:"org.fisco-bcos", name:"web3sdk", version:"2.0.3-SNAPSHOT", changing: true
//compile ('org.fisco-bcos:web3sdk:x.x.x') //如：web3sdk:2.0.0
```

#### maven:

```
<dependency>
    <groupId>org.fisco-bcos</groupId>
    <artifactId>web3sdk</artifactId>
    <version>x.x.x</version> <!-- 如：2.0.0 -->
</dependency>
```

#### 由于引入了以太坊的solidity编译器相关jar包，需要在Java应用的gradle配置文件build.gradle中添加以太坊的远程仓库。

```
repositories {
        mavenCentral()
        maven { url "https://dl.bintray.com/ethereum/maven/" }
    }
```

- ### 配置SDK

  - #### FISCO BCOS节点证书配置

  - #### 配置文件设置

  - #### Spring项目配置

    ![../../_images/sdk_xml.png](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/_images/sdk_xml.png)

  - #### Spring Boot项目配置

    ![../../_images/sdk_yml.png](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/_images/sdk_yml.png)

- ### 使用SDK

  - #### Spring项目开发指引

    - ##### 调用SDK的API

    - ##### 调用SDK Web3j的API

    - ##### 调用SDK Precompiled的API

    - ##### 创建并使用指定外部账号

    - ##### 通过SDK部署并调用合约

    - ##### 准备Java合约文件

    - ##### 部署并调用合约

  - #### Spring Boot项目开发指引(与Spring项目开发类似）

- ### Web3SDK API

## 周二 （2019/6/11）企业老师授课

### 1. 各组PPT展示工作以及对build_chain.sh脚本的理解。

### 2. 授课

### 2.1 以太坊基础

#### 2.1.1 结构与原理

#### 2.1.2 账户与交易

#### 2.1.3 EVM

#### 2.1.4 树

#### 2.1.5 RLP

### 2.2 FISCO-BCOS 2.0 技术解析

#### 2.2.1 FISCO-BCOS的总体架构设计

#### 2.2.2 交易生命周期

#### 2.2.3 FISCO-BCOS核心模块

### 2.3 智能合约

#### 2.3.1 基本概念

#### 2.3.2 EVM原理

#### 2.3.3 Solidity语言

#### 2.3.4 智能合约案例

#### 2.3.5 智能合约安全

### 2.4 实验

#### 2.4.1 积分系统介绍

#### 2.4.2 智能合约实现

#### 2.4.3 JAVA SDK开发

## 周三 （2019/6/12）

### 学习Solidity语言，通过[僵尸游戏](https://cryptozombies.io/zh/course)来熟悉Solidity语言。完成三章节的学习。

- #### 搭建僵尸工厂

- #### 僵尸攻击人类

- #### 高级Solidity理论

## 周四 （2019/6/13）

### 学习更多智能合约编写的案例，编写书店积分系统智能合约。

## 周五 （2019/6/14）

### 配置SDK环境，进行JAVA SDK开发。

-------
# 3. 石望华

### 6.10  周一

- 分析build_chain.sh的第22至32个函数，详见《read_build_chain.md》
- 补充思维导图《build_chain\_思维导图.xmind》
- 读<https://mp.weixin.qq.com/s/nRwHxt2E1TJiopJqLSvMOQ>，了解证书机制

### 6.11  周二

- 分析build_chain.sh的最后一个函数，main函数，详见《read_build_chain.md》
- 上课，详见《上课笔记.md》，主要讲了以太坊、Solidity语言、FISCO-BCOS的构成

### 6.12  周三

- 上午学Loom Solidity教程第一课和第二课
- 下午学Loom Solidity教程第三课
- 实现虚拟机共享文件功能。。。
- 复习之前运行过的部署、调用等命令

### 6.13  周四

- 在remix上写代码

### 6.14-6.16  周五-周日

​	放假

# 4.陈思源

## 以太坊基础
### 账户数据结构：
	Address：20-byte，从公钥计算得来
	nonce:
		外部账户：表示账户发出交易个数
		合约账户：表示创建合约个数
	balance:账户余额
	……

### 账户= 地址+状态
  	• World State = 所有账户状态
  	• 交易改变账户状态
  	• 一个块一次共识
  	• 外部账户和合约账户
  	合约账户得执行：
  		合约账户只有接受到外部账户的执行命令才会执行
  		执行者需要支付一定的费用
  	• 交易
  	由外部账户发起
  	节点收到后验证有效性
  	在EVM中执行合约
  	每执行一条合约都要消耗gas
  	改变账户状态
  	产生回执（收据）
  	日志
  	• 交易数据
  	from
  	to
  	value:转账数量
  	Gas Limit
  	GasPrice
  	nonce:统一用户的最大交易标记
  	hash：以上内容生成的hash
  	r,s,v:交易签名的三部分
  	init or Data：合约代码或者Message Call Input data

### Merkle Patricia Trie (MPT)
    数据存储在LevelDB数据库中
    Key节点RLP编码的SHA3值，Value是节点的RLP编码
    节点类型：
    	空间点：空，没有值；
    	叶节点：Key-Value列表，key是十六进制编码，Value是RLP；
    	拓展节点：Key-Value列表，Value是其他基地那的哈希值
    分支节点

### Fisco-bcos讲解
#### 并行计算模型：
    区块链自动识别交易的互斥资源，构建出DAG，规划出一个可并行执行的路径
    最佳情况下，性能提升可达到CPU核心数的倍数

#### 分布式存储：
    使用分布式存储替换旧的本机存储
    分布式存储定义了标准的数据访问CRUD的接口，适配多种数据存储系统
    区块链节点既可以直接访问存储，也可以通过专用的数据代理访问存储
    适配关系型数据，简化数据操作
    数据管理更方便

####多引擎
    中间件平台/业务模板/预编译合约/控制台……

### 智能合约编程
    1. 基本概念：
      智能合约本身也是区块链参与者，可以接受和存储价值，也可以对外部发送信息和价值。
    2. 比特币脚本缺陷：
      缺少图灵完备性，不能支持所有的运算，缺失循环语句（目的是避免交易出现无限循环）。
    3. 交易执行原理
      发送目的是外部账户：转账
      发送目的是合约账户：调用智能合约
      …

## Solidity学习笔记
1. 用僵尸游戏熟悉solidity规则 https://cryptozombies.io/en/lesson
### 语法入门
msg.sender
在 Solidity 中，有一些全局变量可以被所有函数调用。 其中一个就是 msg.sender，它指的是当前调用者（或智能合约）的 address。

#### 判断语句：require
require使得函数在执行过程中，当不满足某些条件时抛出错误，并停止执行：

    function sayHiToVitalik(string _name) public returns (string) {
    // 比较 _name 是否等于 "Vitalik". 如果不成立，抛出异常并终止程序
    // (敲黑板: Solidity 并不支持原生的字符串比较, 我们只能通过比较
    // 两字符串的 keccak256 哈希值来进行判断)
    require(keccak256(_name) == keccak256("Vitalik"));
    // 如果返回 true, 运行如下语句 return "Hi!"; }


Solidity 并不支持原生的字符串比较, 我们只能通过比较 // 两字符串的 keccak256 哈希值来进行判断。

#### 继承(Inheritance)
合约之间的继承关系，部署子合约后可以调用其父合约中定义的函数。   
语法：BabyDoge是Dog的子合约:

    contract BabyDoge is Doge {     
    function anotherCatchphrase() public returns (string) {
      return "Such Moon BabyDoge";
	     }
    }

#### 引入（import）
用于把文件从一个导入另一个中。
其之前需要用“./”声明同一目录。

     import "./someothercontract.sol";
     contract newContract is SomeOtherContract {
     }
子合约中需要引入父合约。

#### Storage与Memory
在 Solidity 中，有两个地方可以存储变量 —— storage 或 memory。

Storage 变量是指永久存储在区块链中的变量。 Memory 变量则是临时的，当外部函数对某合约调用完成时，内存型变量即被移除。 你可以把它想象成存储在你电脑的硬盘或是RAM中数据的关系。

一般情况下，函数外声明的会被默认永久写入链；    
函数内声明的是“内存”型，函数调用后即消失。    
在处理函数内的结构体或者数组时，需要手动声明存储类型。

#### internal & external     
Solidity 还使用了另外两个描述函数可见性的修饰词：internal（内部） 和 external（外部）。     
internal: 子合约可以访问父合约中的“内部”函数。   
external: 这些函数只能在合约之外调用，不能被合约内的其他函数调用。

#### 与其他合约交互
定义一个interface(接口)。

     contract NumberInterface {
	      function getNum(address _myAddress) public view returns (uint);
      }
只声明了要与之交互的函数。
用分号结束了函数声明。看起来像一个合约框架（完全不写内容），用以使编译器识别其是一个接口。

#### 使用接口

    contract MyContract {
	  address NumberInterfaceAddress = 0xab38...;
	  // ^ 这是FavoriteNumber合约在以太坊上的地址
	  NumberInterface numberContract = NumberInterface(NumberInterfaceAddress);
	  // 现在变量 `numberContract` 指向另一个合约对象
	  function someFunction() public {
		 // 现在我们可以调用在那个合约中声明的 `getNum`函数:
		 uint num = numberContract.getNum(msg.sender);
		// ...在这儿使用 `num`变量做些什么
		}
	}

#### 选择变量

    function getLastReturnValue() external {
      uint c; // 可以对其他字段留空:
      (,,c) = multipleReturns();
    }
如上，c是返回的3个变量中的最后一个
