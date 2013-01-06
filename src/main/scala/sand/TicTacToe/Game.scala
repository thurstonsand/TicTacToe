package sand.TicTacToe

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

  def nextPlayer(player: Int) = if ( player == X_Cell ) O_Cell else X_Cell

  def generateGameGraph = {

    def mkGameGraph(play: Int, b: Board, player: Int) =
        GameGraph( play, player, b, genNextMoves(play+1, b, nextPlayer(player)) )

    def genNextMoves(play: Int, b: Board, player: Int) : Seq[GameGraph] =
      for { i <- 0 until boardSize*boardSize if b.cells(i) == Empty_Cell }
        yield mkGameGraph( play, b.modified(i, player), player)

    mkGameGraph(0, Board.mkInitialBoard(boardSize, Empty_Cell), Empty_Cell)
  }
}
