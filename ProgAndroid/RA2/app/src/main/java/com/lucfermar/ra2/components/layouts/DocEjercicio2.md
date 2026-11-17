# DOCUMENTACIÓN DEL EJERCICIO 2: FORMULARIO DE REGISTRO

## 1. Introducción

El ejercicio consiste en desarrollar un formulario de registro utilizando Jetpack Compose, el sistema de creación de interfaces de Android basado en componentes.

El formulario permite introducir datos personales, seleccionar el género, escribir una contraseña y aceptar las condiciones de uso. También incorpora una validación para evitar registros incompletos.

## 2. Objetivos

- Crear una interfaz mediante Jetpack Compose.
- Gestionar los datos introducidos por el usuario.
- Utilizar diferentes elementos de entrada.
- Validar que todos los campos estén completos.
- Mostrar un mensaje cuando el registro se realice correctamente.

## 3. Funcionamiento del formulario

El formulario está compuesto por los siguientes elementos:

| Componente | Función |
| --- | --- |
| Nombre | Permite introducir el nombre del usuario. |
| Apellidos | Permite introducir los apellidos. |
| Género | Permite seleccionar masculino o femenino. |
| Contraseña | Permite introducir una contraseña y mostrarla u ocultarla. |
| Condiciones de uso | Permite aceptar las condiciones mediante un interruptor. |
| Botón Registrarse | Permite completar el registro. |

## 4. Explicación técnica

El código utiliza diferentes herramientas de Jetpack Compose:

- @Composable: permite crear componentes reutilizables de interfaz.
- rememberSaveable: conserva los valores de los campos.
- mutableStateOf: permite almacenar y actualizar los datos.
- TextField: permite introducir texto.
- RadioButton: permite seleccionar una opción.
- Switch: permite aceptar o rechazar las condiciones.
- Button: ejecuta la acción de registro.
- Column y Row: organizan los elementos vertical y horizontalmente.

También se utiliza `PasswordVisualTransformation()` para ocultar la contraseña y `VisualTransformation.None` para mostrarla.

## 5. Validación del formulario

Se utiliza la variable `formularioCompleto` para comprobar que:

- El nombre no esté vacío.
- Los apellidos no estén vacíos.
- Se haya seleccionado un género.
- La contraseña no esté vacía.
- Se hayan aceptado las condiciones de uso.

El botón Registrarse permanece deshabilitado hasta que se cumplen todos estos requisitos.

Cuando se pulsa, aparece el mensaje:

«¡Registro completado correctamente\!»

Si el usuario modifica algún dato después del registro, el mensaje desaparece.

## 6. Estructura de la interfaz

El formulario sigue esta estructura:

text

```text
FORMULARIO DE REGISTRO
│
├── Nombre
├── Apellidos
├── Género
│   ├── Masculino
│   └── Femenino
├── Contraseña
│   └── Mostrar / Ocultar
├── Aceptar condiciones de uso
├── Botón Registrarse
└── Mensaje de confirmación
```

## 7. Resultado esperado

Al ejecutar la aplicación, aparece un formulario vertical y desplazable que permite completar todos los campos.

El botón aparece en verde cuando está habilitado y en gris cuando está deshabilitado. Una vez completados los datos y aceptadas las condiciones, se puede realizar el registro y visualizar el mensaje de confirmación.

Soporte gráfico: se recomienda añadir una captura de pantalla del formulario vacío y otra con el registro completado.

## 8. Conclusión

Con este ejercicio se ha aprendido a crear formularios mediante Jetpack Compose, utilizar componentes interactivos y gestionar estados en Kotlin.

Además, se ha implementado una validación básica que mejora la experiencia del usuario y evita realizar registros incompletos.