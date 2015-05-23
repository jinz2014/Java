import java.awt.Color;

/*
 * A example to show static variables
 */

class Ball {
  private static int ball_count = 0;
  private static int red_ball_count = 0;
  private static int black_ball_count = 0;

  private static int radius = 0;
  private Color default_color;

  public static int getRedBallCount() {
    return red_ball_count;
  }

  public static int getBlackBallCount() {
    return black_ball_count;
  }

  public static int getBallCount() {
    return ball_count;
  }

  public static void setRadius(int r) {
    radius = r;
  }

  public static int getRadius() {
    return radius;
  }

  public Ball(Color c) {
    ball_count++;
    if (c == Color.RED) {
      default_color = Color.RED;
      red_ball_count++;
    } else {
      default_color = Color.BLACK;
      black_ball_count++;
    }
  }
}

class Ball_Game {

  public static void main(String[] args) {
    int ball_number = (int) (Math.random() * 10);
    int radius = (int) (Math.random() * 20) + 1;
    Ball.setRadius(radius);

    for (int i = 0; i < ball_number; i++) {
      int v = (int) (Math.random()*2);
      if (v == 0) {
        new Ball(Color.RED);
      } else {
        new Ball(Color.BLACK);
      }
    }

    System.out.println("There are " + Ball.getRedBallCount() + " red balls");
    System.out.println("There are " + Ball.getBlackBallCount() + " black balls");
    System.out.println("There are " + Ball.getBallCount() + " balls in total");

  }
}
