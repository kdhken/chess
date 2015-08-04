package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import chessBoard.Board;
import chessGame.Player;

/**
 * Tests all the possible cases for KnightQueen piece
 * 1. Valid movement
 * 2. Capturing condition
 * 3. When player is checked, it can only move to square where it can protect king or it cannot move
 * and so forth
 */
public class KnightQueenTest
{
	Player Wplayer = new Player(1);
	Player Bplayer = new Player(0);
	Board board = new Board(8,8,Wplayer,Bplayer);
	
	private void basicSetup()
	{
		assertEquals("Set White King", true, board.setPiece(Wplayer.getPieces(0), 7, 0));
		assertEquals("Set Black King", true, board.setPiece(Bplayer.getPieces(0), 7, 7));
		assertEquals("Set White KnightQueen", true, board.setPiece(Wplayer.getPieces(16), 3, 3));
	}
	
	@Test
	public void moveLeft()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(16), 1, 3));
		assertEquals(Wplayer.getPieces(16), board.getGrid(1,3).getPiece());
	}
	
	@Test
	public void moveDownLeft()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(16), 1, 5));
		assertEquals(Wplayer.getPieces(16), board.getGrid(1,5).getPiece());
	}
	
	@Test
	public void moveDown()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(16), 3, 5));
		assertEquals(Wplayer.getPieces(16), board.getGrid(3,5).getPiece());
	}
	
	@Test
	public void moveDownRight()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(16), 5, 5));
		assertEquals(Wplayer.getPieces(16), board.getGrid(5,5).getPiece());
	}
	
	@Test
	public void Right()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(16), 5, 3));
		assertEquals(Wplayer.getPieces(16), board.getGrid(5,3).getPiece());
	}
	
	@Test
	public void moveUpRight()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(16), 5, 1));
		assertEquals(Wplayer.getPieces(16), board.getGrid(5,1).getPiece());
	}
	
	@Test
	public void Up()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(16), 3, 1));
		assertEquals(Wplayer.getPieces(16), board.getGrid(3,1).getPiece());
	}
	
	@Test
	public void moveUpLeft()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(16), 1, 1));
		assertEquals(Wplayer.getPieces(16), board.getGrid(1,1).getPiece());
	}
	
	@Test
	public void outOfBound()
	{
		basicSetup();
		assertEquals("OutOfBound", false, board.move(Wplayer.getPieces(16), -1, 1));
		assertEquals("Stay", Wplayer.getPieces(16), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void invalidMove()
	{
		basicSetup();
		assertEquals("invalidMove", false, board.move(Wplayer.getPieces(16), 2, 5));
		assertEquals("Stay", Wplayer.getPieces(16), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void cannotBlocked()
	{	
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 3, 5));
		assertEquals("Cannot Blocked", true, board.move(Wplayer.getPieces(16), 3, 6));
	}
	
	@Test
	public void Capture()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 3, 5));
		assertEquals("Capture", true, board.move(Wplayer.getPieces(16), 3, 5));
		assertEquals("Capture", Wplayer.getPieces(16), board.getGrid(3,5).getPiece());
	}

	@Test
	public void playerIsChecked()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 4, 3));
		assertEquals("Dangerous", false, board.move(Wplayer.getPieces(16), 2, 3));
		assertEquals("Stay", Wplayer.getPieces(16), board.getGrid(3,3).getPiece());
	}
	
}
