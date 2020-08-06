package sample.util;

public final class Literals {
    public static final String[] digits;
    public static final String[] tens;
    public static final String hundred = "yüz ";
    public static final String thousand = "min ";
    public static final String million = "milyon ";
    public static final String billion = "milyard ";
    public static final String EMPTY = "";
    public static final String separator = "tam ";


    static {
        digits = new String[]{"sıfır ", "bir ", "iki ", "üç ", "dörd ",
                "beş ", "altı ", "yeddi ", "səkkiz ", "doqquz "};
        tens = new String[]{"", "on ", "iyirmi ", "otuz ", "qırx ",
                "əlli ", "altmış ", "yetmiş ", "səksən ", "doxsan "};

    }
    private Literals(){
        throw new IllegalStateException("Cannot be instantiated");
    }
}
