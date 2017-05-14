package tutorial.webapp

import org.scalajs.dom
import org.scalajs.dom.svg.SVG

import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import scalatags.JsDom.{TypedTag, all, svgAttrs, svgTags}
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.svgTags._

/**
  * Created by joe on 20/02/17.
  */
object Pieces   {
  @scala.scalajs.js.annotation.JSExport
  def main(): Unit = {

  }

  def getAll():Set[Piece]= for {
    height <- Set(Tall, Short)
    colour <- Set(Orange, Blue)
    shape <- Set(Square, Round)
    top <- Set(Flat, Holed)
  } yield Piece(height, colour, shape, top)

}

sealed trait Attribute
sealed trait Height extends Attribute
case object Tall extends Height
case object Short extends Height
sealed trait Colour extends Attribute
case object Orange extends Colour
case object Blue extends Colour
sealed trait Shape extends Attribute
case object Square extends Shape
case object Round extends Shape
sealed trait Top extends Attribute
case object Holed extends Top
case object Flat extends Top
case class Piece(height: Height,colour: Colour,shape: Shape,top: Top)
