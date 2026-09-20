def main():

    palabras = ["casa", "ordenador", "sol", "programacion"]

    def longer_word(palabras):
        mayor = palabras[0]

        for palabra in palabras:
            if len(palabra) > len(mayor):
                mayor = palabra

        return mayor

    resultado = longer_word(palabras)

    print("La palabra más larga es:", resultado)

if __name__ == "__main__":
    main()