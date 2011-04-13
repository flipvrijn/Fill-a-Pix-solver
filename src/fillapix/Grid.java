package fillapix;

/**
 *
 * @author flipvanrijn
 */
public class Grid
{
    public enum Square
    {
        Cross, Black, Open;

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

    public Grid(int[][] numbersMap)
    {
        this.HEIGHT = numbersMap.length;
        this.WIDTH = numbersMap[0].length;
        this.matrix = new Square[HEIGHT][WIDTH];
        initMatrix();
    }

    @Override
    public String toString()
    {
        String string = new String();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                string += " " + matrix[y][x];
            }
            string += "\n";
        }

        return string;
    }

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

    public int getHeight()
    {
        return HEIGHT;
    }

    public int getWidth()
    {
        return WIDTH;
    }

    public boolean solved()
    {
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                if (matrix[y][x] == Square.Open)
                    return false;

        return true;
    }

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

    public Square[][] getMatrix()
    {
        return matrix;
    }

    private void initMatrix()
    {
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                matrix[y][x] = Square.Open;
    }
}
