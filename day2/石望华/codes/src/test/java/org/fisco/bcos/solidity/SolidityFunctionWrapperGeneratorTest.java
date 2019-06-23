package org.fisco.bcos.solidity;

import static org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler.Options.ABI;
import static org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler.Options.BIN;
import static org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler.Options.INTERFACE;
import static org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler.Options.METADATA;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator;
import org.fisco.bcos.web3j.solidity.compiler.CompilationResult;
import org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

//  Solidity合约文件转Java合约文件测试
@Slf4j
public class SolidityFunctionWrapperGeneratorTest  {

    protected String tempDirPath =  new File("src/test/java/").getAbsolutePath();
    protected String packageName =  "org.fisco.bcos.solidity";

//根据.bin和.abi生成类
    @Test
    public void generateClassFromABIForHelloWorld() throws Exception {

        String binFile1 =  new ClassPathResource("solidity/HelloWorld.bin").getFile().getAbsolutePath();
        String abiFile1 =  new ClassPathResource("solidity/HelloWorld.abi").getFile().getAbsolutePath();
        SolidityFunctionWrapperGenerator.main(Arrays.asList(
               "-b", binFile1,
                "-a", abiFile1,
                "-p", packageName,
                "-o", tempDirPath
        ).toArray(new String[0]));
    }



    @Test
    public void compileSolFilesToJavaTest() throws IOException {
        File solFileList = new File("src/test/resources/contract");
        File[] solFiles = solFileList.listFiles();    //  获取所有合约文件

        for (File solFile : solFiles) {
//            编译
            SolidityCompiler.Result res = SolidityCompiler.compile(solFile, true, ABI, BIN, INTERFACE, METADATA);
            log.info("Out: '{}'" , res.output );
            log.info("Err: '{}'" , res.errors );
//            解析编译结果
            CompilationResult result = CompilationResult.parse(res.output);
            log.info("contractname  {}" , solFile.getName());
            String contractname = solFile.getName().split("\\.")[0];
            CompilationResult.ContractMetadata a = result.getContract(solFile.getName().split("\\.")[0]);   //  从结果中获取合约元数据
            log.info("abi   {}" , a.abi);
            log.info("bin   {}" , a.bin);
//            转为相应的abi和bin文件，保存在src/test/resources/solidity目录下
            FileUtils.writeStringToFile(new File("src/test/resources/solidity/" + contractname + ".abi"), a.abi);//  将合约元数据中的abi信息写入abi文件（将合约文件转为相应的abi文件）
            FileUtils.writeStringToFile(new File("src/test/resources/solidity/" + contractname + ".bin"), a.bin);//  将合约元数据中的bin信息写入bin文件（将合约文件转为相应的bin文件）

//            下面的代码和generateClassFromABIForHelloWorld函数一样的
//            将abi文件和对应的bin文件组合转换为Java合约文件
//            保存在src/test/java/org/fisco/bcos/temp目录下
            String abiFile;
            String binFile;
            String tempDirPath = new File("src/test/java/").getAbsolutePath();
            String packageName = "org.fisco.bcos.temp";
            String filename = contractname;
            abiFile = "src/test/resources/solidity/" + filename + ".abi";
            binFile = "src/test/resources/solidity/" + filename + ".bin";
            SolidityFunctionWrapperGenerator.main(Arrays.asList(
                    "-a", abiFile,
                    "-b", binFile,
                    "-p", packageName,
                    "-o", tempDirPath
            ).toArray(new String[0]));
        }
        System.out.println("generate successfully");
    }
//构造函数
    public SolidityFunctionWrapperGeneratorTest() throws IOException {
        
    }

}
