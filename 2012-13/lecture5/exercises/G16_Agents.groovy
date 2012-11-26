import groovyx.gpars.agent.Agent

def event = new Agent([])
Thread.start {
    event { it << 'Joe' }
    event { it << 'Dave' }
}

println event.instantVal

Thread.start {
    event { it << 'Alice' }
    //TASK Try to read the state of the agent here (using either val or instantVal
    event { it << 'Susan' }
}

println event.instantVal
sleep 2000
println event.val