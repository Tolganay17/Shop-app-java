package com.example.proglanglab.fxControllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RanobeTable {
    private SimpleIntegerProperty ranobe_id=new SimpleIntegerProperty();
    private SimpleStringProperty ranobe = new SimpleStringProperty();
    private SimpleStringProperty ran_uthor = new SimpleStringProperty();
    private SimpleStringProperty ran_release = new SimpleStringProperty();
    private SimpleStringProperty ran_price = new SimpleStringProperty();
    private  SimpleIntegerProperty ran_parts=new SimpleIntegerProperty();
    private SimpleStringProperty ran_langu=new SimpleStringProperty();

    public RanobeTable() {
    }

    public int getRanobe_id() {
        return ranobe_id.get();
    }

    public SimpleIntegerProperty ranobe_idProperty() {
        return ranobe_id;
    }

    public void setRanobe_id(int ranobe_id) {
        this.ranobe_id.set(ranobe_id);
    }

    public String getRanobe() {
        return ranobe.get();
    }

    public SimpleStringProperty ranobeProperty() {
        return ranobe;
    }

    public void setRanobe(String ranobe) {
        this.ranobe.set(ranobe);
    }

    public String getRan_uthor() {
        return ran_uthor.get();
    }

    public SimpleStringProperty ran_uthorProperty() {
        return ran_uthor;
    }

    public void setRan_uthor(String ran_uthor) {
        this.ran_uthor.set(ran_uthor);
    }

    public String getRan_release() {
        return ran_release.get();
    }

    public SimpleStringProperty ran_releaseProperty() {
        return ran_release;
    }

    public void setRan_release(String ran_release) {
        this.ran_release.set(ran_release);
    }

    public String getRan_price() {
        return ran_price.get();
    }

    public SimpleStringProperty ran_priceProperty() {
        return ran_price;
    }

    public void setRan_price(String ran_price) {
        this.ran_price.set(ran_price);
    }

    public int getRan_parts() {
        return ran_parts.get();
    }

    public SimpleIntegerProperty ran_partsProperty() {
        return ran_parts;
    }

    public void setRan_parts(int ran_parts) {
        this.ran_parts.set(ran_parts);
    }

    public String getRan_langu() {
        return ran_langu.get();
    }

    public SimpleStringProperty ran_languProperty() {
        return ran_langu;
    }

    public void setRan_langu(String ran_langu) {
        this.ran_langu.set(ran_langu);
    }
}
