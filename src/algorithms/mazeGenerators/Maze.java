package algorithms.mazeGenerators;

public class Maze {
    private int[][] maze;
    private Position start;
    private Position goal;
    private int rows;
    private int cols;

    public Maze(int rows, int cols) {
        this.maze = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    /* START AND GOAL POSITIONS*/
    public void setStart(Position start) {
        if (isLegalPosition(start)) {
            maze[start.getRowIndex()][start.getColumnIndex()] = 0;
            this.start = start;
        }
    }

    public void setGoal(Position goal) {
        if (isLegalPosition(start)) {
            maze[start.getRowIndex()][start.getColumnIndex()] = 0;
            this.goal = goal;
        }
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }

    public boolean isStart(int i, int j) {
        return (i == this.start.getRowIndex() && j == this.start.getColumnIndex());
    }

    public boolean isGoal(int i, int j) {
        return (i == this.goal.getRowIndex() && j == this.goal.getColumnIndex());
    }

    /* PATH AND WALL POSITIONS*/

    /**
     * this function set value '0' in an appropriate cell of a maze
     *
     * @param position -
     */
    public void setPath(Position position) {
        if (isLegalPosition(position))
            maze[position.getRowIndex()][position.getColumnIndex()] = 0;
    }

    public void setPath(int row, int col) {
        Position position = new Position(row, col);
        if (isLegalPosition(position))
            maze[row][col] = 0;
    }


    protected void setWall(Position position) {
        if (isLegalPosition(position))
            maze[position.getRowIndex()][position.getColumnIndex()] = 1;
    }

    public void setWall(int i, int j) {
        Position position = new Position(i, j);
        if (isLegalPosition(position))
            maze[i][j] = 1;
    }

    public boolean isWall(Position position) {
        if (isLegalPosition(position))
            return this.maze[position.getRowIndex()][position.getColumnIndex()] == 1;
        return false;
    }

    public boolean isPath(Position position) {
        if (isLegalPosition(position))
            return this.maze[position.getRowIndex()][position.getColumnIndex()] == 0;
        return false;
    }

    public boolean isLegalPosition(Position position) {
        boolean isLegalRow = position.getRowIndex() >= 0 && position.getRowIndex() < this.rows;
        boolean isLegalCol = position.getColumnIndex() >= 0 && position.getColumnIndex() < this.cols;
        return isLegalRow && isLegalCol;
    }

    /**
     * this function prints the maze with his special position- when:
     * 'S' marks a start position and 'E' marks a goal position
     */
    public void print() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isStart(row, col))
                    System.out.print("S");
                else if (isGoal(row, col))
                    System.out.print("E");
                else
                    System.out.print(this.maze[row][col]);
            }
            System.out.println();
        }
    }

    //Maze Rows, Maze Cols, Start Position (rows then cols), Goal Position (rows then cols)
    //After that - maze positions represented as bytes.
    // (Yeah I know it looks bad)
    public byte[] toByteArray() {
        String readByte = "";
        int counter = 12;

        int totalMazeSize = this.cols*this.rows;
        byte[] mazeAsBytes = new byte[13+(totalMazeSize/8)];
        mazeAsBytes[0] = ((Integer) (rows/255)).byteValue();
        mazeAsBytes[1] = ((Integer) (rows%255)).byteValue();
        mazeAsBytes[2] = ((Integer) (cols/255)).byteValue();
        mazeAsBytes[3] = ((Integer) (cols%255)).byteValue();
        mazeAsBytes[4] = ((Integer) (start.getRowIndex()/255)).byteValue();
        mazeAsBytes[5] = ((Integer) (start.getRowIndex()%255)).byteValue();
        mazeAsBytes[6] = ((Integer) (start.getColumnIndex()/255)).byteValue();
        mazeAsBytes[7] = ((Integer) (start.getColumnIndex()%255)).byteValue();
        mazeAsBytes[8] = ((Integer) (goal.getRowIndex()/255)).byteValue();
        mazeAsBytes[9] = ((Integer) (goal.getRowIndex()%255)).byteValue();
        mazeAsBytes[10] = ((Integer) (goal.getColumnIndex()/255)).byteValue();
        mazeAsBytes[11] = ((Integer) (goal.getColumnIndex()%255)).byteValue();

        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                if (readByte.length() < 8){
                    readByte = readByte + Integer.toString(maze[i][j]);
                }
                else if (readByte.length() == 8){
                    mazeAsBytes[counter] = Byte.parseByte(readByte, 2);
                    readByte = "";
                    counter++;
                }
            }
        }
        while (readByte.length() < 8){
            readByte = readByte + Integer.toString(0);
        }
        mazeAsBytes[counter] = Byte.parseByte(readByte, 2);
        return mazeAsBytes;
    }


}
