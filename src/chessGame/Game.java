package chessGame;

import chessBoard.Board;

/**
 * Create new board with initial piece setup 
 */
public class Game
{	
	Board board;
	Player[] players;
	
	public Board getBoard() {
		return board;
	}
	public Player getPlayers(int player) {
		return players[player];
	}

	/*Constructor*/
	public Game()
	{
		players = new Player[2];
		players[1] = new Player(1);
		players[0] = new Player(0);
		board = new Board(8,8,players[1],players[0]);
		board.setPiece(players[0].getPieces(2), 0, 0);
		board.setPiece(players[0].getPieces(4), 1, 0);
		board.setPiece(players[0].getPieces(6), 2, 0);
		board.setPiece(players[0].getPieces(1), 3, 0);
		board.setPiece(players[0].getPieces(0), 4, 0);
		board.setPiece(players[0].getPieces(7), 5, 0);
		board.setPiece(players[0].getPieces(5), 6, 0);
		board.setPiece(players[0].getPieces(3), 7, 0);
		for(int pawnI=8; pawnI<16; pawnI++)
			board.setPiece(players[0].getPieces(pawnI), pawnI-8, 1);
		board.setPiece(players[1].getPieces(2), 0, 7);
		board.setPiece(players[1].getPieces(4), 1, 7);
		board.setPiece(players[1].getPieces(6), 2, 7);
		board.setPiece(players[1].getPieces(1), 3, 7);
		board.setPiece(players[1].getPieces(0), 4, 7);
		board.setPiece(players[1].getPieces(7), 5, 7);
		board.setPiece(players[1].getPieces(5), 6, 7);
		board.setPiece(players[1].getPieces(3), 7, 7);
		for(int pawnI=8; pawnI<16; pawnI++)
			board.setPiece(players[1].getPieces(pawnI), pawnI-8, 6);
	}
}
