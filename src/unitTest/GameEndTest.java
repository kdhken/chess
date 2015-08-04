package unitTest;

import junit.framework.TestCase;
import chessBoard.Board;
import chessGame.Player;

/**
 * Check for game ending conditions
 * 1. When player is in check
 * 2. Check Mate
 * 3. Stale Mate
 */
public class GameEndTest extends TestCase
{
	Player Wplayer = new Player(1);
	Player Bplayer = new Player(0);
	Board board = new Board(8,8,Wplayer,Bplayer);
	
	private void basicSetup()
	{
		assertEquals("Set White King", true, board.setPiece(Wplayer.getPieces(0), 7, 0));
		assertEquals("Set Black King", true, board.setPiece(Bplayer.getPieces(0), 7, 7));
		assertEquals("Set White Queen", true, board.setPiece(Wplayer.getPieces(1), 1, 0));
	}
	
	public void testCheck()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(1), 0, 0));
	}
	
	public void testCheckMate()
	{
		basicSetup();
		assertEquals("Set White Rook1", true, board.setPiece(Wplayer.getPieces(2), 6, 0));
		assertEquals("Set White Rook2", true, board.setPiece(Wplayer.getPieces(3), 0, 6));
		assertEquals(true, board.move(Wplayer.getPieces(1), 0, 0));
	}
	
	public void testKingStale()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(1), 7, 6));
		assertEquals(true, board.move(Bplayer.getPieces(0), 7, 6));
	}
	
	public void testStuckStale()
	{
		basicSetup();
		assertEquals("Set White Rook1", true, board.setPiece(Wplayer.getPieces(2), 6, 0));
		assertEquals(true, board.move(Wplayer.getPieces(1), 1, 6));
	}
	
}
