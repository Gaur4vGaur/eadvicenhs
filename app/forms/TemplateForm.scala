package forms

import model.forms.TemplateInput
import play.api.data.Form
import play.api.data.Forms._

object TemplateForm {

  val templateForm = Form(
    mapping(
      "name" -> text
    )(TemplateInput.apply)(TemplateInput.unapply)
  )

}
