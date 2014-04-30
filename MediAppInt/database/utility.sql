-- Use this to reset the database between runs of processing messages
update hl7_q set read_flag = null where msg_seq > 30;
truncate table patient;
truncate table visit;
truncate table hl7_q;

-- use this to see if our patients are properly poulated
select * from patient
inner join visit 
where patient.pid = visit.patient_pid;


select * from patient;
select * from visit;

select * from hl7_q;