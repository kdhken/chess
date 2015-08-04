package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import chessBoard.Board;
import chessGame.Player;

/**
 * Tests all the possible cases for King piece
 * 1. Valid movement
 * 2. Capturing condition
 * 3. When player is checked, it can only move to square where King is not under opponent's attackZone
 * and so forth
 */
public class KingTest
{
	Player Wplayer = new Player(1);
	Player Bplayer = new Player(0);
	Board board = new Board(8,8,Wplayer,Bplayer);
	
	private void basicSetup()
	{
		assertEquals("Set White King", true, board.setPiece(Wplayer.getPieces(0), 3, 3));
		assertEquals("Set Black King", true, board.setPiece(Bplayer.getPieces(0), 7, 7));
	}
	
	@Test
	public void moveLeft()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(0), 2, 3));
		assertEquals(Wplayer.getPieces(0), board.getGrid(2,3).getPiece());
	}
	
	@Test
	public void moveDownLeft()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(0), 2, 4));
		assertEquals(Wplayer.getPieces(0), board.getGrid(2,4).getPiece());
	}
	
	@Test
	public void moveDown()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(0), 3, 4));
		assertEquals(Wplayer.getPieces(0), board.getGrid(3,4).getPiece());
	}
	
	@Test
	public void moveDownRight()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(0), 4, 4));
		assertEquals(Wplayer.getPieces(0), board.getGrid(4,4).getPiece());
	}
	
	@Test
	public void Right()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(0), 4, 3));
		assertEquals(Wplayer.getPieces(0), board.getGrid(4,3).getPiece());
	}
	
	@Test
	public void moveUpRight()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(0), 4, 2));
		assertEquals(Wplayer.getPieces(0), board.getGrid(4,2).getPiece());
	}
	
	@Test
	public void Up()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(0), 3, 2));
		assertEquals(Wplayer.getPieces(0), board.getGrid(3,2).getPiece());
	}
	
	@Test
	public void moveUpLeft()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(0), 2, 2));
		assertEquals(Wplayer.getPieces(0), board.getGrid(2,2).getPiece());
	}
	
	@Test
	public void outOfBound()
	{
		basicSetup();
		assertEquals("OutOfBound", false, board.move(Wplayer.getPieces(0), -1, 1));
		assertEquals("Stay", Wplayer.getPieces(0), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void invalidMove()
	{
		basicSetup();
		assertEquals("invalidMove", false, board.move(Wplayer.getPieces(0), 5, 5));
		assertEquals("Stay", Wplayer.getPieces(0), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void Capture()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 4, 4));
		assertEquals("Capture", true, board.move(Wplayer.getPieces(0), 4, 4));
		assertEquals("Capture", Wplayer.getPieces(0), board.getGrid(4,4).getPiece());
	}

	@Test
	public void moveToDangerousZone()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 4, 4));
		assertEquals("Dangerous", false, board.move(Wplayer.getPieces(0), 2, 2));
		assertEquals("Stay", Wplayer.getPieces(0), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void avoidCheck()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 4, 4));
		assertEquals("avoid", true, board.move(Wplayer.getPieces(0), 3, 4));
		assertEquals("Safe", Wplayer.getPieces(0), board.getGrid(3,4).getPiece());
	}

}
