assert [3, 6, 9, 12, 15] == [1, 2, 3, 4, 5].collect {3 * it}

final words = ['him', 'her', 'he', 'she', 'me', 'his']
assert words.any {it.contains 'me'}
assert words.every {it.size() > 1}
assert words.findAll {it.startsWith 'he'}.size() == 2
assert ['H', 'S'] == words.findAll {it.size() == 3}*.toUpperCase()*.substring(0, 1).unique()

//TASK Print squares smaller than 302875106592254 of all odd numbers in between 1 and 30

println 'done'