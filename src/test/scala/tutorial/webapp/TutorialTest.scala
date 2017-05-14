package tutorial.webapp

import utest._
import org.scalajs.jquery.jQuery

/**
  * Created by joe on 16/02/17.
  */
object TutorialTest extends TestSuite{

  TutorialApp.setupUI()

  def tests = TestSuite {
    'HelloWorld {
      assert(jQuery("p:contains('Hello World')").length == 1)
    }
    'ButtonClick {
      def messageCount =
        jQuery("p:contains('honk')").length
      val button = jQuery("button:contains('Click me')")
      assert(button.length == 1)
      assert(messageCount == 0)

      for (c <- 1 to 5) {
        button.click()
        assert(messageCount == c)
      }
    }
  }

}
