class ProgrammingLanguage {
    String name
    String version
    boolean easy = true

    ProgrammingLanguage() {}

    ProgrammingLanguage(String name, String version, boolean easy) {
        this.name = name
        this.version = version
        this.easy = easy
    }
}

//TASK Use name parameters to create the instance
ProgrammingLanguage lang = new ProgrammingLanguage('Groovy', '1.8', true)

println lang.dump()
lang.version = '1.9'
println lang.dump()