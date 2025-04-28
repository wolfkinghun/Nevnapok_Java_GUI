module com.example.nevnapok {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.nevnapok to javafx.fxml;
    exports com.example.nevnapok;
}