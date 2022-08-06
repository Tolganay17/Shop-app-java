module com.example.proglanglab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires java.naming;
    requires mysql.connector.java;
    requires org.hibernate.orm.core;
    requires spring.context;
    requires spring.web;
    requires com.google.gson;
    requires spring.core;
    requires javafx.graphics;


    opens com.example.proglanglab to javafx.fxml;
    exports com.example.proglanglab;
    exports com.example.proglanglab.webb;
    exports com.example.proglanglab.fxControllers to javafx.fxml;
    opens com.example.proglanglab.fxControllers to javafx.base, javafx.fxml;
    opens com.example.proglanglab.classes to org.hibernate.orm.core;
    exports  com.example.proglanglab.utils to  org.hibernate.orm.core;
}