## shell script 学习笔记

### 5.29 周三  shell变量

**From：**<https://www.runoob.com/linux/linux-shell-variable.html>

- #！（指定解释器类型）

- echo输出

- chmod +x ./test.sh     赋权限

- ./test.sh   执行   ./ 表示在当前目录下搜索此文件

- /bin/sh test.sh 直接运行解释器

- 使用for： 

  ```shell
  - for file in \`ls /etc`    done
  - for file in $(ls /etc)    done
  ```

- $ 使用一个定义过的变量，给变量赋值时不要加$

- 只读变量（const）

  ```shell
  #!/bin/bash
  myUrl="http://www.google.com"
  readonly myUrl
  myUrl="http://www.baidu.com"  报错
  ```

* unset删除变量，不能删除只读变量

  ```shell
  unset variable_name
  ```

* 变量类型：

  * 局部变量：在脚本或命令中定义，仅在当前shell实例中有效
  * 环境变量：所有程序都能访问
  * shell变量：shell程序设置的特殊变量，可以是局部变量也可以是环境变量

* 单/双引号

  * 单引号里的变量无效

  * 双引号里可以有变量和转义字符

    ```shell
    your_name='runoob'
    str="Hello, I know you are \"$your_name\"! \n"
    echo -e $str
    ```

* 获取字符串长度

  ```shell
  string="abcd"
  echo ${#string} #输出 4
  expr length "$string"  # string字符串里边有空格就要添加双引号
  ```

* 提取子字符串

  ```shell
  string="runoob is a great site"
  echo ${string:1:4} # 输出 unoo，从1开始截取4个字符
  ```

* 查找子字符串

  ```shell
  string="runoob is a great site" #查找字符 i 或 o 的位置(哪个字母先出现就计算哪个)
  echo `expr index "$string" io`  # 输出 4
  ```

* 只支持一维数组，定义数组：

  * ```shell
    array_name=(value0 value1 value2 value3)
    my_array=(A B "C" D)
    ```

  * ```shell
    array_name=(
    value0
    value1
    value2
    value3
    )
    ```

  * ```
    array_name[0]=value0 #单独定义数组的各个分量
    array_name[1]=value1 
    array_name[n]=valuen #可以不使用连续的下标,下标的范围没有限制
    ```

* 读取数组 ${数组名[下标]}

  * ```shell
    valuen=${array_name[n]}
    ```

  * ```shell
    echo ${array_name[@]} #获取数组中的所有元素, 把@改成*也可以
    ```

* 数组长度

  ```shell
  # 取得数组元素的个数
  length=${#array_name[@]}
  # 或者
  length=${#array_name[*]}
  # 取得数组单个元素的长度
  lengthn=${#array_name[n]}
  ```

* 注释

  * #号开头的行是注释

  * ```shell
    :<<EOF
    注释内容...
    注释内容...
    注释内容...
    EOF
    ```

  * ```shell
    :<<'
    注释内容...
    注释内容...
    注释内容...
    '
    
    :<<!
    注释内容...
    注释内容...
    注释内容...
    !
    ```

* 8种截取字符串的方式

  * ```shell
    echo ${var#*//} # var 是变量名，# 号是运算符，*// 表示从左边开始删除第一个 // 号及左边的所有字符
    ```

  * ```shell
    echo ${var##*/} # 从左边开始删除最后（最右边）一个 / 号及左边的所有字符
    ```

  * ```shell
    echo ${var%/*}  # 从右边开始，删除第一个 / 号及右边的字符
    ```

  * ```shell
    echo ${var%%/*} # 从右边开始，删除最后（最左边）一个 / 号及右边的字符
    ```

  * ```shell
    echo ${var:0:5} # 左边第一个字符开始，5 表示字符的总个数
    ```

  * ```shell
    echo ${var:7}   # 左边第8个字符开始，一直到结束
    ```

  * ```shell
    echo ${var:0-7:3}#  0-7 表示右边算起第七个字符开始往右获取，3 表示字符的个数。
    ```

  * ```shell
    echo ${var:0-7} # 右边第七个字符开始，一直到结束 左边的第一个字符是用0表示，右边的第一个字符用0-1表示
    ```

* **read**命令  读取键盘输入的内容并将其赋值给shell变量 -p 参数由于设置提示信息

  ```shell
  read -p "input a val:" a    #获取键盘输入的 a 变量数字
  read -p "input b val:" b    #获取键盘输入的 b 变量数字
  r=$[a+b]                    #计算a+b的结果 赋值给r  不能有空格
  echo "result = ${r}"        #输出显示结果 r
  ```

  结果：

  ```shell
  input a val:1
  input b val:2
  result = 3
  ```

  ```shell
  一个一个词组地接收输入的参数，每个词组需要使用空格进行分隔；如果输入的词组个数大于需要的参数个数，则多出的词组将被作为整体为最后一个参数接收：
  ```

  ```shell
  read firstStr secondStr
  echo "第一个参数:$firstStr; 第二个参数:$secondStr"
  ```

  ```shell
  $ sh test.sh 
  一 二 三 四
  第一个参数:一; 第二个参数:二 三 四
  ```

  ```shell
  read -p "请输入一段文字:" -n 6 -t 5 -s password
  echo -e "\npassword is $password"
  ```

  *  **-p** 输入提示文字
  *  **-n** 输入字符长度限制(达到6位，自动结束)
  *  **-t** 输入限时
  *  **-s** 隐藏输入内容

------

### 5.30 周四 传递参数  while循环 expr 基本运算符 

**From：**<https://www.runoob.com/linux/linux-shell-passing-arguments.html>

* 获取命令行传递的参数  $n , $0为执行的文件名（带后缀）

* $# 传递到脚本的参数个数

* $* 以一个单字符串显示所有向脚本传递的参数

* $$ 脚本运行的当前进程ID号

* $! 后台运行的最后一个进程的ID号

* $@ 与$*相同，但是使用时加引号，并在引号中返回每个参数

* $-    显示Shell使用的当前选项，与[set命令](https://www.runoob.com/linux/linux-comm-set.html)功能相同

* $? 显示最后命令的退出状态, 0表示没有错误，其他任何值表明有错误

* 如果包含空格，应该使用单引号或者双引号将该参数括起来，以便于脚本将这个参数作为整体来接收。

* 中括号（包括单中括号与双中括号）可用于一些条件的测试:

  * 算术比较, 比如一个变量是否为0, [ $var -eq 0 ]
  * 文件属性测试，比如一个文件是否存在，`[ -e $var ]`, 是否是目录，`[ -d $var ]`
  * 字符串比较, 比如两个字符串是否相同， `[[ $var1 = $var2 ]]`

* **while**循环--自增方式--示例：

  ```shell
  echo "-------::::WHILE循环输出 使用 let i++ 自增:::::---------"
  j=0
  while [ $j -lt ${#my_arry[@]} ] # -lt小于的意思
  do
    echo ${my_arry[$j]}
    let j++   #1
  done
  
  echo "--------:::WHILE循环输出 使用 let  "n++ "自增: 多了双引号，其实不用也可以:::---------"
  n=0
  while [ $n -lt ${#my_arry[@]} ]
  do
    echo ${my_arry[$n]}
    let "n++" #2
  done
  
  echo "---------::::WHILE循环输出 使用 let m+=1 自增,这种写法其他编程中也常用::::----------"
  m=0
  while [ $m -lt ${#my_arry[@]} ]
  do
    echo ${my_arry[$m]}
    let m+=1 #3
  done
  
  echo "-------::WHILE循环输出 使用 a=$[$a+1] 自增,个人觉得这种写法比较麻烦::::----------"
  a=0
  while [ $a -lt ${#my_arry[@]} ]
  do
   echo ${my_arry[$a]}
   a=$[$a+1] #4
  done
  ```

* linux **expr**命令：表达式计算工具，完成表达式的求值操作

  * 计算字符串长度

    ```shell
    expr length “this is a test”
    ```

    返回14

  * 抓取字串

    ```shell
    expr substr “this is a test” 3 5
    ```

    返回is is

  * 抓取第一个字符数字串出现的位置

    ```shell
    expr index "sarasara"  a
    ```

    返回2

  * 整数运算

    ```shell
     > expr 14 % 9
     5
     > expr 10 + 10
     20
     > expr 1000 + 900
     1900
     > expr 30 / 3 / 2
     5
     > expr 30 \* 3 (使用乘号时，必须用反斜线屏蔽其特定含义。因为shell可能会误解显示星号的意义)
     90
     > expr 30 * 3
     expr: Syntax error
     > expr 5+6    // 直接输出 5+6，运算符左右没空格
     
     #!/bin/bash
     val=`expr 2 + 2` #反引号 也可以 val=$(expr 2 + 2)
     echo "两数之和为 : $val"
    ```

* **基本运算符**：

  * 算术运算符  +  -  *  /   %  =   ==   != 
    * [ $a == $b ]  #假定a为10，b为20，返回 false, [$a==$b] 是错的，条件表达式要放在方括号之间，并且要有空格

  * 关系运算符  -eq相等 -ne不等 -gt大于 -lt小于 -ge大于等于 -le小于等于

    * 结合if else语句：

    ```shell
    	a=10
    	b=20
    	if [ $a -eq $b ]
    	then
       		echo "$a -eq $b : a 等于 b"
    	else
       	echo "$a -eq $b: a 不等于 b"
    	fi  
    ```

    * 也可以使用 **((expression1 OP expression2))**，**OP** 可以为 **>、<、>=、<=、==、!=**。这几个关系运算符都是测试整数表达式 expression1 和 expression2 之间的大小关系。

    * 进行字符串比较时，== 可以使用 = 替代

    * > 和 < 进行字符串比较时，需要使用[[ string1 OP string2 ]] 或者 [ string1 \OP string2 ]，也就是使用 [] 时，> 和 < 需要使用反斜线转义

  * 布尔运算符  ！-o或 -a与运算

  * 逻辑运算符   &&  ||  要用两个[], 比如 if [[ $a -lt 100 && $b -gt 100 ]]

  * 字符串运算符 =  !=  -z -n $

    * -z 检测字符串长度是否为0，为0返回 true

      ```shell
      a="abc"
      [ -z $a ] #返回 false
      ```

    * -n 检测字符串长度是否为0，不为0返回 true

      ```shell
      #!/bin/bash
      a=""
      if [ -n "$a" ] #这里要加双引号，否则 -n $a 的结果永远是 true
      then
         echo "-n $a : 字符串长度不为 0"
      else
         echo "-n $a : 字符串长度为 0"
      fi
      ```

    * $ 检测字符串是否为空，不为空返回 true

  * 文件测试运算符，检测 Unix 文件的各种属性

    举例：

    `if [ -x $file ]
    then
       echo "文件可执行"
    else
       echo "文件不可执行"
    fi`

* **echo命令**

  * ```shell
    echo It is a test # 省略双引号
    ```

  * ```shell
    echo "\"It is a test\"" # 显示转义字符，也可以省略双引号
    ```

  * ```shell
    echo -e "OK! \n" # -e 开启转义  \n换行  （echo本来就自动换行）
    ```

  * ```shell
    echo -e "OK! \c" # -e 开启转义 \c 不换行
    ```

  * ```shell
    echo "It is a test" > myfile  # 显示结果定向至文件
    ```

  * ```shell
    echo '$name\"'   # 单引号 输出 $name\"
    ```

  * ```shell
    echo `date`      # 显示命令执行结果 输出日期
    ```

------

