# 阅读分析build_chain.sh脚本

### 参考：<https://fisco-bcos-documentation.readthedocs.io/zh_CN/release-2.0/docs/manual/build_chain.html>

### FISCO BCOS提供了`build_chain.sh`脚本帮助用户快读搭建FISCO BCOS联盟链，`build_chain.sh`脚本用于快速生成一条链中节点的配置文件

## 函数功能分析

### 1. help()

#### 打印解释通过命令行输入的参数的作用

+ ##### ` l`选项: 用于指定要生成的链的IP列表以及每个IP下的节点数，以逗号分隔。脚本根据输入的参数生成对应的节点配置文件，其中每个节点的端口号默认从30300开始递增，所有节点属于同一个机构和群组

+ ##### `f`选项

  - ##### 用于根据配置文件生成节点，相比于`l`选项支持更多的定制。

  - ##### 按行分割，每一行表示一个服务器，格式为`IP:NUM AgencyName GroupList`，每行内的项使用空格分割，**不可有空行**。

  - ##### `IP:NUM`表示机器的IP地址以及该机器上的节点数。`AgencyName`表示机构名，用于指定使用的机构证书。`GroupList`表示该行生成的节点所属的组，以`,`分割。例如`192.168.0.1:2 agency1 1,2`表示`ip`为`192.168.0.1`的机器上有两个节点，这两个节点属于机构`agency1`，属于group1和group2。

+ ##### `e`选项 用于指定`fisco-bcos`二进制所在的**完整路径**，脚本会将`fisco-bcos`拷贝以IP为名的目录下。不指定时，默认从GitHub下载`master`分支最新的二进制程序。

+ ##### `o`选项 指定生成的配置所在的目录。

+ ##### `p`选项 指定节点的起始端口，每个节点占用三个端口，分别是p2p,channel,jsonrpc使用`,`分割端口，必须指定三个端口。同一个IP下的不同节点所使用端口从起始端口递增。

+ ##### `i`选项 无参数选项，设置该选项时，设置节点的RPC和channel监听`0.0.0.0`

+ ##### `v`选项 用于指定搭建FISCO BCOS时使用的二进制版本。

+ ##### `d`选项 使用docker模式搭建FISCO BCOS，使用该选项时不再拉取二进制，但要求用户启动节点机器安装docker且账户有docker权限。

+ ##### `s`选项 无参数选项，设置该选项时，节点使用mptstate存储合约局部变量，默认使用storagestate存储合约局部变量。

+ ##### `S`选项 无参数选项，设置该选项时，节点使用外部数据库存储数据，目前支持MySQL。

+ ##### `c`选项 无参数选项，设置该选项时，设置节点的共识算法为Raft，默认设置为PBFT。

+ ##### `C`选项 用于指定搭建FISCO BCOS时的链标识。

+ ##### `g`选项 无参数选项，设置该选项时，搭建国密版本的FISCO BCOS。使用g选项时要求二进制fisoc-bcos为国密版本。

+ ##### `z`选项 无参数选项，设置该选项时，生成节点的tar包。

+ ##### `t`选项 该选项用于指定生成证书时的证书配置文件。

+ ##### `T`选项 无参数选项，设置该选项时，设置节点的log级别为DEBUG。

### 2. LOG_WARN()

#### 打印警告信息

### 3. LOG_INFO()

#### 打印普通信息

### 4. parse_params()

#### 解析命令行参数，总共有18个选项，参照help()函数里的参数作用

### 5. print_result()

#### 打印脚本执行结果情况

### 6. fail_message()

#### 打印运行失败的信息

### 7. check_env()

#### 主要检查openssl的版本(1.0.2或者1.1)以及操作系统类型(macOS或者Linux)

### 8. check_and_install_tassl()

#### TaSSL是国密版OpenSSL，这个函数主要检查是否有已经安装TaSSL，如果没有则下载并安装TaSSL

### 9. getname()

#### 根据路径生成名字

### 10. check_name()

#### 用来判断名字是满足正则表达式^[a-zA-Z0-9._-]+$

### 11. file_must_exists()

#### 判断文件是否存在，如果不存在则输出提示,结束脚本

### 12. dir_must_exists()

#### 判断目录是否存在，如果不存在则输出提示，结束脚本

### 13. dir_must_not_exists()

#### 判断目录是否存在，如果存在则输出提示（请删除旧的目录），结束脚本

### 14. gen_chain_cert()

#### 生成区块链私钥和证书

### 15. gen_agency_cert()

#### 生成机构私钥和证书

### 16. gen_cert_secp256k1()

#### 用椭圆曲线加密算法生成节点的私钥和证书

### 17. gen_node_cert()

#### 生成节点id、私钥和证书

### 18. generate_gmsm2_param()

#### 辅助生成国密版区块链私钥和证书

### 19. gen_chain_cert_gm()

#### 生成国密版区块链私钥和证书

### 20. gen_agency_cert_gm()

#### 生成国密版机构私钥和证书

### 21. gen_node_cert_with_extensions_gm()

#### 生成国密版节点私钥和证书

### 22. gen_node_cert_gm()

#### 生成国密版节点id、私钥和证书

### 23. generate_config_ini()

#### 生成节点的配置文件

### 24. generate_group_genesis()

#### 生成群组的genesis, 即起源节点

### 25. generate_group_ini()

#### 生成群组的配置文件

### 26. generate_cert_conf()

#### 生成区块链证书的配置文件

### 27. generate_script_template()

#### 生成节点脚本的模板

### 28. generate_cert_conf_gm()

#### 生成国密版区块链证书的配置文件

### 29. generate_node_scripts()

#### 生成节点的脚本

### 30. genTransTest()

#### 生成pwd/nodes/ip_addr/.transTest.sh

### 31. generate_server_scripts()

#### 生成服务器脚本

### 32. parse_ip_config()

#### 解析ip配置：如果命令行参数中有指定ip文件，那么在main函数中就会调用这个函数，然后从文件中读取ip

### 33. main()

#### main函数，大部分操作是在main函数中发生中,调用其他函数