# linux 命令记录

------

### 1. grep

global search regular expression

使用正则表达式搜索文本，并把匹配的行打印出来

egrep是grep的扩展，支持更多的re元字符；

fgrep就是fixed grep或fast grep，它们把所有的字母都看作单词

### 2. dos2unix

将windows下文本文件**换行格式**转化为unix下文本文件的**换行格式**

通过**cat -v a.txt**查看是否是dos格式，如果有**^M**标识则说明是dos格式

### 3. 共享文件夹

sudo mount -t vboxsf share /mnt/share

sudo mount -t vboxsf course1 /home/fisco-bcos/Desktop/share 

再次打开时总是又没有这个共享文件夹了，要重新运行这行命令

### 4. 指定运行文件

./build_chain.sh -e route_to_fisco_bcos  -l "127.0.0.1:4"

### 5. 运行gradle项目

* ```shell
  如果不想执行某个action，可以用 -x 选项，如： ./gradlew build -x lint -x test， 则不执行 lint 和 test。
  ```

* **./gradlew goJF**  

  Execute the task googleJavaFormat to format all *.java files in the project
  
  **./gradlew verGJF**
  
  Execute the task verifyGoogleJavaFormat to verify that all *.java files are formatted properly
  
  https://github.com/sherter/google-java-format-gradle-plugin

### 6. 切换java、javac的版本

* sudo mkdir /usr/lib/jvm 创建放置新版本的文件夹

* sudo tar zxvf /home/用户名/Downloads/jdk-8u25-linux-i586.tar.gz -C /usr/lib/jvm

  解压在官网下载的jdk

* 不修改环境变量

* 配置默认JDK：

  * sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk1.8.0_25/bin/java 300

  * sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/jdk1.8.0_25/bin/javac 300

    注意这里有两个路径，都必须是绝对路径，第一个路径不需要修改，第二个改成防止java文件夹里相应的路径

* 选择当前使用的java和javac版本

  * sudo update-alternatives --config java
  * sudo update-alternatives --config javac

* 查看版本

  * java -version

  * javac -version

### 7. 查看系统版本

1. cat /etc/issue
2. cat /etc/lsb-release
3. uname -a

### 8. sudo apt install -y openssl curl

- 下载不了，报错：
  - E: Could not get lock /var/cache/apt/archives/lock - open (11: Resource temporarily unavailable)
  - E: Unable to lock directory /var/cache/apt/archives/
- 解决办法，删除锁文件：
  - sudo rm /var/lib/dpkg/lock-frontend
  - sudo rm /var/lib/dpkg/lock

### 9. git命令

- git checkout https://github.com/blockchaingroup4/webank/trunk/Final_project 下载部分文件（注意改成trunk）
