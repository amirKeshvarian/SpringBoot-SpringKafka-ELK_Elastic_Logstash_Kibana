first Run Spring-EurekaServer then etc...

create a user with username 'amir_write' on database for PersonService-Save

create a user with username 'amir_write_2' on database for PersonService-Save-2

create a user with username 'amir_read' on database for PersonService-Find and :

SQL>create table person (person_id number, name varchar2(20), family varchar2(20), record_version number, deleted_date timestamp);

and create a user with username 'amir_seq' on database for SequenceGenerator and:

SQL>create sequence s1 start with 1 increment by 50;



for ELK (we use version 7.17.8): 
run elasticsearch.bat
,and then run kibana.bat
,and for any microservice run in cmd (run as admin):
>logstash --verbose -f [path contains logstash.conf file Ex. E:\...\[microservice name Ex. Spring-EurekaServer]]\logstash.conf


now you can request on :
http://localhost:9909/client/savePerson.do?name=melorin&family=keshvarian
http://localhost:9909/client/saveCar.do?model=benz&personId=1
http://localhost:9909/client/findPersonById.do?personId=1
http://localhost:9909/client/findCarsByPersonId.do?personId=1