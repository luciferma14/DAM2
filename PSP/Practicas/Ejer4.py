def main(): 
    par = int(input("Dime un número par: "))
    
    if par % 2 != 0:
        print("No es correcto")
    else:
        impar = int(input("Dime un número impar: "))

        if impar % 2 == 0:
            print("No es correcto")
        else:
            print("Son correctos")
    
if __name__ == "__main__":
    main()