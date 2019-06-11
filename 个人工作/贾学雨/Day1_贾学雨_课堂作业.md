## 课堂作业

### 1. 查看区块高度

![1](./images/1.JPG)

#### FISCO-BCOS 控制台指令getBlockNumber可以得到区块的高度。

### 2. 查看区块数据

![2](./images/2.JPG)

#### 指令getBlockByNumber + 块Number 可以得到对应块的数据。

### 3. 部署HelloWorld 智能合约

![3](./images/3.JPG)

#### 指令deploy 可以得到合约地址。

### 4. 使用查看getDeployLog

![4](./images/4.JPG)

#### 指令getDeployLog可以得到部署信息。这里我是在课上部署了两次，课后回宿舍部署了一次，所以有三次部署信息，这次实验内容使用的是第三次的部署。

### 5. 调用智能合约

#### 5.1. get方法

![5.1](./images/5.1.JPG)

##### 指令call + 智能合约 + 合约地址 + get(方法名) 可以调用get方法。

##### 再次查看区块高度和获取区块数据

![5.1.1](./images/5.1.1.JPG)

#### 5.2. set方法

![5.2](./images/5.2.JPG)

##### 指令call + 智能合约 + 合约地址 + set(方法名) + 方法的参数来调用合约的set方法。

##### 再次查看区块高度和获取区块数据

![5.2.1](./images/5.2.1.JPG)

##### 可以看到使用set方法后区块的数目增加了1。

![5.3](./images/5.3.JPG)

##### 经过对比可以发现，get方法是不会增加区块的数目。

### 6. 按CNS方式部署HelloWorld智能合约

![6](./images/6.JPG)

#### 指令deployByCNS + 合约名 + 版本名 可以通过CNS方式部署之智能合约，这些可以通过它的源代码来查看。

![6.1](./images/6.1.JPG)

![6.2](./images/6.2.JPG)

![6.3](./images/6.3.JPG)

#### 部署之后可以使用使用queryCNS来查看部署的合约

![7](./images/7.JPG)

#### 使用callByCNS来调用合约的方法

![8](./images/8.JPG)

### 7.  再次查看区块高度以及获取区块数据

![9](./images/9.JPG)

![10](./images/10.JPG)

#### 可以看到CNS部署后区块增加了2。

