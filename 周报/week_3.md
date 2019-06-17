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
# 1. 石望华

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
