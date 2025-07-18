import java.util.*;

class Node {

    int data;
    Node link;

    Node(int data){
        this.data = data;
    }
}

class Main {
	public static void main(String[] args)
	{
		StackUsingLinkedlist obj = new StackUsingLinkedlist();
		Scanner sc = new Scanner(System.in);
        int q;
        q = sc.nextInt();
        while(q-->0){
            int x;
            x = sc.nextInt();
            if(x==1){
                int y;
                y = sc.nextInt();
                obj.push(y);
            }
            if(x==2){
                System.out.println(obj.peek());
            }
            if(x==3){
                obj.pop();
            }
            if(x==4){
                Node temp = obj.display();
                while (temp != null) {

                    System.out.print(temp.data+" ");

                    temp = temp.link;
			    }
				System.out.println();
            }
        }
	}
}

class StackUsingLinkedlist {
    int size;
	Node head;
	StackUsingLinkedlist() { 
        this.head = null;
        this.size = 0; 
    }

	public void push(int x)
	{
		Node n = new Node(x);
        n.link = head;
        head = n;
        this.size++;
	}

	public int peek()
	{
        if(this.size == 0){
            return -1;
        }
        return head.data;
	}

	public void pop()
	{
        if(head == null){
            return;
        }
        head = head.link;
        this.size--;
        
	}

	public Node display()
	{
		return head;
	}
}
