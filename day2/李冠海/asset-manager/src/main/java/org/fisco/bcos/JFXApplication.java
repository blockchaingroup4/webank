package org.fisco.bcos;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.fisco.bcos.contract.AssetClient;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableConfigurationProperties
public class JFXApplication extends Application implements CommandLineRunner {
    @Autowired
    Web3j web3j;
    static Web3j sWeb3j;


    public static void main(String[] args) {
        SpringApplication.run(JFXApplication.class, args);
        Application.launch(args);
    }

    @Override
    public void run(String... args)throws Exception{
        sWeb3j = web3j;
    }

    //test private key:
    //76b0e9b6787daf90e59e9aebcd65523428b63aa8300be474ca33b8c3f988ee6a

    //test other account addr:
    //0x0cf5c47c9f55b6c0c2e017191c68cef908b79d2c
    //test other account private key:
    //abf1ea7acaa9a0ddd8ac39b077a23d17ac3e584efb2b3e9def971460f270c263
    Stage mainStage;
    Stage keyStage;
    Alert alertDialog;
    AssetClient client;
    final double MAIN_STAGE_WIDTH = 450;
    final double MAIN_STAGE_HEIGHT = 400;
    final double KEY_STAGE_WIDTH = 500;
    final double KEY_STAGE_HEIGHT = 200;
    @Override
    public void start(Stage stage){
        alertDialog = new Alert(Alert.AlertType.NONE);
        buildKeyStage();
        keyStage.show();
    }

    void buildKeyStage(){
        keyStage = new Stage();
        keyStage.setWidth(KEY_STAGE_WIDTH);
        keyStage.setHeight(KEY_STAGE_HEIGHT);
        TabPane keyRoot = new TabPane(getStartTab(), getGenTab());
        Scene keyScene = new Scene(keyRoot);
        keyStage.setScene(keyScene);
        keyStage.centerOnScreen();
    }

    void buildMainStage(){
        mainStage = new Stage();
        mainStage.setWidth(MAIN_STAGE_WIDTH);
        mainStage.setHeight(MAIN_STAGE_HEIGHT);
        Button logOutBtn = new Button("Log Out");
        VBox mainRoot = new VBox(getRegisterTitledPane(),
                getAmountTitledPane(),
                getTransferTitledPane(),
                logOutBtn);
        populateVBox(mainRoot);
        Scene mainScene = new Scene(mainRoot);
        mainStage.setScene(mainScene);
        logOutBtn.setOnMouseClicked(e -> {
            mainStage.close();
            buildKeyStage();
            keyStage.show();
        });
    }

    Tab getStartTab(){
        PasswordField privateKeyField = new PasswordField();
        privateKeyField.setPromptText("please enter your private key");
        Button keyOkBtn = new Button("OK");
        VBox startRoot = new VBox(privateKeyField, keyOkBtn);
        Tab startTab = new Tab("Start", startRoot);
        populateVBox(startRoot);

        keyOkBtn.setOnMouseClicked(e -> {
            String privateKey = privateKeyField.getText();
            Credentials credentials = GenCredential.create(privateKey);
            alertDialog.setAlertType(Alert.AlertType.ERROR);
            if (credentials == null) {
                alertDialog.setContentText("Private key invalid!");
                alertDialog.show();
                return;
            }
            client = new AssetClient(sWeb3j, credentials);
            if(client == null){
                alertDialog.setContentText("Create client fail!");
                alertDialog.show();
                return;
            }
            if(!client.load()){
                alertDialog.setContentText("Load contract fail!");
                alertDialog.show();
                return;
            }
            keyStage.close();
            buildMainStage();
            mainStage.centerOnScreen();
            mainStage.show();
        });
        return startTab;
    }

    Tab getGenTab(){
        final String PLACEHOLDER = "0x0000000000000000000000000000000000000000";
        Label privateKeyLabel = new Label("Private Key: ");
        Label publicKeyLabel = new Label("Public Key: ");
        Label addressLabel = new Label("Adddress: ");
        TextField privateKeyField = new TextField(PLACEHOLDER);
        TextField publicKeyField = new TextField(PLACEHOLDER);
        TextField addressField = new TextField(PLACEHOLDER);
        Label[] labels = {addressLabel, publicKeyLabel, privateKeyLabel};
        TextField[] fields = {addressField, publicKeyField, privateKeyField};
        Button genBtn = new Button("Generate");
        GridPane genRoot = new GridPane();
        for(int i = 0; i < 3; i++){
            genRoot.add(labels[i],0,i);
            genRoot.add(fields[i],1, i);
            fields[i].setEditable(false);
            GridPane.setHgrow(fields[i], Priority.ALWAYS);
        }
        genRoot.add(genBtn,0, 3, 2, 1);
        genBtn.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(genBtn,Priority.ALWAYS);
        Tab genTab = new Tab("Account Generator", genRoot);
        populateGrid(genRoot);

        genBtn.setOnMouseClicked(e -> {
            UserKeyUtils.UserKeys keys = UserKeyUtils.createUserKey();
            privateKeyField.setText(keys.privateKey);
            publicKeyField.setText(keys.publicKey);
            addressField.setText(keys.address);
        });
        return genTab;
    }

    TitledPane getRegisterTitledPane(){
        TitledPane registerPane = new TitledPane();
        Label amountLabel = new Label("Amount: ");
        TextField amountField = new TextField();
        Button registerBtn = new Button("Register");
        GridPane registerRoot = new GridPane();
        registerRoot.add(amountLabel,0,0);
        registerRoot.add(amountField,1,0);
        registerRoot.add(registerBtn, 0, 1, 2, 1);
        registerPane.setText("Register");
        registerPane.setContent(registerRoot);
        populateGrid(registerRoot);

        GridPane.setHgrow(amountField,Priority.ALWAYS);
        GridPane.setHalignment(registerBtn, HPos.RIGHT);

        registerBtn.setOnMouseClicked(e -> {
            int result = client.register(new BigInteger(amountField.getText()));
            alertDialog.setAlertType(Alert.AlertType.ERROR);
            switch (result){
                case AssetClient.REGISTER_SUCCESS:
                    alertDialog.setAlertType(Alert.AlertType.INFORMATION);
                    alertDialog.setContentText("Registering succeeded!");
                    break;
                case AssetClient.REGISTER_ACCOUNT_ALREAD_EXISTS:
                    alertDialog.setContentText("Account already exists!");
                    break;
                case AssetClient.REGISTER_UNKOWN_ERROR:
                    alertDialog.setContentText("Registering failed ");
                    break;
            }
            alertDialog.show();
        });
        registerBtn.setDisable(true);
        registerBtn.disableProperty().bind(amountField.textProperty().isEmpty());
        return registerPane;
    }

    TitledPane getAmountTitledPane(){
        TitledPane amountPane = new TitledPane();
        Label amountLabel = new Label("Amount: ");
        TextField resultField = new TextField();
        resultField.setEditable(false);
        Button queryBtn = new Button("Query");
        GridPane amountRoot = new GridPane();
        amountRoot.add(amountLabel, 0, 0);
        amountRoot.add(resultField, 1, 0);
        amountRoot.add(queryBtn,0,1, 2, 1);
        amountPane.setText("Amount");
        amountPane.setContent(amountRoot);
        populateGrid(amountRoot);

        GridPane.setHgrow(resultField, Priority.ALWAYS);
        GridPane.setHalignment(queryBtn, HPos.RIGHT);

        queryBtn.setOnMouseClicked(e -> {
            BigInteger result = client.getAmount();
            if(result.compareTo(new BigInteger("0")) < 0){
                alertDialog.setAlertType(Alert.AlertType.ERROR);
                alertDialog.setContentText("Query failed: Account doesn't exist!");
            }
            else{
                resultField.setText(String.valueOf(result));
                alertDialog.setAlertType(Alert.AlertType.INFORMATION);
                alertDialog.setContentText("Query succeeded!");
            }
            alertDialog.show();
        });
        return amountPane;
    }

    TitledPane getTransferTitledPane(){
        TitledPane transferPane = new TitledPane();
        Label addrLabel = new Label("To: ");
        TextField addrField = new TextField();
        Label amountLabel = new Label("Amount: ");
        TextField amountField = new TextField();
        Button transferBtn = new Button("Transfer");
        GridPane transferRoot = new GridPane();
        transferRoot.add(addrLabel,0,0);
        transferRoot.add(addrField, 1,0);
        transferRoot.add(amountLabel, 0, 1);
        transferRoot.add(amountField,1,1);
        transferRoot.add(transferBtn,0,2, 2, 1);
        transferPane.setText("Transfer");
        transferPane.setContent(transferRoot);
        populateGrid(transferRoot);

        GridPane.setHgrow(addrField, Priority.ALWAYS);
        GridPane.setHgrow(amountField, Priority.ALWAYS);
        GridPane.setHalignment(transferBtn, HPos.RIGHT);

        transferBtn.disableProperty().bind(addrField.textProperty().isEmpty().or(amountField.textProperty().isEmpty()));
        transferBtn.setOnMouseClicked(e -> {
            String toAddr = addrField.getText();
            BigInteger amount = new BigInteger(amountField.getText());
            int retCode = client.transfer(toAddr,amount);
            alertDialog.setAlertType(Alert.AlertType.ERROR);
            switch (retCode){
                case AssetClient.TRANSFER_SUCCESS:
                    alertDialog.setAlertType(Alert.AlertType.INFORMATION);
                    alertDialog.setContentText("Transferring succeeded!");
                    break;
                case AssetClient.TRANSFER_SRC_ACCOUNT_NOT_EXISTS:
                    alertDialog.setContentText("Source account doesn't exist!");
                    break;
                case AssetClient.TRANSFER_DST_ACCOUNT_NOT_EXISTS:
                    alertDialog.setContentText("Target account doesn't exist!");
                    break;
                case AssetClient.TRANSFER_AMOUNT_NOT_ENOUTH:
                    alertDialog.setContentText("Amount of source account is not enough!");
                    break;
                case AssetClient.TRANSFER_AMOUNT_OVERFLOW:
                    alertDialog.setContentText("Amount overflows");
                    break;
                case AssetClient.TRANSFER_UNKOWN_ERROR:
                    alertDialog.setContentText("Transferring failed due to unknown error!");
                    break;
            }
            alertDialog.show();
        });
        return transferPane;
    }

    final double hGap = 5;
    final double vGap = 5;
    final double padding = 5;
    void populateGrid(GridPane pane){
        pane.setHgap(hGap);
        pane.setVgap(vGap);
        pane.setPadding(new Insets(padding));
    }

    void populateVBox(VBox box){
        box.setPadding(new Insets(padding));
        box.setSpacing(vGap);
        box.setAlignment(Pos.TOP_RIGHT);
    }
}
