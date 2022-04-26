module com.example.ee364project {
    exports EE364Project;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens EE364Project to javafx.graphics, javafx.fxml;
}