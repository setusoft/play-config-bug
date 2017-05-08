package controllers

import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import play.api.test.Helpers._
import play.api.test._
import play.api.test.WithApplication

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {

  "Application" should {
    "return the config value" in new WithApplication with Scope {
      val controller = app.injector.instanceOf[Application]

      val result = controller.index(FakeRequest(GET, "/"))

      status(result) must equalTo(OK)
      contentAsString(result) must contain ("This should work in test mode")
    }
  }
}
