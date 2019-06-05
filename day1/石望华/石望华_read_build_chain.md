# 阅读、分析 build_chain.sh脚本

###### 参考：<https://fisco-bcos-documentation.readthedocs.io/zh_CN/release-2.0/docs/manual/build_chain.html>

该脚本的**目标**是：让用户最快的使用FISCO BCOS联盟链，该脚本默认从[GitHub](https://github.com/FISCO-BCOS/FISCO-BCOS)下载`master`分支最新版本预编译可执行程序进行相关环境的搭建。

###### **功能**：快速生成一条链中节点的配置文件

###### 行数：1166

###### 运行脚本示例：bash build_chain.sh -l "127.0.0.1:4" -p 30300,20200,8545

------

### 一. 函数功能简介

#### 1. help()

​	通过cat输出，**解释**调用该脚本时可以通过命令行传入的参数及其作用，一共有**18种**。

* -l <IP list>  是**必须**有的参数，格式为"ip1:nodeNum1,ip2:nodeNum2"， e.g:"192.168.0.1:2,192.168.0.2:3"

* -f<IP list file> 是可选的参数，指定ip参数列表，一行一个ip, 格式为“ip:nodeNum agencyName groupList”，eg "127.0.0.1:4 agency1 1,2"

* -g <Generate guomi nodes> Default no 默认不开启这个模式，设置-g后  guomi_mode="yes";;

* -z <Generate tar packet>    Default no，设置-z后  make_tar="yes";;

  下面的参数全是有默认值的

* -p<Start Port>  选项指定起始端口，分别是p2p_port, channel_port, jsonrpc_port, jsonrpc/channel默认监听127.0.0.1

* -i <Host ip> 用外网访问时用 -i 指定ip

* -e <FISCO-BCOS binary path> 指定FISCO-BCOS二进制文件的路径，默认是从GitHub下载master`分支最新版本预编译可执行程序

* -o <Output Dir> 默认输出在./nodes目录下

* -v <FISCO-BCOS binary version> 设置版本号，默认是在FISCO-BCOS/blob/master/release_note.txt文件中获取

* -d <docker mode> 默认是关闭状态，设置-d后以docker的方式build

* -s <State type> 默认存储，设置-s后用mpt的方式存储

* -S <Storage type> 默认是leveldb, 设置-S后用external存储类型

* -c <Consensus Algorithm> 默认用PBFT共识算法，设置-c后用Raft算法

* -C <Chain id> 默认是1，可以设置成32位无符号整数

* -t <Cert config file> 默认是自动生成的

* -T <Enable debug log> 默认是关闭状态，设置-T后开启debug log

* -F <Disable log auto flush> 默认是开启状态，设置-F后关闭log auto flush

* -h 输出帮助信息，即调用help函数

#### 2. LOG_WARN()

​	打印警告信息

```shell
    local content=${1}
    echo -e "\033[31m[WARN] ${content}\033[0m"
```

#### 3. LOG_INFO()

​	打印正常信息，与LOG_WARN()函数只有一处不同：31m->32m 不懂什么意思，大概是输出的样式不同。可在121行看调用方式

```shell
    local content=${1}
    echo -e "\033[32m[INFO] ${content}\033[0m"
```

#### 4. parse_params() 

​	通过一个while循环逐个解析命令行输入的参数，用**case**判断参数类型，**$OPTARG**可以获取到每个“ -字母 ” 后的参数值，每个case结束后用两个" ; "表示截止，即跳入下一个while循环。

#### 5. print_result()

​	输出脚本运行完的各种信息

* 用到了语法 [] && LOG_INFO "..."

#### 6. fail_message()

​	输出错误信息，即输出调用fail_message后的第一个字符串

* 用到$1获取参数

#### 7. check_env()

1. 判断是否有3个openssl版本之一
2. 如果是reSSL，设置环境变量PATH
3. 如果是Darwin操作系统，设置脚本全局变量OS=“macOS”
4. 如果是Linux操作系统，设置OS="Linux"

* 用到语法  [ ] || [ ] || [ ] || { }
* exit $EXIT_CODE 退出程序？

#### 8. check_and_install_tassl()

* 如果HOME目录下没有tassl文件，去github上下载tassl.tar.gz #天安版国密OpenSSL安全套接字层密码学基础库，囊括主要的密码算法、常用的密钥和证书封装管理功能及SSL/TLS协议，并提供丰富的API
* tar zxvf解压命令
  * x : 从 tar 包中把文件提取出来
  * z : 表示 tar 包是被 gzip 压缩过的，所以解压时需要用 gunzip 解压
  * v : 显示详细信息
  * f xxx.[tar.gz](https://www.baidu.com/s?wd=tar.gz&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao) : 指定被处理的文件是 xxx.[tar.gz](https://www.baidu.com/s?wd=tar.gz&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao)
* chmod u+x tassl 给文件加权限
* mv tassl ${HOME}/.tassl 将文件移动到${HOME}/.tassl

#### 9. getname()

* 如果传入的参数是空的，返回0
*  [[ "$name" =~ ^.*/$ ]]   表示匹配“.../...”这样的路径 如果传入的参数满足这样的字符串，则去掉最右边的 / 以及/之后的字符
* 该函数的作用是获取文件所在文件夹的名字

#### 10. check_name()

* 检测传入的值（第二个参数）是否符合“  ^[a-zA-Z0-9._-]+$ ” 规则，即第一个参数（一个字符串，代表词语的具体含义）的值是否负责这个正则表达式，不符合规则则输出错误信息并停止程序

#### 11. file_must_exists()

* 传入的参数是个文件名，通过**-f**检测该常规文件是否存在，不存在则输出错误信息并停止程序

* -f   filename

  Return true filename exists and is a regular file, not including link file, directory, block special, character special

#### 12. dir_must_exists()

* 传入的参数是个目录，检测该目录是否存在，不存在则输出错误信息并停止程序

#### 13. dir_must_not_exists()

* 通过-e检测传入的文件名是否存在, 如果文件存在则输出请求删除旧目录的信息，停止程序

* -e filename

  Return true filename exists (regardless of type).

#### 14. gen_chain_cert()

* 通过getname获取第二个参数的文件夹名name
* 调用dir_must_not_exists确保第二个参数所代表的路径不存在
* 通过check_name检验chain（一个字符串）所在的文件夹名name是否符合规则
* mkdir创建空目录  -p是指如果父目录不存在则创建，如果存在则不当做错误来处理
* 通过genrsa**生成RSA私钥**（不会生成公钥，因为公钥提取自私钥），将私钥保存到ca.key文件中，私钥长度为2048
* 生成证书请求文件（Cerificate Signing Request）、验证证书请求文件、创建根CA
  * -new -x509 创建**自签署证书文件**，自创建根CA，而不是创建证书请求，用于测试或者为根CA创建自签名证书
  * -days 3650 指定自签名证书的有效期限
  * -subj "/CN=$name/O=fisco-bcos/OU=chain"
    * 替换或自定义证书请求时需要输入的信息，并输出修改后的请求信息
    * CN 是common name，O是Organization，OU是Organization Unit
  * -key $chaindir/ca.key 指定私钥的输入文件，在上面已经创建ca.key
  * -out $chaindir/ca.crt 指定证书请求或自签署证书的输出文件
* 将cert.cnf配置文件移动到链目录下

#### 15. gen_agency_cert()

​	生成服务机构的证书私钥agency.key和两个文件（sha256），一个是证书请求文件（agency.csr），一个是自签署证书（agency.crt）（根据ca.crt、ca.key）。

* -config filename  指定req的配置文件，指定后将忽略所有的其他配置文件。如果不指定则默认使用/etc/pki/tls/openssl.cnf中req段落的值

#### 16. gen_cert_secp256k1()

​	生成了如下数据：

* 参数文件
* 生成公钥
* 生成证书请求文件
* 生成自签署证书
* 

#### 17. gen_node_cert()

#### 18. generate_gmsm2_param()

#### 19. gen_chain_cert_gm()

#### 20. gen_agency_cert_gm()

#### 21. gen_node_cert_with_extensions_gm()

#### 22. gen_node_cert_gm()

#### 23. generate_config_ini()

#### 24. generate_group_genesis()

#### 25. generate_group_ini()

#### 26. generate_cert_conf()

#### 27. generate_script_template()

#### 28. generate_cert_conf_gm()

#### 29. generate_node_scripts()

#### 30. genTransTest()

#### 31. generate_server_scripts()

#### 32. parse_ip_config()

#### 33. main()

