from Person import Person

class Account:

    def __init__(self, holder=None, amount=0):
        self.holder = holder
        self.amount = amount

    def get_holder(self):
        return self.holder

    def set_holder(self, holder):
        if holder != None:
            self.holder = holder

    def get_amount(self):
        return self.amount

    def set_amount(self, amount):
        self.amount = amount

    def show(self):
        print("Titular:", self.holder.get_name())
        print("Cantidad:", self.amount)

    def deposit(self, amount):
        if amount >= 0:
            self.amount = self.amount + amount

    def withdraw(self, amount):
        self.amount = self.amount - amount