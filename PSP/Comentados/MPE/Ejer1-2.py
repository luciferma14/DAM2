# Ejercicio 1 - Pipeline en Linux: listar los procesos de Python ordenados por PID
# (solo funciona en Linux/macOS)
#
# Un pipeline es juntar varios comandos con el símbolo |
# La salida de un comando pasa a ser la entrada del siguiente:
#
#   ps -eo pid,comm     ->  lista todos los procesos (PID y nombre)
#   grep python         ->  se queda solo con las líneas que contienen "python"
#   sort -n             ->  las ordena de menor a mayor PID
#
# Comando completo (se puede escribir directamente en la terminal de Linux):
#
#   ps -eo pid,comm | grep python | sort -n
import subprocess

comando = "ps -eo pid,comm | grep python | sort -n"

# shell=True hace que el sistema entienda el símbolo |
resultado = subprocess.run(comando, shell=True, capture_output=True, text=True)

print("PID COMANDO")
print(resultado.stdout)