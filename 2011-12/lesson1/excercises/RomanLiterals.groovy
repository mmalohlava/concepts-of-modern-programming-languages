/**
* @author http://groovyconsole.appspot.com/script/537002
*/
class UseRomanNumerals {
//Roman Numerals decoding is per http://rosettacode.org/wiki/Roman_numerals/Decode
def decode(sym) { [M: 1000, D: 500, C: 100, L: 50, X: 10, V: 5, I: 1].get(sym) }
def convert(roman) {
roman.reverse().inject([last: 0, arabic: 0]) { acc, sym ->
{ n -> [last: n, arabic: n < acc.last ? acc.arabic - n : acc.arabic + n] }(decode(sym))
}.arabic
}
def propertyMissing(String roman) { convert(roman) }
}
 
def useRoman(closure) {
closure.delegate = new UseRomanNumerals()
closure.resolveStrategy = Closure.DELEGATE_FIRST
closure()
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
//if you are from roman empire you gonna like it
println "Current year is " + useRoman {

 XIII + XMMVIII
 
 
}

