//addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.8")
//resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
logLevel := Level.Warn

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.1")
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.11.0")
