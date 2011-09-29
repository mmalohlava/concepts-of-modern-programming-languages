def calculate(String value) {
    0
}

def calculate(Integer value) {
    value * 10
}

def a = '10'
println 'First attempt: ' + calculate(a)
println 'Second attempt: ' + calculate(a as Integer)

//Static type-cast will not work
//println calculate((Integer)a)

a = 10
println 'Third attempt: ' + calculate(a)