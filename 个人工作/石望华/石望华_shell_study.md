## Shell script 学习笔记

### 5.29 周三  Shell变量

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

  * 局部变量：在脚本或命令中定义，仅在当前Shell实例中有效
  * 环境变量：所有程序都能访问
  * Shell变量：Shell程序设置的特殊变量，可以是局部变量也可以是环境变量

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

* **read**命令  读取键盘输入的内容并将其赋值给Shell变量 -p 参数由于设置提示信息

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
     > expr 30 \* 3 (使用乘号时，必须用反斜线屏蔽其特定含义。因为Shell可能会误解显示星号的意义)
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

### 6.3 周一 printf、test命令

**From**: <https://www.runoob.com/linux/linux-Shell-test.html>

#### - printf

* ```shell
  printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg  
  printf "%-10s %-8s %-4.2f\n" 郭靖 男 66.1234 
  printf "%-10s %-8s %-4.2f\n" 杨过 男 48.6543 
  printf "%-10s %-8s %-4.2f\n" 郭芙 女 47.9876 
  ```

  ```shell
  姓名     性别   体重kg
  郭靖     男      66.12
  杨过     男      48.65
  郭芙     女      47.99
  ```

* %-10s 指一个宽度为10个字符（-表示**左对齐**，没有则表示**右对齐**），任何字符都会被显示在10个字符宽的字符内，如果不足则自动以空格填充，超过也会将内容全部显示出来。

* ```shell
  # format-string为双引号
  printf "%d %s\n" 1 "abc"
  # 单引号与双引号效果一样 
  printf '%d %s\n' 1 "abc" 
  # 没有引号也可以输出
  printf %s abcdef
  # 格式只指定了一个参数，但多出的参数仍然会按照该格式输出，format-string 被重用
  printf %s abc def
  printf "%s\n" abc def
  printf "%s %s %s\n" a b c d e f g h i j
  # 如果没有 arguments，那么 %s 用NULL代替，%d 用 0 代替
  printf "%s and %d \n" 
  ```

  ```shell
  1 abc
  1 abc
  abcdefabcdefabc
  def
  a b c
  d e f
  g h i
  j  
   and 0
  ```

* 其中最后一个参数是 "def"，%c 自动截取字符串的第一个字符作为结果输出

  ```shell
  $  printf "%d %s %c\n" 1 "abc" "def"
  1 abc d
  ```

#### - test

* 检查某个条件是否成立, 可以进行数值、字符和文件三个方面的测试。

  ```shell
  num1=100  #"ru1noob"
  num2=100  #"runoob"
  if test $[num1] -eq $[num2]  # [] 执行基本的算数运算  # if test $num1 = $num2
  then
      echo '两个数相等！'
  else
      echo '两个数不相等！'
  fi
  
  cd /bin
  if test -e ./bash
  then
      echo '文件已存在!'
  else
      echo '文件不存在!'
  fi
  
  if test -e ./notFile -o -e ./bash  # -o或  -a与
  then
      echo '至少有一个文件存在!'
  else
      echo '两个文件都不存在'
  fi
  ```

#### - 文件包含

* 点+空格+文件路径  . ./test1.sh     # 被包含的文件 test1.sh 不需要可执行权限。
* source filename      source ./test1.sh

------

### 6.4 周二 流程控制 Shell函数

**From**:<https://www.runoob.com/linux/linux-shell-func.html>

* ```shell
  if then else-if then else fi
  a=10
  b=20
  if [ $a == $b ]
  then
     echo "a 等于 b"
  elif [ $a -gt $b ]
  then
     echo "a 大于 b"
  elif [ $a -lt $b ]
  then
     echo "a 小于 b"
  else
     echo "没有符合的条件"
  fi
  ```

* for var in item1 item2 ... itemN; do command1; command2… done;

  ```shell
  for loop in 1 2 3 4 5
  do
      echo "The value is: $loop"
  done
  
  for str in 'This is a string'
  do
      echo $str
  done
  
  for((assignment;condition:next));do  # for 的 (()) 中变量调用不需要加 $
      command_1;
      command_2;
      commond_..;
  done;
  #!/bin/bash
  for((i=1;i<=5;i++));do
      echo "这是第 $i 次调用";
  done;
  ```

* while

  ```shell
  #!/bin/bash
  int=1
  while(( $int<=5 ))
  do
      echo $int
      let "int++" #Bash let 命令，变量计算中不需要加上 $ 来表示变量
  done
  
  echo '按下 <CTRL-D> 退出'
  echo -n '输入你最喜欢的网站名: '
  while read FILM #输入信息被设置为变量FILM，按<Ctrl-D>结束循环
  do
      echo "是的！$FILM 是一个好网站"
  done
  ```

* until循环

  ```shell
  a=0
  
  until [ ! $a -lt 10 ]
  do
     echo $a
     a=`expr $a + 1`
  done
  ```

* case 多选择语句

  ```shell
  echo '输入 1 到 4 之间的数字:'
  echo '你输入的数字为:'
  read aNum
  case $aNum in
      1)  echo '你选择了 1'
      ;;                    #用两个分号表示break
      2)  echo '你选择了 2'
      ;;
      3)  echo '你选择了 3'
      ;;
      4)  echo '你选择了 4'
      ;;
      *)  echo '你没有输入 1 到 4 之间的数字'  #  *号匹配其他
      ;;
  esac  #case反过来
  ```

* break continue

  ```shell
  #!/bin/bash
  while :
  do
      echo -n "输入 1 到 5 之间的数字:"
      read aNum
      case $aNum in
          1|2|3|4|5) echo "你输入的数字为 $aNum!"
          ;;
          *) echo "你输入的数字不是 1 到 5 之间的! 游戏结束"
              break #终止执行后面的所有循环  continue命令与break命令类似，只有一点差别，它不会跳出所有循环，仅仅跳出当前循环，循环不会结束
          ;;
      esac
  done
  ```

* 函数

  * 可以带function fun() 定义，也可以直接fun() 定义,不带任何参数

  * 参数返回，可以显示加：return 返回，如果不加，将以最后一条命令运行结果，作为返回值。 return后跟数值n(0-255)

  * ```shell
    demoFun(){
        echo "这是我的第一个 shell 函数!"
    }
    echo "-----函数开始执行-----"
    demoFun
    echo "-----函数执行完毕-----"
    ```

  * ```shell
    funWithReturn(){
        echo "这个函数会对输入的两个数字进行相加运算..."
        echo "输入第一个数字: "
        read aNum
        echo "输入第二个数字: "
        read anotherNum
        echo "两个数字分别为 $aNum 和 $anotherNum !"
        return $(($aNum+$anotherNum))
    }
    funWithReturn
    echo "输入的两个数字之和为 $? !"  #函数返回值在调用该函数后通过 $? 来获得
    ```

  * 传参

    * 在函数体内部，通过 $n 的形式来获取参数的值，$1表示第一个参数

    * ```shell
      funWithParam(){
          echo "第一个参数为 $1 !"
          echo "第二个参数为 $2 !"
          echo "第十个参数为 $10 !"   #显示 第十个参数为 10 ! 当n>=10时，需要使用${n}来获取参数
          echo "第十个参数为 ${10} !" #显示 第十个参数为 34 !
          echo "第十一个参数为 ${11} !"
          echo "参数总数有 $# 个!"
          echo "作为一个字符串输出所有参数 $* !"
      }
      funWithParam 1 2 3 4 5 6 7 8 9 34 73
      ```

      | 参数 | 含义                                                        |
      | :--: | ----------------------------------------------------------- |
      |  $#  | 传递到脚本的参数个数                                        |
      |  $*  | 脚本运行的当前进程ID号                                      |
      |  $!  | 后台运行的最后一个进程的ID号                                |
      |  $@  | 与$*相同，但是使用时加引号，并在引号中返回每个参数。        |
      |  $-  | 显示Shell使用的当前选项，与set命令功能相同                  |
      |  $?  | 显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误 |

  ------

  ### 6.5 周三 Shell输入/输出重定向

* 文件描述符

  *文件描述符 0 通常是标准输入（STDIN），1 是标准输出（STDOUT），2 是标准错误输出（STDERR）*

* 输出重定向

  ```shell
  $ who > users   #执行 who 命令，它将命令的完整的输出重定向在用户文件中(users)
  # >会覆盖文件内容，用>>在文件结尾插入内容
  # 执行后，并没有在终端输出信息，因为输出已被从默认的标准输出设备（终端）重定向到指定的文件
  $ cat users  # 查看文件内容
  _mbsetupuser console  Oct 31 17:35 
  tianqixin    console  Oct 31 17:35 
  tianqixin    ttys000  Dec  1 11:33 
  $ echo "www.runoob.com" > users  # 覆盖文件内容
  $ cat users  
  www.runoob.com
  $ echo "菜鸟教程：www.runoob.com" >> users  # 不覆盖文件
  $ cat users
  菜鸟教程：www.runoob.com
  菜鸟教程：www.runoob.com
  $
  ```

* 输入重定向: 从键盘获取输入的命令会转移到文件读取内容

  ```shell
  $ wc -l users # 查询文件行数
         2 users
  $  wc -l < users
         2  # 不输出文件名
  command1 < infile > outfile #同时替换输入和输出，执行command1，从文件infile读取内容，然后将输出写入到outfile中
  ```

* 一般情况下，每个 Unix/Linux 命令运行时都会打开三个文件

  * 标准输入文件(stdin)：stdin的文件描述符为0，Unix程序默认从stdin读取数据
  * 标准输出文件(stdout)：stdout 的文件描述符为1，Unix程序默认向stdout输出数据
  * 标准错误文件(stderr)：stderr的文件描述符为2，Unix程序会向stderr流中写入错误信息

  ```shell
  $ command 2 > file # 将 stderr 重定向到 file
  ```

* Here Document 将输入重定向到一个交互式 Shell 脚本或程序

  ```shell
  # 将两个 delimiter 之间的内容(document) 作为输入传递给 command
  command << delimiter #开始的delimiter前后的空格会被忽略掉
      document
  delimiter #顶格写, 前面不能有任何字符，后面也不能有任何字符，包括空格和 tab 缩进
  
  $ wc -l << EOF
      欢迎来到
      菜鸟教程
      www.runoob.com
  EOF
  3          # 输出结果为 3 行
  $
  ```

* 希望执行某个命令，但又不希望在屏幕上显示输出结果

  ```shell
  $ command > /dev/null
  $ command > /dev/null 2>&1 # & 表示重定向的目标不是一个文件，而是一个文件描述符 
  #  2>1 代表将stderr重定向到当前路径下文件名为1的regular file中
  #  2>&1代表将stderr重定向到文件描述符为1的文件(即/dev/stdout)中 &>和>&意思相同 
  ```

  