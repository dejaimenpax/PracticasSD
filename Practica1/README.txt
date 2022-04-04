# PracticasSD

Gestión de Estación, Bicicletas y Usuarios:

Listar Usuarios
Dar de alta usuario
Editar/modificar detalles usuario
Dar de baja un usuario
Listar Estaciones
Detalle de una estación concreta : Borrar estación | Modificar coordenadas de una estación                             
Búsqueda de estaciones
Dar de alta una nueva estación
Listar bicicletas de una estación
Modificar ciclo de vida bicicleta de forma manual
Listado bicicletas por número de serie
Listado bicicletas por estado
Detallar los datos de una bicicleta
Poder poner una bicicleta sin estación en una estación concreta
Dar de baja una bicicleta
Dar de alta una bicicleta



Crear Clases:
    Cuando creeis una clase que sea un objeto, guardarla dentro de model y ponerle al principio la
    anotacion @Entity. Estas clases deberan tener un constructor vacio para la base de datos.


Sobre idea para las bases:
    Opción 1 (sin herencia):
        - Clase "Bicicleta" tal cual la tenemos. El estado es un enum tal cual lo tenemos.
        - Método "asignarBase(Estacion es)" que lance una excepcion si enum!=sin_base.
        - Sencilla e intuitiva

    Opción 2 (con herencia):
        - Creamos una clase padre, "Bicicleta". Idealmente y por definición, esta clase debiera ser abstracta, pero no lo haremos así
        por simplicidad.
        - En "Bicicleta" ponemos los atributes y métodos comunes, como el de dar los detalles de una bicicleta
        - Nos bastaría con crear 3 clases hijas (Sin base, en base y reservada), pero haremos 4 incluyendo "baja".
        - Hay un método en "Bicicleta", "cambiarEstado(nuevoEstado)", que reimplementan de una manera u otra cada hija. Así, si
        el estado que recibe como argumento es "baja" la operacion es legal siempre; pero si una bicicleta "sin base" trata de cambiar al
        estado "reservada" no podrá hacerlo porque en la implementacion de "cambiarEstado" de la clase "BicicletaSinBase", al recibir el estado
        "reservada" como argumento lanza una excepción.
        - Mucho más verbosa, pero puede que nos sea útil para la siguiente práctica. Podemos dejar esta idea descartada hasta que lo necesitemos
        o tengamos problemas, ya que no es dificil la implementación.
