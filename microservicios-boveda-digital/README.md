# Digital Vault

## Introducción

Digital Vault es una API que funciona como boveda digital en donde a través de la
creación de un usuario puede realizar la encriptación y almacenado de documentos
encriptados.

### Instalación

Para poder instalar es necesario (si es posible) la creación de un usuario y base de datos
dentro del sistema que requieres utilizar, esto no es exclusivo para que funcione la Digital Vault
si lo requieres puedes utilizar la misma base de datos en PostgreSQL, solo te creará dos tablas.

```
CREATE USER digital_vault_user WITH PASSWORD 'digital_vault_user';
CREATE DATABASE digital_vault OWNER digital_vault_user;
```

Los documentos se almacenan en el directorio por default "/opt/dv" por ello es 
necesario crearlo de la siguiente manera: 

```
$ mkdir /opt/dv
$ chown ${USER} /opt/dv -R
```

Si requerimos modificar los diferentes directorios donde se almacenen los ficheros
podemos cambiarlo dentro del fichero application.properties modificando los siguientes 
parametros. 

```
dv.tmp=/opt/dv/tmp/
dv.home=/opt/dv/encrypt/
```

## Como se usa

La boveda digital tiene los siguientes endpoints: 

| EndPoint      |      Descripción      |  Parámetros | Login |
|---------------|:---------------------:|------------:|------------:|
| /             | Es la raiz de los endpoints solo responderá OK  | Ninguno | No aplica |
| /loginTest    | Se usa para verificar si ya iniciaste sesión  | Ninguno | No aplica |
| /login        | Realiza el login correspondiente |  username: String, password: String  | No aplica |
| /addUser      | Hace el registro de un nuevo usuario, automáticamente le crea una llave privada |  username: String, password: String, rolId: Int  | Necesario |
| /addFile      | Registra un fichero dentro de la bovedad basada en el id de un usuario, el metadato se manda en un String en formato Json       |    userId: Integer, metadata: JsonString, file: File | Necesario |
| /files      | Lista los ficheros de un usuario       |    userId: Integer | Necesario |
| /decryptFile      | Descargar un fichero       |    userId: Integer, fileId: Integer | Necesario |

### Roles dentro de la Boveda
| roleId        |      Role             |  Descripción |
|---------------|:---------------------:|-------------:|
| 1 | ADMIN     | Administrador del Sistema, puede dar de alta usuarios. |
| 2 | ALMACEN   | Tiene la capacidad de desencriptar cualqueir archivo.  |
| 2 | CONSULTA  | Usuario que encripta y desencripta solo sus archivos.  |

## Instalar Digital Vault

Para la instalación del boveda digital, es necesario correr la tarea de mvn de instalación.
```
mvn install
```

Este nos generará el el jar dentro de target, el cual podremos ejecutar de la siguiente manera: 
```
java -jar ./target/digital-vault-0.0.1-SNAPSHOT.jar
```


