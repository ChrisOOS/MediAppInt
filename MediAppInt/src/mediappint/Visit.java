/*******************************************************
* Title:	Visit.java
* Author:	Chris O'Brien
* Dept:		Medical Informatics
* Purpose:	Visit class
*               This class will set/get values into the Visit
*		table object for the database.
********************************************************/
package mediappint;

/**
 *@author Chris O'Brien<br>Medical Informatics<br>MediAppInt
 *This class defines the set and get methods for the VISIT table
 */
public class Visit {

//   private String visit_id;
   private String patient_class;
   private String admission_type;
   private String location;
   private String prior_location;
   private String attending_provider_number;
   private String attending_provider_name;
   private String hospital_service;
   //private String visit_number;
   private String admit_date;
   private String discharge_date;

	Visit(){}

    public String getPatient_class() {
        return patient_class;
    }

    public void setPatient_class(String patient_class) {
        this.patient_class = patient_class;
    }

    public String getAdmission_type() {
        return admission_type;
    }

    public void setAdmission_type(String admission_type) {
        this.admission_type = admission_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrior_location() {
        return prior_location;
    }

    public void setPrior_location(String prior_location) {
        this.prior_location = prior_location;
    }

    public String getAttending_provider_number() {
        return attending_provider_number;
    }

    public void setAttending_provider_number(String attending_provider_number) {
        this.attending_provider_number = attending_provider_number;
    }

    public String getAttending_provider_name() {
        return attending_provider_name;
    }

    public void setAttending_provider_name(String attending_provider_name) {
        this.attending_provider_name = attending_provider_name;
    }

    public String getHospital_service() {
        return hospital_service;
    }

    public void setHospital_service(String hospital_service) {
        this.hospital_service = hospital_service;
    }

//    public String getVisit_number() {
//        return visit_number;
//    }
//
//    public void setVisit_number(String visit_number) {
//        this.visit_number = visit_number;
//    }

    public String getAdmit_date() {
        return admit_date;
    }

    public void setAdmit_date(String admit_date) {
        this.admit_date = admit_date;
    }

    public String getDischarge_date() {
        return discharge_date;
    }

    public void setDischarge_date(String discharge_date) {
        this.discharge_date = discharge_date;
    }

}
