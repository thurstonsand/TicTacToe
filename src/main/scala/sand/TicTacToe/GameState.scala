package sand.TicTacToe

case class GameGraph(play: Int, playersMove: Int, board: Board, nextPlays: Seq[GameGraph])
