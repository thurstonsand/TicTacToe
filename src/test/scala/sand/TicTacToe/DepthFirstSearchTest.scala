package sand.TicTacToe

import org.junit.Test
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DepthFirstSearchTest extends FunSuite with ShouldMatchers {

  test("DFS") {
    val g = Game(3)

    val dfs = new DepthFirstSearch(g, X_Cell)

    dfs.search(g.gameGraph)

    dfs.wins foreach println
  }
}

