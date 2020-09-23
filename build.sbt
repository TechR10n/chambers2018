name := "chambers2018"

version := "0.1"

scalaVersion := "2.12.10"

ThisBuild / useCoursier := false

resolvers += ("Artima Maven Repository" at "http://repo.artima.com/releases").withAllowInsecureProtocol(true)

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"