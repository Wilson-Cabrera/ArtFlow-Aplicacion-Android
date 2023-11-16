### :rocket:ArtFlow-Aplicación-Android:rocket:
Desarrollo de Aplicación para Android -  Proyecto para la catedra Seminario de actualización 1, de la carrera Lic. en Diseño y Producción Multimedia. (Dictada por Patricio Pedersoli)

[![artflow-portada.png](https://i.postimg.cc/RhFvfM13/artflow-portada.png)](https://postimg.cc/sQkFCCNy)


### Indice

- [Introducción](#Introducción)
- [Objetivos Específicos de la Aplicación](#Objetivos-Específicos-de-la-Aplicación)
- [Ejemplo de uso](#Ejemplo-de-uso)
- [Estructura aplicación (MAP) para ArtFlow](#Estructura-aplicación-(MAP)-para-ArtFlow)
- [Arquitectura del Sistema](#Arquitectura-del-Sistema)
- [Historias de usuario](#Historias-de-usuario)
- [Enlaces de interes](#Enlaces-de-interes)
- [Responsables](#Responsables)
- [Autores](#Autores)
  


------------
### Introducción
“ArtFlow” es una aplicación para dispositivos móviles que se trata de agrupar distintos campos de la multimedia con la comunidad a través de la interfaz donde se le dará una gran visibilidad a trabajos, siendo una gran fuente potenciadora de inspiración a los usuarios interesados en desenvolverse Artflow. Gracias a su estructura dinámica, los usuarios podrán categorizar sus trabajos y subir en diversos recursos a la plataforma como fotografía, animaciones, documentales, Foley, podcast, cortometraje, desarrollo de personajes, diseños de todo tipo, entre otros. La particularidad de esta red social, es que el usuario podrá compartir todo sus trabajos sin límites, con la ventaja que podrá potenciarlo con sus pares, siendo para el diseñador Multimedial amateur un gran potenciador de conocimientos, recursos y grandes herramientas para sus propias creaciones.

------------
### Objetivos Específicos de la Aplicación

La aplicación tiene como objetivo conectar a diseñadores multimedia de todo el mundo, proporcionándoles un espacio dedicado para compartir inspiraciones a través de reels creativos. Busca fomentar la colaboración, la inspiración mutua y la promoción del talento creativo en la comunidad de diseño multimedia.
ArtFlow es una red social diseñada exclusivamente para la comunidad del diseño multimedia. La aplicación ofrece un lugar dinámico y vibrante donde los diseñadores pueden compartir sus últimas creaciones, ideas innovadoras y procesos creativos a través de reels cortos y visualmente impactantes.

**Características Principales:**
> Contenido creativo: Los usuarios pueden crear publicar imagenes y reeles breves que muestran su trabajo, procesos creativos, y piezas de inspiración. La función de reels permite una presentación visual y atractiva de proyectos multimedia.

> Facilidad de Registro y autenticación: Brindarles a los diferentes usuarios un proceso de registro sencillo y seguro, para poder acceder a sus cuentas con un nombre de usuario o correo electrónico y una contraseña.

> Conectando Creatividad Global: ofrecer a los usuarios una red social insipirativa de diseñadores multimediales a otros diseñadores multimedia de distintos lugares del mundo, logrando integrar diversos conocimientos, técnicas, ideas y proyectos.

> Adaptabilidad y Eficiencia: Ofrecer una interfaz fácilmente adaptable para cualquier usuario, siendo una red social donde tendrá diversos atajos que permitirse optimizar el tiempo del
usuario.

------------

## Ejemplo de uso
Imagina a un diseñador de animación que ha trabajado en un proyecto innovador. Utiliza ArtFlow para compartir un reel que destaca los momentos clave del proceso de animación, desde bocetos iniciales hasta el producto final. Otros diseñadores multimedia pueden descubrir este reel, dejar comentarios elogiosos y tal vez expresar interés en colaboraciones futuras. La aplicación se convierte en un lugar dinámico donde la comunidad de diseñadores se conecta, comparte ideas y se inspira mutuamente.

------------
## Estructura aplicación (MAP) para ArtFlow

<a href='https://postimg.cc/m1FNTnd4' target='_blank'><img src='https://i.postimg.cc/brm69ckZ/imagen-2023-11-16-132147246.png' border='0' alt='imagen-2023-11-16-132147246'/></a>

-----------

## Arquitectura del Sistema

1 Cliente (Frontend):
- plataforma: Desarrollo Android Studio
- Framework de IU: A definir

2 Servidor (Backend):
- Lenguaje de Programación: Java
- Base de datos: SQLite

3 Autenticación y Autorización:

4 Almacenamiento en la Nube:

5 Seguridad:



**Aviso:**

Esta arquitectura proporciona una visión particular en terminos de los conocimientos basicos presentes en el equipo y se puede ajustar según las necesidades específicas de la aplicación y las competencias adquiridas durante el desarrollo. Es importante considerar que la arquitectura del sistema irá creciendo a medida de los avances. La vision general de la misma y el sistema completo se irá implementando durante el diseño del sistema o mas bien al finalizar el proyecto, debido a lo mencionado anteriormente.

## Historias de usuario
Historia de usuario  | --
------------- | -------------
ID:  | 1
Nombre de la historia  |  Inicio de Sesión en la Aplicación
Programador respondable | Flores, Kiru
Descripción | Como usuario de aplicación, quiero tener la capacidad de iniciar sesión de manera segura y conveniente para acceder a mis datos y personalizar mi experiencia.
Validación | El usuario se asegura de que los datos ingresados como los nombres, dirección de correo electrónico o contraseñas sean correctos y cumplan con ciertos estándares.
Criterios | El usuario tendrá dos campos para llenar, el primero con su Email y el segundo 2 con su contraseña, después tendrá que apretar el botón de iniciar sesión para ingresar. O en su defecto si el usuario no cuenta con una cuenta tendrá un enlace que le llevara a una sección para poder registrarse.


Historia de usuario  | --
------------- | -------------
ID:  | 2
Nombre de la historia  |  Registro de Usuario
Programador respondable | Miotto, Lautaro
Descripción | Como usuario diseñador multimedia responsable quiero registrarme con mi Email, nombre, nombre de usuario y contraseña.
Validación | El usuario responsable debe generar una cuenta por lo que deberá poner los datos requeridos por el sistema.
Criterios | El usuario deberá llenar cuatro campos de texto, E.mail, nombre completo, nombre de usuario y contraseña. En segundo lugar deberá hacer click en el botón (Registrate). O como usuario de Google o Facebook podrás registrarte con la cuenta que tengas existentes.


Historia de usuario  | --
------------- | -------------
ID:  | 3
Nombre de la historia  |  Visualización de la feed tendencias
Programador respondable | Cabrera, Wilson
Descripción | Como usuario responsable, quiero ser capaz de poder ver las publicaciones que están en tendencias para que pueda ver las inspiraciones de la comunidad.
Validación | El usuario responsable al acceder mediante la validación debe explorar las publicaciones.
Criterios | La feed muestra las publicaciones más recientes y populares. Se le permite al usuario hacer click en cada post para acceder a otra sección de la publicación donde podrá ver el posteo y tener otras opciones de interacción. Además tendrá un menú en la parte inferior con funcionalidades como refrescar, guardar, crear y perfil. En la parte superior tendrá un buscador en la cual puede escribir palabras específicas para una búsqueda personalizada.



Historia de usuario  | --
------------- | -------------
ID:  | 4
Nombre de la historia  |  Visualización de video  Reel con interacción.
Programador respondable | Waidatt, Samira.
Descripción | Como usuario de la aplicación, quiero poder acceder a una sección que muestre un video Reel. Y tambien ser capaz de interactuar con el contenido.
Validación | El usuario responsable debe visualizar los videos reel, nombre de usuario y descripción en pantalla completa e interactuar con dicho video. También debe abrir la galería si desea subir un reel haciendo click en el icono "+".
Criterios |Esta sección presentará contenido multimedia de manera clara y atractiva. El usuario puede reproducir el video reel y manejarlo mediante un control de reproducción estándar. Para acceder a los comentarios debe pulsar en un icono de nube, para guardar el video pulsar en un icono de bandera y para dar like a un icono de corazón. En la parte superior tendrá un menú donde podrá "Ir a la sección tendencias, guardados, crear y perfil"

------------

## Enlaces de interes

- Mockups: [Figma](https://www.figma.com/file/Mk79vOIMddFzCAAQutru4Z/Mockup-ArtFlow?type=design&t=I7Lh8mMQmfV63vsz-6)
- Proceso del Mockup: [Reel](https://www.instagram.com/p/CzpA0JVuP3U/)
- (MAP): [MIRO](https://miro.com/app/board/uXjVNWA5kUA=/?share_link_id=327497059956)

  ## Responsables
- Análisis General: Cabrera Wilson Antonio 
- UX/UI: Cabrera Wilson Antonio
- FrontEnd: Miotto González Lautaro, 
- Backend: Waidatt Samira , Flores Kiru Brian Juan

  ## Autores
- [@Wilson-Cabrera](https://github.com/Wilson-Cabrera)
- Miotto Lautaro Gonzalez
- Flores Juan Kiru
- Waidatt Samira
