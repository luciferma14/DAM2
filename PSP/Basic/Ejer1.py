def main(): 

    lista = [1,2,3,4,12,6,7,8,9,10]

    def max_in_list(lista):
        maximo = lista[0]
        for i in lista:
            if i > maximo:
                maximo = i
        return maximo 
    print(max_in_list(lista))

if __name__ == "__main__":
    main()