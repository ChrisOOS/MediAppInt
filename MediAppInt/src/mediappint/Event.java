/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mediappint;

/**
 *
 * @author chriso
 */
public class Event {
    
    private String EventTypeCode = "";
    private String RecDateTime = "";
    private String DateTimePlanned = "";
    private String EventReason = "";
    private String OperatorID = "";
    private String EventOccurred = "";

    public String getEventTypeCode() {
        return EventTypeCode;
    }

    public void setEventTypeCode(String EventTypeCode) {
        this.EventTypeCode = EventTypeCode;
    }

    public String getRecDateTime() {
        return RecDateTime;
    }

    public void setRecDateTime(String RecDateTime) {
        this.RecDateTime = RecDateTime;
    }

    public String getDateTimePlanned() {
        return DateTimePlanned;
    }

    public void setDateTimePlanned(String DateTimePlanned) {
        this.DateTimePlanned = DateTimePlanned;
    }

    public String getEventReason() {
        return EventReason;
    }

    public void setEventReason(String EventReason) {
        this.EventReason = EventReason;
    }

    public String getOperatorID() {
        return OperatorID;
    }

    public void setOperatorID(String OperatorID) {
        this.OperatorID = OperatorID;
    }

    public String getEventOccurred() {
        return EventOccurred;
    }

    public void setEventOccurred(String EventOccurred) {
        this.EventOccurred = EventOccurred;
    }
    
    
    
}
