package tutorial.webapp

import org.scalajs.dom.svg._

import scalatags.JsDom.svgTags._
import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs._
import org.scalajs.jquery.jQuery
import org.scalajs.dom
import dom.document

import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.TypedTag

/**
  * Created by joe on 22/02/17.
  */
@JSExport
object PieceSVG {

  val angle = 0.6
  case class PieceSVG(baseXCoordinate: Double, baseYCoordinate: Double, svg: SvgTag)
  case class BoardSVG(squareCoordinates: Map[(Int,Int),(Double,Double)], svg: SvgTag)

  @JSExport
  def main(): Unit = {
//    jQuery(document.body).append(makePiece(Piece(Short, Orange, Square, Holed)).render)
//    jQuery(document.body).append(makePiece(Piece(Short, Orange, Round, Holed)))
    jQuery(document.body).append(getBoard().svg(width:=2000, height:="auto", preserveAspectRatio:="xMidYMin meet").render)
//    jQuery(document.body).append(FullLayout.get().render)
  }

  def makePiece(piece: Piece):PieceSVG = {

    val squareWidth = 100
    val xmiddle = squareWidth/2
    val tallHeight = 150
    val shortHeight = 85
    val holeWidth = 15
    val roundWidth = 85
    val visibleHoleHeight = Math.sin(angle)*holeWidth
    val actualHeight = if (piece.height == Tall) tallHeight else shortHeight
    val visibleHeight = actualHeight * Math.cos(angle)
    val visibleTop = {
      if (piece.shape == Square) squareWidth else roundWidth
    }* Math.sin(angle)
    val viewboxHeight = tallHeight * Math.cos(angle) + visibleTop
    val top = viewboxHeight - visibleHeight - visibleTop
    val topOfSide = viewboxHeight - visibleHeight - visibleTop / 2
    val topOfMiddle = viewboxHeight - visibleHeight
    val baseOfSide = viewboxHeight - visibleTop / 2
    val baseXCoordinate = 50
    val baseYCoordinate = viewboxHeight - visibleTop/2
    val (darkestColour,middleColour, lightestColour) =
      if (piece.colour == Orange) ("#ff6600","#ff7f2a","#ff9955")
      else ("#2c89a0","#37abc8","#5fbdcd")
    val holeColour = if (piece.top == Holed) "#2b1100" else lightestColour
    val gElement = if (piece.shape == Round)
      g(
        rect(
          style := s"fill:$middleColour",
          width := roundWidth,
          height := visibleHeight,
          x := 7.5,
          y := topOfSide
        ),
        ellipse(
          style := s"fill:$lightestColour",
          cx := xmiddle,
          cy := topOfSide,
          rx := roundWidth*0.5,
          ry := visibleTop*0.5
        ),
        ellipse(
          style := s"fill:$middleColour",
          cx := xmiddle,
          cy := viewboxHeight - visibleTop/2,
          rx := roundWidth *0.5,
          ry := visibleTop*0.5
        ),
        ellipse(
          style:= s"fill:$holeColour",
          cx := xmiddle,
          cy:= topOfSide,
          rx := holeWidth,
          ry := visibleHoleHeight
        )
      )
    else
      g(
        polygon(
          style:=s"fill:$middleColour",
          points:=s"0,$topOfSide $xmiddle,$topOfMiddle $xmiddle,$viewboxHeight 0,$baseOfSide"
        ),
        polygon(
          style:=s"fill:$darkestColour",
          points:=s"$xmiddle,$topOfMiddle $squareWidth,$topOfSide $squareWidth,$baseOfSide $xmiddle,$viewboxHeight"
        ),
        polygon(
          style:=s"fill:$lightestColour",
          points:=s"0,$topOfSide $xmiddle,$top $squareWidth,$topOfSide $xmiddle,$topOfMiddle"
        ),
        ellipse(
          style:= s"fill:$holeColour",
          cx := squareWidth/2,
          cy:= topOfSide,
          rx := holeWidth,
          ry := visibleHoleHeight
        )
      )

    val svgTag = svg(
      width := squareWidth,
      height := viewboxHeight,
      viewBox := s"0 0 $squareWidth $viewboxHeight",
      gElement
    )
    PieceSVG(baseXCoordinate,baseYCoordinate,svgTag)
  }

  def getBoard():BoardSVG = {
    val squareWidth = 150.0
    val visibleSquareHeight = squareWidth*Math.cos(angle)
    val viewBoxHeight = 4* visibleSquareHeight
    val viewBoxWidth = 4*squareWidth
    val darkColour = "#2b1100"
    val lightColour = "#deaa87"
    val squares = for {
      i <- 0 to 3
      j <- 0 to 3
      middlePointX = (squareWidth/2)*(j+i+1)
      middlePointY = (visibleSquareHeight/2) * (4-j+i)
      colour = if ((i+j)%2==0) darkColour else lightColour
      topPointX = middlePointX
      leftPointX = middlePointX - squareWidth/2
      rightPointX = middlePointX + squareWidth/2
      bottomPointX = middlePointX
      topPointY = middlePointY - visibleSquareHeight/2
      leftPointY = middlePointY
      rightPointY = middlePointY
      bottomPointY = middlePointY + visibleSquareHeight/2
    } yield (
      (i,j)->(middlePointX,middlePointY),
      polygon(
        style:=s"fill:$colour",
        points:=s"$topPointX,$topPointY $rightPointX,$rightPointY $bottomPointX,$bottomPointY $leftPointX,$leftPointY"
      )
    )
    val svgTag = svg(
      width:=viewBoxWidth,
      height:=viewBoxHeight,
      viewBox:=s"0 0 $viewBoxWidth $viewBoxHeight",
      g(
        squares.map{case (coordinates,svg)=> svg}:_*
      )
    )
    val coordinates = squares.map{case (coordinate, _) => coordinate}.toMap
    BoardSVG(coordinates,svgTag)
  }

}
