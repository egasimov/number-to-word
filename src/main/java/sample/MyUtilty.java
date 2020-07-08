package sample;

import java.util.HashMap;

public class MyUtilty {
    private MyUtilty() {
    }
    private static final String[] digits;
    private static final String[] tens;
    private static final String hundred = "yuz ";
    private static final String thousand = "min ";
    private static final String million = "milyon ";
    private static final String billion = "milyard ";
    private static final String EMPTY = "";

    static {
        digits = new String[]{"sifir ", "bir ", "iki ", "uc ", "dord ",
                "bes ", "alti ", "yeddi ", "sekkiz ", "doqquz "};
        tens = new String[]{"", "on ", "iyirmi ", "otuz ", "qirx ",
                "elli ", "altmis ", "yetmis ", "seksen ", "doxsan "};

    }

    public String convertToWord(Integer i) {

        String number = String.valueOf(i);

        //splitting number into integer and fractional part
        String[] strings = number.split("[.]");

        StringBuilder builder = new StringBuilder();

        //process integer part and add into buffer
        builder.append(convertIntPart(strings[0]));

        //process fractional part and add into buffer

        builder.append(convertFractionalPart(strings[1]));

        //collect result


        return null;
    }

    public static String convertIntPart(String integer) {
        //does it contain , symbol ?
        //integer = integer.replace("[,]", "");

        StringBuilder builder = new StringBuilder();
        //starting from right hand side and start process
//        boolean start=true;
        int pos = 1;
        for (int i = integer.length(); i >= 1; i = i - 3) {
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
                pos = "";
                break;
            case 2:
                pos = thousand;
                break;
            case 3:
                pos = million;
                break;
            case 4:
                pos = billion;
                break;
        }

        return pos;
    }

    public static String handleTriple(String triple) {
        StringBuilder tripleBuilder = new StringBuilder();
        String word = null;

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
                    throw new RuntimeException("Illegal case occured");
            }
            tripleBuilder.insert(0, word);
        }


        return tripleBuilder.toString().replaceAll(digits[0], EMPTY);
    }

    private static String convertFractionalPart(String fraction) {
        return null;
    }

}
