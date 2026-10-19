#!/bin/bash
# Ejercicio 1. Pipeline en Linux: lista los procesos Python ordenados por PID.
#
# ps -eo pid,cmd  -> lista todos los procesos mostrando PID y comando
# grep [p]ython   -> se queda solo con las líneas que contienen "python"
#                    (los corchetes evitan que el propio grep aparezca en el resultado)
# sort -n         -> ordena numéricamente por la primera columna (el PID)
# El símbolo | conecta la salida de un comando con la entrada del siguiente

ps -eo pid,cmd | grep [p]ython | sort -n
