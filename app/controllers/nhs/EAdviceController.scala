package controllers.nhs

import javax.inject.{Inject, Singleton}

import forms.AdviceForm._
import forms.TemplateForm._
import forms.LoginForm._
import model.{Assessment, Organisations}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.Controller

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext
import play.api.mvc.Action
import services.AdviceService
import templates.KnowledgeBase._

/**
  * Created by gaurav.gaur on 01/10/2016.
  */
@Singleton
class EAdviceController @Inject ()(wsClient: WSClient)(implicit exec: ExecutionContext) extends Controller {

  val adviceService = AdviceService
  val ws: WSClient = wsClient

  def login = Action.async {
    Future {
      Ok(views.html.nhslogin(loginForm))
    }
  }

  def loginAction = Action.async { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        Future{BadRequest(views.html.nhslogin(formWithErrors))}
      },
      login => {
        (login.name == "myname" && login.password == "yourpassword") match {
          case true => Future{
            Ok(views.html.nhshome())
          }
          case _ =>
            Future{
            BadRequest("""<h1>Wrong username or password</h1><br><br><strong><a href="http://localhost:9000/login">login</a></strong>""").as(HTML)
          }
        }
      }
    )
  }

  def homePage = Action.async {
    Future {
      Ok(views.html.nhshome())
    }
  }

  def fetchAdviceMenu() = Action.async {
    val set = knowledgeBase.keySet.map{value => value -> value}
    Future{Ok(views.html.nhstemplateoptions(adviceForm, set.toSeq))}
  }

  def fetchAdviceMenuAction() = Action.async { implicit request =>
      adviceForm.bindFromRequest.fold(
        formWithErrors => {
          val set = knowledgeBase.keySet.map{value => value -> value}
          Future{BadRequest(views.html.nhstemplateoptions(formWithErrors, set.toSeq))}
        },
        advice => {
          val link = knowledgeBase.get(advice.advice).get
          val msg =
            s"""Message sent successfully to <strong>${advice.phNumber}</strong><br>
               |with prescription for <strong>${advice.advice}</strong><br>
               |and the advice is available on <a href=$link target="_blank">$link</a><br><br>
               |<strong><a href="http://localhost:9000/home">home</a></strong>""".stripMargin

          val nhs = advice.advice.replaceAll(" ", "").toLowerCase() + "v1"
          val url = s"https://eadvice.herokuapp.com?doc=$nhs"
          println("\n\n\n"+url)
          ws.url(url).get()

          adviceService.saveAdviceRecord(advice, link)
          Future {Ok(msg).as(HTML)}
        }
      )
  }

  def reviewAdviceMenu() = Action.async {
    val set = knowledgeBase.keySet.map{value => value -> value}
    Future{Ok(views.html.nhstemplatereview(templateForm, knowledgeBase))}
  }

  /*def reviewAdviceMenuAction() = Action.async { implicit request =>
    templateForm.bindFromRequest.fold(
      formWithErrors => {
        println("errors "+formWithErrors)
        val set = knowledgeBase.keySet.map{value => value -> value}
        Future{BadRequest(views.html.nhstemplatereview(formWithErrors, set.toSeq, knowledgeBase))}
      },
      template => {
        val json = knowledgeBase.get(template.name).get

        val assessment = Json.parse(json).as[Assessment]
        Future {Ok(views.html.nhstemplatecontent(template.name, assessment))}
      }
    )
  }*/

  def advicesOut = Action.async{
    Future{Ok(views.html.nhsadvice(adviceService.fetchAllAdvice))}
  }
}
