import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class App {
    private void writeToFile(Path location,List<String> toWrite){
        try {
            Files.write(location, toWrite, Charset.defaultCharset());
        }catch (IOException error) {
            System.out.println("IOException error");
        }
    }

    private int lengthOfLongestLine(List<String>Lines) {
        int currentLength = 0;
        for(String forCountString: Lines) {
            if (currentLength < forCountString.length())
                currentLength = forCountString.length();
        }
        return currentLength;
    }
    private int avergeLineLength(List<String>Lines) {
        int avergeLength = 0;
        for(String forCountString: Lines)
            avergeLength += forCountString.length();
        avergeLength /= Lines.size();
        return avergeLength;
    }

    private int numberofuniqueTokenCS(List<String> token) {
        return token.size();
    }
    private int numberofuniqueTokenCI(List<String> token) {
        List<String>tempToken = new ArrayList<>();
        for(String cheakRepeatTemp: token) {
            if(!tempToken.contains(cheakRepeatTemp.toLowerCase()))
                tempToken.add(cheakRepeatTemp.toLowerCase());
        }
        return tempToken.size();

    }
    private int numberofAllTokenCI(List<Integer> count) {
        int totalToken = 0;
        for(int temp :count)
            totalToken += temp;
        return totalToken;
    }
    private List<String> mostFrequentlyOccurringToken(List<String> token,List<Integer> count) {
        int maxNumber = Collections.max(count);
        int indexToken=0;
        List<String> frequentlyOccurringTokens = new ArrayList<>();
        for(int temp: count) {
            if(maxNumber == temp) {

                frequentlyOccurringTokens.add(token.get(indexToken));
            }
            indexToken++;
        }
        return frequentlyOccurringTokens;
    }
    private int countMostFrequentlyOccurringTokenCI(List<String> token,List<Integer> count) {

        List<String> tempToken = new ArrayList<>();
        List<Integer> tempCount = new ArrayList<>();

        for(int indexForString = 0; indexForString < token.size();indexForString++) {
            if(!tempToken.contains(token.get(indexForString).toLowerCase())) {
                tempToken.add(token.get(indexForString).toLowerCase());
                tempCount.add(count.get(indexForString));
                continue;
            }
            for(int indexForCheckRepeat = tempToken.size()-1; indexForCheckRepeat >= 0; indexForCheckRepeat--) {
                if((tempToken.get(indexForCheckRepeat).toLowerCase())==(token.get(indexForString).toLowerCase())) {
                    tempCount.set(indexForCheckRepeat,
                            tempCount.get(indexForCheckRepeat)+count.get(indexForString));
                }
            }
        }
        return Collections.max(tempCount);
    }
    private List<String> tenMostFrequentlyOccurringTokenCI(List<String> token,List<Integer> count) {

        List<String> tempToken = new ArrayList<String>();
        List<Integer> tempCount = new ArrayList<Integer>();
        List<String> MostFrequenyToken = new ArrayList<String>();
        int maxNum =0;
        for(int indexForString = 0; indexForString < token.size();indexForString++) {
            if(!tempToken.contains(token.get(indexForString).toLowerCase())) {
                tempToken.add(token.get(indexForString).toLowerCase());
                tempCount.add(count.get(indexForString));
                continue;
            }
            for(int indexForCheckRepeat = tempToken.size()-1; indexForCheckRepeat >= 0; indexForCheckRepeat--) {
                if((tempToken.get(indexForCheckRepeat).toLowerCase())==(token.get(indexForString).toLowerCase())) {
                    tempCount.set(indexForCheckRepeat,tempCount.get(indexForCheckRepeat)+count.get(indexForString));
                }
            }
        }
        for(int temp = 0; temp<tempToken.size()&& MostFrequenyToken.size()< 10;temp++)
        {
            maxNum = Collections.max(tempCount);
            MostFrequenyToken.add(tempToken.get(tempCount.indexOf(maxNum)));
            tempToken.remove(tempCount.indexOf(maxNum));
            tempCount.remove(tempCount.indexOf(maxNum));
            temp --;
        }

        return MostFrequenyToken;

    }
    private List<String> tenLeastFrequentlyOccurringTokenCI(List<String> token,List<Integer> count) {

        List<String> tempToken = new ArrayList<String>();
        List<Integer> tempCount = new ArrayList<Integer>();
        List<String> leastFrequenyToken = new ArrayList<String>();
        int minNum =0;

        for(int indexForString = 0; indexForString < token.size();indexForString++) {
            if(!tempToken.contains(token.get(indexForString).toLowerCase())) {
                tempToken.add(token.get(indexForString).toLowerCase());
                tempCount.add(count.get(indexForString));
                continue;
            }
            for(int indexForCheckRepeat = tempToken.size()-1; indexForCheckRepeat >= 0; indexForCheckRepeat--) {
                if((tempToken.get(indexForCheckRepeat).toLowerCase())==(token.get(indexForString).toLowerCase())) {
                    tempCount.set(indexForCheckRepeat, (tempCount.get(indexForCheckRepeat)+count.get(indexForString)));
                }
            }
        }
        for(int temp = 0; temp<tempToken.size() && leastFrequenyToken.size() < 10;temp++){
            minNum = Collections.min(tempCount);
            leastFrequenyToken.add(tempToken.get(tempCount.indexOf(minNum)));
            tempToken.remove(tempCount.indexOf(minNum));
            tempCount.remove(tempCount.indexOf(minNum));
            temp--;

        }

        return leastFrequenyToken;
    }

    public void moreInforemation(File file,List<String> lines) {
        List<String> typeOfToken = new ArrayList<String>();
        List<Integer> countForToken = new ArrayList<Integer>();
        List<String> forOuputString = new ArrayList<String>();
        Path path = Paths.get(file.getAbsolutePath()+".stats");

        if(lines!=null) {
            for(String stringForToken: lines) {
                String temp[] = stringForToken.trim().split(" ");
                for(String items: temp) {
                    if(typeOfToken.size()==0||!typeOfToken.contains(items)) {
                        typeOfToken.add(items);
                        countForToken.add(1);
                    }
                    else if(typeOfToken.contains(items)){
                        int tokenIndex = typeOfToken.indexOf(items);
                        countForToken.set(tokenIndex,(countForToken.get(tokenIndex)+1) );
                    }
                }
            }

            forOuputString.add(String.format("%s:%d\n","Longest Line length",lengthOfLongestLine(lines)));
            forOuputString.add(String.format("%s:%d\n","Average Line length",avergeLineLength(lines)));
            forOuputString.add(String.format("%s:%d\n","Number of unique space-delineated tokens (case-sensitive)",
                    numberofuniqueTokenCS(typeOfToken)));
            forOuputString.add(String.format("%s:%d\n","Number of unique space-delineated tokens (case-Insensitive)",
                    numberofuniqueTokenCI(typeOfToken)));
            forOuputString.add(String.format("%s:%d\n", "Number of all space-delineated tokens in file",
                    numberofAllTokenCI(countForToken)));
            forOuputString.add(String.format("%s", "Most frequently occurring token(s):"+
                    mostFrequentlyOccurringToken(typeOfToken,countForToken))+"\n");
            forOuputString.add(String.format("%20s:%03d\n", "Count of most frequently occurring token (case-insensitive)"
                    ,countMostFrequentlyOccurringTokenCI(typeOfToken,countForToken)));
            forOuputString.add(String.format("%20s","10 most frequent tokens with their counts:"+
                    tenMostFrequentlyOccurringTokenCI(typeOfToken,countForToken))+"\n");
            forOuputString.add(String.format("%20s","10 least frequent tokens with their counts: "+
                    tenLeastFrequentlyOccurringTokenCI(typeOfToken,countForToken))+"\n");
            writeToFile(path,forOuputString);
        }
        else {
            System.out.println("no more information");
        }

    }
    public void readAllFile(File dir){
        File[] fileNameList = dir.listFiles();

        for(int temp = 0; temp < fileNameList.length;temp++)
        {
            if(fileNameList[temp].isDirectory()) {
                readAllFile(fileNameList[temp]);
            }
            else {
                try {
                    if(fileNameList[temp].getName().endsWith("txt")
                            ||fileNameList[temp].getName().endsWith("java")) {

                        List<String> lines =
                                Files.readAllLines(fileNameList[temp].toPath(),Charset.defaultCharset());
                        moreInforemation(fileNameList[temp],lines);
                    }
                }catch(IOException error) {
                    System.out.println("IOException error");
                }
            }
        }
    }
    public static void main(String [] args){
        if(args.length != 0) {
            StringBuilder nameOfPath = new StringBuilder();
            for(int temp = 0; temp<args.length;temp++){
                nameOfPath.append(args[temp]);
            }
            App myProject = new App();
            myProject.readAllFile(new File(nameOfPath.toString()));
        }else {
            File currentDiretory = new File("");
            App myProject = new App();
            myProject.readAllFile(new File(currentDiretory.getAbsolutePath()));
        }
    }
}

