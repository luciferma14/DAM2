# DOCUMENTACIÓN DEL EJERCICIO 5: PEDIDO DE COMIDA RÁPIDA

## 1. Introducción

El ejercicio consiste en desarrollar una pantalla para realizar pedidos de comida rápida utilizando Jetpack Compose, el sistema de creación de interfaces de Android basado en componentes.

La aplicación permite introducir los datos del cliente, seleccionar una hamburguesa, elegir el nivel de picante y añadir un extra de queso. También incorpora un resumen del pedido que se actualiza automáticamente y una validación para evitar confirmar pedidos sin completar los datos obligatorios.

## 2. Objetivos

- Crear una interfaz mediante Jetpack Compose.
- Gestionar los datos introducidos por el usuario.
- Utilizar diferentes elementos interactivos.
- Implementar un menú desplegable con diferentes opciones.
- Utilizar un Slider para seleccionar el nivel de picante.
- Mostrar un resumen del pedido en tiempo real.
- Validar los campos obligatorios antes de confirmar el pedido.
- Conservar los datos al girar el dispositivo.

## 3. Funcionamiento del formulario

La pantalla está compuesta por los siguientes elementos:

| Componente | Función |
| --- | --- |
| Nombre | Permite introducir el nombre del cliente. |
| Teléfono | Permite introducir el número de teléfono utilizando el teclado numérico. |
| Hamburguesa | Permite seleccionar una hamburguesa mediante un menú desplegable con cuatro opciones. |
| Nivel de picante | Permite seleccionar un valor entre 0 y 10 mediante un Slider. |
| Extra de queso | Permite activar o desactivar el queso adicional mediante un interruptor. |
| Resumen del pedido | Muestra los datos seleccionados y se actualiza automáticamente. |
| Botón Confirmar pedido | Permite confirmar el pedido cuando el nombre y el teléfono están completos. |

## 4. Explicación técnica

El código utiliza diferentes herramientas de Jetpack Compose:

- `@Composable`: permite crear componentes reutilizables de interfaz.
- `rememberSaveable`: conserva los valores de los campos incluso al girar el dispositivo.
- `mutableStateOf`: permite almacenar y actualizar los datos.
- `TextField`: permite introducir texto.
- `KeyboardType.Phone`: muestra un teclado adecuado para introducir números de teléfono.
- `DropdownMenu`: permite mostrar una lista de opciones desplegable.
- `Slider`: permite seleccionar un valor dentro de un intervalo.
- `Switch`: permite activar o desactivar el extra de queso.
- `Button`: ejecuta la acción de confirmar el pedido.
- `Column` y `Row`: organizan los elementos vertical y horizontalmente.
- `when`: permite asignar diferentes colores según el nivel de picante.

También se utiliza `state hoisting`, una técnica que consiste en mantener el estado en el componente padre y pasarlo a los componentes hijos mediante parámetros y funciones lambda.

## 5. Validación del pedido

Se utiliza la variable `pedidoCompleto` para comprobar que:

- El nombre no esté vacío.
- El teléfono no esté vacío.

El botón Confirmar pedido permanece deshabilitado hasta que se cumplen estos dos requisitos.

Cuando se pulsa, aparece el mensaje:

«¡Pedido confirmado correctamente!»

Si el usuario modifica algún dato después de confirmar el pedido, el mensaje desaparece.

## 6. Estructura de la interfaz

La pantalla sigue esta estructura:

```text
PEDIDO DE COMIDA RÁPIDA
│
├── Nombre
├── Teléfono
├── Hamburguesa
│   ├── Opción 1
│   ├── Opción 2
│   ├── Opción 3
│   └── Opción 4
├── Nivel de picante
│   └── Slider (0 - 10)
├── Extra de queso
├── Resumen del pedido
│   ├── Nombre
│   ├── Teléfono
│   ├── Hamburguesa
│   ├── Picante
│   └── Extra de queso
├── Botón Confirmar pedido
└── Mensaje de confirmación
```

## 7. Resultado esperado

Al ejecutar la aplicación, aparece una pantalla que permite introducir los datos del cliente y configurar el pedido.

El menú desplegable permite elegir entre cuatro hamburguesas y el Slider permite ajustar el nivel de picante entre 0 y 10. El color de la pista cambia según el valor seleccionado: rojo para valores hasta 4, amarillo hasta 7 y verde para valores superiores.

El resumen del pedido se actualiza automáticamente cada vez que se modifica algún dato.

El botón de confirmación permanece deshabilitado mientras el nombre o el teléfono estén vacíos. Una vez completados, se habilita y permite confirmar el pedido.

Soporte gráfico: se recomienda añadir una captura de pantalla del formulario inicial y otra con el pedido completado y su resumen.

## 8. Problemas encontrados y soluciones

Durante el desarrollo de la práctica surgieron algunos problemas que se solucionaron realizando pequeños cambios en el código.

**1. El menú desplegable no se abría correctamente.**

Se revisó el estado `menuAbierto` y se utilizó una variable de tipo `Boolean` para controlar cuándo debía mostrarse el `DropdownMenu`.

**2. El resumen no se actualizaba al modificar los datos.**

Se comprobó que las variables del formulario estuvieran gestionadas mediante `rememberSaveable` y que los componentes hijos recibieran los valores actualizados del componente padre.

**3. El botón de confirmar aparecía habilitado aunque los campos estuvieran vacíos.**

Se añadió la condición `nombre.isNotBlank() && telefono.isNotBlank()` en la propiedad `enabled` del botón para que solamente pudiera pulsarse cuando ambos campos estuvieran completos.

## 9. Conclusión

Con este ejercicio se ha aprendido a crear una pantalla interactiva mediante Jetpack Compose, utilizar diferentes componentes de entrada y gestionar estados compartidos entre varios elementos.

Además, se ha implementado un menú desplegable, un Slider con colores dinámicos y un resumen que se actualiza en tiempo real.

Por último, se ha añadido una validación para evitar confirmar pedidos incompletos y se ha utilizado `rememberSaveable` para conservar los datos al girar el dispositivo.