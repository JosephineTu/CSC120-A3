import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
interface Chatbot {

  void chat();
  void printTranscript();
  String respond(String inputString); 
  
}

class Conversation implements Chatbot {

  // Attributes 
  int round; //the number of rounds of conversation
  String[] responses; //the random response bank
  ArrayList<String> transcript; //records all the conversation 

  /**
   * Constructor 
   */
  Conversation(int round,  String[] responses, ArrayList transcript) {
    this.round=round;
    this.responses=responses;
    this.transcript=transcript;
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner strInput=new Scanner(System.in);
    System.out.print("What do you want to talk about: ");
    //print a message to start a new round && ask for user input
    String user_input=strInput.nextLine();
    String response=this.respond(user_input);
    System.out.println(response);
    //apends this to the array transcript
    this.transcript.add(user_input);
    this.transcript.add(response);

  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("-----Conversation Transcript------");
    // iterates in the ArrayList<> transcript
    for(int i=0; i<this.transcript.size(); i++){
      String saySomething=this.transcript.get(i);
      // use odd/even turns to decide which side is speaking
      if (i%2==0){
        System.out.println("Human: "+saySomething);
      }
      else if (i%2==1){
        System.out.println("Bot: "+saySomething);
      }
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
        wordsList.set(i, "you");
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
      if (word.equals("you")){
        wordsList.set(i,"me");
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
    System.out.print("How many rounds of conversation do you want: ");
    Scanner numInput=new Scanner(System.in);
    int round=numInput.nextInt();
    ArrayList<String> transcript=new ArrayList<>(); 
    Conversation myConversation = new Conversation(round,new String[]{"Ugh huh...","Yes, and?","Tell me more."},transcript);
    for(int i=0;i<myConversation.round;i++){
      myConversation.chat();
    }
    myConversation.printTranscript();

  }
}
