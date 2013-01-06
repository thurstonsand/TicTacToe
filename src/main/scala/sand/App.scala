package sand

import sand.TicTacToe._

/* Develop Tic Tac Toe Game to run in parallel using AKKA
**
** 1. Figure out structures
**    a) Convert C TicTacToe to scala
**    b) Maybe a graph for the possible moves
**    c) See if game state (board) can be represented as an int
** 2. Gen All Game States
**    This time, maybe don't create invalid states or states after a win? But then that would imply detecting
**    wins during this stage.
**    -- Try to write as an enumerable so that it can be generated in a lazy fashion
** 3. Write DFS graph traversal (single threaded to start with)
** 4. Add multi-threading or parallel work via Akka
**    a) Will need to figure out how to handle Visited Node aspect
**       - could not use that by truncating graph nodes that end in the same game state?
**       - Or create a mutable Visited Node object
*/

/**
 * @author ${user.name}
 */
object App {


  def foo(x : Array[String]) = x.mkString(", ")
  
  def main(args : Array[String]) {
    println( "Hello World!" )
    println("concat arguments = " + foo(args))
  }

}
