public class TestMaze {
  public static void main(String[] args) {
    Maze maze = new Maze();
    if (maze.findPath() == true) {
      maze.printPath();
    } else {
      System.out.println("Path not found");
    }
  }
}
