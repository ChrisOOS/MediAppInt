/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mediappint;

/**
 *
 * @author student
 */
public class LabOrder {
    private String placerNum = "";
    private String labOrderControl = "";
    private String fillerOrderNum = "";
    private String dateTransaction = "";
    private String serviceIdentifier = "";
    
    LabOrder(){}

    public String getPlacerNum() {
        return placerNum;
    }

    public void setPlacerNum(String placerNum) {
        this.placerNum = placerNum;
    }

    public String getLabOrderControl() {
        return labOrderControl;
    }

    public void setLabOrderControl(String labOrderControl) {
        this.labOrderControl = labOrderControl;
    }

    public String getFillerOrderNum() {
        return fillerOrderNum;
    }

    public void setFillerOrderNum(String fillerOrderNum) {
        this.fillerOrderNum = fillerOrderNum;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(String serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }
    
    
}
