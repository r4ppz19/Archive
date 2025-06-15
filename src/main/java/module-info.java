module com.r4ppz {
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;

    requires transitive java.desktop;
    requires org.checkerframework.checker.qual;

    opens com.r4ppz to javafx.fxml;
    opens com.r4ppz.controller.dialog to javafx.fxml;
    opens com.r4ppz.controller.main to javafx.fxml;

    exports com.r4ppz;
    exports com.r4ppz.util;
    exports com.r4ppz.model;
    exports com.r4ppz.service;
    exports com.r4ppz.view.dialog;
}