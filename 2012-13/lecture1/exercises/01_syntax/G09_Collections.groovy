assert 5050 == (1..100).inject(0) {int acc, int v -> acc + v}
assert 5050 == (1..100).sum()

//TASK concatenate all characters between 'a' and 'z'
//assert 'abcdefghijklmnopqrstuvwxyz' == 

//TASK assert that there are 26 characters between 'a' and 'z'
//assert 26 == 

//TASK Find the maximum of the following collection using the inject() function
//assert 26 == [1, 2, 3, 4, 3, 4, 19, 26, 25, 19, 22, 25, 3, 10, 12, 23, 24, 12, 11, 18]

println 'ok'