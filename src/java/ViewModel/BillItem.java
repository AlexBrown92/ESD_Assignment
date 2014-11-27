/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

/**
 *
 * @author david
 */
public class BillItem {

    private int billID;
    private int medicineID;
    private String name;
    private int cost;
    private int quantity;
    
    public BillItem(DatabaseModel.BillItem billItem, DatabaseModel.Medicine medicine){
        billID = billItem.getBillId();
        medicineID = medicine.getID();
        name = medicine.getName();
        cost = medicine.getCost();
        quantity = billItem.getQuanitity();
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getTotal(){
        return this.quantity * this.cost; 
    }
    
    
}
