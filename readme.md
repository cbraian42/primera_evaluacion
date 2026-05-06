# 🏥 API REST - Gestión de Turnos Médicos

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![H2 Database](https://img.shields.io/badge/Database-H2-blue)

## 📝 Descripción
Este proyecto es una API RESTful desarrollada en Spring Boot para gestionar el flujo operativo de una clínica médica. Permite administrar pacientes, profesionales de la salud y la asignación de turnos, aplicando reglas de negocio estrictas para evitar superposiciones y garantizar la integridad de los datos.

## ✨ Características Principales
* **Gestión de Pacientes:** Operaciones CRUD completas con validación de datos (ej. DNI y Email obligatorios).
* **Gestión de Profesionales:** Operaciones CRUD con la capacidad de filtrar listados por especialidad médica.
* **Asignación de Turnos:** Creación de turnos con validaciones cruzadas (verifica que el paciente y el profesional existan, y evita la duplicación de turnos en la misma fecha).
* **Búsquedas Avanzadas:** Filtro de turnos por fecha exacta o por rangos de fechas (`desde` / `hasta`).
* **Carga Inicial de Datos:** La base de datos H2 se puebla automáticamente al iniciar la aplicación para facilitar el testing.
* **Manejo Global de Errores:** Respuestas JSON estandarizadas ante fallos de validación o reglas de negocio (Error 400 y 404).

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** H2 (En memoria)
* **Validaciones:** Jakarta Validation
* **Productividad:** Lombok

## 🚀 Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/tu-usuario/tu-repositorio.git](https://github.com/tu-usuario/tu-repositorio.git)
   ```
2. Actualizar las dependencias de Maven.
3. Ejecutar la clase principal `Application.java`.
4. La aplicación estará corriendo en `http://localhost:8080`.
5. Al iniciar, la base de datos ya contará con Pacientes, Profesionales y Turnos cargados.

## 📡 Endpoints Principales

A continuación se detallan algunas de las rutas clave para interactuar con la API:

### Pacientes
* `POST /pacientes` - Registra un nuevo paciente.
* `GET /pacientes` - Lista todos los pacientes.
* `GET /pacientes/{id}` - Busca un paciente por su ID.

### Profesionales
* `POST /profesionales` - Registra un nuevo médico.
* `GET /profesionales` - Lista todos los médicos.
* `GET /profesionales?especialidad=clinica` - Lista médicos filtrados por especialidad.

### Turnos
* `POST /turnos` - Crea un turno (requiere IDs válidos de paciente y profesional).
* `GET /turnos/fecha/{fecha}` - Busca turnos en una fecha específica (formato `YYYY-MM-DD`).
* `GET /turnos?desde={fecha1}&hasta={fecha2}` - Busca turnos en un rango de fechas.

## 🏗️ Arquitectura
El proyecto fue diseñado siguiendo una **Arquitectura Multicapa** para garantizar la separación de responsabilidades:
* `Controllers`: Exponen los endpoints HTTP y validan los Request iniciales.
* `Services`: Contienen la lógica de negocio y las validaciones cruzadas.
* `Repositories`: Gestionan la persistencia en la base de datos mediante Spring Data JPA.
* `Models & DTOs`: Separan la estructura de la base de datos de los datos que se exponen al cliente.
* `Mappers`: Encargados de la traducción entre Entidades y DTOs.