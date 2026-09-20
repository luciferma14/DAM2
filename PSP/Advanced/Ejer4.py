def main():

    palabras = ["casa", "ordenador", "sol", "programacion"]
    
    numero = int(input("Dime un número: "))

    def filter_words(palabras, n):
        resultado = []

        for palabra in palabras:
            if len(palabra) > n:
                resultado.append(palabra)

        return resultado

    resultado = filter_words(palabras, numero)

    print(resultado)
if __name__ == "__main__":
    main()