import java.util.Scanner;

public class MainClass {
 public static void main(String args[]){

    PriorityQueue priorityQueue = new PriorityQueue(10);


     Scanner scanner = new Scanner(System.in);
     String temp = scanner.nextLine();
     int M = Integer.parseInt(temp);

     for(int i=0;i<M;i++)
     {
         temp = scanner.nextLine();
         String[] oper = temp.split(" ");

         switch (oper[0]){
             case "insert":
                 if(oper.length == 3)
                     priorityQueue.insert( Integer.parseInt(oper[1]), Integer.parseInt(oper[2]));
                 break;

             case "empty":
                 System.out.println(priorityQueue.empty());
                 break;

             case "top":
                 System.out.println(priorityQueue.extractMax());
                 break;

             case "pop":
                 priorityQueue.pop();
                 break;

             case "priority":
                 if(oper.length == 3)
                     priorityQueue.priority( Integer.parseInt(oper[1]), Integer.parseInt(oper[2]),0);
                 break;

             case "print":
                 priorityQueue.print();
                 break;

         }


     }
 }
}
