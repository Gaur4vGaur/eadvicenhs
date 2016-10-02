package forms

import model.forms.AdviceInput
import play.api.data.Form
import play.api.data.Forms._

object AdviceForm {

  val adviceForm = Form(
    mapping(
      "advice" -> text,
      "number" -> text,
      "email" -> text
    )(AdviceInput.apply)(AdviceInput.unapply)
  )

}
