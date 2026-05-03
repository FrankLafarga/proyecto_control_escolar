package models;

public class UserModel {
	private int id;
    private String username;
    private String rol;

    public UserModel(int id, String username, String rol) {
        this.id = id;
        this.username = username;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getRol() {
        return rol;
    }

    public int getId() {
        return id;
    }
}

/*
CREATE TABLE usuarios (
id_usuario INT NOT NULL AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
password_hash VARCHAR(255) NOT NULL,
rol VARCHAR(20) NOT NULL,
estatus VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id_usuario),
UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO usuarios (username, password_hash, rol)
VALUES 
('admin', '1234', 'ADMIN'),
('diego_romero', '1234', 'DEV'),
('francisco_lafarga', '1234', 'DEV'),
('yahir_mendez', '1234', 'DEV');





CREATE TABLE docentes (
id_docente INT AUTO_INCREMENT PRIMARY KEY,
clave VARCHAR(20),

nombre VARCHAR(50),
apellido_paterno VARCHAR(50),
apellido_materno VARCHAR(50),

nombre_completo VARCHAR(150) GENERATED ALWAYS AS (
    CONCAT(nombre,' ',apellido_paterno,' ',apellido_materno)
) STORED,

especialidad VARCHAR(100),
estatus VARCHAR(20),
grado_estudios VARCHAR(50),

correo VARCHAR(100),
telefono VARCHAR(20),
fecha_nacimiento DATE
);







INSERT INTO docentes (clave,nombre,apellido_paterno,apellido_materno,especialidad,estatus,grado_estudios,correo,telefono,fecha_nacimiento) VALUES
('DOC001','Carlos','Ramírez','Lopez','Programación','Activo','Maestría','carlos.ramirez@edu.mx','6121110001','1980-05-10'),
('DOC002','Ana','Martinez','Gomez','Bases de Datos','Activo','Doctorado','ana.martinez@edu.mx','6121110002','1978-08-21'),
('DOC003','Luis','Hernandez','Perez','Redes','Activo','Maestría','luis.hernandez@edu.mx','6121110003','1985-03-15'),
('DOC004','Maria','Lopez','Diaz','Matemáticas','Activo','Doctorado','maria.lopez@edu.mx','6121110004','1975-11-02'),
('DOC005','Jorge','Garcia','Torres','Ingeniería Software','Activo','Maestría','jorge.garcia@edu.mx','6121110005','1982-06-30'),
('DOC006','Laura','Sanchez','Reyes','IA','Activo','Doctorado','laura.sanchez@edu.mx','6121110006','1987-09-12'),
('DOC007','Pedro','Gomez','Vargas','Ciberseguridad','Activo','Maestría','pedro.gomez@edu.mx','6121110007','1983-04-18');







CREATE TABLE grupos (
id_grupo INT AUTO_INCREMENT PRIMARY KEY,
grupo VARCHAR(10),
semestre INT,
turno VARCHAR(20),
capacidad INT,
id_docente INT,
FOREIGN KEY (id_docente) REFERENCES docentes(id_docente)
);

INSERT INTO grupos (grupo,semestre,turno,capacidad,id_docente) VALUES
('A',1,'Matutino',30,1),
('B',2,'Matutino',30,2),
('C',3,'Matutino',30,3),
('D',4,'Vespertino',30,4),
('E',5,'Vespertino',30,5),
('F',6,'Vespertino',30,6),
('G',7,'Mixto',30,7);









CREATE TABLE asignaturas (
id_asignatura INT AUTO_INCREMENT PRIMARY KEY,
clave VARCHAR(20),
nombre VARCHAR(100),
semestre INT,
creditos INT,
id_grupo INT,
id_docente INT,
FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo),
FOREIGN KEY (id_docente) REFERENCES docentes(id_docente)
);

INSERT INTO asignaturas (clave,nombre,semestre,creditos,id_grupo,id_docente) VALUES
('MAT101','Matemáticas I',1,8,1,4),
('PRO102','Programación I',1,8,1,1),
('BD201','Bases de Datos',2,8,2,2),
('RED301','Redes',3,8,3,3),
('SW401','Ing. Software',4,8,4,5),
('IA501','Inteligencia Artificial',5,8,5,6),
('SEG601','Ciberseguridad',6,8,6,7);









CREATE TABLE alumnos (
matricula INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(50),
apellido_paterno VARCHAR(50),
apellido_materno VARCHAR(50),

nombre_completo VARCHAR(150) GENERATED ALWAYS AS (
    CONCAT(nombre,' ',apellido_paterno,' ',apellido_materno)
) STORED,

semestre INT,
grupo VARCHAR(10),
id_grupo INT,

promedio DECIMAL(4,2),
promedio_general DECIMAL(4,2),
estatus VARCHAR(20),

correo VARCHAR(100),
telefono VARCHAR(20),
fecha_nacimiento DATE,
genero VARCHAR(20),
fecha_ingreso DATE,

FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo)
);









DELIMITER //

CREATE PROCEDURE generar_alumnos()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE grupo_id INT;
    DECLARE sem INT;

    WHILE i <= 210 DO

        IF i <= 30 THEN SET grupo_id=1; SET sem=1;
        ELSEIF i <= 60 THEN SET grupo_id=2; SET sem=2;
        ELSEIF i <= 90 THEN SET grupo_id=3; SET sem=3;
        ELSEIF i <= 120 THEN SET grupo_id=4; SET sem=4;
        ELSEIF i <= 150 THEN SET grupo_id=5; SET sem=5;
        ELSEIF i <= 180 THEN SET grupo_id=6; SET sem=6;
        ELSE SET grupo_id=7; SET sem=7;
        END IF;

        INSERT INTO alumnos (
            nombre,apellido_paterno,apellido_materno,
            semestre,grupo,id_grupo,
            promedio,promedio_general,estatus,
            correo,telefono,fecha_nacimiento,genero,fecha_ingreso
        )
        VALUES (
            ELT(FLOOR(1+RAND()*5),'Juan','Maria','Luis','Ana','Carlos'),
            ELT(FLOOR(1+RAND()*5),'Hernandez','Garcia','Lopez','Martinez','Perez'),
            ELT(FLOOR(1+RAND()*5),'Gomez','Diaz','Torres','Vargas','Sanchez'),
            sem,
            CHAR(64+grupo_id),
            grupo_id,
            ROUND(6+RAND()*4,2),
            ROUND(6+RAND()*4,2),
            'Activo',
            CONCAT('alumno',i,'@edu.mx'),
            CONCAT('612',LPAD(i,7,'0')),
            DATE_SUB('2005-01-01', INTERVAL FLOOR(RAND()*5000) DAY),
            IF(RAND()>0.5,'Masculino','Femenino'),
            '2024-08-01'
        );

        SET i = i + 1;
    END WHILE;

END//

DELIMITER ;

CALL generar_alumnos();
*/