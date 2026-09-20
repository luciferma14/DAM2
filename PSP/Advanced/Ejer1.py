def main():

    palabras = []

    numero = int(input("Cuántas palabras quieres meter?? "))

    for i in range(numero):
        palabra = input("Escribe una palabra: ")
        palabras.append(palabra)

    print(palabras)

if __name__ == "__main__":
    main()