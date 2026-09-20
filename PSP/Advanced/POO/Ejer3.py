def main():
    
    class String:

        def __init__(self):
            self.texto = ""

        def get_string(self):
            self.texto = input("Escribe una frase: ")

        def print_string(self):
            print(self.texto.upper())

        def rev_string(self):
            palabras = self.texto.split()
            palabras = palabras[1:] + palabras[:1]
            print(" ".join(palabras))


    texto = String()

    texto.get_string()
    texto.print_string()
    texto.rev_string()

if __name__ == "__main__":
    main()