package testwrapper

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

trait SparkSessionTestWrapper {

  lazy val sc  = new SparkContext(
    new SparkConf().setMaster("local[*]")
      .setAppName("Test")
  )
  lazy val sqlContext = new SQLContext(sc)

}
