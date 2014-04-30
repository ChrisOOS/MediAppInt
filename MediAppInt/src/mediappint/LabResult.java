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
public class LabResult {
    private String placerNum = "";
    private String fillerOrderNum = "";
    private String universalServiceIdentifier = "";
    private String dateTransaction = "";
    private String labStatus = "";
    private String labResults = "";
    private String valueType = "";
    private String observationValue = "";
    private String observationIdentifier= "";
    private String observationResultStatus= "";
    private String labOrderingPhysician= "";
    
    LabResult(){}

    public String getLabOrderingPhysician() {
        return labOrderingPhysician;
    }

    public void setLabOrderingPhysician(String labOrderingPhysician) {
        this.labOrderingPhysician = labOrderingPhysician;
    }

    public String getPlacerNum() {
        return placerNum;
    }

    public void setPlacerNum(String placerNum) {
        this.placerNum = placerNum;
    }

    public String getFillerOrderNum() {
        return fillerOrderNum;
    }

    public void setFillerOrderNum(String fillerOrderNum) {
        this.fillerOrderNum = fillerOrderNum;
    }

    public String getUniversalServiceIdentifier() {
        return universalServiceIdentifier;
    }

    public void setUniversalServiceIdentifier(String universalServiceIdentifier) {
        this.universalServiceIdentifier = universalServiceIdentifier;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getObservationValue() {
        return observationValue;
    }

    public void setObservationValue(String observationValue) {
        this.observationValue = observationValue;
    }

    public String getObservationIdentifier() {
        return observationIdentifier;
    }

    public void setObservationIdentifier(String observationIdentifier) {
        this.observationIdentifier = observationIdentifier;
    }

    public String getObservationResultStatus() {
        return observationResultStatus;
    }

    public void setObservationResultStatus(String observationResultStatus) {
        this.observationResultStatus = observationResultStatus;
    }

    public String getLabStatus() {
        return labStatus;
    }

    public void setLabStatus(String labStatus) {
        this.labStatus = labStatus;
    }

    public String getLabResults() {
        return labResults;
    }

    public void setLabResults(String labResults) {
        this.labResults = labResults;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
    
}
