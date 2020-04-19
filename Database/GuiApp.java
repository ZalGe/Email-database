package Database;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static Database.InterfaceTexts.programName;

public class GuiApp extends Application {
    Database d = new Database();

    Stage window;
    Scene Welcome, Register, MainMenu, NewE, ShowE, DeleteE, Error, Choice;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle(programName);

        GridPane gridW = new GridPane();
        gridW.setPadding(new Insets(10,10,10,10));
        gridW.setVgap(5);
        gridW.setHgap(50);

        Label WelcomeText = new Label("Welcome to " + programName + ", with us your passwords\nwill be safe and always at hand!");
        GridPane.setConstraints(WelcomeText, 1, 0);

        Label nameLable = new Label("Name:");
        GridPane.setConstraints(nameLable, 0, 1);

        TextField nameInput = new TextField();
        nameInput.setPromptText("DATABASE NAME");
        GridPane.setConstraints(nameInput, 1, 1);

        Label passLable = new Label("Password:");
        GridPane.setConstraints(passLable, 0, 2);

        TextField passInput = new TextField();
        passInput.setPromptText("**********");
        GridPane.setConstraints(passInput, 1, 2);

        Label confirmLabel = new Label("Confirm\npassword:");
        GridPane.setConstraints(confirmLabel, 0, 3);

        TextField confirmInput = new TextField();
        confirmInput.setPromptText("Password");
        GridPane.setConstraints(confirmInput, 1, 3);

        Button LogIn = new Button("Log in");
        LogIn.setOnAction(e-> window.setScene(MainMenu));
        GridPane.setConstraints(LogIn, 1, 4);

        Label RegLabel = new Label("Want new database? No problem!");
        GridPane.setConstraints(RegLabel, 1 , 8);

        Button Create = new Button("Register new database");
        Create.setOnAction(e-> window.setScene(Register));
        GridPane.setConstraints(Create, 1, 9);

        Button Cancel1 = new Button("Cancel");
        Cancel1.setOnAction(e-> window.close());
        GridPane.setConstraints(Cancel1, 2, 15);

        gridW.getChildren().addAll(WelcomeText, nameLable, nameInput, passLable, passInput, confirmLabel, confirmInput, LogIn, RegLabel, Create, Cancel1);
        Welcome = new Scene(gridW, 525, 300);


        GridPane gridMM = new GridPane();
        gridMM.setPadding(new Insets(10,10,10,10));
        gridMM.setVgap(5);
        gridMM.setHgap(50);

        Label infoText = new Label("What would you like to do?");
        GridPane.setConstraints(infoText, 0, 0);

        Button AddE = new Button("Enter new Element");
        AddE.setOnAction(e-> window.setScene(NewE));
        GridPane.setConstraints(AddE, 0, 1);

        Button showE = new Button("Show wanted element");
        showE.setOnAction(e-> window.setScene(ShowE));
        GridPane.setConstraints(showE, 0, 2);

        Button DelE = new Button("Delete element");
        DelE.setOnAction(e-> window.setScene(DeleteE));
        GridPane.setConstraints(DelE, 0, 3);

        Button ShowD = new Button("Show whole database");
        ShowD.setOnAction(e-> {
            try {
                d.ReadDatabase();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        GridPane.setConstraints(ShowD, 0, 4);

        Button GenPass = new Button("Generate new password");
        GenPass.setOnAction(e-> d.passGen(15));
        GridPane.setConstraints(GenPass, 0, 5);

        Button Back1 = new Button("<< Back");
        Back1.setOnAction(e-> window.setScene(Welcome));
        GridPane.setConstraints(Back1, 0, 15);

        Button Cancel2 = new Button("Cancel");
        Cancel2.setOnAction(e-> window.close());
        GridPane.setConstraints(Cancel2, 1, 15);

        gridMM.getChildren().addAll(infoText, AddE, showE, DelE, ShowD, GenPass, Back1, Cancel2);
        MainMenu = new Scene(gridMM, 325, 275);

        window.setScene(MainMenu);
        window.show();
    }
}
