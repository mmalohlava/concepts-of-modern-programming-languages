###
# Coffeescript features
#  - evetyrhing is an expression
#  - whitespace is significant
#  - ending line ends the expression
#  - use indentation instead of using {}
#  - use `` to embedd Javascript
#
#  - class model (prototypes are also accessible)
#    - @property = this.property
#    - default values for method parameters
#
#  - strings '...', "... #{variable}...", """ ... """,  ''' ... '''
#  
#  - syntactic sugar for for/ if / of (in) / own (=hasOwnProperty) / until / unless
#
#  - only == and != (translated into === and !==), there is no equivalent to Javascript's == and !=
#   - is = '==', isnt = '!='
#
#  - [2..4] = [2,3,4]
#  - [2...4] = [3]
#
#  - ? to check if variable exist (returns true unless variable is null or undefined)
###

# Assignment:
number   = 42
opposite = true

# Conditions:
number = -42 if opposite

# Functions:
square = (x) -> x * x

# Arrays:
list = [1, 2, 3, 4, 5]

# Objects:
math =
  root:   Math.sqrt
  square: square
  cube:   (x) -> x * square x

# Splats: ... = automatic splicing arguments object into array
race = (winner, runners...) ->
  print winner, runners

# Existence:
console.log "I knew it!" if elvis?

# Array comprehensions:
cubes = (math.cube num for num in list)

console.log "\n=== Basic expressions ===\n"
# Iteration
number = 3
while number < 12
    number = number + 2
    console.log 'The number is:', number

# Instead of indentation then is possible to use
while number < 24 then number += 2
console.log "Now, the number is: #{number}"

for number in [2...10] 
    console.log 'For test:', number

# Prepended body
console.log "Prepended body: #{number}" for number in [2..20] by 3

numbers = (number for number in [0..12] by 2)
console.log "Array generation #{numbers}"

console.log "\n=== Classes ===\n"

# Javascript like object creation
OldRabbit = (adj) ->
    this.adj = adj

OldRabbit.prototype.speak = () -> console.log @adj
oldRabbit = new OldRabbit('Javascript like rabbit\n')
oldRabbit.speak()

class Rabbit
    constructor: (@adjective = 'normal') -> # empty body
    speak: (line = "nothing to say") ->  # default value of method parameter
        console.log "Hi there, here is a #{@adjective} rabbit saying: '#{line}'\n"

blackRabbit = new Rabbit('black');
blackRabbit.speak();


class BigRabbit extends Rabbit 
    constructor: (@weight) -> # weight become the part of BigRabbit object constructor
        super 'fat rabbit' 
    - getWeight: () ->        # private method
        console.log @weight 

bigRabbit = new BigRabbit
bigRabbit.speak("I am a big rabbit")
try
    bigRabbit.getWeight()
catch error then console.log "Calling private method bigRabbit.getWeight() causes the error #{error}"

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

# calling private method does not work
#bigBigRabbit.getWeight()

console.log "\nRabbit constructor", Rabbit
console.log "BigRabbit constructor", BigRabbit
console.log "BigBigRabbit constructor", BigBigRabbit


## modify all instance of rabbit via modifying Rabbit prototype
console.log Rabbit.prototype.speak == Rabbit::speak
Rabbit::dance = -> console.log "Rabbit #{@adjective} is dancing..."
blackRabbit.dance()
bigRabbit.dance()
bigBigRabbit.dance()

