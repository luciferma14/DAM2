# Prueba objetiva PU1: Programación de procesos

**Nombre y apellidos:** Lucía Ferrandis Martínez
**Evaluación:** RA1  
**Fecha:** 19/10/2026  
**Duración:** 14:10-16:00  
**Puntuación:** 10 puntos  
**Material permitido:** Se pueden consultar los apuntes y ejercicios trabajados en clase. No está permitida la conexión a Internet ni el uso de materiales que no se hayan visto en clase.

## Instrucciones

- Lee todos los enunciados antes de empezar y pregunta las dudas de interpretación.
- Implementa cada ejercicio en un archivo Python independiente.
- Utiliza únicamente los mecanismos de creación y comunicación de procesos indicados en cada enunciado.
- Comprueba que todos los procesos hijos terminan correctamente antes de finalizar el programa principal.
- Entrega los archivos Python junto con este fichero completo indicando tu nombre tanto al inicio del mismo como en el nombre del archivo.

## 1. Suma paralela con dos procesos y una cola — 4 puntos

Desarrolla un programa que calcule la suma de los números del 1 al 10 repartiendo el trabajo entre dos procesos hijos. El proceso principal coordinará el cálculo y reunirá los resultados.

- El primer proceso hijo calculará la suma de los números del **1 al 5**, ambos incluidos.
- El segundo proceso hijo calculará la suma de los números del **6 al 10**, ambos incluidos.
- Cada hijo mostrará su PID, el PID de su proceso padre y la suma parcial calculada.
- Cada hijo enviará al proceso principal su suma parcial mediante una `multiprocessing.Queue`.
- El proceso principal recibirá los dos resultados, mostrará cada suma parcial y calculará y mostrará la suma total.
- El proceso principal esperará a que terminen los dos hijos.

### Posible salida por pantalla

Los PID y el orden en que aparecen los mensajes de los hijos y se reciben los resultados pueden variar.

```text
=== Coordinador de cálculo ===
Se crearán 2 procesos para sumar los números del 1 al 10.
[Proceso 1] PID: 16904 | PID del padre: 4864 | Intervalo: 1-5 | Suma parcial: 15
[Coordinador] Resultado recibido para el intervalo 1-5: 15
[Proceso 2] PID: 11156 | PID del padre: 4864 | Intervalo: 6-10 | Suma parcial: 40
[Coordinador] Resultado recibido para el intervalo 6-10: 40
[Coordinador] Suma total: 55
[Coordinador] Los dos procesos han terminado.
=== Fin del programa ===
```

## 2. Tres hijos y comunicación mediante `Pipe` — 5 puntos

Desarrolla un programa en el que el proceso principal cree **tres procesos hijos**. En cada intento, cada hijo generará un número aleatorio entre **1 y 3**, ambos incluidos, y se lo comunicará al padre mediante un `multiprocessing.Pipe`.

El proceso principal recibirá los tres números y calculará su suma:

- Si la suma es **mayor o igual que 5**, mostrará que está satisfecho y terminará el programa.
- Si la suma es **menor que 5**, solicitará otro intento a los mismos tres hijos. Cada hijo generará y enviará un nuevo número.

Cada hijo debe permanecer activo entre intentos, esperando una petición del proceso principal. Cuando la suma alcance 5 o más, el padre enviará a cada hijo una orden para que termine. El padre esperará la finalización de los tres hijos.

### Posible salida por pantalla

Los números generados y el número de intentos pueden variar en cada ejecución.

```text
=== Inicio del proceso principal ===
Intento 1: números recibidos: 1, 1, 2. Suma: 4
La suma es menor que 5. Se solicita otro intento.
Intento 2: números recibidos: 2, 1, 1. Suma: 4
La suma es menor que 5. Se solicita otro intento.
Intento 3: números recibidos: 3, 2, 1. Suma: 6
La suma es mayor o igual que 5. El proceso principal está satisfecho.
Los tres procesos hijos han terminado.
=== Fin del programa ===
```

## 3. Planificación de procesos con RR y SRTF — 1 punto

Simula la planificación de los seis procesos de la tabla aplicando el método híbrido de **Round Robin (quantum 3)** y **SRTF**.

| Proceso | Tiempo de llegada | Tiempo de ejecución (CPU burst) |
|---|---:|---:|
| P1 | 0 | 6 |
| P2 | 0 | 3 |
| P3 | 2 | 3 |
| P4 | 3 | 6 |
| P5 | 5 | 2 |
| P6 | 5 | 4 |

| Intervalo | 0-1 | 1-2 | 2-3 | 3-4 | 4-5 | 5-6 | 6-7 | 7-8 | 8-9 | 9-10 | 10-11 | 11-12 | 12-13 | 13-14 | 14-15 | 15-16 | 16-17 | 17-18 | 18-19 | 19-20 | 20-21 | 21-22 | 22-23 | 23-24 |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Proceso | P5 | P5 | P2 | P2 | P2 | P3 | P3 | P3 | P6 | P6 | P6 | P6 | P1 | P1 | P1 | P1 | P1 | P1 | P4 | P4 | P4 | P4 | P4 | P4 |