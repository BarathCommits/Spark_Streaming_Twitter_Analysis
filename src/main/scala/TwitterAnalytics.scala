import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TwitterAnalytics {


  def main(args: Array[String]): Unit = {

    val SparkConf = new SparkConf().setAppName("Sentimental Analysis").setMaster("local[2]")
    val ssc = new StreamingContext(SparkConf, Seconds(2))
    //Use your keys
    System.setProperty("twitter4j.oauth.consumerKey", "****************** ")
    System.setProperty("twitter4j.oauth.consumerSecret", "******************")
    System.setProperty("twitter4j.oauth.accessToken", "**********-****************")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "****************")

    val stream = TwitterUtils.createStream(ssc, None)

    val hashTags = stream
      .map(status => status.getUser.getLang)

    hashTags.print()

    ssc.start()
    ssc.awaitTermination()


  }

}
