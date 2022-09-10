create sequence hibernate_sequence start 1 increment 1;
create table PeriodicTimeSlot (id int8 not null, description varchar(255), endTime time, startTime time, title varchar(255), aClass_id int8, group_id int8, room_id int8, primary key (id));
create table PeriodicTimeTable (id int8 not null, description varchar(255), title varchar(255), primary key (id));
create table PeriodicTimeTable_PeriodicTimeSlot (PeriodicTimeTable_id int8 not null, periodicTimeSlots_id int8 not null);
create table Room (id int8 not null, primary key (id));
create table t_classes (id int8 not null, name varchar(255) not null, grade_id int8, group_id int8, primary key (id));
create table t_grades (id int8 not null, description varchar(255), title varchar(255) not null, value int2 not null, c_id int8, student_id int8, teacher_id int8, primary key (id));
create table t_groups (id int8 not null, description varchar(255), title varchar(255), primary key (id));
create table t_users (id int8 not null, email varchar(255), fullName varchar(255), password varchar(255), role varchar(255), username varchar(255), primary key (id));
create table t_users_groups (users_id int8 not null, groups_id int8 not null);
alter table if exists PeriodicTimeTable_PeriodicTimeSlot add constraint UK_jpheayai7hly6lfsqwtow1veg unique (periodicTimeSlots_id);
alter table if exists t_users add constraint UK_1f8qpknpngd98342v0j2ceadc unique (email);
alter table if exists t_users add constraint UK_sp0e01od15gf4nu5ffu87qb9n unique (username);
alter table if exists PeriodicTimeSlot add constraint FK2jxc6mddgqucd3418f3talv9v foreign key (aClass_id) references t_classes;
alter table if exists PeriodicTimeSlot add constraint FKgu83w0x1exs0ldja0x7sshwgi foreign key (group_id) references t_groups;
alter table if exists PeriodicTimeSlot add constraint FKsxkfy5gq2a9acpt7obing3xgh foreign key (room_id) references Room;
alter table if exists PeriodicTimeTable_PeriodicTimeSlot add constraint FKj7mtf6xolsc8v6kncmcyq3c1e foreign key (periodicTimeSlots_id) references PeriodicTimeSlot;
alter table if exists PeriodicTimeTable_PeriodicTimeSlot add constraint FKnjw154bymlshjc49x319vpejv foreign key (PeriodicTimeTable_id) references PeriodicTimeTable;
alter table if exists t_classes add constraint FKnl01t0uibd29cr0tbx1r36xx1 foreign key (grade_id) references t_grades;
alter table if exists t_classes add constraint FKe4h5if5ade241ifiyapn5wg8j foreign key (group_id) references t_groups;
alter table if exists t_grades add constraint FKpcm7ehca03wgjgftex79837ey foreign key (c_id) references t_classes;
alter table if exists t_grades add constraint FKjlfutrdmwjp6wfn7housavsk7 foreign key (student_id) references t_users;
alter table if exists t_grades add constraint FK4fulw0sg6dty2pxlifgljx7ue foreign key (teacher_id) references t_users;
alter table if exists t_users_groups add constraint FKoybpvvh28q2qlfrii8b7rvbgs foreign key (groups_id) references t_groups;
alter table if exists t_users_groups add constraint FKgwi0iqh0r1wfpaibe6xr81eh9 foreign key (users_id) references t_users;
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (1, 'debil@gmail.com', '', '$2a$05$Jrq2TDwLUIPFo5Yda8G8neJrd1DdtDjOJjnaRcI9aJ132QUGOLesK', 'USER', 'debil');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (2, 'blatny@blatny.cz', 'Honza', '$2a$12$mlEnv1aMgHvafYCNOBG4N.KUYTS.SLFZtYfnXedUPmJfgeyIlsBKu', 'SUPERADMIN', 'superadmin');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (3, 'teacher@teacher.cz', 'Sample Teacher', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'TEACHER', 'average_teacher');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (4, 'student@student.cz', 'Sample Student', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'STUDENT', 'average_student');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (5, 'user@user.cz', 'Sample Student', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'USER', 'average_parent');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (6, 'student2@student.cz', 'Sample Student 2', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'STUDENT', 'average_student2');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (7, 'student@student.cz', 'Sample Student 3', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'STUDENT', 'average_student3');
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (1, '', 'ahoj', 2, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (2, '', 'chemie s trnkou', 2, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (3, '', 'ctenarsky denik neodevzdan', 5, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (4, '', 'matematika rip', 5, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (5, '', 'zemepis test', 4, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (6, '', 'programovani v c saarp', 1, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (7, '', 'cestina diktat', 3, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (8, '', 'cestion prace v hodine', 1, 1);
