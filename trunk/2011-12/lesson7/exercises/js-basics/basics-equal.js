//
// JavaScript features weak typing. This means that the equality operator coerces types in order to compare them.
// Using == is a bad practice ! not only due to its performance impact
console.log("\nUse == for equal");
console.log('""           ==   "0" is', ""           ==   "0");           // false
console.log('0            ==   "" is', 0            ==   "");            // true
console.log('0            ==   "0" is', 0            ==   "0");           // true
console.log('false        ==   "false" is', false        ==   "false");       // false
console.log('false        ==   "0" is', false        ==   "0");           // true
console.log('false        ==   undefined is', false        ==   undefined);     // false
console.log('false        ==   null is', false        ==   null);          // false
console.log('null         ==   undefined is', null         ==   undefined);     // true
console.log('" \\t\\r\\n"    ==   0 is', " \t\r\n"    ==   0);             // true

// The strict equality operator consists of three equal signs: ===.
console.log("\nUse === for equal");
console.log('""           ===   "0" is', ""           ===   "0");           // false
console.log('0            ===   "" is', 0            ===   "");            // true
console.log('0            ===   "0" is', 0            ===   "0");           // true
console.log('false        ===   "false" is', false        ===   "false");       // false
console.log('false        ===   "0" is', false        ===   "0");           // true
console.log('false        ===   undefined is', false        ===   undefined);     // false
console.log('false        ===   null is', false        ===   null);          // false
console.log('null         ===   undefined is', null         ===   undefined);     // true
console.log('" \\t\\r\\n"    ===   0 is', " \t\r\n"    ===   0);             // true


