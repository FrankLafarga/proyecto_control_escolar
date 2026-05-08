CREATE DATABASE IF NOT EXISTS educadex;
USE educadex;

CREATE TABLE USUARIOS (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    estatus VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE DOCENTES (
    id_docente INT AUTO_INCREMENT PRIMARY KEY,
    clave VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    grado_estudios VARCHAR(50) NOT NULL,
    area_estudios VARCHAR(50) NOT NULL,
    estatus VARCHAR(20) NOT NULL,
    avatar VARCHAR(255)
);

CREATE TABLE ASIGNATURAS (
    id_asignatura INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    clave VARCHAR(20) NOT NULL UNIQUE,
    semestre INT NOT NULL,
    creditos INT NOT NULL
);

CREATE TABLE GRUPOS (
    id_grupo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    turno VARCHAR(20) NOT NULL,
    capacidad INT NOT NULL
);

CREATE TABLE GRUPO_ASIGNATURA (
    id_grupo INT NOT NULL,
    id_asignatura INT NOT NULL,
    id_docente INT NOT NULL,
    PRIMARY KEY (id_grupo, id_asignatura),
    FOREIGN KEY (id_grupo) REFERENCES GRUPOS(id_grupo),
    FOREIGN KEY (id_asignatura) REFERENCES ASIGNATURAS(id_asignatura),
    FOREIGN KEY (id_docente) REFERENCES DOCENTES(id_docente)
);

CREATE TABLE ALUMNOS (
    id_alumno INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    semestre INT NOT NULL,
    carrera CHAR(50) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    promedio DECIMAL(4,2) NOT NULL,
    estatus VARCHAR(20) NOT NULL,
    id_grupo INT,
    FOREIGN KEY (id_grupo) REFERENCES GRUPOS(id_grupo)
);


INSERT INTO USUARIOS (username, password_hash, rol, estatus) VALUES 
('admin', '1234', 'ADMIN', 'ACTIVO'),
('diego romero', '1234', 'DEV', 'ACTIVO'),
('francisco lafarga', '1234', 'DEV', 'ACTIVO'),
('yahir mendez', '1234', 'DEV', 'ACTIVO');

INSERT INTO DOCENTES (clave,nombre,apellido_paterno,apellido_materno,correo,telefono,fecha_nacimiento,grado_estudios,area_estudios,estatus) VALUES
('DOC001','Juan','Perez','Lopez','juan@mail.com','6121110001','1980-05-10','Lic','Matematicas','ACTIVO'),
('DOC002','Maria','Gomez','Ruiz','maria@mail.com','6121110002','1985-07-15','Mtr','Fisica','ACTIVO'),
('DOC003','Luis','Hernandez','Diaz','luis@mail.com','6121110003','1978-02-20','Dr','Quimica','ACTIVO'),
('DOC004','Ana','Martinez','Torres','ana@mail.com','6121110004','1990-11-01','Lic','Historia','ACTIVO'),
('DOC005','Carlos','Sanchez','Vega','carlos@mail.com','6121110005','1982-03-25','Mtr','Programacion','ACTIVO'),
('DOC006','Laura','Ramirez','Castro','laura@mail.com','6121110006','1987-09-30','Lic','Biologia','ACTIVO'),
('DOC007','Pedro','Flores','Mendoza','pedro@mail.com','6121110007','1975-06-12','Dr','Matematicas','ACTIVO'),
('DOC008','Sofia','Ortega','Navarro','sofia@mail.com','6121110008','1992-08-18','Lic','Ingles','ACTIVO'),
('DOC009','Diego','Morales','Rojas','diego@mail.com','6121110009','1983-12-05','Mtr','Fisica','ACTIVO'),
('DOC010','Elena','Castillo','Reyes','elena@mail.com','6121110010','1989-04-22','Lic','Quimica','ACTIVO');

INSERT INTO ASIGNATURAS (nombre,clave,semestre,creditos) VALUES
('Matematicas I','MAT101',1,8),
('Fisica I','FIS101',1,8),
('Quimica','QUI101',1,8),
('Historia','HIS101',1,6),
('Programacion','PRO101',1,10),
('Biologia','BIO101',1,8),
('Ingles','ING101',1,6),
('Algebra','MAT102',2,8);

INSERT INTO GRUPOS (nombre,turno,capacidad) VALUES
('1A','MATUTINO',40),
('1B','MATUTINO',40),
('1C','VESPERTINO',40),
('1D','VESPERTINO',40),
('1E','MATUTINO',40);

INSERT INTO GRUPO_ASIGNATURA VALUES
(1,1,1),(1,2,2),(1,5,5),(1,7,8),
(2,1,7),(2,3,3),(2,6,6),(2,7,8),
(3,2,9),(3,4,4),(3,5,5),(3,7,8),
(4,1,1),(4,3,10),(4,6,6),(4,8,7),
(5,2,2),(5,4,4),(5,5,5),(5,7,8);


DELIMITER //

CREATE PROCEDURE generar_alumnos()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE grupo_id INT;
    DECLARE sem INT;

    WHILE i <= 200 DO

        SET grupo_id = (i MOD 5) + 1;

        IF grupo_id = 1 THEN SET sem = 1;
        ELSEIF grupo_id = 2 THEN SET sem = 2;
        ELSEIF grupo_id = 3 THEN SET sem = 3;
        ELSEIF grupo_id = 4 THEN SET sem = 4;
        ELSE SET sem = 5;
        END IF;

        INSERT INTO ALUMNOS (
            matricula,
            carrera,
            genero,
            nombre,
            apellido_paterno,
            apellido_materno,
            correo,
            telefono,
            fecha_nacimiento,
            promedio,
            estatus,
            id_grupo,
            semestre
        )
        VALUES (
            CONCAT('ALU', LPAD(i,4,'0')),
            ELT(FLOOR(1+RAND()*3),'Ing. Software','Ing. Industrial','Ing. Civil'),
            IF(RAND()>0.5,'Masculino','Femenino'),
            ELT(FLOOR(1+RAND()*5),'Juan','Maria','Luis','Ana','Carlos'),
            ELT(FLOOR(1+RAND()*5),'Hernandez','Garcia','Lopez','Martinez','Perez'),
            ELT(FLOOR(1+RAND()*5),'Gomez','Diaz','Torres','Vargas','Sanchez'),
            CONCAT('alumno',i,'@edu.mx'),
            CONCAT('612',LPAD(i,7,'0')),
            DATE_SUB('2005-01-01', INTERVAL FLOOR(RAND()*4000) DAY),
            ROUND(6+RAND()*4,2),
            'ACTIVO',
            grupo_id,
            sem
        );

        SET i = i + 1;

    END WHILE;

END //

DELIMITER ;

CALL generar_alumnos();