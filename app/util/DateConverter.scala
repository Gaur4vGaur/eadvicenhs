package util

/**
  * Created by gaurav.gaur on 02/10/2016.
  */
object DateConverter {

  def dateConversion(millis: Long = System.currentTimeMillis()) = {
    import java.util.Date
    new Date(millis)
  }
}
