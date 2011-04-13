package fillapix;

/**
 * A small Fill-a-Pix solver.
 * @author Flip van Rijn
 */
public class Main
{

    /**
     * @param args The command line arguments
     */
    public static void main(String[] args)
    {
        FileHandler handler = new FileHandler("puzzle4.txt");
        int[][] matrix = handler.read();
        Solver solver = new Solver(matrix);
        solver.solve();
        solver.show();

        handler.toImage("puzzle4.gif", solver.getPicture());
    }
}