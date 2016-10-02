package forms

import model.forms.LoginInput
import play.api.data.Form
import play.api.data.Forms._

object LoginForm {

  val loginForm = Form(
    mapping(
      "name" -> text,
      "password" -> text
    )(LoginInput.apply)(LoginInput.unapply)
  )

}
