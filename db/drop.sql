alter table if exists PeriodicTimeSlot drop constraint if exists FK2jxc6mddgqucd3418f3talv9v;
alter table if exists PeriodicTimeSlot drop constraint if exists FKgu83w0x1exs0ldja0x7sshwgi;
alter table if exists PeriodicTimeSlot drop constraint if exists FKsxkfy5gq2a9acpt7obing3xgh;
alter table if exists PeriodicTimeTable_PeriodicTimeSlot drop constraint if exists FKj7mtf6xolsc8v6kncmcyq3c1e;
alter table if exists PeriodicTimeTable_PeriodicTimeSlot drop constraint if exists FKnjw154bymlshjc49x319vpejv;
alter table if exists t_classes drop constraint if exists FKnl01t0uibd29cr0tbx1r36xx1;
alter table if exists t_classes drop constraint if exists FKe4h5if5ade241ifiyapn5wg8j;
alter table if exists t_grades drop constraint if exists FKpcm7ehca03wgjgftex79837ey;
alter table if exists t_grades drop constraint if exists FKjlfutrdmwjp6wfn7housavsk7;
alter table if exists t_grades drop constraint if exists FK4fulw0sg6dty2pxlifgljx7ue;
alter table if exists t_users_groups drop constraint if exists FKoybpvvh28q2qlfrii8b7rvbgs;
alter table if exists t_users_groups drop constraint if exists FKgwi0iqh0r1wfpaibe6xr81eh9;
drop table if exists PeriodicTimeSlot cascade;
drop table if exists PeriodicTimeTable cascade;
drop table if exists PeriodicTimeTable_PeriodicTimeSlot cascade;
drop table if exists Room cascade;
drop table if exists t_classes cascade;
drop table if exists t_grades cascade;
drop table if exists t_groups cascade;
drop table if exists t_users cascade;
drop table if exists t_users_groups cascade;
drop sequence if exists hibernate_sequence;
