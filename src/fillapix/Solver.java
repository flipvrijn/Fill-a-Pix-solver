package fillapix;

/**
 *
 * @author flipvanrijn
 */
public class Solver
{
    private Grid grid;
    private int[][] numbersMatrix;

    public Solver(int[][] numbersMatrix)
    {
        this.numbersMatrix = numbersMatrix;
        grid = new Grid(numbersMatrix);
    }

    public void solve()
    {
        this.fillNine();
        this.fillZero();
        this.fillCornerFour();
        this.fillBorderSix();
        while ( ! grid.solved())
            this.finishNumber();
    }

    public void show()
    {
        System.out.print(grid);
    }

    public Grid getPicture()
    {
        return grid;
    }

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
