package executor

import com.typesafe.config.Config
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types.IntegerType

class DataParsing(val sqlContext: SQLContext, val jobConfig: Config, val applicationConfig: Config) {

  /** Job to parse Data
    *
    * @return Output Json
    */
  def runJob(): Any = {

    val inputPath = applicationConfig.getString("input")
    val outputPath = applicationConfig.getString("output")
    val inputData = sqlContext.read.format("csv").option("header", "true")
      .load(inputPath)

    val columnRenamedDF = inputData
      .withColumnRenamed("Person Id", "person_id")
      .withColumnRenamed("Floor Access DateTime", "datetime")
      .withColumnRenamed("Floor Level", "floor_level")
      .withColumnRenamed("Building", "building")

    val schemaCastDF = columnRenamedDF
      .withColumn("floor_level", columnRenamedDF("floor_level").cast(IntegerType))
    schemaCastDF.write.mode("overwrite").json(outputPath)
  }

}
