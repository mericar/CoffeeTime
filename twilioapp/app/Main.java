package app;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    static {
        String ACCOUNT_SID = "";
        String AUTH_TOKEN = "";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
	public void start(Stage primaryStage) throws Exception{

        GridPane grid = new GridPane();
        grid.setBackground(new Background(new BackgroundFill(Paint.valueOf(String.valueOf(Color.DIMGREY)), null, null)));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome to CallTown");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        grid.add(scenetitle, 0, 0, 2, 1);

        Label toNumber = new Label("To Number:");
        grid.add(toNumber, 0, 1);

        TextField toNumberField = new TextField();
        grid.add(toNumberField, 1, 1);

        Label msg = new Label("Message:");
        grid.add(msg, 0, 2);

        TextArea msgArea = new TextArea();
        msgArea.setPrefColumnCount(4);
        msgArea.setPrefRowCount(5);

        grid.add(msgArea, 1, 2);

        Button btn = new Button("Send Message");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(e -> {
		actiontarget.setFill(Color.DARKCYAN);

		PhoneNumber FROM = new PhoneNumber("");

		Message message = Message.creator(new PhoneNumber(toNumberField.getText()), FROM, msgArea.getText()).create();
		actiontarget.setText("Sent Message to " + toNumberField.getText());
		toNumberField.clear();
		msgArea.clear();

		System.out.println(message.getSid());
	    });

        Scene scene = new Scene(grid, 600, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}