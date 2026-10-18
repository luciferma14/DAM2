import subprocess

comando = "ps -eo pid,comm | grep python | sort -n"

resultado = subprocess.run(comando, shell=True, capture_output=True, text=True)

print("PID COMANDO")
print(resultado.stdout)