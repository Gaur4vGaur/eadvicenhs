package forms

import model.forms.OdsInput
import play.api.data.Form
import play.api.data.Forms._

object OdsForm {

  val odsForm = Form(
    mapping(
      "name" -> text
    )(OdsInput.apply)(OdsInput.unapply)
  )

}
