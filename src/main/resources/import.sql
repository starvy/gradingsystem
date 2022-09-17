INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (1, 'debil@gmail.com', '', '$2a$05$Jrq2TDwLUIPFo5Yda8G8neJrd1DdtDjOJjnaRcI9aJ132QUGOLesK', 'USER', 'debil');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (2, 'super@super.cz', 'Superadmin', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'SUPERADMIN', 'super');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (3, 'teacher@teacher.cz', 'Sample Teacher', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'TEACHER', 'average_teacher');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (4, 'student@student.cz', 'Sample Student', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'STUDENT', 'average_student');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (5, 'user@user.cz', 'Sample Student', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'USER', 'average_parent');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (6, 'student2@student.cz', 'Sample Student 2', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'STUDENT', 'average_student2');

INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (7, 'student@student.cz', 'Sample Student 3', '$2y$10$UP1dXgF674vSbl01WJSfseeTJW9IiiGewJEGL/wjGEl1d8ozKNTQW', 'STUDENT', 'average_student3');


/*INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (1, '', 'ahoj', 2, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (2, '', 'chemie s trnkou', 2, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (3, '', 'ctenarsky denik neodevzdan', 5, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (4, '', 'matematika rip', 5, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (5, '', 'zemepis test', 4, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (6, '', 'programovani v c saarp', 1, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (7, '', 'cestina diktat', 3, 1);
INSERT INTO public.t_grades (id, description, title, value, student_id) VALUES (8, '', 'cestion prace v hodine', 1, 1);*/

INSERT INTO public.t_groups (id, description, title) VALUES (1, '1.K prestiz', '1.K');
INSERT INTO public.t_users_groups (users_id, groups_id) VALUES (4, 1);
INSERT INTO public.t_users_groups (users_id, groups_id) VALUES (6, 1);

INSERT INTO public.t_classes (id, title, group_id) VALUES (1, 'Divna cj', 1);
INSERT INTO public.t_users_classes (classes_id, teachers_id) VALUES (1, 3);

