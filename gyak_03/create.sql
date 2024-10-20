CREATE TABLE PROFESSOR (ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, NAME VARCHAR(255) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE STUDENT (ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, NAME VARCHAR(255) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE SUBJECT (ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, SUBJECTNAME VARCHAR(255) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE COURSE (ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, DAYOFWEEK VARCHAR(255) NOT NULL, "hour" INTEGER NOT NULL, "minute" INTEGER NOT NULL, NAME VARCHAR(255) NOT NULL, subject_id BIGINT NOT NULL, PRIMARY KEY (ID))
CREATE TABLE course_professor (professor_id BIGINT NOT NULL, course_id BIGINT NOT NULL, PRIMARY KEY (professor_id, course_id))
CREATE TABLE course_student (student_id BIGINT NOT NULL, course_id BIGINT NOT NULL, PRIMARY KEY (student_id, course_id))
ALTER TABLE COURSE ADD CONSTRAINT COURSE_subject_id FOREIGN KEY (subject_id) REFERENCES SUBJECT (ID)
ALTER TABLE course_professor ADD CONSTRAINT crsprfessorprfssrd FOREIGN KEY (professor_id) REFERENCES PROFESSOR (ID)
ALTER TABLE course_professor ADD CONSTRAINT crsprofessorcrseid FOREIGN KEY (course_id) REFERENCES COURSE (ID)
ALTER TABLE course_student ADD CONSTRAINT crsestudentcurseid FOREIGN KEY (course_id) REFERENCES COURSE (ID)
ALTER TABLE course_student ADD CONSTRAINT crsestudentstdntid FOREIGN KEY (student_id) REFERENCES STUDENT (ID)
