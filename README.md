# gallery
Aplicacion realizada con Spring Boot. Permite la creacion de carpetas de un usuario y subir fotos en cada una de ellas. El host de las fotos se encuentra integrado con la api de cloudinary. La aplicacion tambien posee un frontEnd basic.

# funcionalidades
 - Creacion de usuarios.
 - Creacion y eliminacion de carpetas
 - Subida de fotos.
 - Eliminar fots.
 - Obtener las fotos de una carpeta (url del host).
 
 
# security
 - Se utilizo jwtoken para crear las sesiones de usuario.
 - Posee autenticacion y autorizacion del usuario.
 - Por default se crea un usuario con username= "admin" y password = "admin".


# swagger
La aplicacion se encuentra integada con swagger lo que permite utilizar y ver los endpoint de la restApi accediendo a: http://localhost:8080/swagger-ui/index.html

# ejecucion
Una vez ejecutada la aplicacion, el acceso se realiza a travez de: http://localhost:8080
La base de datos corre sobre el puerto 3306 con el nombre de gallery.

# docker
Se encuentra docker integrado en la aplicacion.
Con el comando "docker compose up" se inicializa autimaticamente la aplicacion.
Tener en cuenta que se puede requerir permisos de superusuario dependiendo la configuracion del sistema operativo donde esta corriendo.
Deben estar libres los siguientes puertos:
 - 8080: puerto donde corre la aplicacion
 - 3306: puerto donde corre la base de datos
Los puertos pueden ser modificados en el archivo "docker-compose.yml"

