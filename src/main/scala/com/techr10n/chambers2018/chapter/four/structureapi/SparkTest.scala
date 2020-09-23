package com.techr10n.chambers2018.chapter.four.structureapi

import org.apache.spark.sql.SparkSession
object SparkTest extends App {
  /** This app is based on the Spark Quick Start article
   * at  https://spark.apache.org/docs/latest/quick-start.html
   * NOTE: .config("spark.master", "local") should be added to run this locally
   */
  val logFile = "/opt/spark/README.md"
  val logFile2 = "/media/ryan/ryanHDD/rawdata/shakespeare.txt"

  val spark = SparkSession.builder().appName("Simple Application")
    .config("spark.master", "local")
    .getOrCreate()
  val logData = spark.read.textFile(logFile2).cache() // TODO Why cache?
  val numAs = logData.filter(line => line.contains("a")).count()
  val numEs = logData.filter(line => line.contains("e")).count()
  val numIs = logData.filter(line => line.contains("i")).count()
  val numOs = logData.filter(line => line.contains("o")).count()
  val numUs = logData.filter(line => line.contains("u")).count()
  val numRs = logData.filter(line => line.contains("r")).count()
  val numSs = logData.filter(line => line.contains("s")).count()
  val numTs = logData.filter(line => line.contains("t")).count()
  val numLs = logData.filter(line => line.contains("l")).count()
  val numNs = logData.filter(line => line.contains("n")).count()
  println(s"Lines with a: $numAs, Lines ")
  println(s"Lines with e: $numEs, Lines ")
  println(s"Lines with i: $numIs, Lines ")
  println(s"Lines with o: $numOs, Lines ")
  println(s"Lines with u: $numUs, Lines ")
  println(s"Lines with r: $numRs, Lines ")
  println(s"Lines with s: $numSs, Lines ")
  println(s"Lines with t: $numTs, Lines ")
  println(s"Lines with l: $numLs, Lines ")
  println(s"Lines with n: $numNs, Lines ")
  spark.stop()

}
