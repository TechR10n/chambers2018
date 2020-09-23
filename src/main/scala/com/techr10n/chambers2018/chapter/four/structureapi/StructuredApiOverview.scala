package com.techr10n.chambers2018.chapter.four.structureapi

import org.apache.spark.sql.SparkSession

object StructuredApiOverview extends App {
  val spark = SparkSession.builder()
    .appName("api-overview")
    .config("spark.master", "local")
    .getOrCreate()

  val df = spark.range(16).toDF("number")
  df.select(df.col("number") + 10)
  print(df.summary())

  spark.stop()
}
