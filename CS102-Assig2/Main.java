import java.io.File;

public class Main {
    public static void main(String[] args) {
        File txt = new File("input.txt");
        File mapTxt = new File("myTexts\\map2.txt");
        Methods.methodTwo(txt);
        Methods.methodOne(txt);

        File encodedTxt = new File("myTexts\\encoded2.txt");
        Methods.reverseOperate(mapTxt, encodedTxt);
    }
}
