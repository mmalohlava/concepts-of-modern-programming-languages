import groovyx.gpars.agent.Agent

interface Cart {
    void addIfNotPresent(String product)

    List getContents()
}

//TODO Correct the implementation using Agents so that it is thread-safe and correct in concurrent execution

class CartImpl implements Cart {
    private contents = []

    void addIfNotPresent(String product) {
        if (!contents.contains(product)) {
            sleep 10 //simulate calculation here
            contents << product
        }
    }

    List getContents() {
        contents.asImmutable()
    }
}

Cart cart = new CartImpl()
50.times {
    Thread.start {
        cart.addIfNotPresent("Groovy")
    }
}
sleep 2000
assert cart.contents.size() == 1