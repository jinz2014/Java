
class MatrixAddTest {
  public static void main(String args[]) {
    Integer[][] a = new Integer[5][3];
    Integer[][] b = new Integer[5][3];
    Integer[][] c = new Integer[5][3];
    Matrix.add(a, b, c);
  }
}

/* Generic type doesn't support binary operator
class MatrixAdd<T extends Number> {
  public void add(T[][] a, T[][] b, T[][] c) {
    System.out.println(a.length);
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[i].length; j++) {
        c[i][j] = a[i][j] + b[i][j]; 
      }
    }
  }
}
*/

// Implement the add function for each primitive/object type.
// We don't need to worry about code duplication
class Matrix {
  public static void add(Integer[][] a, Integer[][] b, Integer[][] c) {
    System.out.println(a.length);
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[i].length; j++) {
        c[i][j] = a[i][j] + b[i][j]; 
      }
    }
  }
}
