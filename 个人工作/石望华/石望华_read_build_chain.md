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

​	生成服务机构的证书私钥agency.key和两个文件（sha256），一个是使用本地私钥（agency.key）生成的证书请求文件（agency.csr），一个是联盟链委员会根据证书请求文件生成的联盟链成员机构证书agency.crt（根据ca.crt、ca.key）。

* -config filename  指定req的配置文件，指定后将忽略所有的其他配置文件。如果不指定则默认使用/etc/pki/tls/openssl.cnf中req段落的值

#### 16. gen_cert_secp256k1()

​	生成了如下数据：

* 椭圆曲线密钥参数生成及操作，使用secp256k1算法生成EC私钥
* 产生密钥的私钥值
* 从私钥提取公钥
* 生成证书请求文件
* 生成自签署证书（联盟链成员机构使用agency.key对证书请求文件进行签发，得到节点/SDK证书）
* 处理椭圆秘钥，转换不同的格式（DER）以及打印相对应的组件信息值

#### 17. gen_node_cert()

​	生成节点证书

* 检查所用openssl版本是否支持secp256k1算法
* 路径处理
* 调用gen_cert_secp256k1生成证书
* 将秘钥信息写入节点id文件
* 目录处理与输出

#### 18. generate_gmsm2_param()

​	生成国密SM2加密算法的参数（输出传入函数的参数以及BggqgRzPVQGCLQ==）

#### 19. gen_chain_cert_gm()

​	用国密算法生成链的证书

* 路径与参数处理（和gen_chain_cert函数一样）
* 调用generate_gmsm2_param输出参数
* 在tassl的cmd下用genpkey产生私钥参数值，输出至gmca.key中
* 在tassl的cmd下生成自签名证书
* 检测是否建链成功

#### 20. gen_agency_cert_gm()

​	用国密算法生成服务机构的证书

* 路径与参数处理（和gen_agency_cert函数一样）
* 在在tassl的cmd下执行genpkey、req、x509, 和gen_agency_cert类似，就是不是用openssl
* 文件目录处理（和gen_agency_cert函数一样）

#### 21. gen_node_cert_with_extensions_gm()

​	生成扩展的国密版节点证书，在gen_node_cert_gm函数中被调用，主要执行了genpkey、req、x509（和gen_agency_cert_gm类似）

#### 22. gen_node_cert_gm()

​	国密版的节点证书生成函数，和第17个函数gen_node_cert类似，不同的是在TASSL_CMD下执行的公钥生成、证书请求和签名操作，并且是基于扩展机制实现的，最后把ca证书拷贝到了agency下，但都加了'gm'前缀。

#### 23. generate_config_ini()

​	输出初始化的配置信息（[rpc]、[p2p]、[certificate_blacklist]、[group]、[network_security]、[storage_security]、[chain]、[compatibility]、[log]），主要包括各种ip、port和各种数据所在的文件目录。

​	将输出重定向到了${output}, 也就是传入函数的第一个参数（一个文件），一个“>”表示直接覆盖文件，两个“>”表示在文件末尾添加，下面的多个函数同理。

#### 24. generate_group_genesis()

​	输出群组的创始信息（[consensus]、[storage]、[state]、[tx]、[group]）到文件，主要是type信息，即规定了各种类型。

#### 25. generate_group_ini()

​	输出群组的初试信息（[consensus]、[tx_pool]、[tx_execute]）到文件，主要是数字上的限制，规定了某些时间、大小。

#### 26. generate_cert_conf()

​	输出证书的配置信息（[][][][][ca]、[req]、[v3_req]、[v4_req]）到文件，主要是各种字符串型的name，与证书所在组织有关。

#### 27. generate_script_template()

​	输出脚本模版（每个脚本都有的内容）到脚本文件，给文件授可执行权。

​	在generate_node_scripts、genTransTest、generate_server_scripts中被调用。

#### 28. generate_cert_conf_gm()

​	输出国密版的节点配置信息到文件（[new_oids]、[ca]、[CA_default]、[policy_match]、[policy_anything]、[req]、[req_distinguished_name]、[usr_cert]、[v3_req]、[v3enc_req]、[v3_agency_root]、[v3_ca]），包括路径、Name、数值型限制、基本约束等。

#### 29. generate_node_scripts()

​	生成节点脚本的代码，主要是各种cmd的操作，输出到对应的start.sh、stop.sh脚本中。

​	由于之前已经有模板输入到了脚本中，所以本函数中输出到脚本时用在结尾添加的方式。

#### 30. genTransTest()

​	生成交易测试的代码并输出到transTest.sh文件，发起伪装的交易请求，看节点能否察觉。在generate_server_scripts中被调用

* 获取节点版本并输出getNodeVersion
* 发起多次交易send_many_tx
  * 获取块限制block_limit
  * 根据版本输出类型txBytes
  * 发送POST请求（包含txBytes）

#### 31. generate_server_scripts()

​	生成服务器端脚本（start_all.sh、stop.all.sh），对每个脚本同样先调用generate_script_template模板化

* 首先将输入文件目录传入genTransTest，生成测试脚本文件
* 生成启动代码并输出到start_all.sh
* 生成结束代码并输出到stop.all.sh

#### 32. parse_ip_config()

​	解析ip文件中的ip、机构、群组，一行3个值，分别存入数组ip_array、agency_array、group_array不能有空行。

 	split by line, every line should be "ip:nodeNum agencyName groupList". eg "127.0.0.1:4 agency1 1,2"

#### 33. main()

