package fillapix;

/**
 * Class to represent a puzzle as a grid.
 * @author Flip van Rijn
 */
public class Grid
{
    /**
     * Enumeration of one square in the grid.
     */
    public enum Square
    {
        Cross, Black, Open;

        /**
         * String representation of one square.
         * @return String
         */
        @Override public String toString()
        {
            switch (this)
            {
                case Cross: return " ";
                case Black: return "#";
                case Open:  return " ";
            }
            
            return "";
        }
    }

    private int HEIGHT, WIDTH;
    private Square[][] matrix;

    /**
     * Constructor which initializes values based on a given numeric
     * multi-dimensional array.
     * @param numbersMap Numeric multi-dimensional array
     */
    public Grid(int[][] numbersMap)
    {
        this.HEIGHT = numbersMap.length;
        this.WIDTH = numbersMap[0].length;
        this.matrix = new Square[HEIGHT][WIDTH];
        initMatrix();
    }

    /**
     * String representation of the whole grid.
     * @return String
     */
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                string.append(" ").append(matrix[y][x]);
            }
            string.append("\n");
        }

        return string.toString();
    }

    /**
     * Fills all Open squares adjacent to the coordinate with the
     * specified enumeration type.
     * @param row The Y-coordinate
     * @param column The X-coordinate
     * @param kind The enumeration type Square
     */
    public void fillRemaining(int row, int column, Square kind)
    {
        for (int y = row - 1; y <= row + 1; y++)
        {
            for (int x = column - 1; x <= column + 1; x++)
            {
                if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && matrix[y][x] == Square.Open)
                    matrix[y][x] = kind;
            }
        }
    }

    /**
     * Returns the height of the puzzle.
     * @return int Height
     */
    public int getHeight()
    {
        return HEIGHT;
    }

    /**
     * Returns the width of the puzzle.
     * @return int Width
     */
    public int getWidth()
    {
        return WIDTH;
    }

    /**
     * Determines if the puzzle is solved already by looking at each square
     * and see if it encounters an Open square.
     * @return boolean Solved
     */
    public boolean solved()
    {
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                if (matrix[y][x] == Square.Open)
                    return false;

        return true;
    }

    /**
     * Counts all occurring squares adjacent to the coordinate with the
     * specified enumeration type.
     * @param row The Y-coordinate
     * @param column The X-coordinate
     * @param kind The enumeration type Square
     * @return int Amount of squares
     */
    public int countKind(int row, int column, Square kind)
    {
        int count = 0;
        for (int y = row - 1; y <= row + 1; y++)
        {
            for (int x = column - 1; x <= column + 1; x++)
            {
                if (x >= 0 && y >= 0 && y < HEIGHT && x < WIDTH && matrix[y][x] == kind)
                    count++;
            }
        }

        return count;
    }

    /**
     * Returns the whole matrix of the puzzle.
     * @return Square[][] The matrix
     */
    public Square[][] getMatrix()
    {
        return matrix;
    }

    /**
     * Initializes the matrix with Open squares.
     */
    private void initMatrix()
    {
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                matrix[y][x] = Square.Open;
    }
}
