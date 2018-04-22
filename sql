 create table USER (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    age INTEGER NOT NULL,
    salary REAL NOT NULL,
    PRIMARY KEY (id)
 );

 INSERT INTO USER(name,age,salary)
 VALUES ('Tom',30,60000);

 INSERT INTO USER(name,age,salary)
 VALUES ('Sam',40,70000);

