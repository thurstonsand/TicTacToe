package sand

/**
 * Created with IntelliJ IDEA.
 * User: Bob Sandberg
 * Date: 1/5/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
package object TicTacToe {

  val Empty_Cell = 0
  val O_Cell = 1
  val X_Cell = 2

  def nextPlayer(player: Int) = if ( player == X_Cell ) O_Cell else X_Cell
  def playerToString(p: Int) = p match { case Empty_Cell => "E"; case O_Cell => "O"; case X_Cell => "X"; case _ => "?" }
}
