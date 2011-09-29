assert 5050 == (1..100).inject(0) {int acc, int v -> acc + v}
assert 5050 == (1..100).sum()

//TASK concatenate all characters between 'a' and 'z'
//assert 'abcdefghijklmnopqrstuvwxyz' == 

//TASK assert that there are 16 characters between 'a' and 'z'
//assert 26 == 

println 'done'