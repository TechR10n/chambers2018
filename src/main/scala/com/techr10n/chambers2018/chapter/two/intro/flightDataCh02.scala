package com.techr10n.chambers2018.chapter.two.intro

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object flightDataCh02 extends App {
  val spark = SparkSession.builder()
    .config("spark.master", "local")
    .appName("Ch02 Intro to Spark")
    .getOrCreate()

//  val myRange = spark.range(16).toDF("number")
//
//  val divisBy2 = myRange.where("number % 2 = 0")
//  println(divisBy2.count())

  val flightDataFile = "/media/ryan/ryanHDD/rawdata/data-chambers2018/flight-data/csv/2015-summary.csv"
  val flightData2015 = spark
    .read
    .option("inferSchema", true)
    .option("header", "true")
    .csv(flightDataFile)

  flightData2015.show(4)
  flightData2015.sort("count").explain()

  flightData2015.createOrReplaceTempView("flight_data_2015_view")

  println(spark.sql("SELECT max(count) FROM flight_data_2015_view").takeAsList(3))

  val maxSqlText =
    """
      |SELECT DEST_COUNTRY_NAME, sum(count) as destination_total
      |FROM flight_data_2015_view
      |GROUP BY DEST_COUNTRY_NAME
      |ORDER BY sum(count) DESC
      |LIMIT 5
      |""".stripMargin

  val maxSql = spark.sql(maxSqlText)
  maxSql.show()

  flightData2015
    .groupBy("DEST_COUNTRY_NAME")
    .sum("count")
    .withColumnRenamed("sum(count)", "destination_total")
    .sort(desc("destination_total"))
    .limit(5)
    .show()
  spark.stop()
}
