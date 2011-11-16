resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.8"))
// for the older version of the plugin, use the following instead:
// you will need to change jetty's scope from 'container' to 'jetty' above
//libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % ("0.1.0-"+v))

// Eclipse project migration tool
//resolvers += Classpaths.typesafeResolver

//addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.4.0")


