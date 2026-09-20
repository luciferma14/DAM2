from Account import Account

class YouthAccount(Account):

    def __init__(self, holder=None, amount=0, bonus=0):
        super().__init__(holder, amount)
        self.bonus = bonus

    def get_bonus(self):
        return self.bonus

    def set_bonus(self, bonus):
        if bonus >= 0:
            self.bonus = bonus

    def isValidHolder(self):
        if self.holder.get_age() >= 18 and self.holder.get_age() < 25:
            return True
        else:
            return False

    def withdraw(self, amount):
        if self.isValidHolder():
            self.amount = self.amount - amount

    def show(self):
        print("Youth Account")
        print("Titular:", self.holder.get_name())
        print("Cantidad:", self.amount)
        print("Bonus:", self.bonus, "%")