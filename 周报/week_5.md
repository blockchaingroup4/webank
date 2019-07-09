# 第五周周报

------

# 1. 陈思源

## 智能合约

### TransactionManagementContract
    pragma solidity ^0.4.25;

    contract TransactionManagementContract{
      struct Transaction{
      uint timestamp;
      address cardId;
      string cardname;
      uint price;
      address sellerAddress;
      address buyerAddress;
      uint transactionId;
      bool isReversing; //the transaction is under reversing
      bool isReversed; // the trasaction has been reversed(one trasaction can only be reversed once)
      bool reverseResult; //true:   successful
                          //false:  unsuccessful
                        }

    Transaction[] transactions;
    function createTransaction(uint timestamp, address cardId, string cardname, uint price, address sellerAddress, address buyerAddress)external returns (uint){
      uint transactionId = transactions.push(Transaction(timestamp, cardId, cardname, price, sellerAddress, buyerAddress, 0, false, false, false))-1;
      transactions[transactionId].transactionId = transactionId;
      return transactionId;
    }

    function getTransactionInfo(uint transactionId)external view returns(uint, address, string, uint, address, address, uint, bool, bool, bool){
      Transaction storage transaction = transactions[transactionId];
      return (transaction.timestamp, transaction.cardId, transaction.cardname, transaction.price, transaction.sellerAddress, transaction.buyerAddress,
              transaction.transactionId, transaction.isReversing, transaction.isReversed, transaction.reverseResult);
            }

    function getTransaction(uint transactionId)external view returns(uint, address, string, uint){
      Transaction storage transaction = transactions[transactionId];
      return (transaction.timestamp, transaction.cardId, transaction.cardname, transaction.price);
    }

    function getTransaction_re(bool role, uint transactionId)external view returns(address)
    {
      Transaction storage transaction = transactions[transactionId];
      if (role){
          return (transaction.sellerAddress);
      }
      else{
          return (transaction.buyerAddress);
      }
      //role:  0:buyer
      //       1:seller
    }

    function getRole(address who, uint transactionId)external view returns (bool){
      Transaction storage transaction = transactions[transactionId];
      if(transaction.sellerAddress == who){
          return false;
      }
      else{
          return true;
      }
    }

    function getCardId(uint transactionId)external view returns(address){
      Transaction storage transaction = transactions[transactionId];
      return transaction.cardId;
    }

    function getPriceOf(uint transactionId)external view returns(uint){
      Transaction storage transaction = transactions[transactionId];
      return transaction.price;
    }

    function setReversingTrue(uint transactionId)external{
      transactions[transactionId].isReversing = true;
    }

    function dealWithRequestions(uint transactionId, bool result)external{
      transactions[transactionId].isReversed = true;
      transactions[transactionId].isReversing = false;
      transactions[transactionId].reverseResult = result;

      //true:exchange the card and delete the reverseApplication
      //false: delete the reverseApplication
    }

    function getBuyer(uint transactionId)external view returns(address){
      return transactions[transactionId].buyerAddress;
    }

    function getSeller(uint transactionId)external view returns(address){
      return transactions[transactionId].sellerAddress;
    }
    }

###  ReverseManagementContract
    pragma solidity ^0.4.25;
    contract TransactionInterface{
      function getTransaction_re(bool role, uint transactionId)external view returns(address);
      function getRole(address who, uint transactionId)external view returns (bool);
      function getCardId(uint transactionId)external view returns (address);
      function getPriceOf(uint transactionId)external view returns(uint);
      function setReversingTrue(uint transactionId)external;
      function dealWithRequestions(uint transactionId, bool result)external;
      function getBuyer(uint transactionId)external view returns(address);
      function getSeller(uint transactionId)external view returns(address);
    }

    contract AccountManagementInterface{
      function addRequestions(address who, uint requestionId)external;
      function getBalanceOf(address addr)external view returns(uint);
      function setBalanceOf(address addr, uint balance)external;
      function removeCard(address owner, address cardId)external;
      function addCard(address who, address cardId)external;
    }

    contract CardManagementInterface{
      function getCardOwner(address cardId)external view returns(address);
      function setCardOwner(address cardId, address owner)external;
    }

contract ReverseManagementContract{
    //public the address of managers
    // address managerAddress = ...;

    struct ReverseApplication{
        uint reverseApplyId;
        uint transactionId;
        bool role;  
        string discribe;
        bool dealed; //whether dealed by manager

        //role:  0:seller
        //       1:buyer
    }

    ReverseApplication[] reverseApplications;
    TransactionInterface transactionInterface;
    AccountManagementInterface accountManagementInterface;
    CardManagementInterface cardManagementInterface;
    function setACTInterfaces(address accountAddr, address cardAddr, address transactionAddr)external{
        accountManagementInterface = AccountManagementInterface(accountAddr);
        cardManagementInterface = CardManagementInterface(cardAddr);
        transactionInterface = TransactionInterface(transactionAddr);
    }

    function getReverseInfo(uint reverseId)external view returns(uint, uint, bool, string, bool){
        ReverseApplication storage reverse = reverseApplications[reverseId];
        return (reverse.reverseApplyId, reverse.transactionId, reverse.role, reverse.discribe, reverse.dealed);
    }

    function createReverseApplies(address who, uint transactionId, string discribe)external{
        address cardId = transactionInterface.getCardId(transactionId);
        require(cardManagementInterface.getCardOwner(cardId) == who);

        bool role = transactionInterface.getRole(who, transactionId);
        transactionInterface.setReversingTrue(transactionId);
        uint reverseApplyId = reverseApplications.push(ReverseApplication(0, transactionId, role, discribe, false))-1;
        reverseApplications[reverseApplyId].reverseApplyId = reverseApplyId;
        //create re_applicationId
        //reverseApplications ++
    }

    function getReverseAppliesNum() external view returns (uint){
        return reverseApplications.length;
    }

    function getReverseApply(uint index)external view returns(uint, uint, bool, string, bool){
        return (reverseApplications[index].reverseApplyId, reverseApplications[index].transactionId, reverseApplications[index].role,
        reverseApplications[index].discribe, reverseApplications[index].dealed );
    }

    function sendReverseInform(uint reverseApplyId)external{
        uint transactionId = reverseApplications[reverseApplyId].transactionId;
        bool role = reverseApplications[reverseApplyId].role;
        address aim = transactionInterface.getTransaction_re(role, transactionId);

        accountManagementInterface.addRequestions(aim, reverseApplyId);
    }

    function setReverseResult(uint reverseApplyId,bool result)external{
        reverseApplications[reverseApplyId].dealed = true;
        uint transactionId = reverseApplications[reverseApplyId].transactionId;
        bool anotherRole = reverseApplications[reverseApplyId].role; //the user who give me the requestion
        address who = transactionInterface.getTransaction_re(anotherRole, transactionId); //who is the user who use this function


        transactionInterface.dealWithRequestions(transactionId, result);
        if (result){
            address cardId = transactionInterface.getCardId(transactionId);
            uint price = transactionInterface.getPriceOf(transactionId);
            address buyer = transactionInterface.getBuyer(transactionId);
            address seller = transactionInterface.getSeller(transactionId);
            uint buyerBalance = accountManagementInterface.getBalanceOf(buyer);
            uint sellerBalance = accountManagementInterface.getBalanceOf(seller);

            accountManagementInterface.setBalanceOf(buyer, buyerBalance + price);
            accountManagementInterface.setBalanceOf(seller, sellerBalance - price);
            address aim;
            if(who == seller)aim = buyer;
            else aim = seller;
            cardManagementInterface.setCardOwner(cardId,aim);
            accountManagementInterface.addCard(seller, cardId);
            accountManagementInterface.removeCard(buyer, cardId);

        }

    }

    }

### Ownable
    pragma solidity ^0.4.25;

    /**
    * @title Ownable
    * @dev The Ownable contract has an owner address, and provides basic authorization control
    * functions, this simplifies the implementation of "user permissions".
    */
    contract Ownable {
      address private _owner;

      event OwnershipTransferred(
        address indexed previousOwner,
        address indexed newOwner
        );

        /**
        * @dev The Ownable constructor sets the original `owner` of the contract to the sender
        * account.
        */
        constructor() internal {
          _owner = msg.sender;
          emit OwnershipTransferred(address(0), _owner);
        }

        /**
        * @return the address of the owner.
        */
        function owner() public view returns(address) {
          return _owner;
        }

        /**
        * @dev Throws if called by any account other than the owner.
        */
        modifier onlyOwner() {
          require(isOwner());
          _;
        }

        /**
        * @return true if `msg.sender` is the owner of the contract.
        */
        function isOwner() public view returns(bool) {
          return msg.sender == _owner;
        }

        /**
        * @dev Allows the current owner to relinquish control of the contract.
        * @notice Renouncing to ownership will leave the contract without an owner.
        * It will not be possible to call the functions with the `onlyOwner`
        * modifier anymore.
        */
        function renounceOwnership() public onlyOwner {
          emit OwnershipTransferred(_owner, address(0));
          _owner = address(0);
        }

        /**
        * @dev Allows the current owner to transfer control of the contract to a newOwner.
        * @param newOwner The address to transfer ownership to.
        */
        function transferOwnership(address newOwner) public onlyOwner {
          _transferOwnership(newOwner);
        }

        /**
        * @dev Transfers control of the contract to a newOwner.
        * @param newOwner The address to transfer ownership to.
        */
        function _transferOwnership(address newOwner) internal {
          require(newOwner != address(0));
          emit OwnershipTransferred(_owner, newOwner);
          _owner = newOwner;
        }
      }

### MarketContract
    pragma solidity ^0.4.25;
    contract CardManagementInterface{
      function getPriceOf(address cardId)external view returns(uint);
      function getCardOwner(address cardId)external view returns(address);
      function getCardName(address cardId)external view returns(string);
      function setCardOwner(address cardId, address owner)external;
      function setCardOnSale(address cardId, bool onSale)external;
      function setCardPrice(address cardId, uint price)external;
      function createCard(string name, address cardId, string url, int8 level, address owner)external;
    }

    contract AccountManagementInterface{
      function getBalanceOf(address addr)external view returns(uint);
      function setBalanceOf(address addr, uint balance)external;
      function removeCard(address owner, address cardId)external;
      function addCard(address who, address cardId)external;
      function addTransaction(address who, uint transactionId)external;
      function getDrawCountOf(address addr)external view returns(uint32);
      function setDrawCountOf(address addr, uint32 count)external;
    }
    contract TransactionManagementInterface{
      function createTransaction(uint timestamp, address cardId, string cardname, uint price, address sellerAddress, address buyerAddress)external returns(uint);
    }
    contract MarketContract{
      address[] cardsOnSale;
      event DrawCardEvent(int8 level, address cardId);
      AccountManagementInterface accountManagementInterface;
      CardManagementInterface cardManagementInterface;
      TransactionManagementInterface transactionManagementInterface;
      function getCardsOnSaleNum()external view returns(uint){
        return cardsOnSale.length;
    }

    function getAddressOfCardOnSale(uint index)external view returns(address){
        return cardsOnSale[index];
    }

    function setACTInterfaces(address accountAddr, address cardAddr, address transactionAddr)external{
        accountManagementInterface = AccountManagementInterface(accountAddr);
        cardManagementInterface = CardManagementInterface(cardAddr);
        transactionManagementInterface = TransactionManagementInterface(transactionAddr);
    }

    function setAccoundManagementInterface(address addr)external{
        accountManagementInterface = AccountManagementInterface(addr);
    }

    function setCardManagementInterface(address addr)external{
        cardManagementInterface = CardManagementInterface(addr);
    }

    function setTransactionManagementInterface(address addr)external{
        transactionManagementInterface = TransactionManagementInterface(addr);
    }

    function recharge(address addr, uint amount)external{
        uint balance = accountManagementInterface.getBalanceOf(addr);
        accountManagementInterface.setBalanceOf(addr, balance + amount);
    }

    //0:success
    //1:balance not enough
    function buyDrawCards(address addr, uint times)external returns(uint){
        uint amountPerTime = 10;
        uint balance = accountManagementInterface.getBalanceOf(addr);
        uint needBalance = times*amountPerTime;
        if(needBalance > balance){
            return 1;
        }
        accountManagementInterface.setBalanceOf(addr, balance - needBalance);
        uint32 count = accountManagementInterface.getDrawCountOf(addr);
        accountManagementInterface.setDrawCountOf(addr, uint32(count + times));
        return 0;
    }

    function pushCard(address cardId, uint price)external returns(uint){
        cardsOnSale.push(cardId);
        cardManagementInterface.setCardOnSale(cardId, true);
        cardManagementInterface.setCardPrice(cardId, price);
        return 0;
    }

    //0:success
    //1:card is not on sale
    function pullCard(address cardId)external returns(uint){
        cardManagementInterface.setCardOnSale(cardId, false);
        return _removeCardOnSale(cardId);
    }

    function buyCard(address buyer, address cardId)external{
        uint balanceOfBuyer = accountManagementInterface.getBalanceOf(buyer);
        address seller = cardManagementInterface.getCardOwner(cardId);
        uint balanceOfSeller = accountManagementInterface.getBalanceOf(seller);
        uint price = cardManagementInterface.getPriceOf(cardId);
        string memory name = cardManagementInterface.getCardName(cardId);
        cardManagementInterface.setCardOwner(cardId, buyer);
        cardManagementInterface.setCardOnSale(cardId, false);

        address buyeraddress = buyer;
        uint transactionId = transactionManagementInterface.createTransaction(now, cardId, name, price, seller, buyeraddress);

        accountManagementInterface.removeCard(seller, cardId);
        accountManagementInterface.addCard(buyer, cardId);

        accountManagementInterface.addTransaction(buyer, transactionId);
        accountManagementInterface.addTransaction(seller, transactionId);

        accountManagementInterface.setBalanceOf(buyer, balanceOfBuyer -  price);
        accountManagementInterface.setBalanceOf(seller, balanceOfSeller + price);

        _removeCardOnSale(cardId);
    }

    //0:success
    //1:card is not on sale
    function _removeCardOnSale(address cardId)private returns(uint){
        for(uint i = 0; i < cardsOnSale.length; i++){
            if(cardsOnSale[i] == cardId){
                for(uint j = i; j < cardsOnSale.length-1; j++){
                    cardsOnSale[j] = cardsOnSale[j+1];
                }
                cardsOnSale.length--;
                return 0;
            }
        }
        return 1;
    }

    uint nonce = 0;
    function drawCard(address who, string wish)external{
        require(accountManagementInterface.getDrawCountOf(who) > 0);
        uint cardIdInt = uint(keccak256(abi.encodePacked(wish))) +
                uint(keccak256(abi.encodePacked(now, msg.sender, nonce)));
        uint rand = cardIdInt % 10000;
        address cardId = address(cardIdInt);
        nonce++;
        int8 level = 0;
        if(rand < 5000){
            level = 1;
        }else if(rand < 7500){
            level = 2;
        }else if(rand < 8550){
            level = 3;
        }else if(rand < 9390){
            level = 4;
        }else if(rand <9890){
            level = 5;
        }else{
            level = 6;
        }
        emit DrawCardEvent(level, cardId);
    }

    function createCardAndGiveTo(string name, address who, address cardId, string url, int8 level)external{
        cardManagementInterface.createCard(name, cardId, url, level, who);
        accountManagementInterface.addCard(who, cardId);
        accountManagementInterface.setDrawCountOf(who, accountManagementInterface.getDrawCountOf(who) - 1);
    }
  }

  ### CardManagementContract
    pragma solidity ^0.4.25;

    contract CardManagementContract{
    struct Card{
        string name;
        int8 level;
        address cardId;
        string url;
        bool isOnSale;
        uint price;
        address owner;
    }
    mapping (address => Card) cards;

    function setCardPrice(address cardId, uint price)external{
        // require(cards[cardId].owner == msg.sender);
        cards[cardId].price = price;
    }

    function getCardInfo(address cardId)external view returns(string, int8, address, string, bool, uint, address){
        Card storage card = cards[cardId];
        return (card.name, card.level, card.cardId, card.url, card.isOnSale, card.price, card.owner);
    }

    function getPriceOf(address cardId)external view returns(uint){
        // require(cards[cardId].owner == msg.sender);
        return cards[cardId].price;
    }

    function getCardOwner(address cardId)external view returns(address){
        address ownerAddr = cards[cardId].owner;
        return ownerAddr;
    }

    function getCardName(address cardId)external view returns(string){
        return cards[cardId].name;
    }

    function setCardOwner(address cardId, address owner)external{
        cards[cardId].owner = owner;
    }

    function setCardOnSale(address cardId, bool onSale)external{
        cards[cardId].isOnSale = onSale;
    }

    function createCard(string name, address cardId, string url, int8 level, address owner)external{
        cards[cardId] = Card(name, level, cardId, url, false, 99999, owner);
    }

  }

### AccountManagementContract
    pragma solidity ^0.4.25;
    contract AccountManagementContract{
      struct Account{
        string name;
        uint balance;
        uint32 drawCount;
        address[] cardsId;
        uint[] transactionsId;
        uint[] requestionsId;   //reverseapplications(requestions) the user get
    }
    mapping(address => Account)accounts;

    function addAccount(address addr, string name)public{
        accounts[addr] = Account(name, 0, 5, new address[](0), new uint[](0), new uint[](0));
    }

    function getAccountInfo(address who)external view returns(string, uint, uint32, uint, uint, uint){
        require(msg.sender == who);
        Account storage account = accounts[who];
        return (account.name, account.balance, account.drawCount, account.cardsId.length, account.transactionsId.length, account.requestionsId.length);
    }

    function getBalanceOf(address addr)external view returns(uint){
        return accounts[addr].balance;
    }

    function setBalanceOf(address addr, uint balance)external{
        accounts[addr].balance = balance;
    }

    function removeCard(address owner, address cardId)external{
        address[] storage ids = accounts[owner].cardsId;
        for(uint i = 0; i < ids.length; i++){
            if(ids[i] == cardId){
                for(uint j = i; j < ids.length - 1; j++){
                    ids[j] = ids[j+1];
                }
                ids.length--;
                return;
            }
        }
    }

    function addCard(address who, address cardId)external{
        accounts[who].cardsId.push(cardId);
    }

    function getCardsNum(address who)external view returns(uint){
        return accounts[who].cardsId.length;
    }

    function getCardId(address who, uint index)external view returns(address){
        return accounts[who].cardsId[index];
    }

    function getDrawCountOf(address addr)external view returns(uint32){
        return accounts[addr].drawCount;
    }

    function setDrawCountOf(address addr, uint32 count)external{
        accounts[addr].drawCount = count;
    }

    function addTransaction(address who, uint transactionId)external{
        accounts[who].transactionsId.push(transactionId);
    }

    function getTransactionsNum(address who)external view returns(uint){
        return accounts[who].transactionsId.length;
    }

    function getTransactionId(address who, uint index)public view returns(uint){
        return accounts[who].transactionsId[index];
    }

    function addRequestions(address who, uint requestionId)external{
        accounts[who].requestionsId.push(requestionId);    
    }

    function getRequestionsNum(address who)external view returns(uint){
        return accounts[who].requestionsId.length;
    }

    function getRequestionId(address who, uint index)public view returns(uint){
        return accounts[who].requestionsId[index];
    }
  }
=======

---
# 2. 李冠海

## 周一   
继续完善区块链教程应用   
## 周二   
讨论项目的需求，参与编写项目的设计文档：包括需求分析、用户角色分析等    
## 周三   
编写智能合约：   
[AccountManagementContract.sol](../Final_project/Contracts/AccountManagementContract.sol)   
[MarketContract.sol](../Final_project/Contracts/MarketContract.sol)    
## 周四   
编写后端控制器和beans：  
**控制器：**    
[AccountController.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/controllers/AccountController.java)    
[CardController.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/controllers/CardController.java)    
[IndexController.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/controllers/IndexController.java)    
[MarketController.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/controllers/MarketController.java)    
[ReverseController.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/controllers/ReverseController.java)    
[TransactionController.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/controllers/TransactionController.java)    
**beans：**    
[AccountInfo.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/beans/AccountInfo.java)    
[CardInfo.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/beans/CardInfo.java)    
[ContractAddr.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/beans/ContractAddr.java)    
[ReverseInfo.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/beans/ReverseInfo.java)    
[TransactionInfo.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/beans/TransactionInfo.java)    

## 周五   
将合约转换为java类，并封装为Client：   
[AccountContractClient.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/clients/AccountContractClient.java)    
[CardContractClient.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/clients/CardContractClient.java)    
[ContractClient.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/clients/ContractClient.java)    
[MarketContractClient.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/clients/MarketContractClient.java)    
[ReverseContractClient.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/clients/ReverseContractClient.java)    
[TransactionContractClient.java](../Final_project/CardGame/src/main/java/org/fisco/bcos/clients/TransactionContractClient.java)    

---
# 3. 石望华
## [个人周报汇总](../day3/石望华/石望华_周报_all_weeks.md)
### 6.24 周一

- 在虚拟机中配置1.8版本的java，出现了新的错误，问了老师，无果，最后的效果还不如上周的了，build不能成功了，详见[《作业报告.md》](../day2/石望华/作业报告_day2_swhua.md)
- 下午一行行代码的测试，debug，终于build成功，在java8下实现了和上周五一样的功能......详情见[《作业报告.md》](../day2/石望华/作业报告_day2_swhua.md)
- 更新了《commands.md》
- 在虚拟机里装中文输入法和IDEA

### 6.25 周二

- 上课

### 6.26 周三

- 请假



### 6.27 周四

- 请假



### 6.28 周五

- 上午请假
- 写项目甘特图
- 在虚拟机的IDEA里跑测试框架，遇到log符号无法识别的问题

### 6.29 周六

- run通了一个**vue**模板，把张羽颀写的登录页面给嵌入了，但动画效果未实现（js文件有问题，与vue不兼容），最后弃疗了，转去实现了几个页面的跳转，但贾学雨发布了个比较全面的基本页面结构，相当于我今天没什么贡献...

### 6.27 周日

- 收集126张卡片图片
- 学部署多群组架构，又遇到了很多问题，问了老师，最后下午四点多才成功打开了控制台！（通过重启节点！！！）详见《FISCO_BCOS文档学习_**3.0**.md》。

# 4. 贾学雨

## 周一 （2019/6/24）

##### 复习spring-boot-starter部署应用的方法，浏览FISCO-BCOS官方文档，学习FISCO-BCOS更多相关知识与应用。

## 周二 （2019/6/25）企业老师授课

- ### 搭建多群组架构区块链

  #### 使用企业级部署工具

  ##### 1. 下载安装

  ##### 1.1 下载安装

  ```
  cd ~/ && git clone https://github.com/FISCO-BCOS/generator.git
  cd generator && bash ./scripts/install.sh
  ```

  ##### 1.2 拉取节点二进制

  ```
  ./generator --download_fisco ./meta
  ```

  ##### 2. 典型示例

  ##### 部署6节点3机构2群组的组网模式

  ![../../_images/tutorial_step_2.png](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/_images/tutorial_step_2.png)

  ##### 3. 联盟链初始化

  ##### 3.1 初始化机构A和B

  ```
  cp -r ~/generator ~/generator-A
  cp -r ~/generator ~/generator-B
  ```

  ##### 3.2 初始化链证书

  ```
  cd ~/generator
  ./generator --generate_chain_certificate ./dir_chain_ca
  ```

  ##### 4. 机构A, B构建群组1

  ##### 4.1 初始化机构A

  ```
  cd ~/generator
  ./generator --generate_agency_certificate ./dir_agency_ca ./dir_chain_ca agencyA
  cp ./dir_chain_ca/ca.crt ./dir_agency_ca/agencyA/agency.crt ./dir_agency_ca/agencyA/agency.key ~/generator-A/meta/
  ```

  ##### 4.2 初始化机构B

  ```
  cd ~/generator
  ./generator --generate_agency_certificate ./dir_agency_ca ./dir_chain_ca agencyB
  cp ./dir_chain_ca/ca.crt ./dir_agency_ca/agencyB/agency.crt ./dir_agency_ca/agencyB/agency.key ~/generator-B/meta/
  ```

  ##### 4.3. 机构A修改配置文件

  ```
  cd ~/generator-A
  cat > ./conf/node_deployment.ini << EOF
  [group]
  group_id=1

  [node0]
  ; host ip for the communication among peers.
  ; Please use your ssh login ip.
  p2p_ip=127.0.0.1
  ; listen ip for the communication between sdk clients.
  ; This ip is the same as p2p_ip for physical host.
  ; But for virtual host e.g. vps servers, it is usually different from p2p_ip.
  ; You can check accessible addresses of your network card.
  ; Please see https://tecadmin.net/check-ip-address-ubuntu-18-04-desktop/
  ; for more instructions.
  rpc_ip=127.0.0.1
  p2p_listen_port=30300
  channel_listen_port=20200
  jsonrpc_listen_port=8545

  [node1]
  p2p_ip=127.0.0.1
  rpc_ip=127.0.0.1
  p2p_listen_port=30301
  channel_listen_port=20201
  jsonrpc_listen_port=8546
  EOF
  ```

  ##### 4.4. 机构B修改配置文件

  ```
  cd ~/generator-B
  cat > ./conf/node_deployment.ini << EOF
  [group]
  group_id=1

  [node0]
  ; host ip for the communication among peers.
  ; Please use your ssh login ip.
  p2p_ip=127.0.0.1
  ; listen ip for the communication between sdk clients.
  ; This ip is the same as p2p_ip for physical host.
  ; But for virtual host e.g. vps servers, it is usually different from p2p_ip.
  ; You can check accessible addresses of your network card.
  ; Please see https://tecadmin.net/check-ip-address-ubuntu-18-04-desktop/
  ; for more instructions.
  rpc_ip=127.0.0.1
  p2p_listen_port=30302
  channel_listen_port=20202
  jsonrpc_listen_port=8547

  [node1]
  p2p_ip=127.0.0.1
  rpc_ip=127.0.0.1
  p2p_listen_port=30303
  channel_listen_port=20203
  jsonrpc_listen_port=8548
  EOF
  ```

  ##### 4.5. 机构A生成并发送节点信息

  ```
  cd ~/generator-A
  ./generator --generate_all_certificates ./agencyA_node_info
  cp ./agencyA_node_info/peers.txt ~/generator-B/meta/peersA.txt

  ```

  ##### 4.6. 机构B生成并发送节点信息

  ```
  cd ~/generator-B
  ./generator --generate_all_certificates ./agencyB_node_info
  cp ./agencyB_node_info/cert*.crt ~/generator-A/meta/
  cp ./agencyB_node_info/peers.txt ~/generator-A/meta/peersB.txt

  ```

  ##### 4.7. 机构A生成群组1创世区块

  ```
  cd ~/generator-A
  cat > ./conf/group_genesis.ini << EOF
  [group]
  group_id=1

  [nodes]
  node0=127.0.0.1:30300
  node1=127.0.0.1:30301
  node2=127.0.0.1:30302
  node3=127.0.0.1:30303
  EOF
  ./generator --create_group_genesis ./group
  cp ./group/group.1.genesis ~/generator-B/meta

  ```

  ##### 4.8. 机构A生成所属节点

  ```
  cd ~/generator-A
  ./generator --build_install_package ./meta/peersB.txt ./nodeA
  bash ./nodeA/start_all.sh

  ```

  ##### 4.9. 机构B生成所属节点

  ```
  cd ~/generator-B
  ./generator --build_install_package ./meta/peersA.txt ./nodeB
  bash ./nodeB/start_all.sh

  ```

  ##### 5. 证书授权机构初始化机构C

  ```
  cd ~/generator
  cp -r ~/generator ~/generator-C
  ./generator --generate_agency_certificate ./dir_agency_ca ./dir_chain_ca agencyC
  cp ./dir_chain_ca/ca.crt ./dir_agency_ca/agencyC/agency.crt ./dir_agency_ca/agencyC/agency.key ~/generator-C/meta/

  ```

  ##### 6. 机构A, C构建群组2

  ##### 6.1 机构A发送节点信息

  ```
  cd ~/generator-A
  cp ./agencyA_node_info/cert*.crt ~/generator-C/meta/
  cp ./agencyA_node_info/peers.txt ~/generator-C/meta/peersA.txt

  ```

  ##### 6.2 机构C修改配置文件

  ```
  cd ~/generator-C
  cat > ./conf/node_deployment.ini << EOF
  [group]
  group_id=2

  [node0]
  ; host ip for the communication among peers.
  ; Please use your ssh login ip.
  p2p_ip=127.0.0.1
  ; listen ip for the communication between sdk clients.
  ; This ip is the same as p2p_ip for physical host.
  ; But for virtual host e.g. vps servers, it is usually different from p2p_ip.
  ; You can check accessible addresses of your network card.
  ; Please see https://tecadmin.net/check-ip-address-ubuntu-18-04-desktop/
  ; for more instructions.
  rpc_ip=127.0.0.1
  p2p_listen_port=30304
  channel_listen_port=20204
  jsonrpc_listen_port=8549

  [node1]
  p2p_ip=127.0.0.1
  rpc_ip=127.0.0.1
  p2p_listen_port=30305
  channel_listen_port=20205
  jsonrpc_listen_port=8550
  EOF

  ```

  ##### 6.3 机构C生成并发送节点信息

  ```
  cd ~/generator-C
  ./generator --generate_all_certificates ./agencyC_node_info
  cp ./agencyC_node_info/peers.txt ~/generator-A/meta/peersC.txt

  ```

  ##### 6.4 机构C生成群组2创世区块

  ```
  cd ~/generator-C
  cat > ./conf/group_genesis.ini << EOF
  [group]
  group_id=2

  [nodes]
  node0=127.0.0.1:30300
  node1=127.0.0.1:30301
  node2=127.0.0.1:30304
  node3=127.0.0.1:30305
  EOF
  ./generator --create_group_genesis ./group
  cp ./group/group.2.genesis ~/generator-A/meta/

  ```

  ##### 6.5 机构C生成所属节点

  ```
  cd ~/generator-C
  ./generator --build_install_package ./meta/peersA.txt ./nodeC
  bash ./nodeC/start_all.sh

  ```

  ##### 6.6 机构A为现有节点初始化群组2

  ```
  cd ~/generator-A
  ./generator --add_group ./meta/group.2.genesis ./nodeA
  ./generator --add_peers ./meta/peersC.txt ./nodeA
  bash ./nodeA/stop_all.sh
  bash ./nodeA/start_all.sh

  ```

- ### 公布课程最终作业和考核内容

  - ##### 设计宠物售卖和转卖的分布式市场

  - ##### 区块链搭建

  - ##### 智能合约

  - ##### 业务逻辑

  - ##### 前端设计

  - ##### 测试项

## 周三 （2019/6/26）

##### 参与小组内区块链场景设计，被分配搭链的工作，于是开始学习FISCO-BCOS多群组架构和企业级部署的知识。

## 周四 （2019/6/27）

##### 完成FISCO-BCOS多群组架构和企业级部署的知识学习，并开始尝试搭建多群组区块链。

## 周五 （2019/6/28）

##### 由于组内计划更改，被分配了前端设计的工作。于是设计出前端页面的基本结构，并开始构建前端。
--------
# 5. 张羽颀

## 周一
1. 继续学习Vue.js，Vue-cli，学习HTML/CSS/JS等前端知识，为之后的前端页面制作打下基础。
## 周二
请假
## 周三
参与讨论需求，确定前端页面需求，制作登陆页面:    
![333](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/login.png)
## 周四
- 设计并制作初始页面:![初始页面](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/index.png)

- 设计并制作用户页面:![](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/account.png)
## 周五
- 设计并制作交易信息页面，交易表格：![](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/transactions.png)

- 设计并制作管理员信息界面：![](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/ad.png)
## 周六
- 设计并制作用户卡组信息界面：![](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/card.png)
- 设计并制作卡片商店界面： ![](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/card.png)
## 周日
-设计并制作抽卡界面：![](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/card_out1.png)
- 完成抽卡页面：![](https://raw.githubusercontent.com/blockchaingroup4/webank/master/day3/%E5%BC%A0%E7%BE%BD%E9%A2%80/images_day3/card_out2.png)


# 最后一周：
完善各个页面的HTML/CSS/JS文件，修正部分bug。

与后端工程师陈思源，李冠海一同植入区块链逻辑，完成前后端连接工作，完成逻辑连接工作。

结束前端制作，玩成PPT制作和答辩。

### 特别鸣谢：前端制作过程中李冠海同学参与帮助完成了前端逻辑架构，陈思源同学参与了页面的制作和PS美工。
