-- Use this to reset the database between runs of processing messages
update hl7_q set read_flag = null where msg_seq > 0;
truncate table patient;
truncate table visit;
truncate table hl7_q;

-- use this to see if our patients are properly poulated
select * from patient
inner join visit 
where patient.pid = visit.patient_pid;


update visit
	set location = null, prior_location = null, admission_type = 'D', discharge_date = '3'
where patient_pid = (select pid from patient where mrn = '000003123331');


update visit set prior_location = 'MED1^101^1^^^^^', location = 'MED1^101^2^^^' where location = 'MED1^101^1^^^^^';



select * from patient;
select * from visit;
select * from hl7_q;
select * from lab_orders;





