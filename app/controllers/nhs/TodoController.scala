package controllers.nhs

import play.api.mvc.Controller
import javax.inject.{Inject, Singleton}

import play.api.mvc.Action

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TodoController @Inject ()(implicit exec: ExecutionContext) extends Controller {

  def tasksToDo() = Action.async {

    val tasksToDo = List ("Create a form with checkbox/form/radio button/input box",
      "Form validations",
      "Read through API and display data in front end",
      "Read through xls",
      "Read through txt file",
      "Write to xls/txt file",
      "Try to integrate logos into the page",
      "If time permits try to create a table",
      "check how to use cache")

    Future {Ok(views.html.todoview(tasksToDo))}
  }

}