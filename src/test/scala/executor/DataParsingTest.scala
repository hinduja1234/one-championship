package executor

import java.io.File

import com.typesafe.config.ConfigFactory
import org.apache.commons.io.FileUtils
import org.apache.spark.sql.Row
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import testwrapper.SparkSessionTestWrapper

class DataParsingTest extends FunSuite with SparkSessionTestWrapper with BeforeAndAfterAll {

  private val applicationConfig = ConfigFactory.parseResources("application.conf")

  test("DataParsing - runJob") {
    val jobConfig = ConfigFactory.empty()
    new DataParsing(sqlContext, jobConfig, applicationConfig).runJob()

    val outputPath = applicationConfig.getString("output")
    val actualParsedJsonDF = sqlContext.read.json(outputPath).sort("person_id")
    val actualParsedJsonRowList = actualParsedJsonDF.rdd.collect().toList
    val expectedParsedRowList =  List(
      Row.fromSeq(Seq("B", "10/1/15 8:02", 6, "1")),
      Row.fromSeq(Seq("C", "10/1/15 8:02", 5, "2")),
      Row.fromSeq(Seq("C", "10/1/15 8:03", 1, "3"))
    )



    assert(actualParsedJsonRowList === expectedParsedRowList)


  }

  override def afterAll(): Unit = {
    val outputDeleteFilePath = "src/test/resources/output/*"

    val file = new File(outputDeleteFilePath).getParentFile
    FileUtils.deleteDirectory(file)
    super.afterAll()
  }


}


