def main(): 

    lista = ["Lucia", "Ivan", "Manzana", "Ordenador"]

    def mas_larga(lista):

        palabra_mas_larga = ""

        for palabra in lista:
            if len(palabra) > len(palabra_mas_larga):
                palabra_mas_larga = palabra
                
        return palabra_mas_larga

    print(mas_larga(lista))

if __name__ == "__main__":
    main()