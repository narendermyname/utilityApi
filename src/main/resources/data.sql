select * from JCP_PERSON;
select * from JCO_STACK;
select CREATED_DATE from JCO_STACK;
select LINKED_RESOURCE_ID from JCO_RESOURCE_COMPONENTS;
ALTER TABLE JCO_RESOURCE_COMPONENTS ADD (LINKED_RESOURCE_ID number);
commit; 
select * from DATABASECHANGELOG;
select * from JCP_UI_METADATA where resource_key='jsdn.store.cloudtrendgraph.alertattributes';
select *  from jcp_analysis where actor_id=4001108 and analyis_duration='D' and record_type='OverAllUsage_Price';
select * from JCP_ANALYSIS_DATA;
select * from jcp_analysis_data where analysis_id >409;

delete from jcp_analysis_data where analysis_id in =201;commit;
select * from JCP_SERVER_TMP;