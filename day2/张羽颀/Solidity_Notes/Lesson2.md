# Solidity学习笔记_Lesson 2
### 学习地址：https://cryptozombies.io/en/lesson/2/

* Addresss: 每个用户都有一个address，类似银行accout，例如： 0x0cE446255506E92DF41614C46F1d6df9Cc969183

* Mapping : 映射是存储和查找数据用的键-值对，例如：mapping (address => uint) public accountBalance;
定义了一个名为 accoutBalance的映射，在该映射中，键是一个address，值是一个uint。

* msg.sender: 是一个全局变量，指向当前调用者（或智能合约）的address（地址）

* require： require使得函数在执行过程中，当不满足某些条件时抛出错误，并停止执行：
例如：比较_name是否等于"ZED"，不等于，则抛出异常并终止程序：require(keccak256(_name) == keccak256("ZED")); （另：solidity不支持原生的字符串比较，只能通过字符串的keccak256哈希值来判断）

* 继承： solidity继承和其他语言的继承相似。合约可以继承自父合约，并可以直接调用public或internal的父合约函数。

* solidity也可以import其他.sol文件

* Storage和Memory：Storage 变量是指永久存储在区块链中的变量。 Memory 变量则是临时的，当外部函数对某合约调用完成时，内存型变量即被移除。 可以把它想象成存储在你电脑的硬盘或是RAM中数据的关系。
有些情况需要手动声明存储类型。主要用在处理函数内部的结构体和数组。

* internal和external修饰符：internal 和 private 类似，不过， 如果某个合约继承自其父合约，这个合约即可以访问父合约中定义的“内部”函数。external 与public 类似，只不过这些函数只能在合约之外调用 - 它们不能被合约内的其他函数调用。

* 接口（interface）：用于和其他合约交互。接口的定义和合约的定义相似，但有如下不同：(1)只声明了要与之交互的函数. (2)接口内不用大括号定义函数体，仅用分号结束函数声明。如：contract NumberInterface {
  function getNum(address _myAddress) public view returns (uint);
}

* 处理多返回值：函数可以返回多个返回值，用逗号隔开。
批量赋值的时候用（）括号，变量用逗号隔开即可。
如：(a,b,c)=multiple_return();

* if语句：if语句和java差不多
