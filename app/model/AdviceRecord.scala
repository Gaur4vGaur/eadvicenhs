package model

import model.forms.AdviceInput
import java.util.Date

/**
  * Created by gaurav.gaur on 02/10/2016.
  */
case class AdviceRecord(adviceInput: AdviceInput, timeStamp: Date, link: String)
