package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import chessBoard.Board;
import chessGame.Player;

/**
 * Tests all the possible cases for Protector piece
 * 1. Valid movement
 * 2. Capturing condition
 * 3. When player is checked, it can only move to square where it can protect king or it cannot move
 * and so forth
 */
public class ProtectorTest
{
	Player Wplayer = new Player(1);
	Player Bplayer = new Player(0);
	Board board = new Board(8,8,Wplayer,Bplayer);
	
	private void basicSetup()
	{
		assertEquals("Set White King", true, board.setPiece(Wplayer.getPieces(0), 7, 0));
		assertEquals("Set Black King", true, board.setPiece(Bplayer.getPieces(0), 7, 7));
		assertEquals("Set White Rook", true, board.setPiece(Wplayer.getPieces(17), 3, 3));
	}
	
	@Test
	public void moveJumpLeft()
	{
		basicSetup();
		board.setPiece(Bplayer.getPieces(8), 2, 3);
		assertEquals(true, board.move(Wplayer.getPieces(17), 1, 3));
		assertEquals(Wplayer.getPieces(17), board.getGrid(1,3).getPiece());
	}
	
	@Test
	public void moveJumpDown()
	{
		basicSetup();
		board.setPiece(Bplayer.getPieces(8), 3, 4);
		assertEquals(true, board.move(Wplayer.getPieces(17), 3, 5));
		assertEquals(Wplayer.getPieces(17), board.getGrid(3,5).getPiece());
	}
	
	@Test
	public void moveJumpRight()
	{
		basicSetup();
		board.setPiece(Bplayer.getPieces(8), 4, 3);
		assertEquals(true, board.move(Wplayer.getPieces(17), 5, 3));
		assertEquals(Wplayer.getPieces(17), board.getGrid(5,3).getPiece());
	}
	
	@Test
	public void moveJumpUp()
	{
		basicSetup();
		board.setPiece(Bplayer.getPieces(8), 3, 2);
		assertEquals(true, board.move(Wplayer.getPieces(17), 3, 1));
		assertEquals(Wplayer.getPieces(17), board.getGrid(3,1).getPiece());
	}
	
	@Test
	public void outOfBound()
	{
		basicSetup();
		assertEquals("OutOfBound", false, board.move(Wplayer.getPieces(17), -1, 1));
		assertEquals("Stay", Wplayer.getPieces(17), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void invalidMove()
	{
		basicSetup();
		assertEquals("invalidMove", false, board.move(Wplayer.getPieces(17), 5, 5));
		assertEquals("Stay", Wplayer.getPieces(17), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void moveLeft()
	{
		basicSetup();
		assertEquals(false, board.move(Wplayer.getPieces(17), 1, 3));
		assertEquals("Stay", Wplayer.getPieces(17), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void moveDown()
	{
		basicSetup();
		assertEquals(false, board.move(Wplayer.getPieces(17), 3, 5));
		assertEquals("Stay", Wplayer.getPieces(17), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void moveRight()
	{
		basicSetup();
		assertEquals(false, board.move(Wplayer.getPieces(17), 5, 3));
		assertEquals("Stay", Wplayer.getPieces(17), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void moveUp()
	{
		basicSetup();
		assertEquals(false, board.move(Wplayer.getPieces(17), 3, 1));
		assertEquals("Stay", Wplayer.getPieces(17), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void Capture()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 3, 5));
		assertEquals("Set White Bishop", true, board.setPiece(Wplayer.getPieces(6), 3, 4));
		assertEquals("Capture", true, board.move(Wplayer.getPieces(17), 3, 5));
	}

	@Test
	public void playerIsChecked()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 4, 3));
		assertEquals("Dangerous", false, board.move(Wplayer.getPieces(17), 2, 3));
		assertEquals("Stay", Wplayer.getPieces(17), board.getGrid(3,3).getPiece());
	}

	
}
