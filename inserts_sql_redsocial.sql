
INSERT INTO user (id, birth_date, name, pass, score, start_date, surname, user) VALUES (1,'1998-01-25','Alejandro','123456',20,'2020-07-08 05:05:12','Tejada','alejandrot');
INSERT INTO user (id, birth_date, name, pass, score, start_date, surname, user) VALUES (2,'1998-01-25','Andrea','123456',50,'2020-07-08 10:15:32','Lopez','andream');
INSERT INTO user (id, birth_date, name, pass, score, start_date, surname, user) VALUES (3,'1999-05-15','Jorge','123456',130,'2020-07-09 12:22:22','Manuel','jorgem');
INSERT INTO user (id, birth_date, name, pass, score, start_date, surname, user) VALUES (4,'1985-08-12','Juan','123456',240,'2020-07-10 04:10:51','Martinez','juanm');
INSERT INTO user (id, birth_date, name, pass, score, start_date, surname, user) VALUES (5,'1991-12-10','Ruben','123456',0,'2020-07-10 22:55:06','Rodriguez','rubenr');

INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (1,0,2,1);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (2,0,1,2);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (3,0,3,1);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (4,0,1,3);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (5,2,4,1);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (6,2,1,4);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (7,0,5,1);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (8,0,1,5);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (9,0,5,2);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (10,0,2,5);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (11,2,4,3);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (12,2,3,4);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (13,0,2,4);
INSERT INTO relationship (id, state, user_friend_id, user_me_id) VALUES (14,0,4,2);

INSERT INTO event (id, description, event_date, name, state, user_id) VALUES (1,'¡¡Nos vamos a subir el desierto con el club!!','2020-12-05 17:05:00','Montaña de murcia',0,1);
INSERT INTO event (id, description, event_date, name, state, user_id) VALUES (2,'Cumpleaños en su campo por la noche, no olvidéis las cervezas','2020-11-19 05:15:00','Cumple Ruben',0,2);
INSERT INTO event (id, description, event_date, name, state, user_id) VALUES (3,'Hará mucho frio, llevad whisky y lo que haga falta','2020-07-09 20:30:00','Subida alpes',2,1);
INSERT INTO event (id, description, event_date, name, state, user_id) VALUES (4,'Aunque da mucha pereza debéis venir, confirmad asistencia','2020-08-05 22:25:00','Comida empresa',0,1);
INSERT INTO event (id, description, event_date, name, state, user_id) VALUES (5,'Que mejor para celebrar el verano que una cenita, ¡vamos!','2020-09-01 01:55:00','Cena amigos',0,4);
INSERT INTO event (id, description, event_date, name, state, user_id) VALUES (6,'Mi hijo cumple 4 años, nos vemos en el castillo de bolas','2020-10-16 08:45:00','Fiesta infantil',0,2);

INSERT INTO assistance (id, state, event, user) VALUES (1,1,1,2);
INSERT INTO assistance (id, state, event, user) VALUES (2,1,1,3);
INSERT INTO assistance (id, state, event, user) VALUES (3,1,1,4);
INSERT INTO assistance (id, state, event, user) VALUES (4,1,2,1);
INSERT INTO assistance (id, state, event, user) VALUES (5,1,2,5);
INSERT INTO assistance (id, state, event, user) VALUES (6,1,3,5);
INSERT INTO assistance (id, state, event, user) VALUES (7,1,5,4);
INSERT INTO assistance (id, state, event, user) VALUES (8,0,6,1);
INSERT INTO assistance (id, state, event, user) VALUES (9,0,5,2);

INSERT INTO message (id, content, publish_date, user_id) VALUES (1,'Siempre codifica como si la persona que finalmente mantendrá tu código fuera un psicópata violento que sabe dónde vives.','2020-07-09 17:05:00',2);
INSERT INTO message (id, content, publish_date, user_id) VALUES (2,'La mayoría de los buenos programadores programan, no porque esperan que se les pague o por adulación por parte del público, sino porque es divertido programar.','2020-01-08 05:10:01',3);
INSERT INTO message (id, content, publish_date, user_id) VALUES (3,'Iterar es humano, recursivo divino.','2020-08-02 10:15:05',1);
INSERT INTO message (id, content, publish_date, user_id) VALUES (4,'El problema de los programadores es que nunca se sabe lo que un programador está haciendo hasta que ya es demasiado tarde.','2020-07-10 22:12:55',1);
INSERT INTO message (id, content, publish_date, user_id) VALUES (5,'La mayoría de ustedes están familiarizados con las virtudes de un programador. Hay tres, por supuesto: pereza, impaciencia y orgullo desmedido.','2020-06-10 21:07:45',1);
INSERT INTO message (id, content, publish_date, user_id) VALUES (6,'Medir los avances de programación por líneas de código es como medir el progreso de la construcción de aviones por peso.','2020-07-10 14:50:32',2);
INSERT INTO message (id, content, publish_date, user_id) VALUES (7,'En teoría, la teoría y la práctica son los mismos. En la práctica, no es.','2020-07-10 12:45:25',4);
INSERT INTO message (id, content, publish_date, user_id) VALUES (8,'La educación en computación no puede hacer a nadie un experto programador más que el estudio de pinceles y pigmentos puede hacer a alguien un pintor experto.','2020-06-10 05:32:12',4);

INSERT INTO reaction (id, type, message, user) VALUES (1,0,2,1);
INSERT INTO reaction (id, type, message, user) VALUES (2,2,1,2);
INSERT INTO reaction (id, type, message, user) VALUES (3,2,1,4);
INSERT INTO reaction (id, type, message, user) VALUES (4,1,1,5);
INSERT INTO reaction (id, type, message, user) VALUES (5,1,3,5);
INSERT INTO reaction (id, type, message, user) VALUES (6,1,3,1);
INSERT INTO reaction (id, type, message, user) VALUES (7,2,3,2);
INSERT INTO reaction (id, type, message, user) VALUES (8,0,3,3);
INSERT INTO reaction (id, type, message, user) VALUES (9,0,5,1);