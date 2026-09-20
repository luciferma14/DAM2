def main(): 

    lista = ["Lucia", "Ivan", "Manzana", "Ordenador", "Uno", "Muñeco"]
    num = 5

    def filtrar_palabras(lista, num):

        palabras = []

        for palabra in lista:
            if len(palabra) > num:
                palabras.append(palabra)
                
        return palabras

    print(filtrar_palabras(lista, num))

if __name__ == "__main__":
    main()