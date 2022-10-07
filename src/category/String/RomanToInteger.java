package category.String;

public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        int[] res = new int[s.length()];

        for(int i=0; i<s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M' :
                    res[i] = 1000;
                    break;
                case 'D' :
                    res[i] = 500;
                    break;
                case 'C' :
                    res[i] = 100;
                    break;
                case 'L' :
                    res[i] = 50;
                    break;
                case 'X' :
                    res[i] = 10;
                    break;
                case 'V' :
                    res[i] = 5;
                    break;
                case 'I' :
                    res[i] = 1;
                    break;
            }
        }

        int sum = 0;
        for(int i=0; i<s.length()-1; i++) {
            if(res[i] < res[i+1]) {
                sum-=res[i];
            }else{
                sum+=res[i];
            }
        }

        sum+=res[s.length()-1];
        return sum;
    }
}
