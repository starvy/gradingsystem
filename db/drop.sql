alter table if exists t_grades drop constraint if exists FKjlfutrdmwjp6wfn7housavsk7;
drop table if exists t_grades cascade;
drop table if exists t_subjects cascade;
drop table if exists t_users cascade;
drop sequence if exists hibernate_sequence;
