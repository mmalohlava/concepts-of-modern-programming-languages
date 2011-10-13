def o1 = order pizza to "Malostranske namesti"


println o1

println 'done'


def order(requestedMeal) {
    final newOrder = new Order(meal : requestedMeal)
    newOrder.meals[requestedMeal]
}

class Order {
    String meal
    String address = ''
    final meals = [
        pizza : [to :  {place -> address = place;delegate}]
    ]

    String toString() {
        "*An order of $meal to $address*"
    }
}

def propertyMissing(String name) {name}