# Proyecto-Spring-Boot

## FUNCIONALIDADES AGREGADAS EN LA ENTREGA 2

### Api publica la cual da una repuesta en json donde se pueden acceder a los inventarios del proyecto
- La api en cuestion o la url para acceder a la misma es : /items/api/stock

### Se soluciono el problema del login, el cual mandaba a una pagina de error cuando se trataba de iniciar sesion
-Bug menor
### Se implemento una api externa con la cual se pueden observar frases chistosas de chuck norris
- La api en cuestion es gratis y se puede acceder de la siguiente manera: https://api.chucknorris.io/jokes/random

### Ademas se implemento la pagina en 2 idiomas ingles y español
- Para poder Observar estos cambios se necesita hacerlo de forma manual, en la barra de busqueda se tiene que poner lo siguiente ?lang=es o ?lang=en, esto actualizara
unas cookies que haran que la pagina cambie de idioma

### Se implemento una forma de descarga de reportes en pdf
- No solo un pdf tambien es posible simular la funcionalidad de descarga de un excel pero este es solo desde consola, la forma de descargar el reporte en pdf es desde el nadvar despues de iniciar sesion

### Se agrego mensaje de error en login
- Cuando se hace un login hacia un usuario con las credenciales malas, esto se te avisa

### Nuevo despliegue en aws
- El despliegue se hace mediante 2 contenedores, uno para la base de datos en postgress y otro para la aplicacion en si.
- Se hace en una instancia EC2 de tamaño medio para que no haya problemas de rendimiento
- Adquirimos un dominio con el cual la aplicacion puede ser identificada mas facilmente
