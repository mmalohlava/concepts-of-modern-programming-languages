number = 3

while number < 12
    number = number + 2
    console.log 'Ahoj:', number

while number < 24 then number += 2

console.log number

for number in [2...10] 
    console.log 'X=', number

console.log number for number in [2..20] by 3
###
prompt '''What's your number?''', 
    (answer) -> console.log 'ahoj', number
###
numbers = (number for number in [0..12] by 2)
console.log numbers

class Rabbit
    constructor: (@adjective = 'rabbitkitty') ->
    speak: (line) -> console.log "Hi there #{@adjective} say #{line}"

blackRabbit = new Rabbit('hello kitty');
blackRabbit.speak();

X = (adj) ->
    this.adj = adj

X.prototype.speak = () -> console.log @adj
x = new X('a')
x.speak()

class BigRabbit extends Rabbit 
    constructor: (@weight) ->
        super 'fat rabbit'
    - getWeight: () -> console.log @weight

bigRabbit = new BigRabbit
bigRabbit.speak('a')
try
    bigRabbit.getWeight()
catch error then console.log "Calling private method causes the error #{error}"

class BigBigRabbit extends BigRabbit
    constructor: (@weight) ->
        super @weight
    jump: =>
        console.log "Jump with #{@weight} kilos!"
    jump2: ->
        console.log "Jump with #{@weight} kilos!"

class Elephant
    weight: 30
    show: (fn) ->
        console.log "Elephant has #{@weight} kilos"
        fn()

bigBigRabbit = new BigBigRabbit(300)
bigBigRabbit.jump()

elephant = new Elephant()
elephant.show(bigBigRabbit.jump)
elephant.show(bigBigRabbit.jump2)

### calling private method
bigBigRabbit.getWeight()
###

applyFunc = (func) -> func(3)

applyFunc (x) -> console.log("hi #{x}")

console.log "ahoj", BigRabbit
console.log "ahoj", Rabbit

console.log Rabbit.prototype.speak == Rabbit::speak

## modify all instance of rabbit
Rabbit::dance = -> console.log "Rabbit #{@adjective} is dancing..."
blackRabbit.dance()
bigRabbit.dance()
bigBigRabbit.dance()

