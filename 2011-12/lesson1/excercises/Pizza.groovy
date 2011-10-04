class Pizza {

    def attributes = [:]

    enum Size {
        small(0.75), medium(1), large(1.25)
        public double factor

        Size(double factor) { this.factor = factor }
    }
    enum Ingredients {
        cheese, onions, olives, mushrooms, tomato, ham
    }

    Object getProperty(String name) {
        def meta = Pizza.metaClass.getMetaProperty(name)
        if (meta) {
            meta.getProperty(this)
        }
        else {
            def val = null
            [Size, Ingredients].any {
                try {
// Works in my local console, but not in the web console:
                    //            val = it.valueOf( it, name )
                    //           } catch (IllegalArgumentException) {}
                    // Web console Workaround: use an instance and apply getProperty
                    val = it.MIN_VALUE.getProperty(name)
                } catch (IllegalArgumentException) {}
            }
            return val
        }
    }



    static order(Closure c) {
        def clone = c.clone()
        def pizza = new Pizza()
        clone.delegate = pizza
        clone.resolveStrategy = Closure.DELEGATE_ONLY
        clone.call()
        pizza
    }

    def to(String location) {
        attributes.to = location
    }

    def with(Ingredients[] i) {
        attributes.with = i
    }

    def size(Size s) {
        attributes.size = s
    }

    def getPrice() {
        String.format "%.2f",
                ((10 + 2.5 * attributes.with.length) * attributes.size.factor * 20 + 0.5) / 20
    }

    def getTime() {
        switch (attributes.to) {
            case ~/.*Branik.*/: return 20
            case ~/.*JetBrains.*/: return 10
            default: return 60
        }
    }

    String toString() {
        "Pizza for CZK ${price} will arrive in ${time} minutes. You wish: ${attributes}"
    }

}
  
  