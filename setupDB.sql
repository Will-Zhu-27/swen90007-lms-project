DROP TABLE IF EXISTS SUBJECTS CASCADE;

CREATE TABLE subjects(
   code   VARCHAR(20),
   name  VARCHAR(50),
   coordinator VARCHAR(50),
   PRIMARY KEY (code)
);

INSERT INTO SUBJECTS
VALUES('SWEN90007', 'Architecture', 'Yuqiang');

INSERT INTO SUBJECTS
VALUES('COMP90025', 'Parallel and Multicore Computing', 'Aaron');

INSERT INTO SUBJECTS
VALUES('SWEN90004', 'Modelling Complex Software Systems', 'Congran');

INSERT INTO SUBJECTS
VALUES('SWEN9999', 'Big Boss', 'MGS');