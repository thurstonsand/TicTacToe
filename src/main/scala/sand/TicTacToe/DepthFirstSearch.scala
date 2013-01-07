package sand.TicTacToe

/**
 * Created with IntelliJ IDEA.
 * User: Bob Sandberg
 * Date: 1/6/13
 * Time: 5:32 PM
 *
 * Make this really generic
 * It will loop through game graph in a depth first manner
 * That is it will traverse the tree down to a leaf first
 *
 * Then on each Node it will determine if there is a win
 * If so, then it will :
 * - halt depth search on this node
 * - add this node as a win node
 *
 * For now, it will operate on GameGraph, but later we will add a trait to provide accessors to tree traversal
 */
case class DepthFirstSearch(g: Game, player: Int) {

  val wins = scala.collection.mutable.Set[GameGraph]()

  def printWins =
    wins.zipWithIndex.foreach{ case (gg,i) => println( "%3d. %X / %s".format(i, gg.hashCode, gg.board) ) }

  def search(node: GameGraph) : Unit = {
    if( g.isWin(node.board,player) )
      wins += node
    else if( !g.isWin(node.board,nextPlayer(player)) )
      node.nextPlays foreach search
  }
}
