package controllers.auth

import javax.inject.Inject

import play.api._
import play.api.i18n.{ I18nSupport, Messages, MessagesApi }
import play.api.mvc._

class Application @Inject() (
  configuration: Configuration,
  val messagesApi: MessagesApi
) extends Controller with I18nSupport {

  def config = Action {
    Ok(configuration.getString("test.value").getOrElse("Config value is not available"))
  }

  def message = Action {
    Ok(Messages("root.module.message"))
  }

  def router = Action {
    Ok
  }
}
