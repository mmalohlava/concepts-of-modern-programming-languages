# Lesson 1 details #

## Agenda ##

  * Language dynamism - typing, casting, object construction, method invocation
  * Functional programming in OO languages
  * Scripting
  * Dynamic meta-programming
  * Intro into Domain Specific Languages

## Preparation ##

  * Grab Groovy 2.3 from [the groovy download page](http://groovy.codehaus.org/Download)
  * Install Java JDK (if you don't have it installed) from [the Java download site](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Resources ##

Grab the [lecture1.zip](http://vaclavpech.eu/groovy/lecture1.zip) file to get the slides and code examples to work with during the lesson.

## Homework ##

```
//TASK Build a processor for user scripts that will be provided in plain text at runtime. The processor should output urls of websites, returned by the user script.
//The user scripts may be allowed to use custom commands - 'download', 'unless', talksAboutGroovy', 'remember' and 'rememberredSites'.
//Use the tricks we learnt about scripting, passing parameters to GroovyShell through binding, properties, 
//closure delegates, the 'object.with(Closure)' method, etc.


List<String> filterSitesByUserScript(String userScript, List<String> sites) {
    //Filterring function. Needs to be implemented

    return []
}





//************* Do not modify after this point!

//A test user input.
String userInput = '''
    for(site in allSites) {
        def content = download site
        unless (talksAboutGroovy(content)) {
            remember site
        }
    }
    return rememberredSites
'''

//Calling the filterring method on a list of sites. 
def result = filterSitesByUserScript(userInput, ["http://groovy.cz", "http://groovy.codehaus.org", "http://infoq.com", "http://oracle.com", "http://ibm.com"])
result.each {
    println 'No groovy mention at ' + it
}
assert result.size()>0 && result.size() < 5   
println 'ok'
```