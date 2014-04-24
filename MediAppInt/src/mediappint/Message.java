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
public class Message {
    
    private String SendingApp;
    private String SendingFacility;
    private String ReceivingApp;
    private String ReceivingFacility;
    private String MsgDateTime;
    private String Security;
    private String MsgType;
    private String MsgSubType;
    private String MsgCtrl;
    private String ProcessingId;
    private String VersionId;
    
	Message(){}

    public String getSendingApp() {
        return SendingApp;
    }

    public void setSendingApp(String SendingApp) {
        this.SendingApp = SendingApp;
    }

    public String getSendingFacility() {
        return SendingFacility;
    }

    public void setSendingFacility(String SendingFacility) {
        this.SendingFacility = SendingFacility;
    }

    public String getReceivingApp() {
        return ReceivingApp;
    }

    public void setReceivingApp(String ReceivingApp) {
        this.ReceivingApp = ReceivingApp;
    }

    public String getReceivingFacility() {
        return ReceivingFacility;
    }

    public void setReceivingFacility(String ReceivingFacility) {
        this.ReceivingFacility = ReceivingFacility;
    }

    public String getMsgDateTime() {
        return MsgDateTime;
    }

    public void setMsgDateTime(String MsgDateTime) {
        this.MsgDateTime = MsgDateTime;
    }

    public String getSecurity() {
        return Security;
    }

    public void setSecurity(String Security) {
        this.Security = Security;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String MsgType) {
        this.MsgType = MsgType;
    }
    
    public String getMsgSubType() {
        return MsgSubType;
    }

    public void setMsgSubType(String MsgSubType) {
        this.MsgSubType = MsgSubType;
    }
    public String getMsgCtrl() {
        return MsgCtrl;
    }

    public void setMsgCtrl(String MsgCtrl) {
        this.MsgCtrl = MsgCtrl;
    }

    public String getProcessingId() {
        return ProcessingId;
    }

    public void setProcessingId(String ProcessingId) {
        this.ProcessingId = ProcessingId;
    }

    public String getVersionId() {
        return VersionId;
    }

    public void setVersionId(String VersionId) {
        this.VersionId = VersionId;
    }

        
}
