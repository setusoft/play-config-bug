package controllers

import javax.inject.Inject

import play.api._
import play.api.mvc._

class Application @Inject() (configuration: Configuration) extends Controller {

  def index = Action {
    Ok(configuration.getString("test.value").getOrElse("Config value is not available"))
  }
}
