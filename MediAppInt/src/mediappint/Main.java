/*******************************************************
 * Title:    Main.java
 * Course:   4006-430/730 Medical Application Integration
 * Dept:     Medical Informatics
 * Purpose:  This java application's main function is to
 *           load HL7 messages from a queue
 *           and write them to a database
 **********************************************************/
package mediappint;


import java.util.*;
import org.urhl7.igor.*;

/**
 *@author Marco Casale<br>Medical Informatics<br>University of Rochester
 */
public class Main {

    /**
     * Purpose:   This java application's main function is to
     *            load HL7 messages from a queue
     *            and write them to a database<br>
     */
    public Main() {
    }

    public static void main(String[] args)
            throws Exception {
        /*************************************************
         * Simple Variable Declarations
         **************************************************/
        int iqseq = 0;

        /*************************************************
         * Object Declarations
         *************************************************/
        DBLoader dbloader = new DBLoader();	// Database connection object
        MsgParse mp;


        /***************************************************
         * Main Logic Section
         ****************************************************/
        try {

            String hl7messageString = null;

            
            //get sequence number of first unprocessed message
            iqseq = dbloader.getUnreadMessage();
    
            //while there is a message that has not been processed
            while (iqseq > 0){

                // -----------------------------------------
                // Read in an HL7 message from the database
                //------------------------------------------
                hl7messageString = dbloader.getMessage(iqseq);
                System.out.println("Message: " + hl7messageString);



                // -------------------------------------------------------
                // Load the HL7 message string into an HL7 message object
                //--------------------------------------------------------
                HL7Structure myStructure = Igor.structure(hl7messageString);
                mp = new MsgParse(myStructure);  // Instantiate a Message Parse Object
                mp.parse_Segments();

                // -------------------------------------------------------
                // Dump out MSH contents
                //--------------------------------------------------------
                System.out.println("The Sending App is: " + mp.message.getSendingApp());
                System.out.println("The SendingFacility: " + mp.message.getSendingFacility());
                System.out.println("The ReceivingApp: " + mp.message.getReceivingApp());
                System.out.println("The ReceivingFacility; " + mp.message.getReceivingFacility());
                System.out.println("The MsgDateTime; " + mp.message.getMsgDateTime());
                System.out.println("The Security; " + mp.message.getSecurity());
                System.out.println("The MsgType; " + mp.message.getMsgType());
                System.out.println("The MsgSubType; " + mp.message.getMsgSubType());
                System.out.println("The MsgCtrl; " + mp.message.getMsgCtrl());
                System.out.println("The ProcessingId; " + mp.message.getProcessingId());
                System.out.println("The VersionId; " + mp.message.getVersionId());
                
                //---------------------------------------------------------
                // Dump the contents of the EVENT object
                //---------------------------------------------------------

                System.out.println("EventTypeCode: " + mp.event.getEventTypeCode());
                System.out.println("RecDateTime: " + mp.event.getRecDateTime());
                System.out.println("DateTimePlanned: " + mp.event.getDateTimePlanned());
                System.out.println("EventReason: " + mp.event.getEventReason());
                System.out.println("OperatorID: " + mp.event.getOperatorID());
                System.out.println("EventOccurred: " + mp.event.getEventOccurred());
                
                //---------------------------------------------------------
                // Dump the contents of the Patient object
                //---------------------------------------------------------

                System.out.println("MRN: " + mp.patient.getMRN());
                System.out.println("Patient First Name: " + mp.patient.getFirstName());
                System.out.println("Patient Last Name: " + mp.patient.getLastName());
                System.out.println("Patient BirthDate: " + mp.patient.getBirthDate());
                System.out.println("Patient Sex: " + mp.patient.getSexCd());
                System.out.println("Patient Ethnicity: " + mp.patient.getEthnicCd());
                System.out.println("Patient Address1: " + mp.patient.getAddress1());
                System.out.println("Patient Address2: " + mp.patient.getAddress2());
                System.out.println("Patient City: " + mp.patient.getCity());
                System.out.println("Patient State: " + mp.patient.getState());
                System.out.println("Patient Zip: " + mp.patient.getZip());
                //System.out.println("Patient County: " + mp.patient.getCountyCd());
                System.out.println("Patient Home Phone: " + mp.patient.getHomePhone());
                System.out.println("Patient Work Phone: " + mp.patient.getWorkPhone());
                System.out.println("Patient Marital Status: " + mp.patient.getMaritalCd());
                System.out.println("Patient Religion: " + mp.patient.getReligionCd());
                System.out.println("Patient SSN: " + mp.patient.getSSN());

                /* 
                 * Print the contents of the Visit object
                 */

                System.out.println("Patient Class: " + mp.visit.getPatient_class());
                System.out.println("Admission Type: " + mp.visit.getAdmission_type());
                System.out.println("Location: " + mp.visit.getLocation());
                System.out.println("Prior Location: " + mp.visit.getPrior_location());
                System.out.println("Attending Provider Number: " + mp.visit.getAttending_provider_number());
                System.out.println("Attending Provider Name: " + mp.visit.getAttending_provider_name());
                System.out.println("Hospital Service: " + mp.visit.getHospital_service());
//                System.out.println("Visit Number: " + mp.visit.getVisit_number());
                System.out.println("Admit Date: " + mp.visit.getAdmit_date());
                System.out.println("Discharge Date: " + mp.visit.getDischarge_date());

                

                if (mp.event.getEventTypeCode().equals("A01")){
                    //Load Patient Object into Database
                    dbloader.set_Patient(mp);
                    //Load Visit Object into Database
                    dbloader.set_Visit(mp);
                }
                else if (mp.event.getEventTypeCode().equals("A02")) {
                    
                    // CHRIS
                    //get the current location from the visit table for patient                     
                    //update table -- prior location gets value of current location
                    //update table -- current location gets value from the A02 message
                    //
                    
                    // TODO
                    // IGoing to parse the location similar to how we parsed the attending provide
                    // pull out the sub-components and piece them together
                    // then we can compare the values...
                    dbloader.update_Visit(mp);
                    

                }
                else if (mp.event.getEventTypeCode().equals("A03")) {
                    // CHRIS
                    // new method for dbloader.discharge(mp)
                    // use the mrn in the A03 to identify the patient (we want the pid)
                    // use patient.pid as visit.patient_pid
                    //    where visit.patient_pid = patient.pid
                    //    -- update the location fields to be null
                    //    -- update admission_type to be 'D'
                    //    -- set discharge date to now()
                    
                }

                //set the message as processed
                dbloader.set_processed(iqseq);
                //get sequence number of next unprocessed message
                iqseq = dbloader.getUnreadMessage();

            } //WHILE IQSEQ <> 1
        } catch (Exception e) {
            System.out.println("Timestamp: " + Calendar.getInstance().getTime());
            System.out.println("While loop Error is - " + e.toString() + " iqseq: " + iqseq);
        }

        //  Close the connection to the database
        dbloader.close_dbloader();

    } // end main
}//end class 

