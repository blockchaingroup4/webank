## Solidity学习笔记_CH1

### 学习地址：https://cryptozombies.io/en/lesson/1/chapter/4
### Chapter 1
* 版本指令：所有的 Solidity 源码都必须冠以 "version pragma" — 标明 Solidity 编译器的版本. 以避免将来新的编译器可能破坏你的代码。

* 状态变量：状态被永久地保存在合约中。也就是说它们被写入以太币区块链中。

* 数学运算。solidity支持乘方

* 结构体：与其他语言的结构体大致相同。允许你生成一个更复杂的数据类型，它有多个属性。
_CH1
* 数组：一个数据的集合。solidity支持静态数组和动态数组。动态数组长度不固定，可以动态添加元素。如：uint [] dynamicArray;   可以定义公共数组，solidity会自动创建getter方法，如：Zombie [] public zombies;

* 函数：习惯上函数里的变量都是以下划线开头 (但不是硬性规定) 以区别全局变量。

* 使用结构体和数组：数组的push方法：zombies.push(Zombie(_name,_dna));  将元素添加到数组尾部。使用结构的的时候直接传参即可，如： Person satoshi= Person(172,"Satoshi")

* 私有/共有函数：默认的函数都是public。定义私有函数时，在函数名前面要加下划线_，并在函数名后添加private关键字。如:function _creatZombie(string _name, uint _dna) private{   }

* 函数返回值：可以定义函数返回的类型，定义时在公有/私有关键字后添加returns，如：function sayHello( ) public returns (string){...}

* view和pure：将函数定义为view，则它只能读取数据，不能更改数据。定义为pure，则不能读取也不能修改数据。如：function _generateRandomDna(string _str) private view returns (uint) {...}

* Keccak256:
Ethereum 内部有一个散列函数keccak256，它用了SHA3版本。一个散列函数基本上就是把一个字符串转换为一个256位的16进制数字。字符串的一个微小变化会引起散列数据极大变化。这在 Ethereum 中有很多应用，但是现在我们只是用它造一个伪随机数。

* 类型转换： 与java不同，类型转换语法：  类型名(变量名)，如： uint8  a = 8; uint b = uint(a);

* 事件（event）：事件 是合约和区块链通讯的一种机制。你的前端应用“监听”某些事件，并做出反应。

* array.push()返回数组的长度，返回类型为uint。
