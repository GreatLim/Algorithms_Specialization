public class Main {
    final static int LOW = 0;
    final static int HIGH = 1;


    public static int[] Mul(int[] num1, int[] num2) {
        int res[] = new int[num1.length * 2];
        if(num1.length == 1 && num2.length == 1) {
            return MulHelper(num1, num2);
        }
        int[][] res1 = BreakNumArray(num1);
        int[][] res2 = BreakNumArray(num2);
        int n = num1.length;
        int[] a = res1[HIGH];
        int[] b = res1[LOW];
        int[] c = res2[HIGH];
        int[] d = res2[LOW];
        int[] temp1 = ChangeSize(Mul(a, c));
        int[] temp2 = ChangeSize(Mul(b, d));
        int[] temp3 = ChangeSize(Mul(a, d));
        int[] temp4 = ChangeSize(Mul(b, c));
        int[] temp5 = MoveToLeft(temp1, n);
        int[] temp6 = MoveToLeft(AddWithoutChangedSize(temp3, temp4), n/2);
        int[] temp7 = AddWithoutChangedSize(temp2, temp5);
        res = AddWithoutChangedSize(temp7, temp6);

        return res;

    }

    public static int[] ChangeSize(int[] num) {
        int[] res = new int[2 * num.length];
        for(int i = 0; i < num.length; i++) {
            res[i + num.length] = num[i];
        }
        return res;
    }


    public static int[] MoveToLeft(int[] num, int n) {
        int[] res = new int[num.length];
        for(int i = n; i < num.length; i++) {
            res[i - n] = num[i];
        }
        return res;
    }

    public static int[] MulHelper(int[] num1, int[] num2) {
        int[] res = new int[2];
        int mul = num1[0] * num2[0];
        res[0] = mul / 10;
        res[1] = mul % 10;
        return res;
    }

    public static int[] AddWithChangedSize(int[] num1, int[] num2) {
        int[] res = new int[num1.length * 2];
        int carry = 0;
        for(int i = num1.length - 1; i >= 0; i--) {
            int sum = num1[i] + num2[i] + carry;
            res[i + num1.length] = sum % 10;
            carry = sum / 10;
        }
        if(carry != 0) {
            res[num1.length - 1] = carry;
        }
        return res;
    }

    public static int[] AddWithoutChangedSize(int[] num1, int[] num2) {
        int[] res = new int[num1.length];
        int carry = 0;
        for(int i = num1.length - 1; i >= 0; i--) {
            int sum = num1[i] + num2[i] + carry;
            res[i] = sum % 10;
            carry = sum / 10;
        }
        return res;
    }

    public static int[][] BreakNumArray(int[] num) {
        int[][] res = new int[2][num.length / 2];
        for(int i = 0; i < num.length / 2; i++) {
            res[LOW][i] = num[i + num.length / 2];
        }

        for(int i = 0; i < num.length / 2; i++) {
            res[HIGH][i] = num[i];
        }
        return res;
    }



    public static int[] StringToNumArray(String s) {
        char[] chars = s.toCharArray();
        int [] res = new int[chars.length];
        for(int i = 0; i < chars.length; i++) {
            res[i] = chars[i] - '0';
        }
        return res;
    }

    public static String NumArrayToString(int[] num) {
        String s = "";
        for(int i = 0; i < num.length; i++) {
            s += num[i];
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println("---test StringToInts & NumArrayToString---");
        String s1 = "3141592653589793238462643383279502884197169399375105820974944592";
        System.out.println(s1);
        int[] num = StringToNumArray(s1);
        System.out.println(NumArrayToString(num));

        System.out.println("\n---test BreakNumArray---");
        int[][] res = BreakNumArray(num);
        int[] num1 = res[HIGH];
        int[] num2 = res[LOW];
        System.out.println(NumArrayToString(num1));
        System.out.println(NumArrayToString(num2));

        System.out.println("\n---test AddWithChangedSize---");
        System.out.println("99 + 99 =");
        int[] num3 = StringToNumArray("99");
        int[] num4 = StringToNumArray("99");
        System.out.println(NumArrayToString(AddWithChangedSize(num3, num4)));

        System.out.println("\n---test AddWithoutChangedSize---");
        System.out.println("4 + 4 =");
        int[] num5 = StringToNumArray("4");
        int[] num6 = StringToNumArray("4");
        System.out.println(NumArrayToString(AddWithoutChangedSize(num5, num6)));

        System.out.println("\n---test MulHelper---");
        System.out.println("9 * 9 =");
        int[] num7 = StringToNumArray("9");
        int[] num8 = StringToNumArray("9");
        System.out.println(NumArrayToString(MulHelper(num7, num8)));

        System.out.println("\n---test MoveToLeft---");
        System.out.println("0009 -> 0900");
        int[] num9 = StringToNumArray("0009");
        System.out.println(NumArrayToString(MoveToLeft(num9, 2)));

        System.out.println("\n---test ChangeSize---");
        System.out.println("09 -> 0009");
        int[] num10 = StringToNumArray("09");
        System.out.println(NumArrayToString(ChangeSize(num10)));

        System.out.println("\n---test Mul---");
        System.out.println("11 * 11");
        int[] num11 = StringToNumArray("11");
        int[] num12 = StringToNumArray("11");
        System.out.println(NumArrayToString(Mul(num11, num12)));

        System.out.println("\n---test Mul---");
        System.out.println("1111 * 1111");
        System.out.println(1111 * 1111);
        int[] num13 = StringToNumArray("1111");
        int[] num14 = StringToNumArray("1111");
        System.out.println(NumArrayToString(Mul(num13, num14)));

        System.out.println("\n---FINAL RESULT---");
        int[] numF1 = StringToNumArray("3141592653589793238462643383279502884197169399375105820974944592");
        int[] numF2 = StringToNumArray("2718281828459045235360287471352662497757247093699959574966967627");
        System.out.println(NumArrayToString(Mul(numF1, numF2)));

    }
}
