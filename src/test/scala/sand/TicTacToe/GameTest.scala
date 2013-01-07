/*
 * Copyright 2001-2009 Artima, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sand.TicTacToe

import org.junit.Test
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameTest extends FunSuite with ShouldMatchers {

  val bs = 3
  val g = Game(bs)
  val gg1a = GameGraph(Board(bs, Array(0,0,0, 1,1,1, 0,0,0)), Seq())
  val gg1b = GameGraph(Board(bs, Array(0,0,0, 1,1,1, 0,0,0)), Seq(gg1a))
  val gg2a = GameGraph(Board(bs, Array(1,0,0, 1,1,1, 0,0,0)), Seq(gg1a))

  test("Game of 3x3 winLines") {
    val expected = Array(
      Array(0, 1, 2),
      Array(3, 4, 5),
      Array(6, 7, 8),
      Array(0, 3, 6),
      Array(1, 4, 7),
      Array(2, 5, 8),
      Array(0, 4, 8),
      Array(2, 4, 6))
    g.winLines.corresponds(expected)( (a1,a2) => a1.sameElements(a2) ) should be (true)
  }

  test("Win Check of 3x3 board") {
    g.isWin((new Board(g.boardSize,Array(1,1,1, 0,0,0, 0,0,0))), 1) should be (true)
    g.isWin((new Board(g.boardSize,Array(0,0,0, 1,1,1, 0,0,0))), 1) should be (true)
    g.isWin((new Board(g.boardSize,Array(0,0,0, 0,0,0, 1,1,1))), 1) should be (true)
    g.isWin((new Board(g.boardSize,Array(1,0,0, 1,0,0, 1,0,0))), 1) should be (true)
    g.isWin((new Board(g.boardSize,Array(0,1,0, 0,1,0, 0,1,0))), 1) should be (true)
    g.isWin((new Board(g.boardSize,Array(0,0,1, 0,0,1, 0,0,1))), 1) should be (true)
    g.isWin((new Board(g.boardSize,Array(1,0,0, 0,1,0, 0,0,1))), 1) should be (true)
    g.isWin((new Board(g.boardSize,Array(0,0,1, 0,1,0, 1,0,0))), 1) should be (true)

    g.isWin((new Board(g.boardSize,Array(1,0,1, 0,0,0, 0,0,0))), 1) should be (false)
    g.isWin((new Board(g.boardSize,Array(1,0,0, 0,1,1, 0,0,0))), 1) should be (false)
    g.isWin((new Board(g.boardSize,Array(0,0,1, 0,0,0, 1,1,0))), 1) should be (false)
    g.isWin((new Board(g.boardSize,Array(1,0,0, 0,1,0, 1,0,0))), 1) should be (false)
    g.isWin((new Board(g.boardSize,Array(1,0,0, 0,1,0, 0,1,0))), 1) should be (false)
    g.isWin((new Board(g.boardSize,Array(0,0,1, 0,0,1, 1,0,0))), 1) should be (false)
    g.isWin((new Board(g.boardSize,Array(1,0,0, 0,1,0, 0,1,0))), 1) should be (false)
    g.isWin((new Board(g.boardSize,Array(1,0,0, 0,1,0, 1,0,0))), 1) should be (false)

    g.isWin((new Board(g.boardSize,Array(2,2,2, 0,0,0, 0,0,0))), 2) should be (true)
    g.isWin((new Board(g.boardSize,Array(2,2,2, 0,0,0, 0,0,0))), 1) should be (false)
  }

  test("GameGraph Node equals itself") {
    gg1a should be (gg1a)
  }

  test("Two GameGraph Nodes are equals with same values") {
    gg1a should be (gg1b)
    gg1a.hashCode should be (gg1b.hashCode)
  }

  test("Two GameGraph Nodes are not equals with different values") {
    gg1a should not be (gg2a)
    gg1a.hashCode should not be (gg2a.hashCode)
  }

  test("hashSet contains an object") {
    val hashSet = scala.collection.Set( gg1a, gg2a )
    hashSet.contains(gg1a) should be (true)
  }

  test("hashSet contains an object of same hashCode") {
    val hashSet = scala.collection.Set( gg1a, gg2a )
    hashSet.contains(gg1b) should be (true)
  }

  test("hashSet does not contains an object of different hashCode") {
    val hashSet = scala.collection.Set( gg1a )
    hashSet.contains(gg2a) should be (false)
  }


  test("Generate Graph for 2x2") {

    def printGG(t: String, gg: GameGraph, play: Int, player: Int) {
      println("%s%3d: [%s] %s".format(t, play, playerToString(player), gg.board))
      gg.nextPlays foreach (nextGG => printGG(t + "  ", nextGG, play+1, nextPlayer(player)))
    }

    val g = Game(2)
    val gg = g.generateGameGraph
    printGG("", gg, 0, Empty_Cell)
  }
}
