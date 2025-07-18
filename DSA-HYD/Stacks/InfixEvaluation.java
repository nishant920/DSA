import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();
    Solution ob =new Solution();
    ob.evaluate(exp);
  }
}

class Solution{
    public int getPriority(char ch){
      if(ch == '+' || ch == '-'){
        return 1;
      }else{
        return 2;
      }
    }
    public int calculate(int val1, int val2, char opr){
      if(opr == '+'){
        return val1 + val2;
      }else if(opr == '-'){
        return val1 - val2;
      }else if(opr  == '/'){
        return val1/val2;
      }else{
        return val1*val2;
      }
    }
    public void infixEvaluation(String exp){
      Stack<Integer> oprandSt = new Stack<>();
      Stack<Character> oprSt = new Stack<>();
      for(int i = 0; i < exp.length(); i++){
        char ch = exp.charAt(i);
        if(ch >= '0' && ch <= '9'){
          int num = ch - '0';
          oprandSt.push(num);
        }else if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
          while(oprSt.size() > 0 && oprSt.peek() != '(' && getPriority(ch) <= getPriority(oprSt.peek())){
            char opr = oprSt.pop();
            int val2 = oprandSt.pop();
            int val1 = oprandSt.pop();
            int res = calculate(val1, val2, opr);
            oprandSt.push(res);
          }
          oprSt.push(ch);
        }else if(ch == '('){
          oprSt.push(ch);
        }else{
          while(oprSt.peek() != '('){
            char opr = oprSt.pop();
            int val2 = oprandSt.pop();
            int val1 = oprandSt.pop();
            int res = calculate(val1, val2, opr);
            oprandSt.push(res);
          }
          oprSt.pop();
        }
      }
       while(oprSt.size() != 0){
            char opr = oprSt.pop();
            int val2 = oprandSt.pop();
            int val1 = oprandSt.pop();
            int res = calculate(val1, val2, opr);
            oprandSt.push(res);
      }

      System.out.println(oprandSt.peek());
    }
    public void evaluate(String exp){
        infixEvaluation(exp);
    }
}               
