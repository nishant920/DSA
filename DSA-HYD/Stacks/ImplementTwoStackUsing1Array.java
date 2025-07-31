import java.util.*;

class TwoStacks {
	int[] arr;
	int st1Idx;
	int st2Idx;

	// Constructor
	TwoStacks(int n)
	{
		arr = new int[n];
		st1Idx = -1;
		st2Idx = n;
	}

	// Method to push an element x to stack1
	void push1(int x)
	{
		 if(st1Idx + 1 == st2Idx){
			System.out.println(-1);
			return;
		 }
         st1Idx++;
		
		 
		 arr[st1Idx] = x;
	}

	// Method to push an element
	// x to stack2
	void push2(int x)
	{
	   if(st1Idx == st2Idx - 1){
			System.out.println(-1);
			return;
		}	
       st2Idx--;
	   arr[st2Idx] =  x;
	}

	// Method to pop an element from first stack
	void pop1()
	{
		if(st1Idx == -1){
			System.out.println(-1);
			return;
		}
		System.out.println(arr[st1Idx]);
		st1Idx--;
	}

	// Method to pop an element
	// from second stack
	void pop2()
	{
		if(st2Idx == arr.length){
			System.out.println(-1);
			return;
		}
		System.out.println(arr[st2Idx]);
		st2Idx++;
	}
};
public class Main {

	/* Driver program to test twoStacks class */
	public static void main(String[] args)
	{
		TwoStacks ts = new TwoStacks(50);
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        for(int i =0; i<n; i++){
            int temp;
            temp = sc.nextInt();
            if(temp == 1) ts.pop1();
            else if(temp==3) ts.pop2();
            else if(temp == 2) {
                int temp2;
                temp2 = sc.nextInt();
                ts.push1(temp2);
            }
            else{
                int temp2;
                temp2 = sc.nextInt();
                ts.push2(temp2);
            }
        }
	}
}
