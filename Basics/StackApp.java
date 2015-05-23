public class StackApp {
  public static void main (String[] args) {
    Stack<Object> oldStack = new Stack<Object>();
    //oldStack.push(10);   // see below 
    oldStack.push("test");
    oldStack.push("stack");
    for (int i = 0; i < 2; i++) {
      // Runtime error: Integer cannot be cast to String
      String s = (String) oldStack.pop(); 
      System.out.println(s);
    }
    dumpStack(oldStack);

    NumberStack<Long> longStack = new NumberStack<Long>();
    longStack.push(10L);
    longStack.push(5L);
    dumpStack(longStack);
  }

  private static void dumpStack(Stack<?> s) {
    for (Object n : s.getStack()) {
      System.out.println(n);
    }
  }

  private static void dumpStack(NumberStack<?> s) {
  //private static void dumpStack(Stack<? extends Number> s) {
    for (Number n : s.getStack()) {
      System.out.println(n);
    }
  }
}

/*
 * javap Stack shows the intermediate code
 */
class Stack<T> {
  private T[] stack = (T[]) new Object[5];
  private int ptr = -1;

  public T[] getStack() {
    return stack;
  }

  public void push(T data) {
    ptr++;
    stack[ptr] = data;
  }

  public T pop() {
    return (T) stack[ptr--];
  }
}

/*
 *  Constrain the stack to operate on numeric type
 */
class NumberStack<T extends Number> {
  /* 
   illegal to create an array of generic types in the constructor
   public NumberStack() {
    stack = new T[5];
   }
  
   it is good to assign a variable to an array of real type
   public NumberStack(T[] stack) {
    this.stack = stack;
   }
   */

  private Number[] stack = new Number[5];
  private int ptr = -1;

  public Number[] getStack() {
    return stack;
  }

  public void push(T data) {
    ptr++;
    stack[ptr] = data;
  }

  public T pop() {
    return (T) stack[ptr--];
  }
}
