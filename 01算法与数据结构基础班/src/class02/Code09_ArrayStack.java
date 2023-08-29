package class02;

/**
 * @author:fish
 * @date: 2023/5/24-21:55
 * @content:
 */

public class Code09_ArrayStack {

    public class ArrayStack {
        private int[] stack;
        private int top;
        private int size;

        public ArrayStack(int size) {
            this.size = size;
            stack = new int[size];
            top = -1;
        }

        public void push(int value) {
            if (top == size - 1) {
                System.out.println("Stack is full");
                return;
            }
            top++;
            stack[top] = value;
        }

        public int pop() {
            if (top == -1) {
                System.out.println("Stack is empty");
                return -1;
            }
            int value = stack[top];
            top--;
            return value;
        }

        public int peek() {
            if (top == -1) {
                System.out.println("Stack is empty");
                return -1;
            }
            return stack[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == size - 1;
        }
    }
}

