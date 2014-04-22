/*******************************************************
* Title:	Patient.java
* Author:	Marco Casale
* Dept:		Medical Informatics
* Purpose:	Patient class
*               This class will set/get values into the Patient
*		table object for the database.
********************************************************/
package mediappint;

/**
 *@author Marco Casale<br>Medical Informatics<br>University of Rochester
 *This class defines the set and get methods for the PATIENT table
 */
public class Patient {

   private String LastName = "",FirstName = "",MiddleName = "";
   private String NameSuffix = "";
   private String NamePrefix = "";
   private String NameDegree = "";
   private String BirthDt = "";
   private String SexCd = "";
   private String Race = "";
   private String Address1 = "";
   private String Address2 = "";
   private String Address3 = "";
   private String City = "";
   private String State = "";
   private String Zip = "";
   private String CountyCd = "";
   private String HomePhone = "";
   private String WorkPhone = "";
   private String MaritalCd = "";
   private String ReligionCd = "";
   private String ssn = "";
   private String MotherID = "";
   private String FatherID = "";
   private String LivingWillCd = "";
   private String SpecialNeeds = "";
   private String AdvDirective = "";
   private String EmpName = "";
   private String EmpStatus = "";
   private String Occupation = "";
   private String Deceased_Date = "";
   private String specNeed1 = "";
   private String IDXspecNeeds = "";
   private String IDXspecInd = "";
   private String vipFlag = "";
   private String mrn = "";


	Patient(){}

//  MRN
	public void setMRN(String mrnum){
		this.mrn = mrnum;
	}

	public String getMRN(){
		return mrn;
	}


//  Last Name
	public void setLastName(String LastName){
		this.LastName = LastName;
	}

	public String getLastName(){
		return LastName;
	}

//  First Name
	public void setFirstName(String FirstName){
		this.FirstName = FirstName;
	}

	public String getFirstName(){
		return FirstName;
	}

//  Middle Name
	public void setMiddleName(String MiddleName){
		this.MiddleName = MiddleName;
	}

	public String getMiddleName(){
		return MiddleName;
	}

//  Name Suffix
	public void setNameSuffix(String NameSuffix){
		this.NameSuffix = NameSuffix;
	}

	public String getNameSuffix(){
		return NameSuffix;
	}


//  Name Prefix
	public void setNamePrefix(String NamePrefix){
		this.NamePrefix = NamePrefix;
	}

	public String getNamePrefix(){
		return NamePrefix;
	}

//  Name Degree
	public void setNameDegree(String NameDegree){
		this.NameDegree = NameDegree;
	}

	public String getNameDegree(){
		return NameDegree;
	}

//  Address Line1
	public void setAddress1(String Address1){
		this.Address1 = Address1;
	}

	public String getAddress1(){
		return Address1;
	}

//  Address Line2
	public void setAddress2(String Address2){
		this.Address2 = Address2;
	}

	public String getAddress2(){
		return Address2;
	}

//  Address Line3
	public void setAddress3(String Address3){
		this.Address3 = Address3;
	}

	public String getAddress3(){
		return Address3;
	}


//  City
	public void setCity(String City){
		this.City = City;
	}

	public String getCity(){
		return City;
	}

//  State
	public void setState(String State){
		this.State = State;
	}

	public String getState(){
		return State;
	}

//  Zip
	public void setZip(String Zip){
		this.Zip = Zip;
	}

	public String getZip(){
		return Zip;
	}


//  Home Phone
	public void setHomePhone(String HomePhone){
		this.HomePhone = HomePhone;
	}

	public String getHomePhone(){
		return HomePhone;
	}

//  Work Phone
	public void setWorkPhone(String WorkPhone){
		this.WorkPhone = WorkPhone;
	}

	public String getWorkPhone(){
		return WorkPhone;
	}

//  Social Security Number
	public void setSSN(String ssn){
    this.ssn = ssn;
	}

	public String getSSN(){
		return ssn;
	}

//  Religion Code
	public void setReligionCd(String ReligionCd){
		this.ReligionCd = ReligionCd;
	}

	public String getReligionCd(){
		return ReligionCd;
	}

//  Sex Code
	public void setSexCd(String SexCd){
		this.SexCd = SexCd;
	}

	public String getSexCd(){
		return SexCd;
	}

//  Birth Date
	public void setBirthDate(String BirthDt){
		this.BirthDt = BirthDt;
	}

	public String getBirthDate(){
		return BirthDt;
	}

//  Ethnic Code / Race
	public void setEthnicCd(String Race){
		this.Race = Race;
	}

	public String getEthnicCd(){
		return Race;
	}

//  County Code
	public void setCountyCd(String CountyCd){
		this.CountyCd = CountyCd;
	}

	public String getCountyCd(){
		return CountyCd;
	}

//  Marital Code
	public void setMaritalCd(String MaritalCd){
		this.MaritalCd = MaritalCd;
	}

	public String getMaritalCd(){
		return MaritalCd;
	}

//  Mother's ID / Name
	public void setMotherID(String MotherID){
		this.MotherID = MotherID;
	}

	public String getMotherID(){
		return MotherID;
	}

//  Father's ID / Name
	public void setFatherID(String FatherID){
		this.FatherID = FatherID;
	}

	public String getFatherID(){
		return FatherID;
	}

//  Living Will Code
	public void setLivingWillCd(String LivingWillCd){
		this.LivingWillCd = LivingWillCd;
	}

	public String getLivingWillCd(){
		return LivingWillCd;
	}


//  Special Needs
	public void setSpecialNeeds(String SpecialNeeds){
		this.SpecialNeeds = SpecialNeeds;
	}

	public String getSpecialNeeds(){
		return SpecialNeeds;
	}

//  Advance Directive
	public void setAdvDirective(String AdvDirective){
		this.AdvDirective = AdvDirective;
	}

	public String getAdvDirective(){
		return AdvDirective;
	}

//  Employer Name
	public void setEmpName(String EmpName){
		this.EmpName = EmpName;
	}

	public String getEmpName(){
		return EmpName;
	}

//  Employment Status
	public void setEmpStatus(String EmpStatus){
		this.EmpStatus = EmpStatus;
	}

	public String getEmpStatus(){
		return EmpStatus;
	}

//  Occupation
	public void setOccupation(String Occupation){
		this.Occupation = Occupation;
	}

	public String getOccupation(){
		return Occupation;
	}

//  Deceased_Date
	public void setDeceased_Date(String Deceased_Date){
		this.Deceased_Date = Deceased_Date;
	}

	public String getDeceased_Date(){
		return Deceased_Date;
	}

//  Special Need 1
	public void setSpecNeed1(String newSpecNeed){
		this.specNeed1 = newSpecNeed;
	}

	public String getSpecNeed1(){
		return specNeed1;
	}

//  IDX Special Needs
	public void setIDXSpecNeeds(String newSpecNeed){
		this.IDXspecNeeds = newSpecNeed;
	}

	public String getIDXSpecNeeds(){
		return IDXspecNeeds;
	}

//  Special Need 3
	public void setIDXSpecInd(String newSpecInd){
		this.IDXspecInd = newSpecInd;
	}

	public String getIDXSpecInd(){
		return IDXspecInd;
	}

//  VIP Flag
	public void setVipFlag(String newVipFlag){
		this.vipFlag = newVipFlag;
	}

	public String getVipFlag(){
		return vipFlag;
	}
}
