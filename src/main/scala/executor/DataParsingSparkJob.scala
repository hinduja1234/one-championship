package executor

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import spark.jobserver.{SparkJob, SparkJobValid, SparkJobValidation}


object DataParsingSparkJob extends SparkJob{

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DataParsingJob").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val jobConfig = ConfigFactory.empty()
    runJob(sc, jobConfig)
  }

  /** Run Spark Job
    *
    * @param sc        Spark Context
    * @param jobConfig Job Config
    * @return
    */
  override def runJob(sc: SparkContext, jobConfig: Config): Any = {
    val sqlContext = new SQLContext(sc)
    val applicationConfig = ConfigFactory.load("application")
    val dataParsingJob = new DataParsing(sqlContext, jobConfig, applicationConfig)
    dataParsingJob.runJob()
  }

  /** Valid Spark Job Parameter
    *
    * @param sc        Spark Context
    * @param jobConfig Job Config
    * @return
    */
  override def validate(sc: SparkContext, config: Config): SparkJobValidation = SparkJobValid

}
