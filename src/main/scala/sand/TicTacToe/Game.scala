package sand.TicTacToe

import collection.mutable

case class GameGraph(board: Board, nextPlays: Seq[GameGraph]) {
  override def hashCode = board.hashCode
  override def equals(other: Any) = other match {
    case otherGG: GameGraph => otherGG.hashCode == this.hashCode
    case _ => false
  }

  override def toString = board.toString
}

/**
 * Created with IntelliJ IDEA.
 * User: Bob Sandberg
 * Date: 1/5/13
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
case class Game(boardSize : Int) {

  def play() {
    println("Playing game")
  }

  val winLines : Array[Array[Int]] = genWinLines
  lazy val gameGraph = generateGameGraph

  def rc2Idx(r: Int, c: Int) = r * boardSize + c

  private def genWinLines =
    winLinesByRow ++ winLinesByCol ++ winLinesByDiag

  def winLinesByRow =
    ((0 until boardSize) map ( r =>
      (rc2Idx(r,0) to rc2Idx(r,boardSize-1)).toArray )).toArray

  def winLinesByCol =
    ((0 until boardSize) map ( c =>
      ((0 until boardSize) map (rc2Idx(_,c))).toArray  )).toArray

  def winLinesByDiag = Array(
    (for( i <- 0 until boardSize) yield rc2Idx(i,i)).toArray,
    (for( i <- 0 until boardSize) yield rc2Idx(i,boardSize-i-1)).toArray )

  def isWin(b: Board, player: Int) =
    winLines exists ( _ forall ( b.cells(_) == player ) )

  def generateGameGraph = {

    //val graphSet = new mutable.Set[GameGraph] {} // Try to use this by implementing hashCode in GameGraph
    val graphSet = new mutable.HashMap[Board, GameGraph] ()

    def mkGameGraph(b: Board, player: Int) = {
      val gg = GameGraph( b, genNextMoves(b, nextPlayer(player)) )
      graphSet += gg.board -> gg
      gg
    }

    def genNextMoves(b: Board, player: Int) : Seq[GameGraph] =
      for {
        i <- 0 until boardSize*boardSize if b.cells(i) == Empty_Cell
        nextB = b.modified(i, player)
        gameGraph = if ( graphSet.contains(nextB) ) graphSet(nextB) else mkGameGraph( nextB, player)
      } yield gameGraph


    val gg = mkGameGraph(Board.mkInitialBoard(boardSize, Empty_Cell), Empty_Cell)
//    println("Created " + graphSet.size + " gameGraphs")
//    graphSet.values.foreach (gg => println(gg.board))

    gg
  }
}
