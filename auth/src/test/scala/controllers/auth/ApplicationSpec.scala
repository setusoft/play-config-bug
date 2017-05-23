package controllers.auth

import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import play.api.test.Helpers._
import play.api.test.{ WithApplication, _ }

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {

  "Application" should {
    "return the config value from root package" in new WithApplication with Scope {
      val controller = app.injector.instanceOf[Application]

      val result = controller.config(FakeRequest(GET, "/auth/config"))

      status(result) must equalTo(OK)
      contentAsString(result) must contain ("This should work in test mode")
    }

    "return the message from root package" in new WithApplication with Scope {
      val controller = app.injector.instanceOf[Application]

      val result = controller.message(FakeRequest(GET, "/auth/message"))

      status(result) must equalTo(OK)
      contentAsString(result) must contain ("I'm located in the root package")
    }

    "route to the correct action" in new WithApplication with Scope {
      val Some(result) = route(app, FakeRequest(GET, "/auth/router"))

      status(result) must equalTo(OK)
    }
  }
}
