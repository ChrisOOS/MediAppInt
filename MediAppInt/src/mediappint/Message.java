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
    private String MsgCtrl;
    private String ProcessingId;
    private String VersionId;
    
	Message(){}

    public String getSendingApp() {
        return SendingApp;
    }

    public void setSendingApp(String SendingApp) {
        this.SendingApp = SendingApp;
        System.out.println("Message.java.setSendingApp: " + SendingApp);
    }
    
    
}
