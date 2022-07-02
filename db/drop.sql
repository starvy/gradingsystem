alter table if exists t_grades drop constraint if exists FKrjj9bbla62kwk06dcdgmxjt0h;
alter table if exists t_grades drop constraint if exists FKqgid0d6nv6xp9tgi2k4w05urp;
drop table if exists t_grades cascade;
drop table if exists t_subjects cascade;
drop table if exists t_users cascade;
drop sequence if exists hibernate_sequence;
