package CodeJam.Y2016.Qualification.PancakesRevenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 30102913
 */
public class PancakesRevenge {

    PrintWriter writer;
    private Stack<Boolean> pancakes = new Stack<>();

    private void test() {
        pancakes.push(Boolean.TRUE);
        pancakes.push(Boolean.FALSE);
        pancakes.push(Boolean.FALSE);
        pancakes.push(Boolean.FALSE);
        pancakes.push(Boolean.FALSE);
        pancakes.push(Boolean.TRUE);
        pancakes.push(Boolean.TRUE);
        pancakes.push(Boolean.FALSE);
        pancakes.push(Boolean.FALSE);
        pancakes.push(Boolean.TRUE);
      
        makeHappyPancakes(pancakes, 1);

    }

    private int getLowestEmptyPancake(Stack<Boolean> pancakesStack){
        for (int j = 0; j < pancakesStack.size(); j++) {
            if (!pancakesStack.get(j)) {
                return pancakesStack.size() - j;
            }
        }
        return -1;
    }

      private void processFile(String filePath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            int i = 0;
            String T = br.readLine();
            String pancakesDef;
            while (i < Integer.parseInt(T)) {
                pancakesDef = br.readLine();
                Stack<Boolean> pancakesStack = new Stack<>();
                for (int j = 0; j < pancakesDef.length(); j++) {
                    if(pancakesDef.charAt(pancakesDef.length()-j-1)=='+')
                        pancakesStack.push(Boolean.TRUE);
                    else if(pancakesDef.charAt(pancakesDef.length()-j-1)=='-')
                        pancakesStack.push(Boolean.FALSE);
                }                
                
                makeHappyPancakes(pancakesStack, i + 1);
                i++;
            }
        } finally {
            br.close();
        }
    }
    private void makeHappyPancakes(Stack<Boolean> pancakesStack, int caseNumber) {
        int i = 0;
        System.out.println("Initial state:");
        printStackStatus(pancakesStack);
        while (!allPancakesHappy(pancakesStack)) {
            flipPancakesStack(pancakesStack, getLowestEmptyPancake(pancakesStack));
            System.out.println("Flip result:");
            printStackStatus(pancakesStack);  
            i++;
        }
        writer.println("Case #"+caseNumber+": "+i);
    }

    private void printStackStatus(Stack<Boolean> pancakesStack) {
        Stack<Boolean> temp = (Stack<Boolean>) pancakesStack.clone();
        while (!temp.empty()) {
            System.out.print(temp.pop() == Boolean.TRUE ? "+" : "-");
        }
        System.out.println("");

    }

    private void flipPancakesStack(Stack<Boolean> pancakesStack, int numPancakes) {
        Stack<Boolean> temp = new Stack<>();

        for (int i = 0; i < numPancakes; i++) {
            temp.push(!pancakesStack.pop());
        }

        while (!temp.empty()) {
            pancakesStack.push(temp.pop());
        }

    }

    private boolean allPancakesHappy(Stack<Boolean> pancakesStack) {
        for (int i = 0; i < pancakesStack.size(); i++) {
            if (!pancakesStack.elementAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            PancakesRevenge pb = new PancakesRevenge();
            pb.writer = new PrintWriter("C:\\CodeJam\\B\\PancakesRevenge_OutputSmallPractice.txt", "UTF-8");
            pb.processFile("C:\\CodeJam\\B\\B-small-practice.in");
            pb.writer.close();
        } catch (IOException ex) {
            Logger.getLogger(PancakesRevenge.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
