module fr.utbm.info.ap4b_project_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens fr.utbm.info.ap4b_project_fx to javafx.fxml;
    exports fr.utbm.ap4b_project_fx.energySims.controller;
    opens fr.utbm.ap4b_project_fx.energySims.controller to javafx.fxml;
    exports fr.utbm.ap4b_project_fx.energySims;
    opens fr.utbm.ap4b_project_fx.energySims to javafx.fxml;
}