package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import chessBoard.Board;
import chessGame.Player;

/**
 * Tests all the possible cases for Knight piece
 * 1. Valid movement
 * 2. Capturing condition
 * 3. When player is checked, it can only move to square where it can protect king or it cannot move
 * and so forth
 */
public class KnightTest
{
	Player Wplayer = new Player(1);
	Player Bplayer = new Player(0);
	Board board = new Board(8,8,Wplayer,Bplayer);
	
	private void basicSetup()
	{
		assertEquals("Set White King", true, board.setPiece(Wplayer.getPieces(0), 7, 0));
		assertEquals("Set Black King", true, board.setPiece(Bplayer.getPieces(0), 7, 7));
		assertEquals("Set White Knight", true, board.setPiece(Wplayer.getPieces(4), 3, 3));
	}
	
	@Test
	public void moveDown2Left1()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(4), 2, 5));
		assertEquals(Wplayer.getPieces(4), board.getGrid(2,5).getPiece());
	}
	
	@Test
	public void moveDown1Left2()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(4), 1, 4));
		assertEquals(Wplayer.getPieces(4), board.getGrid(1,4).getPiece());
	}
	
	@Test
	public void moveDown1Right2()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(4), 5, 4));
		assertEquals(Wplayer.getPieces(4), board.getGrid(5,4).getPiece());
	}
	
	@Test
	public void moveDown2Right1()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(4), 4, 5));
		assertEquals(Wplayer.getPieces(4), board.getGrid(4,5).getPiece());
	}
	
	@Test
	public void moveUp2Right1()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(4), 4, 1));
		assertEquals(Wplayer.getPieces(4), board.getGrid(4,1).getPiece());
	}
	
	@Test
	public void moveUp1Right2()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(4), 5, 2));
		assertEquals(Wplayer.getPieces(4), board.getGrid(5,2).getPiece());
	}
	
	@Test
	public void moveUp2Left1()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(4), 2, 1));
		assertEquals(Wplayer.getPieces(4), board.getGrid(2,1).getPiece());
	}
	
	@Test
	public void moveUp1Left2()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(4), 1, 2));
		assertEquals(Wplayer.getPieces(4), board.getGrid(1,2).getPiece());
	}
	
	@Test
	public void outOfBound()
	{
		basicSetup();
		assertEquals("OutOfBound", false, board.move(Wplayer.getPieces(4), -1, 1));
		assertEquals("Stay", Wplayer.getPieces(4), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void invalidMove()
	{
		basicSetup();
		assertEquals("invalidMove", false, board.move(Wplayer.getPieces(6), 4, 4));
		assertEquals("Stay", Wplayer.getPieces(4), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void Capture()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 2, 1));
		assertEquals("Capture", true, board.move(Wplayer.getPieces(4), 2, 1));
		assertEquals("Capture", Wplayer.getPieces(4), board.getGrid(2,1).getPiece());
	}

	@Test
	public void playerIsChecked()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 4, 3));
		assertEquals("Dangerous", false, board.move(Wplayer.getPieces(4), 5, 4));
		assertEquals("Stay", Wplayer.getPieces(4), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void protectKing()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 4, 3));
		assertEquals("protect", true, board.move(Wplayer.getPieces(4), 5, 2));
		assertEquals("protect", Wplayer.getPieces(4), board.getGrid(5,2).getPiece());
	}
	
	@Test
	public void jump()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 2, 3));
		assertEquals("jump", true, board.move(Wplayer.getPieces(4), 1, 4));
		assertEquals("jump", Wplayer.getPieces(4), board.getGrid(1,4).getPiece());
	}
}
