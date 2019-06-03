# build_chain.sh代码分析
build_chain.sh的代码可以分为三部分：声明和定义全局变量、定义函数、调用函数
### 声明和定义全局变量
代码中第5到第36行声明和定义了31个全局变量，包括CA文件位置(ca_file)、节点数(node_num)、ip文件(ip_file)、日志文件位置(logfile)、日志等级(log_level)、fisco的版本、操作系统类型......有的变量有初始值，如log_level初始值是'info'，共享算法类型consensus_type初始值是'pbft'等；有的变量没有初始值，如操作系统类型(OS)等。它们的值大部分都会在后面的过程中通过命令行参数确定或者被修改，主要是在parse_params()方法中确定或者修改。
### 定义函数
代码中第38到第1160行定义了33个函数，如果按照功能来划分的话，大概可以分为六类：
###### 用来打印信息的函数
1. help()   
打印帮助信息
2. LOG_WARN()    
打印警告信息
3. LOG_INFO()   
打印普通信息
4. print_result()    
打印脚本的执行结果
5. fail_message()    
打印失败信息，并终止脚本

###### 用来检验是否满足某些条件的函数
1. check_env()    
主要检查openssl的版本(1.0.2或者1.1)以及操作系统类型(macOS或者Linux)
2. check_and_install_tassl()   
TaSSL是国密版OpenSSL，这个函数主要检查是否有已经安装TaSSL，如果没有则下载并安装TaSSL    
如果在命令行参数中有指定国密模式，那么在main函数中就会调用这个函数
3. check_name()    
用来判断名字是满足正则表达式^[a-zA-Z0-9.\_-]+$，在后面生成证书的时候会多次使用到

###### 用来判断文件或者目录是否存在的函数
1. file_must_exists()   
判断文件是否存在，如果不存在则输出提示并结束脚本
2. dir_must_exists()   
判断目录是否存在，如果不存在则输出提示并结束脚本
3. dir_must_not_exists()   
判断目录是否存在，如果存在则输出提示（删除旧的目录）并结束脚本

###### 用来生成证书、密钥、配置文件的函数
1. gen_chain_cert()
2. gen_chain_cert_gm()
3. gen_agency_cert()
4. gen_agency_cert_gem()
5. gen_node_cert()
6. gen_cert_secp256k1()
7. gen_node_cert_gm()
8. gen_node_cert_with_extensions_gm()
9. generate_gmsm2_param()
10. generate_config_ini()
11. generate_group_genesis()
12. generate_group_ini()
13. generate_cert_conf()
14. generate_cert_conf_gm()
15. generate_script_template()
16. generate_node_scripts()
17. genTransTest()
18. generate_server_scripts()

###### 用来解析的函数
1. parse_params()   
解析命令行参数，总共有18个选项，help函数中给出了这18个选项的作用，如-C用来设置生成的区块链的id，-f用来指定ip文件，而如果有-c则使用Raft共识算法，没有-c则默认使用PBFT共识算法......
2. parse_ip_config()   
解析ip配置：如果命令行参数中有指定ip文件，那么在main函数中就会调用这个函数，然后从文件中读取ip

###### 其他函数
1. getname()    
根据路径生成名字
2. main()    
main函数，大部分操作是在main函数中发生中，大概可以分为七部分
    1. 准备输出目录   
    确保输出目录不存在，并且创建目录。默认的输出目录名是nodes，可以通过-o选项指定目录名。
    2. 处理ip参数   
    可以通过-l选项从命令行给出ip地址（可以有多个，用逗号分隔），也可以通过-f选项从指定的文件给出ip地址。有多少个ip地址，就有多少个agency和group；如果是用命令行参数给出ip地址，那么agency默认是"agency"，group默认是1；根据参数或者文件得到ip_array，后面会对每个ip都执行操作。
    3. 获取fisco版本、下载并校验fisco-bcos  
    可以通过-v选项指定fisco的版本，如果没有指定版本，那么默认使用master版本。
    4. 准备CA   
    如果CA文件已经存在，那么就会跳过这一步。如果不存在的话，首先，它会先确保${output_dir}/chain目录不存在，然后调用gen_chain_cert生成区块链的证书，然后再调用gen_agency_cert为每个代理生成证书。
    5. 处理国密模式   
    如果使用-g选项，那么就会使用国密模式。首先，它会先检查并安装TaSSL，然后调用generate_cert_conf_gm生成国密版的证书配置，然后调用gen_chain_cert_gm生成国密版的区块链证书，最后调用gen_agency_cert_gm生成国密版的代理证书（只有一个）。
    6. 生成密钥   
    7. 生成配置

### 调用函数
代码的最后依次调用了四个函数：check_env、parse_params、main、print_result   
- 首先先检查环境
- 然后解析命令行参数
- 之后执行main方法
- 最后打印执行的结果
