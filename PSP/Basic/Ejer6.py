def main():
    año = int(input("En que año estamos?? "))

    for i in range(3):
        print("Persona", i + 1)

        nombre = input("Nombre: ")
        año_nacimiento = int(input("Año nacimiento: "))

        edad = año - año_nacimiento

        print(nombre, "cumplirá", edad, "años en el año", año)

if __name__ == "__main__":
    main()