event = "Meeting"
final currentDate = new Date()

final printDate = {println "$event $date:$month at $time"}

//TASK uncomment and see the exception
//printDate()

println "Owner: ${printDate.owner}"
println "Delegate: ${printDate.delegate}"
printDate.delegate = currentDate
println "Delegate: ${printDate.delegate}"

printDate()

time = 'My correct time'
printDate()

time = 'My incorrect time'
//TASK Pick the right Resolution strategy to use the time property of currentDate and not the time property of the script
//printDate.resolveStrategy = Closure...
printDate()
