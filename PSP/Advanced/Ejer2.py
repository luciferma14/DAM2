def main():

    palabras = []

    numero = int(input("Cuántas palabras quieres meter?? "))

    for i in range(numero):
        palabra = input("Escribe una palabra: ")
        palabras.append(palabra)

    buscar = input("Qué palabra quieres buscar?? ")

    veces = palabras.count(buscar)

    print("La palabra aparece", veces, "veces")

if __name__ == "__main__":
    main()