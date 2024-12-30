import java.util.Random;
import java.util.Scanner;

public class IDCardCheck {
    public static void main(String[] args) {
        boolean valid = false;
        do {
            int[] idArray = getIdAndSend();
            valid = checkProprietyId(idArray);
            if(valid){
                System.out.println("yore ID is fine");
            }
            else{
                System.out.println("you did not typing right yore ID / this ID dose not exist.");
            }
        }
        while (!valid);
    }

    public static int[] getIdAndSend(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter yore ID card without a space: ");
        int numbersOfId = scanner.nextInt();
        int id = numbersOfId;

        int counter = 0;
        while ( numbersOfId > 0){
            numbersOfId = (numbersOfId / 10);
            counter++;
        }

        int[] idArray = new int[counter];

        for (int i = 0; i < counter; i++) {
            idArray[i] = id % 10;
            id = id / 10;
        }

        int start = 0;
        int end = idArray.length - 1;
        while (start < end){
            int num = idArray[start];
            idArray[start] = idArray[end];
            idArray[end] = num;
            start++;
            end--;
        }

        return idArray;
    }

    public static boolean checkProprietyId(int[] idArray){

        if (idArray.length == 9){
            int[] multiplication = new int[9];
            for (int i = 0; i < idArray.length; i++) {
                if ((i+1) % 2 == 0){
                    multiplication[i] = idArray[i] * 2;
                }
                else multiplication[i] = idArray[i];

            }
            int[] sam = new int[9];
            for (int i = 0; i < multiplication.length; i++) {
                if (multiplication[i] >= 10){
                    int num1 = multiplication[i] % 10;
                    int num2 = multiplication[i] / 10;
                    sam[i] = num1 + num2;
                }
                else sam[i] = multiplication[i];
            }
            int samOfTheArray = 0;
            for (int i = 0; i < sam.length; i++) {
                samOfTheArray += sam[i];
            }
            if (samOfTheArray % 10 == 0){
                return true;
            }
        }

        if (idArray.length == 8){
            finedTheNumber9InIdIfNecessary(idArray);
            int samOfTheArray2 = 0;
            for (int i = 0; i < finedTheNumber9InIdIfNecessary(idArray).length; i++) {
                samOfTheArray2 += finedTheNumber9InIdIfNecessary(idArray)[i];
            }
            if (samOfTheArray2 % 10 == 0){
                return true;
            }
        }
        return false;
    }

    public static int[] finedTheNumber9InIdIfNecessary(int[] idArray){
        if (idArray.length == 9){
            return idArray;
        }
        int[] multiplication2 = new int[8];
        if (idArray.length == 8) {
            for (int i = 0; i < idArray.length; i++) {
                if ((i + 1) % 2 == 0) {
                    multiplication2[i] = idArray[i] * 2;
                } else multiplication2[i] = idArray[i];
            }
        }

        int[] sam = new int[8];
        for (int i = 0; i < multiplication2.length; i++) {
            if (multiplication2[i] > 10){
                int num1 = multiplication2[i] % 10;
                int num2 = multiplication2[i] / 10;
                sam[i] = num1 + num2;
            }
            else sam[i] = multiplication2[i];
        }

        int samOfTheArray = 0;
        for (int i = 0; i < sam.length; i++) {
            samOfTheArray  += sam[i];
        }
        int numToAdd = 10 - (samOfTheArray % 10);
        int[] newArrayWhiteTheNumber9 = new int[9];

        for (int i = 0; i < 8; i++) {
            newArrayWhiteTheNumber9[i] = sam[i];
            newArrayWhiteTheNumber9[8] = numToAdd;
        }
        return newArrayWhiteTheNumber9;

    }
}


class worldLd {
    public static void main(String[] args) {
        countGuessesAndExplainLocationOfWorld(sandeRandomWord());
    }

    public static String sandeRandomWord(){
        Random random = new Random();
        String worldsOfTheGame = "apple bread table water dance house smile dream snake world";
        String[] worldsArray = worldsOfTheGame.split(" ");

        int indexRandom = random.nextInt(worldsArray.length);
        String gameWorld = worldsArray[indexRandom];

        return gameWorld;
    }

    public static void countGuessesAndExplainLocationOfWorld(String gameWorld){
        Scanner scanner = new Scanner(System.in);
        System.out.println("hello and welcome to WORLD-LD game!!");
        System.out.println("the system gave you a word white five letters and you have 6 attempts to guess the world :) ");
        System.out.println("every word you guess i tell you each world is in the correct place or a letter is correct but not in the correct place:");
        int i = 0;
        while (i < 6) {
            System.out.println("you have " + (6 - i) + " to guess");
            System.out.println("enter your world guess: ");
            String guessWorld = scanner.nextLine().toLowerCase();
            String[] guessWorldArray = guessWorld.split("");
            String temp = "abcdefghijklmnopqrstuvwxyz";
            boolean trueFalse = true;
            for (int j = 0; j < guessWorldArray.length; j++) {
                String letter = guessWorldArray[j];
                if (!temp.contains(letter)){
                    trueFalse = false;
                    break;
                }


            }
            if (!trueFalse){
                System.out.println("you enter legal word try again");
                continue;
            }

            if (guessWorld.length() != 5){
                System.out.println("the world you enter dont have five letters");
                continue;
            }

            if (guessWorld.equals(gameWorld)){
                System.out.println("you win!!");
                break;
            }
            for (int j = 0; j < guessWorldArray.length; j++) {
                String letterGuessWorld = guessWorldArray[j];
                String[] gameWordArray = gameWorld.split("");
                String letterGameWord = gameWordArray[j];
                if (letterGuessWorld.equals(letterGameWord)){
                    System.out.print("G");
                }
                else if (letterGuessWorld.contains(letterGameWord)){
                    System.out.println("Y");

                }
                else {
                    System.out.print("_");
                }
            }
            i++;
        }
        if (i == 6){
            System.out.println("you lost the game");
            System.out.println("the word was: " + gameWorld);
        }
    }
}