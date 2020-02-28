public class Concatenation {

    protected static String[] getRegionsCode()
    {
        String[] regionsCode = new String[100];
        for (int i = 0; i < regionsCode.length; i++) {
            regionsCode[i] = (i < 10) ? padNumber(i, 2) : Integer.toString(i);
        }
        return regionsCode;
    }

    protected static String[] getNumbers()
    {
        String[] numbers = new String[1000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (i < 100) ? padNumber(i, 3) : Integer.toString(i);
        }
        return numbers;
    }

    private static String padNumber(int number, int numberLength)
    {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for(int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
