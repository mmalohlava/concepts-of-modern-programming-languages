# Introduction #

# Basics #

## Strings ##
```
def value = 'value'
def longText = """
  This is very long text!
  And it uses variable  ${value}
"""

def longText2 = '''
  This is long text does not expanding variable ${value}
'''

// string reverse
def a = "abcd"
println a[-1..0]
```

# Closures #
```
Closure plus1 = {int a, int b -> return a + b }
Closure plus2 = {int a, int b -> a + b }
Closure plus3 = {a, b -> a + b }
def plus4 = {a, b -> a + b }
// implicit parameter "it"
def plus5 = {it + 5}
```

> ## Currying ##
```
def plus = {a, b -> a + b }
def plusWith4 = plus.curry 4
assert 7 == plusWith4(3)
```

# Collections #
  * `size`
  * `any`
  * `every`
  * `collect`
  * `findAll`
  * `inject`
  * `memoize`
  * `memoizeAtMost`

```
assert 26 = ('a'..'z').inject(0) { acc, letter -> acc + 1 }
assert 26 = ('a'..'z').sum()

def final list = (1..300)
final squares = list.findAll { it % 2 == 1}.collect {it**2}.findAll {it < 4567 }
```

# Scripting #

# Dynamic meta-programming #
> ## Categories ##
```
class MyMath {
    static int factorial(Integer value, String nop) {
        value > 1 ? value * factorial(value - 1, nop) : 1
    }
}

// use MyMath as a category
use(MyMath) {
 foo()
}

def foo() {
    println 1.factorial('a')
    println 10.factorial('b')
}

use(org.codehaus.groovy.runtime.TimeCategory) {
  println 1.day.from.now + 4.week - 3.minute
}
```

> ## MetaClass ##
```
final oldTrim = s.metaClass.getMetaMethod('trim')
String.metaClass.trim = {->
 '*' + oldTrim.invoke(delegate) + '*'
}
```

```
Integer.metaClass {
    getEur = {-> delegate + "E" }
  }
}
10.eur
```

# IDE #
The best way how to start with Groovy is to use _Groovy Console_ which is a part of Groovy installation.

# Links #
  * _Groovy Official Homepage_ - http://groovy.codehaus.org
  * _Groovy CZ_ - http://www.groovy.cz/
  * Grails - http://grails.org
  * Online Groovy Console - http://groovyconsole.appspot.com/