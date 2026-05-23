# GameKeyHub - Plataforma Marketplace de Llaves de Videojuegos

GameKeyHub es una solución digital completa orientada al modelo de negocio de un marketplace de distribución de licencias de activación (Keys) de videojuegos. El sistema permite a múltiples vendedores ofrecer sus catálogos de ofertas de manera competitiva, mientras que los clientes finales pueden explorar, adquirir y recibir sus códigos digitales de forma automatizada y segura.

Este proyecto semestral está desarrollado bajo una arquitectura robusta y totalmente desacoplada de microservicios distribuidos, superando las limitaciones de un monolito convencional para responder a demandas de alta escalabilidad y resiliencia en un entorno de producción real.

---

## Arquitectura del Sistema

El ecosistema está construido utilizando el framework principal **Spring Boot** y herramientas avanzadas de **Spring Cloud**. La arquitectura se compone de un punto de entrada unificado (**API Gateway**), un directorio dinámico de localización (**Eureka Service Discovery**) y un conjunto de **10 microservicios** de lógica de negocio independientes y autosuficientes.

* **API Gateway**: Actúa como el punto de entrada único que centraliza y gestiona de manera transparente todas las solicitudes externas del sistema.
* **Eureka Service Discovery**: Funciona como un directorio dinámico donde cada microservicio se registra automáticamente al iniciar, facilitando la localización por nombre dentro de la red interna.

---

## Detalle de los 10 Microservicios

Cada componente backend posee una responsabilidad única, lógica de negocio propia, interfaces de consumo estandarizadas RESTful y su propia base de datos persistente independiente.

### 1. Auth-Service
* [**Responsabilidad**: Centraliza la gestión de usuarios, el registro y los flujos de autenticación y autorización del ecosistema.
* **Seguridad**: Implementa login con generación de tokens seguros sin estado mediante JSON Web Token (JJWT) y aplica un estricto Control de Acceso Basado en Roles (RBAC).
* **Persistencia**: Almacena las credenciales de los usuarios utilizando el algoritmo de cifrado BCrypt para resguardar las contraseñas en la base de datos.

### 2. Game-Catalog
* **Responsabilidad**: Administra el catálogo maestro global de los videojuegos del sistema.
* **Funcionalidades**: Provee información de referencia estandarizada como nombres, descripciones técnicas, categorizaciones y recursos de imágenes multimedia.

### 3. Inventory-Service
* **Responsabilidad**: Gestiona el almacenamiento físico digital de las licencias de activación de videojuegos (Keys) en stock vinculadas a cada juego.
* **Lógica Extendida**: Controla de forma rigurosa los estados de cada llave (Disponible, Reservada, Vendida, Inválida) para evitar problemas transaccionales como la duplicidad de ventas.

### 4. Seller-Service
* **Responsabilidad**: Administra los perfiles públicos de los vendedores del marketplace, su reputación histórica y sus catálogos de ofertas específicas.
* **Lógica Extendida**: Permite que múltiples vendedores compitan con diferentes precios y condiciones para un mismo título del catálogo maestro.

### 5. Order-Service
* **Responsabilidad**: Orquesta el flujo completo de creación de pedidos y compras en la plataforma.
* **Lógica Extendida**: Valida la disponibilidad de stock en tiempo real mediante comunicación interna y realiza el seguimiento exhaustivo del estado del flujo de compra.

### 6. Payment-Service
* **Responsabilidad**: Ejecuta la simulación transaccional de pagos del sistema y se comunica con pasarelas externas.
* **Lógica Extendida**: Valida transacciones financieras y emite eventos de éxito o fallo para actualizar el estado del pedido de forma inmediata.

### 7. Key-Delivery
* **Responsabilidad**: Componente especializado encargado de liberar y despachar de forma segura la Key adquirida al cliente final.
* **Funcionalidades**: Procesa la entrega automatizada a través de visualización directa en el perfil del usuario o mediante simulación de envío por correo electrónico una vez confirmado el pago.

### 8. Review-Service
* **Responsabilidad**: Gestiona el sistema de calificaciones, reseñas y comentarios de la plataforma.
* **Funcionalidades**: Recopila la retroalimentación de los clientes hacia los videojuegos específicos y evalúa el comportamiento transaccional de los vendedores para calcular su reputación.

### 9. Wallet-Service
* **Responsabilidad**: Maneja los saldos virtuales del sistema para los usuarios compradores y las herramientas de liquidación para los vendedores.
* **Lógica Extendida**: Permite recargas de saldo, pagos nativos utilizando fondos de la cartera virtual y distribuye automáticamente las ganancias netas correspondientes a las cuentas de los oferentes tras una venta exitosa.

### 10. Support-Service
* **Responsabilidad**: Módulo de postventa que administra el sistema de tickets de soporte técnico.
* **Funcionalidades**: Permite a los clientes reportar inconvenientes con llaves inválidas o fallos imprevistos en las transacciones de pago, habilitando un canal directo de resolución operativa.

---

## Comunicación e Interacción entre Componentes

Para garantizar la consistencia, el desacoplamiento total y un flujo de información fluido, el ecosistema implementa una estrategia de comunicación híbrida:

1.  **Comunicación Sincrónica (OpenFeign)**: Utilizada para llamadas HTTP directas y ágiles entre microservicios que requieren de una respuesta o validación inmediata. *(Ejemplo: Validar stock disponible en Inventory-Service desde Order-Service antes de avanzar a la pasarela de pago).*
2.  **Comunicación Asincrónica Basada en Eventos (Apache Kafka)**: Empleada para notificaciones masivas, desacoplamiento estructural y garantizar la consistencia eventual de los datos en el ecosistema. *(Ejemplo: Sincronizar proyecciones de datos de ventas hacia Wallet-Service o disparar la liberación de la clave en Key-Delivery tras un pago exitoso sin bloquear hilos de ejecución).*

---

## Persistencia e Integridad de Datos

* **Autonomía de Datos**: Está estrictamente prohibido compartir tablas o esquemas entre microservicios. Cada componente posee su base de datos independiente (motores recomendados: **MySQL**, **PostgreSQL** o **Oracle**).
* **Optimización y Replicación**: Se implementan proyecciones sincronizadas mediante mensajería y proyecciones de JPA para agilizar los accesos a los datos y disminuir la sobrecarga de consultas cruzadas.
* **Modelado Relacional**: El diseño de datos está normalizado y validado mediante diagramas de modelo relacional estructurados.

---

## Seguridad Corporativa

El ecosistema implementa un módulo robusto bajo las directrices de **Spring Security** para mitigar vulnerabilidades y gestionar los accesos de forma limpia:

* **Cifrado**: Contraseñas Hasheadas con el algoritmo hash seguro **BCrypt** antes de guardarse en persistencia.
* **Sesiones Sin Estado**: Login e interacciones validadas mediante **JSON Web Tokens (JJWT)**.
* **Control de Acceso (RBAC)**: Endpoints protegidos mediante anotaciones de Spring Security basadas estrictamente en 3 perfiles funcionales definidos:
    * `Administrador`: Gestión y auditoría global de la plataforma, catálogo maestro y soporte.
    * `Vendedor`: Administración de stock de llaves, fijación de precios y cobro de ganancias.
    *`Cliente`: Exploración de ofertas, compras en línea, uso de la wallet y visualización de llaves.
* **Filtros Personalizados**: Configuración de interceptores a nivel de Gateway y servicios para validar la firma y vigencia de los tokens entrantes.

---

## Estándares de Código y Calidad

* **Validación Estricta**: Uso mandatorio de Data Transfer Objects (**DTOs**) combinados con **Spring Boot Starter Validation** (`@NotNull`, `@Size`, etc.) para sanitizar los datos antes de procesar reglas de negocio.
* **Consultas Eficientes**: Utilización avanzada de **Query Methods** y **Custom Queries** en Spring Data JPA para búsquedas personalizadas.
* **Estructura de URLs**: Rutas estandarizadas bajo las convenciones internacionales de arquitectura RESTful usando de forma correcta la jerarquía de entidades y parámetros:
    `Protocolo://Dominio:Puerto/Ruta/Recurso?Parametros`.

---

## Pruebas Unitarias y Documentación

* **Calidad del Código**: Implementación de pruebas automatizadas con **JUnit 5** y simulación de dependencias aisladas con **Mockito** para certificar el correcto funcionamiento de los servicios backend de manera independiente.
* **Documentación de APIs**: Cada microservicio expone su manual interactivo autogenerado a través de **SpringDoc OpenAPI / Swagger**, permitiendo testear los endpoints directamente desde el navegador de internet.

---

## Despliegue y Ejecución

El proyecto está diseñado para funcionar de manera unificada mediante **Docker y Docker Compose**, permitiendo levantar la infraestructura completa, los motores de bases de datos y el broker de mensajería (Kafka) con un único comando local. 

Adicionalmente, se incluye soporte listo para exposición mediante túneles seguros (**Ngrok**) o despliegues directos orientados a la nube en plataformas Web estables como **Railway, Render o Koyeb** para otorgar una URL pública funcional de acceso global.
