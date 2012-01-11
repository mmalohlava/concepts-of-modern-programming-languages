// set function to be called after 1 second
setTimeout(function() {
   console.log('Timeout ran at ' + new Date().toTimeString());
}, 1000);

// store the start time
var start = new Date();
console.log('Enter loop at: '+start.toTimeString());

// run a loop for 4 seconds
var i = 0;
// increment i while (current time < start time + 4000 ms)
while(new Date().getTime() < start.getTime() + 4000) {
   i++;
}
console.log('Exit loop at: '
            +new Date().toTimeString()
            +'. Ran '+i+' iterations.');