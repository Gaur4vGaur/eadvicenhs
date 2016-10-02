package controllers.nhs

import play.api.mvc.{Action, Controller}
import javax.inject.{Inject, Singleton}

import model.Organisations
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest}
import forms.OdsForm.odsForm
import play.api.i18n.Messages.Implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class OpenODSController @Inject()(wsClient: WSClient) extends OpenODSControllerTrait {
  override val ws: WSClient = wsClient

}

trait OpenODSControllerTrait extends Controller {

  val ws: WSClient

  def fetchSampleODSData() = Action.async { implicit request =>
    odsForm.bindFromRequest.fold(
      formWithErrors => {
        Future{BadRequest(views.html.odsinputview(formWithErrors))}
      },
      ods => {
        val url = "http://www.openods.co.uk/api/organisations"
        ws.url(url).withQueryString("q" -> "medway").get().map {
          response =>
            val orgs = Json.parse(response.body).as[Organisations]
            Ok(views.html.odsopendataview(orgs))
        }
      }
    )
  }

  def provideInput() = Action.async {
    Future{
      Ok(views.html.odsinputview(odsForm))
    }
  }
}
