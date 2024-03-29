package sand.TicTacToe

/**
 * Created with IntelliJ IDEA.
 * User: Bob Sandberg
 * Date: 1/5/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
case class Board(n: Int, c: Array[Int]) {

  val boardSize = n
  val cells = c

  def rc2Idx(r: Int, c: Int) = r * boardSize + c
  def cell(r: Int, c: Int) = cells(rc2Idx(r,c))

  def modified(idx: Int, newCV: Int) : Board = {
    val newCells = cells.zipWithIndex.map{ case (cv,i) => if( i==idx) newCV else cv }
    new Board(boardSize, newCells)
  }

  def modified(r: Int, c: Int, newCV: Int) : Board = modified(rc2Idx(r,c), newCV)

  override def hashCode = cells reduceLeft ( _ * 4 + _ )

  override def equals(other: Any) = other match {
    case otherB: Board => otherB.hashCode == this.hashCode
    case _ => false
  }

  override def toString = "%05X / %s".format(hashCode, cells.mkString(" "))
}

object Board {

  def mkInitialBoard(boardSize: Int, initCellValue: Int) = {
    Board(boardSize, Array.fill[Int](boardSize*boardSize)(initCellValue))
  }
}
