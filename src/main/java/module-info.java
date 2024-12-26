module org.r4ppz {
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;

    requires transitive java.desktop;

    opens org.r4ppz to javafx.fxml;
    opens org.r4ppz.controller.dialog to javafx.fxml;
    opens org.r4ppz.controller.main to javafx.fxml;

    exports org.r4ppz;
    exports org.r4ppz.util;
    exports org.r4ppz.model;
    exports org.r4ppz.test;
    exports org.r4ppz.service;
    exports org.r4ppz.view.dialog;
}