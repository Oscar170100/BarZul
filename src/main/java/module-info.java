module org.uacm.barzul.bar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens org.uacm.barzul.bar to javafx.fxml;
    exports org.uacm.barzul.bar;
}
