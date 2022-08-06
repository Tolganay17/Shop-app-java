package com.example.proglanglab.classes;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
public class ShopAssistant extends User {

    private String AssistantName;
    private String AssistantSecondName;
    private String phoneNum;

    public ShopAssistant() {
    }

    @Override
    public void remove(User user) {

    }

    public ShopAssistant(String username, String password, List<Order> myOrders, boolean hasRights, String assistantName, String assistantSecondName, String phoneNum) {
        super(username, password, myOrders, hasRights);
        AssistantName = assistantName;
        AssistantSecondName = assistantSecondName;
        this.phoneNum = phoneNum;
    }

    public ShopAssistant(String username, String password, String assistantName, String assistantSecondName, String phoneNum) {
        super(username, password);
        AssistantName = assistantName;
        AssistantSecondName = assistantSecondName;
        this.phoneNum = phoneNum;
    }

    public String getAssistantName() {
        return AssistantName;
    }

    public void setAssistantName(String assistantName) {
        AssistantName = assistantName;
    }

    public String getAssistantSecondName() {
        return AssistantSecondName;
    }

    public void setAssistantSecondName(String assistantSecondName) {
        AssistantSecondName = assistantSecondName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


}
