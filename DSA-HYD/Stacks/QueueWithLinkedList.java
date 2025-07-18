import java.util.*;


class Node{
    int val;
    Node next;
    public Node(int val){
        this.val = val;
    }
}

class Queue {

    Node head = null;
    Node tail = null;
    int size = 0;
    

    public void push(int value) {
        Node nn = new Node(value);
        if(head == null && tail == null){
            head = nn;
            tail = nn;
        }else{
            tail.next = nn;
            tail = nn;
        }
        this.size++;
    }
    public int pop() {
       int val = head.val;
       head = head.next;
       this.size--;
       return val;
       
    }
    public int front() {
        return head.val;
    }
    public int getSize() {
        //Write your code here
        return this.size;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue q = new Queue();
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int op = sc.nextInt();
            if(op == 1) {
                int x = sc.nextInt();
                q.push(x);
            }
            else if(op == 2) {
                System.out.println(q.pop());
            }
            else if(op == 3)
                System.out.println(q.front());
            else if(op == 4)
                System.out.println(q.getSize());
        }
    }
}
