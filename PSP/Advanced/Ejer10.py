def main():

    numero = int(input("¿Cuántos elementos tendrá el menú? "))
    caracter = input("Introduce el carácter del menú: ")

    for i in range(numero):
        texto = input("Introduce el texto del elemento: ")
        print(caracter, texto)

    salir = input("Introduce el carácter para salir: ")

    print("Para salir del menú escribe:", salir)

if __name__ == "__main__":
    main()