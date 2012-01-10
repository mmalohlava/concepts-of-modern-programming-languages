// ---------------------------------
// closure is a function with defined environment
console.log('\n=== Closure #1 ===');
var variable = "top-level";
function parentFunction() {
    var variable = "local"; // function environment
    function childFunction() {
        console.log('Inside the closure the variable has value:', variable);
    }
    return childFunction;
}

var child = parentFunction(); // closure
child();

// ---------------------------------
console.log('\n=== Closure #2 ===');
function makeAddFunction(amount) {
    function add(number) {
        return number + amount;
    }
    return add;
}

var addTwo = makeAddFunction(2);
var addFive = makeAddFunction(5);
console.log('Calling the closures (2+1)+(5+1) =', addTwo(1) + addFive(1));

// ---------------------------------
console.log('\n=== Closure #3 ===');
var makeCounter = function() {  
    var privateCounter = 0;  
    function changeBy(val) {  
        privateCounter += val;  
    }  
    return {  
        increment: function() {  
            changeBy(1);  
        },  
        decrement: function() {  
            changeBy(-1);  
        },  
        value: function() {  
            return privateCounter;  
        }  
    }    
};  

var Counter1 = makeCounter();  
var Counter2 = makeCounter();  
console.log(Counter1.value()); /* Alerts 0 */  
Counter1.increment();  
Counter1.increment();  
console.log(Counter1.value()); /* Alerts 2 */  
Counter1.decrement();  
console.log(Counter1.value()); /* Alerts 1 */  
console.log(Counter2.value()); /* Alerts 0 */  
