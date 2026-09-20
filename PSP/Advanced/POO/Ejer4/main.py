from Person import Person
from Account import Account
from YouthAccount import YouthAccount

def main():

    persona = Person("Lucia", 21, "12345678A")

    print("PERSONA")
    persona.show()

    print("Es mayor de edad??", persona.isAdult())

    cuenta = Account(persona, 500)

    print("\nCUENTA")
    cuenta.show()

    cuenta.deposit(100)
    cuenta.show()

    cuenta.withdraw(50)
    cuenta.show()

    cuenta_joven = YouthAccount(persona, 500, 2)

    print("\nCUENTA JOVEN")
    cuenta_joven.show()

    print("El titular es válido??", cuenta_joven.isValidHolder())

    cuenta_joven.deposit(100)
    cuenta_joven.withdraw(50)

    cuenta_joven.show()


if __name__ == "__main__":
    main()