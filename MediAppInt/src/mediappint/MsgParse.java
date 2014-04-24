/*******************************************************
 * Title:	MsgParse.java
 ********************************************************/
package mediappint;

import org.urhl7.igor.*;

/**
 *@author Marco Casale<br>Medical Informatics<br>University of Rochester
 */
public class MsgParse {

    protected Patient patient;
    private HL7Structure myStructure;
    
    protected Visit visit;
    protected Message message;

    MsgParse(HL7Structure myStructureInput) {
        patient = new Patient();
        visit = new Visit();
        message = new Message();
        myStructure = myStructureInput;
    }
    
    /****************************************************/
    /**
     * Method parse_PID<br>
     * Parses HL7 Segments into data objects (i.e. Patient)
     * PID - Patient Identifier Segment
     * PV1 - Patient Visit Segment
     * MSH - Message Header
     * 
     */
    public void parse_Segments() {

/* 
 * Parse MSH for MESSAGE Information
 */
        
    String SendingApp = ""; // MSH 3
    String SendingFacility = ""; // MSH 4
    String ReceivingApp = ""; // MSH 5
    String ReceivingFacility = ""; // MSH 6
    String MsgDateTime = ""; // MSH 7
    String Security = ""; // MSH 8
    String MsgType = ""; // MSH 9.1
    String MsgSubType = ""; // MSH 9.2
    String MsgCtrl = ""; // MSH 10
    String ProcessingId = ""; // MSH 11
    String VersionId = ""; // MSH 12
        
    
    SendingApp = myStructure.helper().get("MSH-3").getData();
    message.setSendingApp(SendingApp);

    SendingFacility = myStructure.helper().get("MSH-4").getData();
    message.setSendingFacility(SendingFacility);
    
    ReceivingApp = myStructure.helper().get("MSH-5").getData();
    message.setReceivingApp(ReceivingApp);
    
    ReceivingFacility = myStructure.helper().get("MSH-6").getData();
    message.setReceivingFacility(ReceivingFacility);
    
    MsgDateTime = myStructure.helper().get("MSH-7").getData();
    message.setMsgDateTime(MsgDateTime);
    
    Security = myStructure.helper().get("MSH-8").getData();
    message.setSecurity(Security);
    
    MsgType = myStructure.helper().get("MSH-9.1").getData();
    message.setMsgType(MsgType);
    
    MsgSubType = myStructure.helper().get("MSH-9.2").getData();
    message.setMsgSubType(MsgSubType);
    
    MsgCtrl = myStructure.helper().get("MSH-10").getData();
    message.setMsgCtrl(MsgCtrl);    

    ProcessingId = myStructure.helper().get("MSH-11").getData();
    message.setProcessingId(ProcessingId);
    
    VersionId = myStructure.helper().get("MSH-12").getData();
    message.setVersionId(VersionId);

    
    
    
    
    String BirthDt = "";  		// PID 7
    String SexCd = "";    		// PID 8
    String Race = "";			// PID 10
//    String Address = "";		// PID 11
    String addrLine1 = "";    	// PID 11.1
    String addrLine2 = "";		// PID 11.2
    String city = "";			// PID 11.3
    String stateCd = "";		// PID 11.4
    String zipCd = "";		// PID 11.5
//    String CountyCd = "";     	// PID 12
    String HomePhone = "";    	// PID 13
    String WorkPhone = "";    	// PID 14
    String MaritalCd = "";    	// PID 16
    String ReligionCd = "";   	// PID 17
//    String AcctNum = "";              // PID 18
    String ssn = "";          	// PID 19
//    String MotherID = "";     	// PID 21


        //Access fields directly if you know your specification explicitly
        // Medical Record Number
        String myMRN = "";                // PID 3.1
        myMRN = myStructure.helper().get("PID-3.1").getData();
        patient.setMRN(myMRN);
        // Birthdate
        BirthDt = myStructure.helper().get("PID-7").getData();
        patient.setBirthDate(BirthDt);
        
        //SexCd (sex code)
        SexCd = myStructure.helper().get("PID-8").getData();
        patient.setSexCd(SexCd);
        
        //Set Ethnicity
        Race = myStructure.helper().get("PID-10").getData();
        patient.setEthnicCd(Race);

        /**
        //Get all segments within the Address PID
        **/
        //Set Address
        addrLine1 = myStructure.helper().get("PID-11.1").getData();
        patient.setAddress1(addrLine1);
        
        addrLine2 = myStructure.helper().get("PID-11.2").getData();
        patient.setAddress2(addrLine2);

        city = myStructure.helper().get("PID-11.3").getData();
        patient.setCity(city);

        stateCd = myStructure.helper().get("PID-11.4").getData();
        patient.setState(stateCd);
        
        zipCd = myStructure.helper().get("PID-11.5").getData();
        patient.setZip(zipCd);

        HomePhone = myStructure.helper().get("PID-13").getData();
        patient.setHomePhone(HomePhone);
        
        //WorkPhone - 14
        WorkPhone = myStructure.helper().get("PID-14").getData();
        patient.setWorkPhone(WorkPhone);
        
        //MaritalCd - 16
        MaritalCd = myStructure.helper().get("PID-16").getData();
        patient.setMaritalCd(MaritalCd);
        
        //ReligionCd - 17
        ReligionCd = myStructure.helper().get("PID-17").getData();
        patient.setReligionCd(ReligionCd);
        
        //ssn - 19
        ssn = myStructure.helper().get("PID-19").getData();
        patient.setSSN(ssn);
                
        //You can also access data 'the long way' -- using the underlying data structures (not recommended unless you have to).
        for (HL7Segment segment : myStructure.getSegments()) { //loop through all the segments
            if (segment.getSegmentName().equals("PID")) { //find the segment that is PID

                //the underlying structure starts with REPEATING fields, not FIELDS (this is because a normal field is a part of a repeating field (cardinality of 1)
                //rather than having strange behaviour for when it is a repeating field).
                HL7RepeatingField pid5 = segment.getRepeatingField(5); //0 = segment name, > 0 is the position as expected.

                // Patient Name    PID 5
                //PID 5 could, feasibly, be a repeating field, so loop through!
                for (HL7Field field : pid5.getFields()) {
                    //we can get the data here if we wish...
                    // System.out.println(field.getData());

                    //but we want to get the DISCRETE data points, so we can get last and first name, lets ignore the initials.
                    String myFirstName = field.getFieldComponent(1).getData(); //This is ZERO BASED, not ONE BASED like the HL7 Spec
                    String myLastName = field.getFieldComponent(0).getData();
                    patient.setLastName(myLastName);
                    patient.setFirstName(myFirstName);
                    
                } // End of PID 5 for loop
            } // End of IF  PID segment
        } // End of For loop

/* 
 * Parse PV1 for Visit Information
 */        
   String patientClass;
   String admissionType;
   String location;
   String priorLocation;
   String attendingProviderNumber;
   String attendingProviderName;
   String hospitalService;
   String visitNumber;
   String admitDate;
   String dischargeDate;
   
   patientClass = myStructure.helper().get("PV1-2").getData();
   visit.setPatient_class(patientClass);
   
   admissionType = myStructure.helper().get("PV1-4").getData();
   visit.setAdmission_type(admissionType);
   
   location = myStructure.helper().get("PV1-3").getData();
   visit.setLocation(location);
   
   priorLocation = myStructure.helper().get("PV1-6").getData();
   visit.setPrior_location(priorLocation);
   
   attendingProviderNumber = myStructure.helper().get("PV1-7.1").getData();
   visit.setAttending_provider_number(attendingProviderNumber);
   
   //parse out all three segments of the provider's name and concatenate
   attendingProviderName = myStructure.helper().get("PV1-7.2").getData() + " " + 
           myStructure.helper().get("PV1-7.3").getData() + " " +
           myStructure.helper().get("PV1-7.4").getData();
   visit.setAttending_provider_name(attendingProviderName);
   
   hospitalService = myStructure.helper().get("PV1-10").getData();
   visit.setHospital_service(hospitalService);
   
   visitNumber = myStructure.helper().get("PV1-19").getData();
   visit.setVisit_number(visitNumber);
   
   admitDate = myStructure.helper().get("PV1-44").getData();
   visit.setAdmit_date(admitDate);
   
   dischargeDate = myStructure.helper().get("PV1-45").getData();
   visit.setDischarge_date(dischargeDate);
   
    } // End Method parse_Segments
}  // End of MsgParse Class