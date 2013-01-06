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

  test("Game of 3x3 winLines") {
    val g = Game(3)
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
    val g = Game(3)
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
  }

  test("Generate Graph for 2x2") {
    def playerToString(p: Int) = p match { case Empty_Cell => "E"; case O_Cell => "O"; case X_Cell => "X"; case _ => "?" }
    def boardToString(b: Board) = b.cells.mkString(" ")

    def printGG(t: String, gg: GameGraph) {
      println("%s%3d: [%s] %s".format(t,gg.play,playerToString(gg.playersMove),boardToString(gg.board)))
      gg.nextPlays foreach (nextGG => printGG(t + "  ", nextGG))
    }

    val g = Game(2)
    val gg = g.generateGameGraph
    printGG("",gg)
  }
}
