package fillapix;

/**
 * Class of the solver for a Fill-a-Pix puzzle.
 * @author Flip van Rijn
 */
public class Solver
{
    private Grid grid;
    private int[][] numbersMatrix;

    /**
     * Constructor which crates a new Grid of a numeric matrix.
     * @param numbersMatrix The matrix with numbers
     */
    public Solver(int[][] numbersMatrix)
    {
        this.numbersMatrix = numbersMatrix;
        grid = new Grid(numbersMatrix);
    }

    /**
     * Solves a Fill-a-Pix puzzle with basic heuristics.
     */
    public void solve()
    {
        this.fillNine();
        this.fillZero();
        this.fillCornerFour();
        this.fillBorderSix();
        while ( ! grid.solved())
            this.finishNumber();
    }

    /**
     * Prints the puzzle grid to STDOUT.
     */
    public void show()
    {
        System.out.print(grid);
    }

    /**
     * Returns the Grid object
     * @return Grid
     */
    public Grid getPicture()
    {
        return grid;
    }

    /**
     * Completes a square based on the number. If there are enough Black
     * squares, the other adjacent squares must be Cross. If there are enough
     * Cross squares, the other adjacent squares must be Black.
     */
    private void finishNumber()
    {
        for (int y = 0; y < grid.getHeight(); y++)
        {
            for (int x = 0; x < grid.getWidth(); x++)
            {
                if (numbersMatrix[y][x] != -1 && numbersMatrix[y][x] != 9 && numbersMatrix[y][x] != 0)
                {
                    if (numbersMatrix[y][x] - grid.countKind(y, x, Grid.Square.Black) == 0)
                        grid.fillRemaining(y, x, Grid.Square.Cross);

                    if (numbersMatrix[y][x] - grid.countKind(y, x, Grid.Square.Black) - grid.countKind(y, x, Grid.Square.Open) == 0)
                        grid.fillRemaining(y, x, Grid.Square.Black);
                }
            }
        }
    }

    /**
     * Fills all squares adjacent to a square with a nine in it.
     */
    private void fillNine()
    {
        for (int y = 1; y < grid.getHeight(); y++)
        {
            for (int x = 1; x < grid.getWidth(); x++)
            {
                if (numbersMatrix[y][x] == 9)
                    grid.fillRemaining(y, x, Grid.Square.Black);
            }
        }
    }

    /**
     * Crosses all squares adjacent to a square with a zero in it.
     */
    private void fillZero()
    {
        for (int y = 0; y < grid.getHeight(); y++)
        {
            for (int x = 0; x < grid.getWidth(); x++)
            {
                if (numbersMatrix[y][x] == 0)
                    grid.fillRemaining(y, x, Grid.Square.Cross);
            }
        }
    }

    /**
     * Fills all squares adjacent to the corner square with a four in it.
     */
    private void fillCornerFour()
    {
        if (numbersMatrix[0][0] == 4)
            grid.fillRemaining(0, 0, Grid.Square.Black);

        if (numbersMatrix[0][grid.getWidth() - 1] == 4)
            grid.fillRemaining(0, grid.getWidth() - 1, Grid.Square.Black);

        if (numbersMatrix[grid.getHeight() - 1][0] == 4)
            grid.fillRemaining(grid.getHeight() - 1, 0, Grid.Square.Black);

        if (numbersMatrix[grid.getHeight() - 1][grid.getWidth() - 1] == 4)
            grid.fillRemaining(grid.getHeight() - 1, grid.getWidth() - 1, Grid.Square.Black);
    }

    /**
     * Filles all squares adjacent to the border square with a six in it.
     */
    private void fillBorderSix()
    {
        for (int y = 0; y < grid.getHeight(); y++)
        {
            if (y == 0)
            {
                for (int x = 0; x < grid.getWidth(); x++)
                {
                    if (numbersMatrix[y][x] == 6)
                        grid.fillRemaining(y, x, Grid.Square.Black);
                }
            }
            else if (y == grid.getHeight() - 1)
            {
                for (int x = 0; x < grid.getWidth(); x++)
                {
                    if (numbersMatrix[y][x] == 6)
                        grid.fillRemaining(y, x, Grid.Square.Black);
                }
            }
            else
            {
                if (numbersMatrix[y][0] == 6)
                    grid.fillRemaining(y, 0, Grid.Square.Black);
                if (numbersMatrix[y][grid.getWidth() - 1] == 6)
                    grid.fillRemaining(y, grid.getWidth() - 1, Grid.Square.Black);
            }

        }
    }
        
}
