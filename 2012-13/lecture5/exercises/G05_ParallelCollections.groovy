import static groovyx.gpars.GParsPool.withPool

final numbers = [1G, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 5, 6, 7, 8, 4, 5, 6, 3, 4, 5, 7, 2, 23, 43, 235, 64G]

def product1 = 1
numbers.each {
    product1 = product1 * it
}
println product1

//TODO Calculate the product of all the numbers in parallel so that the script passes
def product2 = 1
withPool {

}

println product2

assert product1 == product2