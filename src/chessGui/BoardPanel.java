package chessGui;

import java.awt.*;

import javax.swing.*;

import chessBoard.Board;
import chessPiece.Piece;

/**
 * On the main Panel, this class provides the board Panel
 * updateBoard method updates the location of pieces.
 */
public class BoardPanel extends JPanel
{
	private static final String blackKing = "\u265A";
	private final String blackQueen = "\u265B";
	private final String blackRook = "\u265C";
	private final String blackBishop = "\u265D";
	private final String blackKnight = "\u265E";
	private final String blackPawn = "\u265F";
	private final String blackKnightQueen = "\u263B";
	private final String blackProtector = "\u2605";
	
	private final String whiteKing = "\u2654";
	private final String whiteQueen = "\u2655";
	private final String whiteRook = "\u2656";
	private final String whiteBishop = "\u2657";
	private final String whiteKnight = "\u2658";
	private final String whitePawn = "\u2659";
	private final String whiteKnightQueen = "\u263A";
	private final String whiteProtector = "\u2606";
	
	private JButton[][] squares = new JButton[8][8];
	
	private Board board;
	private Font font = new Font("Ariel", Font.PLAIN, 50);

	public JButton getSquares(int row, int col) {
		return squares[row][col];
	}
	
	/*Constructor*/
	public BoardPanel(Board board, Piece piece)
	{
		this.board = board;
		updateBoard(piece);
		
		setLayout(new GridLayout(8, 8));

        for(int col=0; col<8; col++)
        	for(int row=0; row<8; row++)
        	{
        		add(getSquares(row,col));
        	}
	}
	
	/**
	 * 1:King, 2:Queen, 3:Rook, 4:Knight, 5:Bishop, 6:Pawn
	 * On pieces String, "" is for 0 index because pieceNumber starts with 1. 
	 */
    public void updateBoard(Piece piece)
    {
    	for(int row=0; row<8; row++)
			for(int col=0; col<8; col++)
			{
				if(getPiece(row,col)==null)
					squares[row][col] = new JButton("");
				else if(getPiece(row,col).getIsWhite()==0)
				{
					String[] pieces = {"",blackKing,blackQueen,blackRook,blackKnight,blackBishop,blackPawn,blackKnightQueen,blackProtector};
					squares[row][col] = new JButton(pieces[getPieceNumber(row,col)]);
				}
				else
				{
					String[] pieces = {"",whiteKing,whiteQueen,whiteRook,whiteKnight,whiteBishop,whitePawn,whiteKnightQueen,whiteProtector};
					squares[row][col] = new JButton(pieces[getPieceNumber(row,col)]);
				}
				squares[row][col].setFont(font);
				squares[row][col].setBackground((row+col)%2 == 0 ? Color.WHITE : Color.GRAY);				
			}
    	movableSquare(piece);
    }
    
    private void movableSquare(Piece piece)
    {
    	if(piece == null)
    		return;
    	
    	for(int row=0; row<8; row++)
			for(int col=0; col<8; col++)
			{
				int movable = board.canMove(piece, row, col);
				if(movable == 1)
					squares[row][col].setBackground(Color.GREEN);
				else if(movable == 2)
					squares[row][col].setBackground(Color.ORANGE);
			}
    }

    private Piece getPiece(int row, int col)
	{
		return board.getGrid(row, col).getPiece();
	}
	private int getPieceNumber(int row, int col)
	{
		return board.getGrid(row, col).getPiece().getPieceNumber();
	}

}