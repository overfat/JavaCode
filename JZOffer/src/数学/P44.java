package 数学;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 */
public class P44 {
    public static void main(String[] args) {
        int res = findNthDigit(10000);
        System.out.println(res);
    }
    public static int findNthDigit(int n) {
        if(n == 0){
            return 0;
        }
        // 首先确定在哪个区间,找到先除以9，观察结果是几位数，如果是4位数，就和4321进行比较
        int count = n/9;
        int digit = (count + "").length();
        int number = 0;
        for(int i = 1; i<=digit; i++){
            number += i*Math.pow(10,i-1);
        }
        if(count > number){
            // 就要减去某个值
            n = n - number*9;
            int value = n/(digit+1);
            int value2 = n % (digit+1);
            int value3 = (int)Math.pow(10,digit) + value + 1;
            // 然后找到第value2位就可以了
            char res = (value3+"").charAt(value2 - 1);
            int res2 = Integer.parseInt(res+"");
            return res2;
        }else{
            // 先减去某个值

        }

        return 0;

    }
}
