package sample;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyUtiltyTest {

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

    @Test
    public void when_one_digit_then(){

        assertEquals(MyUtilty.handleTriple("0"),digits[0]);
        assertEquals(MyUtilty.handleTriple("2"),digits[2]);
        assertEquals(MyUtilty.handleTriple("5"),digits[5]);
        assertEquals(MyUtilty.handleTriple("7"),digits[7]);

    }

    @Test
    public void when_two_digit_then(){

        assertEquals(MyUtilty.handleTriple("10"),tens[1]);
        assertEquals(MyUtilty.handleTriple("99"),tens[9]+digits[9]);
        assertEquals(MyUtilty.handleTriple("55"),tens[5]+digits[5]);
        assertEquals(MyUtilty.handleTriple("17"),tens[1]+digits[7]);
        assertEquals(MyUtilty.handleTriple("87"),tens[8]+digits[7]);
        assertEquals(MyUtilty.handleTriple("36"),tens[3]+digits[6]);

    }

    @Test
    public void when_three_digit_then(){

        assertEquals(MyUtilty.handleTriple("101"),digits[1]+hundred+digits[1]);
        assertEquals(MyUtilty.handleTriple("998"),digits[9]+hundred+tens[9] +digits[8]);
        assertEquals(MyUtilty.handleTriple("505"),digits[5]+hundred+digits[5]);
        assertEquals(MyUtilty.handleTriple("107"),digits[1]+hundred+digits[7]);
        assertEquals(MyUtilty.handleTriple("807"),digits[8]+hundred + digits[7]);
        assertEquals(MyUtilty.handleTriple("100"),digits[1]+hundred);

    }


    @Test
    public void when_4_digit_then(){

        assertEquals(MyUtilty.convertIntPart("1000"),digits[1]+thousand);
        assertEquals(MyUtilty.convertIntPart("1010"),digits[1]+thousand+tens[1]);
        assertEquals(MyUtilty.convertIntPart("1100"),digits[1]+thousand+digits[1]+hundred);
        assertEquals(MyUtilty.convertIntPart("5444"),digits[5]+thousand+digits[4]+hundred+tens[4]+digits[4]);
        assertEquals(MyUtilty.convertIntPart("9001"),digits[9]+thousand+ digits[1]);
        assertEquals(MyUtilty.convertIntPart("1001"),digits[1]+thousand + digits[1]);

    }


    @Test
    public void when_5_digit_then(){

        assertEquals(MyUtilty.convertIntPart("10000"),tens[1]+thousand);
        assertEquals(MyUtilty.convertIntPart("10100"),tens[1]+thousand+digits[1]+hundred);
        assertEquals(MyUtilty.convertIntPart("10120"),tens[1]+thousand+digits[1]+hundred + tens[2]);
        assertEquals(MyUtilty.convertIntPart("54044"),tens[5]+digits[4]+thousand+tens[4]+digits[4]);
        assertEquals(MyUtilty.convertIntPart("90001"),tens[9]+thousand + digits[1]);
        assertEquals(MyUtilty.convertIntPart("10001"),tens[1]+thousand + digits[1]);

    }

    @Test
    public void when_6_digit_then(){

        assertEquals(MyUtilty.convertIntPart("100000"),digits[1]+hundred+thousand);
        assertEquals(MyUtilty.convertIntPart("101001"),digits[1]+hundred+digits[1]+thousand+digits[1]);
        assertEquals(MyUtilty.convertIntPart("101208"),digits[1]+hundred+digits[1]+thousand+digits[2]+hundred + digits[8]);
        assertEquals(MyUtilty.convertIntPart("540434"),digits[5]+hundred+tens[4]+thousand+digits[4]+hundred+tens[3]+digits[4]);
        assertEquals(MyUtilty.convertIntPart("900031"),digits[9]+hundred+thousand+tens[3]+digits[1]);
        assertEquals(MyUtilty.convertIntPart("106061"),digits[1]+hundred+digits[6] +thousand+tens[6]+digits[1]);

    }


    @Test
    public void when_7_digit_then(){

        assertEquals(MyUtilty.convertIntPart("1000000"),digits[1]+million);
        assertEquals(MyUtilty.convertIntPart("1011001"),digits[1]+million+tens[1]+digits[1]+thousand+digits[1]);
        assertEquals(MyUtilty.convertIntPart("8762318").trim(),"sekkiz milyon yeddi yuz altmis iki min uc yuz on sekkiz");
    }

    @Test
    public void when_8_digit_then(){

        assertEquals(MyUtilty.convertIntPart("10010100").trim(),"on milyon on min bir yuz");
        assertEquals(MyUtilty.convertIntPart("14100501").trim(),"on dord milyon bir yuz min bes yuz bir");
        assertEquals(MyUtilty.convertIntPart("45314523").trim(),"qirx bes milyon uc yuz on dord min bes yuz iyirmi uc");
    }

    @Test
    public void when_10_digit_then(){

        assertEquals(MyUtilty.convertIntPart("1001010550").trim(),"bir milyard bir milyon on min bes yuz elli");
        assertEquals(MyUtilty.convertIntPart("6124100501").trim(),"alti milyard bir yuz iyirmi dord milyon bir yuz min bes yuz bir");
        assertEquals(MyUtilty.convertIntPart("1000000000").trim(),"bir milyard");
    }

    @Test
    public void when_9_digit_then(){

        assertEquals(MyUtilty.convertIntPart("10010100").trim(),"on milyon on min bir yuz");
        assertEquals(MyUtilty.convertIntPart("14100501").trim(),"on dord milyon bir yuz min bes yuz bir");
        assertEquals(MyUtilty.convertIntPart("45314523").trim(),"qirx bes milyon uc yuz on dord min bes yuz iyirmi uc");
    }

    @Test
    public void when_12_digit_then(){

        assertEquals(MyUtilty.convertIntPart("121432123452").trim(),"bir yuz iyirmi bir milyard dord yuz otuz iki milyon bir yuz iyirmi uc min dord yuz elli iki");
        assertEquals(MyUtilty.convertIntPart("100000100000").trim(),"bir yuz milyard bir yuz min");
        assertEquals(MyUtilty.convertIntPart("121001212001").trim(),"bir yuz iyirmi bir milyard bir milyon iki yuz on iki min bir");
    }


}
