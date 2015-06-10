import java.util.*;

public class Maze {

  private int[][] maze = { 
      {1,1,1,1,1,1,1,1,1,1,1,1},
      {1,0,1,1,1,1,1,0,0,0,0,1},
      {1,0,0,0,0,0,1,0,1,0,0,1},
      {1,0,0,0,1,0,1,0,0,0,0,1},
      {1,0,1,0,1,0,1,0,1,1,0,1},
      {1,0,1,0,1,0,1,0,1,0,0,1},
      {1,0,1,1,1,0,1,0,1,0,1,1},
      {1,0,1,0,0,0,1,0,1,0,1,1},
      {1,0,1,0,1,1,1,0,1,0,0,1},
      {1,1,0,0,0,0,0,0,1,0,0,1},
      {1,0,0,0,0,1,1,1,1,0,0,1},
      {1,1,1,1,1,1,1,1,1,1,1,1}
    };
  private int m;
  private Stack<Position> path;

  // constructor a sample maze
  public Maze() {
    m = maze.length;
    path = new Stack<Position>();
  }

  public void printPath() {
    // Constructs a list containing the elements of the specified collection, 
    // in the order they are returned by the collection's iterator.
    List<Position> list = new ArrayList<Position>(path);
    for (Position p : list) {
      System.out.print(p);
    }
    System.out.println();
  }

  public boolean findPath() {

    Position[] p = new Position[4];
    p[0] = new Position(0, 1);  // right
    p[1] = new Position(1, 0);  // down
    p[2] = new Position(0, -1); // left
    p[3] = new Position(-1, 0); // up

    Position here = new Position(1,1);

    int i, r = -1, c = -1;
    while (here.row != m - 2 || here.col != m - 2) {
      for (i = 0; i < 4; i++) {
        r = here.row + p[i].row;
        c = here.col + p[i].col;
        if (maze[r][c] == 0) break;
      }
      if (i < 4) {
        path.push(here);
        here = new Position(r, c);
        maze[r][c] = 1;
        System.out.printf("move to (%d %d)%n", r, c);
        i = 0;
      } else {
        if (path.isEmpty()) return false;
        Position next = path.pop();
        if (next.row == here.row) {
          // move left or right
          i = 1 + next.col - here.col;
        } else {
          // move up or down
          i = 2 + next.row - here.row;
        }
        here = next;
      }
    }
    return true;
  }

  private class Position {
    public int row, col;
    public Position(int r, int c) {
      row = r;
      col = c;
    }

    @Override
    public String toString() {
      return "(" + row + ", " + col + ") ";
    }
  }
}




