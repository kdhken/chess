package unitTest;

import junit.framework.TestCase;
import chessBoard.Board;
import chessGame.Player;

/**
 * General tests for chess game
 * 1. Checking whether each player gets one move at a time.
 */
public class ChessTest extends TestCase
{
	Player Wplayer = new Player(1);
	Player Bplayer = new Player(0);
	Board board = new Board(8,8,Wplayer,Bplayer);
	
	private void basicSetup()
	{
		assertEquals("Set White King", true, board.setPiece(Wplayer.getPieces(0), 0, 0));
		assertEquals("Set Black King", true, board.setPiece(Bplayer.getPieces(0), 7, 7));
	}
	
	public void testTurn()
	{
		basicSetup();
		assertEquals("White turn", false, board.move(Bplayer.getPieces(0), 7, 6));
		assertEquals("White turn", true, board.move(Wplayer.getPieces(0), 0, 1));
		assertEquals("Black turn", false, board.move(Wplayer.getPieces(0), 0, 2));
		assertEquals("Black turn", true, board.move(Bplayer.getPieces(0), 7, 6));
	}
	
}
