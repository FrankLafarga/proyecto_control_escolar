package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthModel {

	public UserModel validarUsuario(String user, String password) {

	    String sql = "SELECT id_usuario, username, rol FROM usuarios WHERE username = ? AND password_hash = ? AND estatus='ACTIVO'";

	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/educadex","root","educadex2026");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, user);
	        stmt.setString(2, password);

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return new UserModel(
	                rs.getInt("id_usuario"),
	                rs.getString("username"),
	                rs.getString("rol")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
    /*CREATE DATABASE IF NOT EXISTS educadex;
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
        id_docente INT NOT NULL,
        id_asignatura INT NOT NULL,
        CONSTRAINT fk_grupo_docente
            FOREIGN KEY (id_docente) REFERENCES DOCENTES(id_docente)
            ON DELETE RESTRICT ON UPDATE CASCADE,
        CONSTRAINT fk_grupo_asignatura
            FOREIGN KEY (id_asignatura) REFERENCES ASIGNATURAS(id_asignatura)
            ON DELETE RESTRICT ON UPDATE CASCADE
    );

    CREATE TABLE ALUMNOS (
        id_alumno INT AUTO_INCREMENT PRIMARY KEY,
        matricula VARCHAR(20) NOT NULL UNIQUE,
        nombre VARCHAR(50) NOT NULL,
        apellido_paterno VARCHAR(50) NOT NULL,
        apellido_materno VARCHAR(50) NOT NULL,
        correo VARCHAR(100) NOT NULL UNIQUE,
        telefono VARCHAR(15) NOT NULL,
        fecha_nacimiento DATE NOT NULL,
        promedio DECIMAL(4,2) NOT NULL,
        estatus VARCHAR(20) NOT NULL,
        id_grupo INT,
        CONSTRAINT fk_alumno_grupo
            FOREIGN KEY (id_grupo) REFERENCES GRUPOS(id_grupo)
            ON DELETE SET NULL ON UPDATE CASCADE
    );

    INSERT INTO DOCENTES (clave, nombre, apellido_paterno, apellido_materno, correo, telefono, fecha_nacimiento, grado_estudios, area_estudios, estatus)
    VALUES ('D001', 'Juan', 'Pérez', 'López', 'juan@edu.com', '6121234567', '1980-05-10', 'Maestría', 'Matemáticas', 'Activo');

    INSERT INTO ASIGNATURAS (nombre, clave, semestre, creditos)
    VALUES ('Matemáticas', 'MAT101', 1, 8);

    INSERT INTO GRUPOS (nombre, turno, id_docente, id_asignatura)
    VALUES ('1A', 'Matutino', 1, 1);

    INSERT INTO ALUMNOS (matricula, nombre, apellido_paterno, apellido_materno, correo, telefono, fecha_nacimiento, promedio, estatus, id_grupo)
    VALUES ('A2024001', 'María', 'González', 'Sánchez', 'maria@edu.com', '6127654321', '2005-03-15', 9.5, 'Activo', 1);

    INSERT INTO USUARIOS (username, password_hash, rol)
    VALUES ('admin', '1234', 'ACTIVO');*/
}