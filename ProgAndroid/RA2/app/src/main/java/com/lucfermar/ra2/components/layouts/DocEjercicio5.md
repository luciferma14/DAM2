# DOCUMENTACIÓN – PRÁCTICA 5: PEDIDO DE RESTAURANTE

## 1. Introducción

El ejercicio consiste en desarrollar una pantalla de pedidos para un restaurante utilizando **Jetpack Compose** y Kotlin.

La aplicación permite al cliente introducir sus datos, elegir una hamburguesa, seleccionar el nivel de picante y decidir si quiere añadir queso extra.

## 2. Objetivos

- Crear una interfaz mediante Jetpack Compose.
- Utilizar diferentes componentes interactivos.
- Gestionar los datos introducidos por el usuario.
- Mostrar un resumen del pedido actualizado.
- Adaptar la pantalla a diferentes orientaciones.
- Validar los datos antes de confirmar el pedido.

## 3. Funcionamiento de la aplicación

La pantalla contiene los siguientes elementos:

| Componente | Función |
|---|---|
| Nombre | Permite introducir el nombre del cliente. |
| Teléfono | Permite introducir su número de teléfono. |
| Menú desplegable | Permite elegir una hamburguesa. |
| RangeSlider | Permite seleccionar un rango de picante entre 0 y 10. |
| Switch | Permite indicar si se desea queso extra. |
| Resumen | Muestra los datos del pedido seleccionado. |
| Botón Confirmar pedido | Permite confirmar el pedido. |

## 4. Explicación técnica

Para desarrollar la aplicación se utilizan los siguientes componentes:

- **@Composable:** permite crear funciones reutilizables de interfaz.
- **rememberSaveable:** conserva los valores introducidos al cambiar la orientación.
- **mutableStateOf:** permite almacenar y actualizar los datos.
- **TextField:** permite introducir el nombre y el teléfono.
- **DropdownMenu:** muestra las opciones de hamburguesas.
- **RangeSlider:** permite seleccionar el rango de picante.
- **Switch:** permite activar o desactivar el queso extra.
- **Card:** organiza visualmente el resumen del pedido.
- **Button:** permite confirmar el pedido.
- **Column:** organiza los componentes verticalmente.

También se utiliza `verticalScroll(rememberScrollState())` para permitir desplazarse por la pantalla cuando el dispositivo está en horizontal.

## 5. Cambio de color del picante

El color de la pista del RangeSlider cambia según el nivel medio del rango seleccionado:

- **Rojo:** nivel inferior a 5.
- **Amarillo:** nivel entre 5 y 7.
- **Verde:** nivel superior a 7.

De esta forma, el usuario puede identificar visualmente la intensidad del picante.

## 6. Resumen del pedido

Debajo de los componentes aparece un resumen que se actualiza automáticamente cuando se modifican los datos.

Muestra:

- Nombre del cliente.
- Teléfono.
- Hamburguesa seleccionada.
- Rango de picante.
- Elección de queso extra.

Si algún dato todavía no se ha introducido, se muestra un texto indicando que no está seleccionado.

## 7. Validación del pedido

El botón **Confirmar pedido** solamente se habilita cuando los campos de nombre y teléfono están completos.

Cuando se pulsa, aparece el mensaje:

**«¡Pedido confirmado correctamente!»**

Si se modifica algún dato después de confirmar, el mensaje desaparece.

## 8. Adaptación de pantalla

Se utiliza `verticalScroll()` para permitir el desplazamiento vertical cuando se gira el dispositivo y la pantalla tiene menos altura.

Además, `rememberSaveable` conserva los valores introducidos durante los cambios de orientación.

## 9. Resultado esperado

Al ejecutar la aplicación, aparece una pantalla donde el usuario puede configurar su hamburguesa y consultar el resumen del pedido antes de confirmarlo.

La interfaz es interactiva, permite modificar las opciones y se adapta a la orientación del dispositivo.

**Soporte gráfico:** se recomienda incluir una captura de pantalla del formulario completo y otra del dispositivo en orientación horizontal.

## 10. Conclusión

Con esta práctica se ha aprendido a utilizar componentes interactivos de Jetpack Compose, gestionar estados y crear interfaces adaptables.

También se ha trabajado con RangeSlider, menús desplegables y Switch, además de implementar un resumen dinámico y una validación básica del pedido.