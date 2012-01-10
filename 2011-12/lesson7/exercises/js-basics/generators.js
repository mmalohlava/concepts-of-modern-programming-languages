//\*
// Features of JS 1.7+
// 
function fibionacciGenerator() {
    var fn1 = 0;
    var fn2 = 1;

    while (1) {
        var fnNext = fn1 + fn2;
        fn1 = fn2;
        fn2 = fnNext;

        var reset = yield fnNext;
        if (reset) {
            fn1 = 0;
            fn2 = 1;
        }
    }
}

var seq = fibionacciGenerator();
console.log(seq.next());
console.log(seq.next());
console.log(seq.next());
console.log(seq.next());
console.log(seq.send(true));
console.log(seq.next());
console.log(seq.next());

var doubled = [i * 2 for (i in fibionacciGenerator)];


