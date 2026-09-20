def main():

    def es_bisiesto(año):
    
        if (año % 4 == 0 and año % 100 != 0) or (año % 400 == 0):
            return True
        else:
            return False
        
    año_usuario = int(input("Dime un año: "))
    
    if es_bisiesto(año_usuario):
        print("El año", año_usuario, "sí es bisiesto.")
    else:
        print("El año", año_usuario, "no es bisiesto.")

if __name__ == "__main__":
    main()