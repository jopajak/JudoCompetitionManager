module com.example.judocompetitionmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires leveldb.api;
    requires java.logging;
    requires leveldb;
    requires json;
    requires json.simple;

    opens com.example.judocompetitionmanager to javafx.fxml;
    exports com.example.judocompetitionmanager;
}