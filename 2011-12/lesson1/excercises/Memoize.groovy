def fib = null
fib = {
    it <= 1 ? it : (fib(it - 2) + fib(it - 1))
}.memoizeAtMost(2)

long b = System.currentTimeMillis()
fib(1000G)
long a = System.currentTimeMillis()
println((a - b) / 1000 + ' seconds') // about 0.08 seconds on my machine