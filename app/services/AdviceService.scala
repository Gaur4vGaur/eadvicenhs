package services

import model.AdviceRecord
import model.forms.AdviceInput

import scala.collection.mutable._
import java.util.Date

/**
  * Created by gaurav.gaur on 02/10/2016.
  */
object AdviceService {

  private var status = Map[String, AdviceRecord]()

  def saveAdviceRecord(adviceInput: AdviceInput, link: String) =
    status += (adviceInput.phNumber -> AdviceRecord(adviceInput, new Date(System.currentTimeMillis), link))


  def fetchAllAdvice = status


}
