package sand.TicTacToe

import org.junit.runner.RunWith
import org.specs._
import org.specs.matcher._
import org.specs.runner.{ JUnitSuiteRunner, JUnit }

/**
 * Sample specification.
 *
 * This specification can be executed with: scala -cp <your classpath=""> ${package}.SpecsTest
 * Or using maven: mvn test
 *
 * For more information on how to write or run specifications, please visit: http://code.google.com/p/specs.
 *
 */
@RunWith(classOf[JUnitSuiteRunner])
class BoardTest extends Specification with JUnit /*with ScalaCheck*/ {

  "Init Board 3x3" should {
    val bs = 3
    val b = Board.mkInitialBoard(bs, Empty_Cell)

    "have a size of 3 x 3" in {
      b.cells.size must_== bs * bs
    }

    "have all values = empty cell" in {
      b.cells.zipWithIndex.map { case (v,i) => v must_== Empty_Cell }
      b.cells.exists(Empty_Cell != _) must_== false
    }

    "have correct modified value " in {
      val nv =
      for ( r <- 0 to b.boardSize-1;
            c <- 0 to b.boardSize-1 ) {
        val idx = b.rc2Idx(r,c)
        b.modified(r,c,X_Cell).cells.zipWithIndex.map {
          case (v,i) if (i == idx) => v must_== X_Cell
          case (v,i)               => v must_== Empty_Cell
        }
      }
    }
  }

}

object BoardTestMain {
  def main(args: Array[String]) {
    new BoardTest().main(args)
  }
}