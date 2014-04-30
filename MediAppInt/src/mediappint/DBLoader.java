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
                           // + "attending_provider_number, attending_provider_name, hospital_service, visit_number, admit_date,"
                              + "attending_provider_number, attending_provider_name, hospital_service, admit_date,"
                              + "discharge_date, patient_pid) "
                           // + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + patientId + ")");
                              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, " + patientId + ")");

            prepStmt.setString(1, mp.visit.getPatient_class());
            prepStmt.setString(2, mp.visit.getAdmission_type());
            prepStmt.setString(3, mp.visit.getLocation());
            prepStmt.setString(4, mp.visit.getPrior_location());
            prepStmt.setString(5, mp.visit.getAttending_provider_number());
            prepStmt.setString(6, mp.visit.getAttending_provider_name());
            prepStmt.setString(7, mp.visit.getHospital_service());
//            prepStmt.setString(8, mp.visit.getVisit_number());
//            prepStmt.setString(9, mp.visit.getAdmit_date());
//            prepStmt.setString(10, mp.visit.getDischarge_date());
            prepStmt.setString(8, mp.visit.getAdmit_date());
            prepStmt.setString(9, mp.visit.getDischarge_date());
    

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
     * close_dbloader
     * This method close the database connection to CDR
     * @throws SQLException
     */
    public void close_dbloader()
            throws SQLException {
        connection.close();
    }
}
