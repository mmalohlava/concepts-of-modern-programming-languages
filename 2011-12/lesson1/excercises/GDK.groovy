abstract class Person {
    String name
}

class Man extends Person {
    public List marry(Woman partner) {
        [partner, this]
    }
}

class Woman extends Person {
    public List marry(Man partner) {
        [this, partner]
    }
}

final joe = new Man(name: 'Joe')
final dave = new Man(name: 'Dave')
final alice = new Woman(name: 'Alice')
final susan = new Woman(name: 'Susan')

assert [alice, joe] == joe.marry(alice)
assert [susan, dave] == susan.marry(dave)

//assert [joe, dave] == joe.marry(dave)

println 'done'