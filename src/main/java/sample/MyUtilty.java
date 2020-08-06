package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static sample.util.Literals.*;
public class MyUtilty {
    private MyUtilty() {
        throw new IllegalStateException("cannot be instantiated");
    }



    public static String convertToWord(String originalNumber) {

        //number beeing valid format checking feature will be added later

        //splitting number into integer and fractional part
        String[] strings = originalNumber.split("[.]");

        String integerPart=convertIntPart(strings[0]);
        String fractionalPart=convertFractionalPart(strings[1]);

        return integerPart+separator+fractionalPart;
    }


    public static String convertIntPart(String integer) {
        //does it contain separator like ' , '
        integer = integer.replaceAll("[,]", "");

        StringBuilder builder = new StringBuilder();
        //starting from right hand side ,pick up triples and start process it

        int pos = 1; //used to indicate : level : 10^3,10^6,10^9
        for (int i = integer.length(); i >= 0 /*1*/; i = i - 3) {
            String key = getKeyword(pos++);
            String handledTriple = handleTriple(integer.substring(i - 3 >= 0 ? i - 3 : 0, i));
            builder.insert(0, handledTriple.isEmpty() ? /*digits[1]*/EMPTY : handledTriple + key);
        }

        return builder.toString();
    }

    private static String getKeyword(int position) {
        String pos = null;
        switch (position) {
            case 1:
                //first triple:10^3
                pos = "";
                break;
            case 2:
                //second triple 10^6
                pos = thousand;
                break;
            case 3:
                //third triple  10^9
                pos = million;
                break;
            case 4:
                //fourth triple 10^12
                pos = billion;
                break;
        }

        return pos;
    }

    public static String handleTriple(String triple) {
        StringBuilder tripleBuilder = new StringBuilder();
        String word ;

        if (triple.length() == 1) {
            return digits[Integer.parseInt(triple)];
        }
        if (triple.matches("[0]+")) return EMPTY;


        if (triple.length() == 2) {
            int indx = Integer.parseInt(String.valueOf(triple.charAt(1)));
            tripleBuilder.insert(0, indx == 0 ? EMPTY : digits[indx]);

            indx = Integer.parseInt(String.valueOf(triple.charAt(0)));
            tripleBuilder.insert(0, tens[indx]);

            return tripleBuilder.toString();

        }

        for (int i = triple.length() - 1; i >= 0; i--) {
            switch (i) {
                case 2:
                    int indx = Integer.parseInt(String.valueOf(triple.charAt(i)));
                    word = indx != 0 ? digits[indx] : EMPTY;
                    break;
                case 1:
                    indx = Integer.parseInt(String.valueOf(triple.charAt(i)));
                    word = indx != 0 ? tens[indx] : EMPTY;
                    break;
                case 0:
                    indx = Integer.parseInt(String.valueOf(triple.charAt(i)));
                    //yuzlukde dayanan reqem 1 dise ignor et
                    //word = (digits[indx] != digits[1] ? digits[indx] : "") + hundred;
                    //word= indx==0 || indx==1 ?

                    String prefix = /*indx != 1 && */ indx != 0 ? digits[indx] : EMPTY;
                    word = !prefix.isEmpty() ? prefix + hundred : EMPTY;
                    break;
                default:
                    throw new RuntimeException("Illegal case occured ");
            }
            tripleBuilder.insert(0, word);
        }


        return tripleBuilder.toString().replaceAll(digits[0], EMPTY);
    }


    public static String convertFractionalPart(String fraction) {
        String prefix=getPrefixForFractions(fraction.length()); //on,min,on min ...
        String suffix=getSuffixForFractions(prefix); // 'da','de'
        String word=convertIntPart(fraction);

        //prefix + "da , de ,son saite uygun"+ word

        //putting all together
        return prefix + suffix + " " + word;
    }

    private static String getSuffixForFractions(String prefix) {
        //prefix deki son saitie gore define edek
        List<Character> thin_letter= Arrays.asList('e', 'ə', 'i', 'ö', 'ü');
        char ch=prefix.charAt(prefix.length()-2);

        return  thin_letter.contains(ch) ? "də" : "da" ;
    }

    private static String getPrefixForFractions(int digitCounts){
        //for now switch case is OK, later will be found dynamically
        String prefix;
        switch (digitCounts){
            case 1:
                prefix=tens[1];
                break;
            case 2:
                prefix=hundred;
                break;
            case 3:
                prefix=thousand;
                break;
            case 4:
                prefix=tens[1] + thousand;
                break;
            case 5:
                prefix=hundred+thousand;
                break;
            case 6:
                prefix=million;
                break;
            case 7:
                prefix=tens[1]+million;
                break;
            case 8:
                prefix=hundred + million;
                break;
            case 9:
                prefix=billion;
                break;
            default:
                throw new IllegalArgumentException(digitCounts + " degree precision is not supported yet!");
        }

        return  prefix.trim();
    }

}
