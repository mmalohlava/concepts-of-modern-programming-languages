class Money {
    Integer amount
    String currency

    Money plus(Money other) {
        if (this.currency != other.currency) throw new IllegalArgumentException('Cannot add different currencies');
        new Money(amount: this.amount + other.amount, currency: this.currency)
    }

    public String toString() {
        "${amount} ${currency}"
    }
}

Integer.metaClass {
    getEur = {->
        new Money(amount: delegate, currency: 'EUR')
    }
    getUsd = {->
        new Money(amount: delegate, currency: 'USD')
    }
    getCzk = {->
        new Money(amount: delegate, currency: 'CZK')
    }
}

println 10.eur
println 10.eur + 20.eur
println 10.usd + 20.usd

