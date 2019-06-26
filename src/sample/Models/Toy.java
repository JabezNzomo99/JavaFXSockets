package sample.Models;

import java.io.Serializable;

public class Toy implements Serializable {
    private String Code;
    private String Name;
    private String Description;
    private int Price;
    private String Dom;
    private String BatchNumber;

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    private String customMessage;
    private ToyManfacturer toyManfacturer;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getDom() {
        return Dom;
    }

    public void setDom(String dom) {
        Dom = dom;
    }

    public String getBatchNumber() {
        return BatchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        BatchNumber = batchNumber;
    }

    public ToyManfacturer getToyManfacturer() {
        return toyManfacturer;
    }

    public void setToyManfacturer(ToyManfacturer toyManfacturer) {
        this.toyManfacturer = toyManfacturer;
    }
}
