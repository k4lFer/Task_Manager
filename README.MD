# 🚀 NexBoard Backend (Task Manager API)

Backend REST robusto y escalable para una plataforma avanzada de gestión de proyectos estilo **Kanban / Agile**, construida con **Java 21** y **Spring Boot 3.5**, aplicando arquitectura modular con **Spring Modulith**, principios de **Clean Architecture** y el patrón **CQRS**.

> NexBoard está diseñado como el núcleo backend de un ecosistema moderno de colaboración, tableros, tareas, mensajería y notificaciones en tiempo real.

![Status](https://img.shields.io/badge/status-alpha-orange)
![Java](https://img.shields.io/badge/java-21-red)
![Spring Boot](https://img.shields.io/badge/spring--boot-3.5.7-brightgreen)
![PostgreSQL](https://img.shields.io/badge/postgresql-supported-blue)
![Architecture](https://img.shields.io/badge/architecture-clean--architecture%20%7C%20cqrs%20%7C%20modulith-purple)

---

## 📚 Tabla de Contenidos

- [📌 Descripción General](#-descripción-general)
- [🧠 Principios de Arquitectura](#-principios-de-arquitectura)
- [📌 Estado Actual del Proyecto](#-estado-actual-del-proyecto)
- [✨ Features Principales](#-features-principales)
- [🧩 Roadmap](#-roadmap)
- [⚙️ Stack Tecnológico](#️-stack-tecnológico)
- [🏛️ Arquitectura General](#️-arquitectura-general)
- [📂 Arquitectura de Directorios (Spring Modulith)](#arquitectura-de-directorios-orientada-al-dominio-modulith)
- [🚀 Despliegue y Ejecución Local](#-despliegue-y-ejecución-en-red-local)


---

## 📌 Descripción General

**NexBoard** es el núcleo backend (API Rest) de una plataforma avanzada de gestión de proyectos, colaboración y tareas inspirada en metodologías **Kanban** y **Agile**.

El sistema fue diseñado desde cero para ser **robusto, escalable, modular y mantenible**, utilizando un enfoque moderno basado en **Java 21**, **Spring Boot 3.5**, y un ecosistema modular implementado con **Spring Modulith** (microservicios lógicos dentro de un único despliegue).

---

## 🧠 Principios de Arquitectura

El proyecto está construido bajo pilares sólidos:

- **Clean Architecture**: reglas de negocio aisladas, desacoplamiento de infraestructura y alta testabilidad.
- **CQRS (Command Query Responsibility Segregation)**: separación estricta entre escritura (Commands) y lectura (Queries).
- **Modularización fuerte con Spring Modulith**: dominios independientes y organizados por contexto.
- **Diseño orientado a dominio (DDD-like)**: cada módulo encapsula su propia lógica.

Esto permite que NexBoard evolucione hacia un ecosistema enterprise sin perder control estructural.

---

## 📌 Estado Actual del Proyecto

📍 **Versión:** `0.0.1-SNAPSHOT`  
📍 **Fase:** Alpha / En Desarrollo activo  
📍 **Objetivo actual:** construir bases sólidas intermodulares antes de integrar funcionalidades complejas.

---

## ✨ Features Principales

- Autenticación robusta con **JWT** y **Spring Security**
- Arquitectura modular con **Spring Modulith**
- Implementación CQRS para todos los casos de uso principales
- Persistencia sólida con **PostgreSQL + JPA + Hibernate**
- Dominio desacoplado con enfoque Clean Architecture
- Chat modular integrado (WebSockets + Persistencia)
- Documentación automática vía Swagger / OpenAPI

---

## 🧩 Roadmap

### ✅ Módulos Implementados / Avanzados

- [x] **Auth Module** (Spring Security + JWT)
- [x] **Boards Module** (dominio + CQRS + controladores + persistencia)
- [x] **Workspaces Module** (dominio + CQRS + controladores + persistencia)
- [x] **Sistema de permisos y roles por workspace**
- [x] **Chat Module** (CQRS + WebSockets + persistencia + endpoints)

### 🚧 Módulos Pendientes / Próximos

- [ ] **Cards / Lists** (core Kanban: columnas, mover tarjetas, flujo de trabajo)
- [ ] **Tasks** (subtareas, checklists, asignaciones internas)
- [ ] **Notifications Module** (eventos en tiempo real + colas diferidas)
- [ ] **Comments / Mentions** (interacción avanzada dentro de tarjetas)
- [ ] **Deadlines & Scheduling** (fechas de vencimiento + recordatorios)
- [ ] **Auditoría de eventos del sistema**

---

## ⚙️ Stack Tecnológico

Tecnologías principales utilizadas:

- **Lenguaje:** Java 21 (Records, Pattern Matching, Virtual Threads)
- **Framework Principal:** Spring Boot 3.5.7
- **Arquitectura Interna:** Spring Modulith + Clean Architecture + CQRS
- **Seguridad:** Spring Security + JWT (`jjwt`)
- **Persistencia:** PostgreSQL + Spring Data JPA + Hibernate
- **Tipos avanzados DB:** Hibernate Types (`vladmihalcea`)
- **Mapeo DTO:** MapStruct
- **Utilidades:** Lombok
- **Jobs / Background Tasks:** Quartz Scheduler
- **Documentación API:** Springdoc OpenAPI (Swagger UI WebMVC)
- **Testing:** Spring Boot Test + Spring Modulith Test + Spring Security Test

---

## 🏛️ Arquitectura General

El flujo interno de la aplicación sigue una estructura limpia y desacoplada:

```text
Client (Angular / React / Mobile)
        |
        v
REST Controllers (Web Layer)
        |
        v
Use Cases (CQRS Handlers)
   |                |
Commands         Queries
   |                |
   v                v
Domain Model     Read Model
   |
   v
Infrastructure (JPA + PostgreSQL)
```

## Arquitectura de Directorios Orientada al Dominio (Modulith)

Todo el código fuente se encapsula lógicamente en **módulos independientes y funcionales** respetando estrictamente la filosofía Modulith. Cada contexto (ej. `auth`, `board`, `workspace`, `chat`) mantiene su autonomía encapsulando su propio dominio e infraestructura:

```text
src/main/java/com/pck4x/task_manager/
├── config/              # Configuraciones de seguridad, filtros, manejo de errores de cruce modular
└── modules/             # Contextos (Módulos de la aplicación base)
    ├── auth/            # Módulo Core de Autenticación, Logins, Gestión del Usuario
    ├── workspace/       # Módulo Core de Entornos/Empresas y Espacios de Trabajo
    ├── board/           # Módulo de Tableros (Proyectos específicos dentro del Workspace)
    └── chat/            # Módulo de mensajería (Websockets / Persistencia de la comunicación)
        ├── controllers/     # Controladores REST (Exposición hacia el exterior)
        ├── domain/          # Entidades Core y Clases Portadoras de Reglas de Negocio Puras
        ├── infrastructure/  # Persistencia DB, Repositorios JPA y Mappers de Implementación
        ├── interfaces/      # Contratos, Puertos e Interfaces para inyección de dependencias
        ├── objects/dtos/    # Modelos de entrada (Peticiones) y salida (Respuestas)
        └── use_cases/       # Inteligencia de la Aplicación y Manejadores CQRS aislados
```

## 🚀 Despliegue y Ejecución en Red Local

1. Pre-requisitos ambientales: Asegúrate de tener instalado **Java 21**, **Maven**, y un motor **PostgreSQL** activo escuchando conexiones locales.
2. Clona el repositorio oficial de este sistema en tu máquina. Navega a través de la terminal hasta encontrar el archivo `pom.xml` en la raíz (`task-manager`).
3. Revisa, edita o sobrescribe temporalmente tus credenciales (URL, User, Pass) de conexión a PostgreSQL editando el archivo `application.yml` u `application.properties` correspondiente en la ruta `src/main/resources`.
4. Ejecuta el empaque inicial ordenando al compilador que limpie el entorno, compile binarios estables, y baje dependencias (sin interrupciones por pruebas si es ambiente rápido):
   ```bash
   ./mvnw clean install -DskipTests
   ```
5. Inicia el ecosistema y despliega el contenedor de Servlets incrustado tomcat de Spring Boot:
   ```bash
   ./mvnw spring-boot:run
   ```
6. Si el levantamiento ha resultado un éxito, dirígete mediante tu navegador favorito al panel de Swagger, habitualmente publicitado en la dirección `http://localhost:8080/swagger-ui.html` para interactuar gráficamente con cada endpoint disponible.
