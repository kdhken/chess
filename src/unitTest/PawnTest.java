package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import chessBoard.Board;
import chessGame.Player;

/**
 * Tests all the possible cases for Pawn piece
 * 1. Valid movement
 * 2. Capturing condition
 * 3. When player is checked, it can only move to square where it can protect king or it cannot move
 * and so forth
 */
public class PawnTest
{
	Player Wplayer = new Player(1);
	Player Bplayer = new Player(0);
	Board board = new Board(8,8,Wplayer,Bplayer);
	
	private void basicSetup()
	{
		assertEquals("Set White King", true, board.setPiece(Wplayer.getPieces(0), 7, 0));
		assertEquals("Set Black King", true, board.setPiece(Bplayer.getPieces(0), 7, 7));
		assertEquals("Set White Pawn", true, board.setPiece(Wplayer.getPieces(8), 3, 3));
		assertEquals("Set Black Pawn", true, board.setPiece(Bplayer.getPieces(8), 4, 4));
	}
	
	@Test
	public void whitePawnMoveUp2()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(8), 3, 1));
		assertEquals(Wplayer.getPieces(8), board.getGrid(3,1).getPiece());
	}
	
	@Test
	public void whitePawanMoveUp1()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(8), 3, 2));
		assertEquals(Wplayer.getPieces(8), board.getGrid(3,2).getPiece());
	}
	
	@Test
	public void blackPawnMoveDown2()
	{
		basicSetup();
		board.setTurn(0);
		assertEquals(true, board.move(Bplayer.getPieces(8), 4, 6));
		assertEquals(Bplayer.getPieces(8), board.getGrid(4,6).getPiece());
	}
	
	@Test
	public void blackPawanMoveDown1()
	{
		basicSetup();
		board.setTurn(0);
		assertEquals(true, board.move(Bplayer.getPieces(8), 4, 5));
		assertEquals(Bplayer.getPieces(8), board.getGrid(4,5).getPiece());
	}
	
	@Test
	public void whitePawanMoveUp1AfterFirstMove()
	{
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(8), 3, 2));
		board.setTurn(1);
		assertEquals(true, board.move(Wplayer.getPieces(8), 3, 1));
	}
	
	@Test
	public void blackPawnMoveDown1AfterFirstMove()
	{
		basicSetup();
		board.setTurn(0);
		assertEquals(true, board.move(Bplayer.getPieces(8), 4, 5));
		board.setTurn(0);
		assertEquals(true, board.move(Bplayer.getPieces(8), 4, 6));
	}
	
	@Test
	public void outOfBound()
	{
		basicSetup();
		assertEquals("OutOfBound", false, board.move(Wplayer.getPieces(8), -1, 1));
		assertEquals("Stay", Wplayer.getPieces(8), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void whitePawnMoveDown()
	{
		basicSetup();
		assertEquals("invalidMove", false, board.move(Wplayer.getPieces(8), 3, 4));
		assertEquals("Stay", Wplayer.getPieces(8), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void blackPawnMoveUp()
	{
		basicSetup();
		board.setTurn(0);
		assertEquals("invalidMove", false, board.move(Bplayer.getPieces(8), 4, 3));
		assertEquals("Stay", Bplayer.getPieces(8), board.getGrid(4,4).getPiece());
	}
	
	@Test
	public void whitePawnMoveRow()
	{
		basicSetup();
		assertEquals("invalidMove", false, board.move(Wplayer.getPieces(8), 2, 3));
		assertEquals("Stay", Wplayer.getPieces(8), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void blackPawnMoveRow()
	{
		basicSetup();
		board.setTurn(0);
		assertEquals("invalidMove", false, board.move(Bplayer.getPieces(8), 5, 4));
		assertEquals("Stay", Bplayer.getPieces(8), board.getGrid(4,4).getPiece());
	}
	
	@Test
	public void blocked()
	{	
		basicSetup();
		assertEquals("Set Black Rook", true, board.setPiece(Bplayer.getPieces(2), 3, 2));
		assertEquals("blocked", false, board.move(Wplayer.getPieces(8), 3, 2));
		assertEquals("Stay", Wplayer.getPieces(8), board.getGrid(3,3).getPiece());
	}
	
	@Test
	public void whitePawnMoveUp2AfterFirstMove()
	{	
		basicSetup();
		assertEquals(true, board.move(Wplayer.getPieces(8), 3, 2));
		board.setTurn(1);
		assertEquals(false, board.move(Wplayer.getPieces(8), 3, 0));
	}
	
	@Test
	public void blackPawnMoveDown2AfterFirstMove()
	{	
		basicSetup();
		board.setTurn(0);
		assertEquals(true, board.move(Bplayer.getPieces(8), 4, 5));
		board.setTurn(0);
		assertEquals(false, board.move(Bplayer.getPieces(8), 4, 7));
	}
	
	@Test
	public void whitePawnCapture()
	{
		basicSetup();
		assertEquals("Set Black Queen", true, board.setPiece(Bplayer.getPieces(1), 4, 2));
		assertEquals(true, board.move(Wplayer.getPieces(8), 4, 2));
		assertEquals(Wplayer.getPieces(8), board.getGrid(4,2).getPiece());
	}
	
	@Test
	public void blackPawnCapture()
	{
		basicSetup();
		board.setTurn(0);
		assertEquals("Set White Queen", true, board.setPiece(Wplayer.getPieces(1), 3, 5));
		assertEquals(true, board.move(Bplayer.getPieces(8), 3, 5));
		assertEquals(Bplayer.getPieces(8), board.getGrid(3,5).getPiece());
	}

	@Test
	public void playerIsChecked()
	{
		basicSetup();
		assertEquals("Set Black Bishop", true, board.setPiece(Bplayer.getPieces(6), 4, 3));
		assertEquals(false, board.move(Wplayer.getPieces(8), 3, 2));
		assertEquals("Stay", Wplayer.getPieces(8), board.getGrid(3,3).getPiece());
	}
	
}
