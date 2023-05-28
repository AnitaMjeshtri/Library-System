module com.example.anitamjeshtrifinalpj {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.anitamjeshtrifinalpj to javafx.fxml;
    exports com.example.anitamjeshtrifinalpj;
}