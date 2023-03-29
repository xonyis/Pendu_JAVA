module com.example.pendu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pendu to javafx.fxml;
    exports com.example.pendu;
}