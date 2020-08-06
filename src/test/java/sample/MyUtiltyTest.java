package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sample.util.Literals.*;
public class MyUtiltyTest {



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
        assertEquals(MyUtilty.convertIntPart("8762318").trim(),"səkkiz milyon yeddi yüz altmış iki min üç yüz on səkkiz");
    }

    @Test
    public void when_8_digit_then(){

        assertEquals(MyUtilty.convertIntPart("10010100").trim(),"on milyon on min bir yüz");
        assertEquals(MyUtilty.convertIntPart("14100501").trim(),"on dörd milyon bir yüz min beş yüz bir");
        assertEquals(MyUtilty.convertIntPart("45314523").trim(),"qırx beş milyon üç yüz on dörd min beş yüz iyirmi üç");
    }

    @Test
    public void when_10_digit_then(){

        assertEquals(MyUtilty.convertIntPart("1001010550").trim(),"bir milyard bir milyon on min beş yüz əlli");
        assertEquals(MyUtilty.convertIntPart("6124100501").trim(),"altı milyard bir yüz iyirmi dörd milyon bir yüz min beş yüz bir");
        assertEquals(MyUtilty.convertIntPart("1000000000").trim(),"bir milyard");
    }

    @Test
    public void when_9_digit_then(){

        assertEquals(MyUtilty.convertIntPart("10010100").trim(),"on milyon on min bir yüz");
        assertEquals(MyUtilty.convertIntPart("14100501").trim(),"on dörd milyon bir yüz min beş yüz bir");
        assertEquals(MyUtilty.convertIntPart("45314523").trim(),"qırx beş milyon üç yüz on dörd min beş yüz iyirmi üç");
    }

    @Test
    public void when_12_digit_then(){

        assertEquals(MyUtilty.convertIntPart("121432123452").trim(),"bir yüz iyirmi bir milyard dörd yüz otuz iki milyon bir yüz iyirmi üç min dörd yüz əlli iki");
        assertEquals(MyUtilty.convertIntPart("100000100000").trim(),"bir yüz milyard bir yüz min");
        assertEquals(MyUtilty.convertIntPart("121001212001").trim(),"bir yüz iyirmi bir milyard bir milyon iki yüz on iki min bir");
    }


}
