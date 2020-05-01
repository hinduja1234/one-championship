name := "one-championship"

version := "0.1"

scalaVersion := "2.10.4"

val sparkVersion = "1.5.2"

resolvers += "Job Server Bintray" at "https://dl.bintray.com/spark-jobserver/maven"


// Spark Library
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion excludeAll
  ExclusionRule(organization = "org.eclipse.jetty")
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion

// Source and Sink Library
libraryDependencies += "com.databricks" %% "spark-csv" % "1.3.0"
libraryDependencies += "com.datashop" %% "spark-avro" % "2.1.1"


libraryDependencies += "spark.jobserver" %% "job-server-api" % "0.6.2"

fork := true


// Assembly Setting
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

// Scala Unit Test Library
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0"