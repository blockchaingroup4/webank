# WEEK One

------

### 5.27 周一

老师上课；下午做一个小实验，建链，用postman测试  post、get

### 5.28 周二

企业人士讲课，建虚拟机环境，搭链，使用fisco-bcos，安装控制台等

### 5.29 周三

- 完成昨天下午的作业（十个实验要求），写成报告上传到github；
- 总览build_chain.sh脚本文件
- 学习shell教程（变量），见《shell.study.md》之5.29

### 5.30 周四

* 继续学习shell script （传递参数、while循环、expr、read命令、基本运算符），见《shell.study.md》之5.30
* 对脚本做一个简单处理，见《read_build_chain.md》

### 5.31 周五

​		放假

### 6.1 周六

​		放假

### 6.2 周日

​		放假

------

# WEEK Two

------

### 6.3 周一

- 学shell printf命令、test命令，见《石望华_shell_study.md》之**6.3**
- 读build_chain.sh，主要是前6个函数，写代码注释，功能介绍写在《read_build_chain.md》里
- 画思维导图《build_chain\_思维导图.xmind》
- 看了FISCO BCOS文档中关键概念的前面一部分，做笔记于《FISCO BCOS文档学习.md》

### 6.4 周二

- 学shell script流程控制和函数，见《石望华_shell_study.md》之**6.4**
- 继续阅读FISCO BCOS文档之关键概念（看完了第一部分），见《FISCO BCOS文档学习.md》
- 读build_chain.sh, 写代码注释，分析了第7到第15个函数，见《read_build_chain.md》

### 6.5 周三

- 学shell script输入输出重定向，见《石望华_shell_study.md》之**6.5**
- 阅读完了FISCO BCOS文档之关键概念部分，见《FISCO BCOS文档学习.md》
- 学习并实现FISCO BCOS文档“教程”之“构建第一个联盟区块链应用——实现资产的基本管理
- 读build_chain.sh, 写代码注释，分析第16-21个函数，见《read_build_chain.md》

### 6.6-6.9 周四-周日

放假

------

# WEEK 3

------

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

* 在remix上写代码

### 6.14-6.16  周五-周日

​	放假

------

# WEEK 4

------

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

# WEEK 5

------

### 6.24 周一

* 在虚拟机中配置1.8版本的java，出现了新的错误，问了老师，无果，最后的效果还不如上周的了，build不能成功了，详见《作业报告.md》
* 下午一行行代码的测试，debug，终于build成功，在java8下实现了和上周五一样的功能......详情见《作业报告.md》
* 更新了《commands.md》
* 在虚拟机里装中文输入法和IDEA

### 6.25 周二

* 

### 6.26 周三



### 6.27 周四



### 6.28 周五

* 

------

# WEEK 6

------

### 7.1 周一

* 

### 7.2 周二

* 

### 7.3 周三

* 

### 7.4 周四

* 

### 7.5 周五

