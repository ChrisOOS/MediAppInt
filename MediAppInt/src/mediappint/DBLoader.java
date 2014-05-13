/*******************************************************
 * Title:	DBLoader.java
 * Author:	Marco Casale
 * Date:		
 * Dept:		
 * Purpose:	This class connects to the database and manages all reads / writes
 *               to the database.
 * URL on MySQL: http://www.developer.com/java/data/article.php/3417381
 ********************************************************/
package mediappint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class DBLoader {

    Statement stmt = null;
    ResultSet rs = null;
    private Connection connection;
    private PreparedStatement prepStmt;
    private String msgStr;
    public int next_seq = 0;
    public int patientId = 0;
    

    public DBLoader()
            throws ClassNotFoundException, SQLException {
        //Register the JDBC driver for MySQL.
        Class.forName("com.mysql.jdbc.Driver");
        // Use localhost or 127.0.0.1 to point to your machine
        String url = "jdbc:mysql://127.0.0.1/mirthdb";
        String userName = "mirthman";
        String password = "mirthman";
        connection = DriverManager.getConnection(url, userName, password);
        stmt = connection.createStatement();
    }

    
//--------------------------------------------------------------------
/*
 * getUnreadMessage
 * The method will return the lowest msg_seq (sequence number) of
 * an unread message in the queue
 * @returns int
 */
    public int getUnreadMessage(){
        int remaining = 0;
        try {
            PreparedStatement checkCount = connection.prepareStatement(
                "select min(msg_seq) from hl7_q where read_flag is null");
        
            ResultSet rs = checkCount.executeQuery();
            while (rs.next()){
                remaining = rs.getInt(1);
                System.out.println("DBLoader.getUnreadMessage: " + remaining);
            }
            checkCount.close();
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.getUnreadMessage: " + se);
        }
        
        return remaining;
    }
//--------------------------------------------------------------------
/*
 * set_processed
 * The method will update a row in the database to mark it
 * processed
 * @params iqseq - the msg_seq of the processed message
 */
    public void set_processed(int iqseq){
        try {
            PreparedStatement markRead = connection.prepareStatement(
                "update hl7_q set read_flag = 'R' where msg_seq = ?");
            
            markRead.setInt(1, iqseq);
            markRead.executeUpdate();
            
            markRead.close();
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.set_processed: " + se);
        }
    }
    
    
  
//--------------------------------------------------------------------
    public String getMessage(int iqseq) {
        String msgText = "";
        int msgId = iqseq;
        try {

            PreparedStatement prepStmt = connection.prepareStatement(
                    "select msg_seq, msg_text from hl7_q where msg_seq = ?");

            prepStmt.setInt(1, msgId);

            ResultSet rs = prepStmt.executeQuery();
            // list the results
            while (rs.next()) {
                int msgID = rs.getInt(1);
                msgText = rs.getString(2);
                System.out.println("-------------------------------------------------------");
                System.out.println("Message ID: " + msgID);
                System.out.println("HL7 Message: " + msgText.replaceAll("\r", "\r\n"));
                System.out.println("-------------------------------------------------------");
            }
            msgStr = msgText;
            prepStmt.close();
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.getMessage: " + se);
        }
        return msgStr;
    }

//--------------------------------------------------------------------
    /**
     * set_Patient<br>
     * This method will call a preparedStatement which writes to the patient table
     * @param MsgParse mp
     * @throws SQLException
     */
    public void set_Patient(MsgParse mp)
            throws SQLException {
        try {

            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into patient (mrn, last_name, first_name, birth_date, sex_code, ethnic_code, address_line1, address_line2,"
                            + "address_city, address_state, address_zip, home_phone, work_phone, marital_code, religion_code, ssn) "
                            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prepStmt.setString(1, mp.patient.getMRN());
            prepStmt.setString(2, mp.patient.getLastName());
            prepStmt.setString(3, mp.patient.getFirstName());
            prepStmt.setString(4, mp.patient.getBirthDate());
            prepStmt.setString(5, mp.patient.getSexCd());
            prepStmt.setString(6, mp.patient.getEthnicCd());
            prepStmt.setString(7, mp.patient.getAddress1());
            prepStmt.setString(8, mp.patient.getAddress2());
            prepStmt.setString(9, mp.patient.getCity());
            prepStmt.setString(10, mp.patient.getState());
            prepStmt.setString(11, mp.patient.getZip());
            prepStmt.setString(12, mp.patient.getHomePhone());
            prepStmt.setString(13, mp.patient.getWorkPhone());
            prepStmt.setString(14, mp.patient.getMaritalCd());
            prepStmt.setString(15, mp.patient.getReligionCd());
            prepStmt.setString(16, mp.patient.getSSN());      

            prepStmt.execute();

            //Get max PID that we just inserted so we can use it as FK in Visit Insert
            prepStmt = connection.prepareStatement("select max(pid) from patient");
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                patientId = rs.getInt(1);
                System.out.println("MAX PID: " + patientId);
            }
            
            prepStmt.close();
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.set_Patient: " + se);
        }
    }
    
//--------------------------------------------------------------------
    /**
     * set_Visit<br>
     * This method will call a preparedStatement which writes to the visit table
     * @param MsgParse mp
     * @throws SQLException
     */
    public void set_Visit(MsgParse mp)
            throws SQLException {
        if (!mp.visit.getPatient_class().isEmpty())
        try {

            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into visit (patient_class, admission_type, location, prior_location, "
                            + "attending_provider_number, attending_provider_name, hospital_service, visit_number, admit_date,"                           
                            + "discharge_date, patient_pid) "
                            //   + "attending_provider_number, attending_provider_name, hospital_service, admit_date,"                           
                            // + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + patientId + ")");
                            // This pid comes from SET_PATIENT where we select MAX(PID)  
                            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?," + patientId + ")");

            prepStmt.setString(1, mp.visit.getPatient_class());
            prepStmt.setString(2, mp.visit.getAdmission_type());
            prepStmt.setString(3, mp.visit.getLocation());
            prepStmt.setString(4, mp.visit.getPrior_location());
            prepStmt.setString(5, mp.visit.getAttending_provider_number());
            prepStmt.setString(6, mp.visit.getAttending_provider_name());
            prepStmt.setString(7, mp.visit.getHospital_service());
            prepStmt.setString(8, mp.patient.getAcctNum());
            prepStmt.setString(9, mp.visit.getAdmit_date());
            prepStmt.setString(10, mp.visit.getDischarge_date());
            //prepStmt.setString(8, mp.visit.getAdmit_date());
            //prepStmt.setString(9, mp.visit.getDischarge_date());
    

            prepStmt.execute();

            prepStmt.close();
            patientId = 0;
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.set_Visit: " + se);
        }
    }

//--------------------------------------------------------------------
//--------------------------------------------------------------------
/**
 * doA02<br>
 * This method will call a preparedStatement which fetches and then updates
 * from visit table
 * AKA - Process an A02, update a patient location
 * @param MsgParse mp
 * @throws SQLException
 */
       public void doA02(MsgParse mp)
            throws SQLException {
        if (!mp.visit.getPatient_class().isEmpty())
        try {
        //get the current location from the visit table for patient                     
        //update table -- prior location gets value of current location
        //update table -- current location gets value from the A02 message
            PreparedStatement prepStmt = connection.prepareStatement(
                    "update visit "
                            + "set prior_location = ?, location = ? "
                            + "where location = ? "
                            + "AND admit_date = ?");
            
            prepStmt.setString(1, mp.visit.getPrior_location());
            prepStmt.setString(2, mp.visit.getLocation());
            prepStmt.setString(3, mp.visit.getPrior_location());
            prepStmt.setString(4, mp.visit.getAdmit_date());
            prepStmt.executeUpdate();

            prepStmt.close();
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.doA02: " + se);
        }
    }//doA02

 //--------------------------------------------------------------------
/**
 * doA03<br>
 * This method will call a preparedStatement which fetches and then updates
 * from visit table
 * AKA - Process an A03, discharge a patient
 * @param MsgParse mp
 * @throws SQLException
 */      
    public void doA03(MsgParse mp)
        throws SQLException {
        if (!mp.visit.getPatient_class().isEmpty())
        try {
            // use the mrn in the A03 to identify the patient (we want the pid)
            // use patient.pid as visit.patient_pid
            //    where visit.patient_pid = patient.pid
            //    -- update the location fields to be null
            //    -- update admission_type to be 'D'
            //    -- set discharge date to field found in message
            PreparedStatement prepStmt = connection.prepareStatement(
                    "update visit "
                            + "set location = ?, prior_location = ?, admission_type = ?" 
                            + ", discharge_date = ? "
                            + "where patient_pid = "
                            + "(select pid from patient where mrn = ?)");
            
            prepStmt.setString(1, mp.visit.getLocation());
            prepStmt.setString(2, mp.visit.getPrior_location());
            prepStmt.setString(3, mp.visit.getAdmission_type()); 
            prepStmt.setString(4, mp.visit.getDischarge_date());
            prepStmt.setString(5, mp.patient.getMRN());
            prepStmt.executeUpdate();

            prepStmt.close();
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.doA03: " + se);
        }     
    }//doA03 - discharge
//--------------------------------------------------------------------
/**
 * doA08<br>
 * This method will call a preparedStatement which fetches and then updates
 * from patient table
 * AKA - Process an A08, update patient information
 * @param MsgParse mp
 * @throws SQLException
 */      
    public void doA08(MsgParse mp)
        throws SQLException {
        if (!mp.visit.getPatient_class().isEmpty())
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "update patient set "
                            + "last_name = ?"
                            + ", first_name = ?"
                            + ", birth_date = ?"
                            + ", sex_code = ?"
                            + ", ethnic_code = ?"
                            + ", address_line1 = ?"
                            + ", address_line2 = ?"
                            + ", address_city = ?"
                            + ", address_state = ?"
                            + ", address_zip = ?"
                            + ", home_phone = ?"
                            + ", work_phone = ?"
                            + ", marital_code = ?"
                            + ", religion_code = ?"
                            + ", ssn = ? "
                            + "where mrn = ? ");

            prepStmt.setString(1, mp.patient.getLastName());
            prepStmt.setString(2, mp.patient.getFirstName());
            prepStmt.setString(3, mp.patient.getBirthDate());
            prepStmt.setString(4, mp.patient.getSexCd());
            prepStmt.setString(5, mp.patient.getEthnicCd());
            prepStmt.setString(6, mp.patient.getAddress1());
            prepStmt.setString(7, mp.patient.getAddress2());
            prepStmt.setString(8, mp.patient.getCity());
            prepStmt.setString(9, mp.patient.getState());
            prepStmt.setString(10, mp.patient.getZip());
            prepStmt.setString(11, mp.patient.getHomePhone());
            prepStmt.setString(12, mp.patient.getWorkPhone());
            prepStmt.setString(13, mp.patient.getMaritalCd());
            prepStmt.setString(14, mp.patient.getReligionCd());
            prepStmt.setString(15, mp.patient.getSSN()); 
            prepStmt.setString(16, mp.patient.getMRN());
            prepStmt.executeUpdate();

            prepStmt.close();
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.doA08: " + se);
        }     
    }//doA08 - update patient information
    
    
//--------------------------------------------------------------------
    /**
     * set_LabOrder<br>
     * This method will call a preparedStatement which writes to the vLabOrder table
     * @param MsgParse mp
     * @throws SQLException
     */
    public void set_LabOrder(MsgParse mp)
            throws SQLException {
        String labOrderProvider = "";
        try {
            // We need to get the provider ID from the initial Visit which was sent by bedboard
            PreparedStatement prepStmt = connection.prepareStatement(
                    "select attending_provider_number from visit "
                            + "where visit_number = ? ");

            prepStmt.setString(1, mp.patient.getAcctNum());
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                labOrderProvider = rs.getString(1);
                System.out.println("labOrderProvider: " + labOrderProvider);
            }
            prepStmt.execute();

            //We're inserting into the labOrders table, breaking a little bit of referential inegrity
            // because I'm inserting the provider ID directly into the table after getting it from visit above
            //psst... we're not using the provider table
            prepStmt = connection.prepareStatement(
                    "insert into Lab_orders (placerNum, visit_vid, labOrderControl, fillerOrderNum, dateTransaction, "
                            + "serviceIdentifier, visit_patient_pid, provider_providerID) "
                            + "values (?, ?, ?, ?, ?, ?," 
                            + "(select pid from patient where mrn = ?), ?)");

            prepStmt.setString(1, mp.labOrder.getPlacerNum());
            //Getting the "VISIT NUMBER" from PID-18 in Patient
            prepStmt.setString(2, mp.patient.getAcctNum());
            prepStmt.setString(3, mp.labOrder.getLabOrderControl());
            //TODO : Need to write a method to generate a fillerNumber
            prepStmt.setString(4, "fakefiller");
            prepStmt.setString(5, mp.labOrder.getDateTransaction());
            prepStmt.setString(6, mp.labOrder.getServiceIdentifier()); 
            
            prepStmt.setString(7, mp.patient.getMRN());
            prepStmt.setString(8, labOrderProvider);

            prepStmt.execute();

            prepStmt.close();
            } catch (SQLException se) {
                System.out.println("Error in DBLoader.set_LabOrder: " + se);
            }
        }
    
//--------------------------------------------------------------------
    /**
     * cancel_LabOrder<br>
     * This method will call a preparedStatement which writes to the vLabOrder table
     * @param MsgParse mp
     * @throws SQLException
     */
    public void cancel_LabOrder(MsgParse mp)
            throws SQLException {
        try {
                
                PreparedStatement prepStmt = connection.prepareStatement(
                        "delete from laborder where placerNum = ?");
                
                prepStmt.setString(1, mp.labOrder.getPlacerNum());
                prepStmt.execute();
                
                prepStmt.close();
            } catch (SQLException se) {
                System.out.println("Error in DBLoader.set_LabOrder: " + se);
            }
        }
    
    
//--------------------------------------------------------------------
//grab lab results
    public void send_LabResults(MsgParse mp)
            throws SQLException {
        try {

            prepStmt = connection.prepareStatement(
                    "SELECT * FROM Lab_Results WHERE Visit_Num = ?");

            //Getting the "VISIT NUMBER" from PID-18 in Patient
            prepStmt.setString(1, mp.patient.getAcctNum());
            prepStmt.execute();

            prepStmt.close();
            } catch (SQLException se) {
                System.out.println("Error in DBLoader.set_LabOrder: " + se);
            }
        }

//--------------------------------------------------------------------
    //Grab mrns from patient table for GUI
    public ArrayList get_PatientMRN(MsgParse mp)
            throws SQLException {
        ArrayList<String> arr = new ArrayList<String>();
        try {
           ResultSet rs = stmt.executeQuery("SELECT last_name, first_name, mrn FROM Patient");
           
           while(rs.next()) {
               String val = rs.getString("last_name");
               val += "      ";
               val += rs.getString("first_name");
               val += "      ";
               val += rs.getString("mrn");
               arr.add(val);
           }
        }catch (SQLException se) {
                System.out.println("Error in DBLoader.set_LabOrder: " + se);
            }
        return arr;
    }
    
    public void send_Request(MsgParse mp, String mrn)
            throws SQLException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE mrn = mrn");
            
        } catch (SQLException se) {
            System.out.println("Error in DBLoader.set_LabOrder: " + se);
        }
    }
//--------------------------------------------------------------------
    /**
     * close_dbloader
     * This method close the database connection to CDR
     * @throws SQLException
     */
    public void close_dbloader()
            throws SQLException {
        connection.close();
    }
}
