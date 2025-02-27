import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
class Conversation implements Chatbot {

  // Attributes 
  int round; //the number of rounds of conversation
  String[] responses; //the random response bank
  ArrayList<String>transcript; //records all the conversation 

  /**
   * Constructor 
   */
  Conversation() {
    this.round=0;
    this.responses=new String[]{"Yes,and?", "Ugh huh...","I see."};
    this.transcript=new ArrayList<String>();
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner input = new Scanner(System.in);
    System.out.print("How many rounds? ");
    this.round = input.nextInt();
    input.nextLine();
    System.out.println("Hi there! What's on your mind?");
    this.transcript.add("Hi there! What's on your mind?");

    for (int i = 0; i < this.round; i++) {
        // Get user input
        String user_input = input.nextLine();
        String response = this.respond(user_input);

        // Display response
        System.out.println(response);

        // Store in transcript
        this.transcript.add("You: " + user_input);
        this.transcript.add("Bot: " + response);
    }
    input.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("-----Conversation Transcript------");
    // iterates in the ArrayList<> transcript
    for(int i=0; i<this.transcript.size(); i++){
      String saySomething=this.transcript.get(i);
      System.out.println(saySomething);
      }
    }


  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    // Set up a canned response
    
    Random rand = new Random();
    int random = rand.nextInt(this.responses.length);
    String returnString = this.responses[random];
    // split the input string by sentences, cast variable into ArrayList<>
    String[] words = inputString.split("\\s+");
    ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(words));
    // Detect mirror words, replace words based on the rules
    for (int i = 0; i < wordsList.size(); i++) {
      boolean notDefault=false;
      String word = wordsList.get(i);
      if (word.equals("I")) {
        wordsList.set(i, "You");
        notDefault=true;
      }
      if (word.equals("my")) {
        wordsList.set(i, "your");
        notDefault=true;
      }
      if (word.equals("My")) {
        wordsList.set(i, "Your");
        notDefault=true;
      }
      if (word.equals("am")){
        wordsList.set(i,"are");
        notDefault=true;
      }
      if (word.equals("me")){
        wordsList.set(i,"you");
        notDefault=true;
      }
      if (word.equals("you")){
        wordsList.set(i,"me");
        notDefault=true;
      }
      if (word.equals("your")){
        wordsList.set(i,"my");
        notDefault=true;
      }
      if (word.equals("Your")){
        wordsList.set(i,"My");
        notDefault=true;
      }
      // See if mirror words appeared, add punctuation as needed
      if(notDefault){
        returnString = String.join(" ", wordsList);
        if (Character.isUpperCase(wordsList.get(0).charAt(0))){
          returnString+="?";
        }
      }
    }
    return returnString;
  }
  public static void main(String[] arguments) {
    Conversation myConversation=new Conversation();
    myConversation.chat();
    myConversation.printTranscript();
  }
}
