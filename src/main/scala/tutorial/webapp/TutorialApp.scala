package tutorial.webapp

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

import scala.scalajs.js.annotation.JSExport
import dom.html._
import org.scalajs.dom.raw.Element
import org.scalajs.jquery.{JQueryEventObject, jQuery}
import org.w3c.dom.html.HTMLParagraphElement

object TutorialApp {
//  def main(): Unit = {
//    appendPar(document.body, "Hello World!?")
//  }
  def appendPar(targetNode: dom.Node, text: String):Unit = {
    val parNode: Element = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)

  }
  @JSExport
  def addClickedMessagef(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }
//  def main(): Unit = {
//    jQuery(setupUI _)
//  }

  def addClickedMessage() : Unit = {
    jQuery(document.body).append("<p>honk</p>")
  }

  def setupUI(): Unit ={
    jQuery("""<button type="button">Click me!</button>""")
      .click(addClickedMessage _)
      .appendTo(jQuery("body"))
    jQuery("body").append("<p>Hello World</p>")
  }
}
