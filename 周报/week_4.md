
# 第四周周报

------

# 1. 石望华

### 6.17 周一

* 写代码，在remix上测试，写测试报告（见《作业报告.md》)
* 在虚拟机上编译和部署合约，问了老师，最终还是没成功，说是缺少.class文件，但直接javac编译java文件会产生很多错误，而且我将合约转成java文件时也有报与abi相关的错误，但生成了java文件

### 6.18 周二

* 结合Spring-Boot项目和AssetAPP应用，学习官方文档的Web3SDK（主要是配置SDK和使用SDK），“使用SDK” 部分主要讲的是Spring项目下调用SDK API、创建账号和部署、调用合约，而没有讲SpringBoot项目的开发；了解了Web3SDK API, 做笔记于《FISCO-BCOS文档学习_2.0.md》
* 上午根据SpringBootStarter的REAADME_CN.md进行部署时，在“运行”一步“./gradlew build”命令就没运行成功了，问了老师好久，检查了各种地方，最后发现是因为gradlew是windows下的格式，通过dos2unix转成unix下的格式就运行成功了......
* 熟悉Spring-Boot项目结构（主要是src（main和test）、各种gradle），认真阅读了main下的8个java类文件
* 部署groovy环境（springboot里有.groovy文件）

### 6.19 周三

* 学git，clone仓库并成功commit、push到远程仓库（之前一直是在网页web端直接修改contributions）
* 继续读spring-boot的源码（今天读的主要是test目录下的5个java类文件），发现对合约的部署与调用是在/src/test部分而不是/src/main部分，而用spring实现的asset-app中是在/src/main下的一个AssetClient类中实现的！
* 而且在spring项目中没有看到**.bin**文件和**.abi**文件，在spring-boot中这两种格式的文件在将**.sol**合约文件转化为.java文件的类SolidityFunctionWrapperGeneratorTest中有用到，而在spring中是通过console的脚本文件sol2java.sh进行编译生成的，也许这是两种合约文件编译为java文件的方式。（但本质应该相同，因为通过脚本编译后会生成.abi和.bin，只是对用户透明了）
* 运行spring-boot项目还是有问题

### 6.20 周四

* 根据《DAY2-3.实验要求》里的代码写**ShopClient.java**类和**UserKeyutil.java**类，并在**ContractTest.java**里加入了相应的测试代码

* 虚拟机里又出现了文件不能共享的问题（第三次），这次又花了较长时间实现文件共享（尝试了各种命令），并将命令记录在了《commands_record.md》
* 在windows下的IDEA里写好代码后在虚拟机里./gradlew build, 产生一堆bugs，一个个解决，最终还剩一两个。

### 6.21 周五

* 继续改代码，debug（import包，日志处理，加注解等）
* 熟悉spring与springboot（通过发现它们的不同）
* 写作业报告《作业报告.md》

------

------

# 2. 李冠海

## 周一   
学习spring和spring-boot，为实验做准备。[在Remmix上用solidity编写积分系统的智能合约](../day2/李冠海/LAGCredit.sol)，[并将其转换为java类](../day2/李冠海/LAGCredit.java)。    
## 周二   
[下载idea，搭好spring-boot开发环境，下载spring-boot-starter项目，导入idea，将智能合约转换后的java类复制到项目中，编写配置、服务类，进行测试](../day2/李冠海/homework_sprint_boot_starter.md)。    
## 周三   
阅读FISCO-BCOS的文档，根据[文档](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/browser/browser.html)搭建区块链浏览器，并[使用浏览器查看区块、节点信息](../day2/李冠海/blockchain_browser_usage.md)。    
## 周四~周五    
阅读FISCO-BCOS的文档，根据[文档](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/tutorial/sdk_application.html)搭建一个区块链应用：[在一台机器上部署节点和合约，并在另一台机器上通过SDK开发一个可以调用合约的JavaFX应用](../day2/李冠海/asset_app.md)。

------

# 3. 贾学雨

## 周一 （2019/6/17）

#### 继续完成[僵尸游戏](https://cryptozombies.io/)的章节练习，熟悉solidity语法。

- #### 搭建僵尸工厂

- #### 僵尸攻击人类

- #### 高级solidity理论

## 周二 （2019/6/18）

#### 学习spring和spring boot的知识，为后面使用spring-boot-starter做准备。并了解和学习spring-boot-starter的结构和文件源码。

## 周三 （2019/6/19）

#### 下载IntelliJ IDEA,利用官方文档提供的spring-boot-starter开始开发LAGC书店积分系统。编写服务和测试，运行项目并debug。

## 周四 （2019/6/20）

- #### 继续周三的spring-boot-starter项目开发测试，并最终[完成测试](https://github.com/blockchaingroup4/webank/blob/master/day2/%E8%B4%BE%E5%AD%A6%E9%9B%A8/LAGC%E7%A7%AF%E5%88%86%E7%B3%BB%E7%BB%9F%E9%83%A8%E7%BD%B2.md)。

- #### 阅读FISCO-BCOS官方文档的[构建第一个区块链应用](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/tutorial/sdk_application.html),并参照案例的asset-app完成LAG-app书店积分系统开发。

## 周五 （2019/6/21）

#### 继续开发LAG-app。这里最难处理的就是业务开发，因为asset-app和LAG-app的业务不用，所以很大程度上我需要重新开发业务。最终完成业务开发。并修改调用业务的脚本，最终完成LAG-app的开发。

![lag3.png](https://github.com/blockchaingroup4/webank/blob/master/day2/%E8%B4%BE%E5%AD%A6%E9%9B%A8/images/lag3.png?raw=true)



------

------



# 4.



------

------



# 5.



------

------
