import java.io.IOException;
import java.util.*;

public class Calculator {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию:");
        String matOperation = scanner.nextLine();
        String result = calc(matOperation);
        System.out.println(result);
    }

    public static String calc(String input) throws IOException {
        String[] signsForCheck = {"+", "-", "*", "/"};
        String[] arrayForMatOper = input.split(" ");

        boolean signTrueOrFalse = false;
        int signIndex = -1;
        for (int i = 0; i < signsForCheck.length; i++) {
            if (input.contains(signsForCheck[i])) {
                signTrueOrFalse = true;
                signIndex = i;
                break;
            }
        }

        if (!signTrueOrFalse) {
            throw new IOException("throws Exception");
            // return "Exception: Cтрока не является доступной математической операцией. Доступны операции: +, -, *, /.";
        }

        if (arrayForMatOper.length != 3) {
            throw new IOException("throws Exception");
            //return "Exception: Неверный формат математической операции. Верный: 1 + 1, 2 + 3 или I + I";
        }

//        if (!NumberConverter.isCorrecFrom1to10(arrayForMatOper[0]) || !NumberConverter.isCorrecFrom1to10(arrayForMatOper[2]) ) {
//            return "Exception: Калькулятор работает только с цифрами от 1(I) до 10(X)";
//        }

        if (NumberConverter.isRoman(arrayForMatOper[0]) == NumberConverter.isRoman(arrayForMatOper[2])) {
            int a = 0;
            int b = 0;

            if (NumberConverter.isRoman(arrayForMatOper[0])) {
                a = NumberConverter.romanToArabic(arrayForMatOper[0]);
                b = NumberConverter.romanToArabic(arrayForMatOper[2]);
            } else {
                a = Integer.parseInt(arrayForMatOper[0]);
                b = Integer.parseInt(arrayForMatOper[2]);
            }

            if (a <= 0 || a > 10 || b <= 0 || b > 10) {
                throw new IOException("throws Exception");
                //return "Exception: Калькулятор работает только с цифрами от 1(I) до 10(X)";
            }

            int result = 0;
            switch (signsForCheck[signIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
            }

            if (NumberConverter.isRoman(arrayForMatOper[0])) {
                String resultRomanNum = NumberConverter.arabicToRoman(result);
                if (resultRomanNum == null) {
                    throw new IOException("throws Exception");
                    // return "Exception: Римские числа не могут быть представлены отрицательными, или равными нулю";
                } else {
                    return "Итог: " + resultRomanNum;
                }
            } else {
                return "Итог: " + result;
            }
        } else {
            throw new IOException("throws Exception");
            // return "Exception: Числа должны быть только арабские или только Римские";
        }
    }
}

class NumberConverter {

    public static boolean isRoman(String number) {
        String[] stringArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (String s : stringArray) {
            if (Objects.equals(number, s)) {
                return true;
            }
        }
        return false;
    }

    static final Map<String, Integer> romanDictionary = new HashMap<>();
    static final Map<Integer, String> arabicToRomanDic = new TreeMap<>((a, b) -> b - a);
    static {
        romanDictionary.put("I", 1);
        romanDictionary.put("II", 2);
        romanDictionary.put("III", 3);
        romanDictionary.put("IV", 4);
        romanDictionary.put("V", 5);
        romanDictionary.put("VI", 6);
        romanDictionary.put("VII", 7);
        romanDictionary.put("VIII", 8);
        romanDictionary.put("IX", 9);
        romanDictionary.put("X", 10);

        arabicToRomanDic.put(100, "C");
        arabicToRomanDic.put(90, "XC");
        arabicToRomanDic.put(50, "L");
        arabicToRomanDic.put(40, "XL");
        arabicToRomanDic.put(10, "X");
        arabicToRomanDic.put(9, "IX");
        arabicToRomanDic.put(5, "V");
        arabicToRomanDic.put(4, "IV");
        arabicToRomanDic.put(1, "I");
    }

    static boolean romanIntDictionary(String roman) {
        return romanDictionary.containsKey(roman);
    }

    static int romanToArabic(String roman) {
        return romanDictionary.getOrDefault(roman, 0);
    }

    static String arabicToRoman(int number) {
        if (number <= 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (Integer key : arabicToRomanDic.keySet()) {
            while (number >= key) {
                result.append(arabicToRomanDic.get(key));
                number -= key;
            }
        }
        return result.toString();
    }

    static boolean isCorrecFrom1to10 (String number) {
        int num = Integer.parseInt(number);
        return num >= 1 && num <= 10;
    }
}
