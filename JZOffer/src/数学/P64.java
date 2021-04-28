package 数学;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
 */
public class P64 {
    public static void main(String[] args) {
        int res = sumNums(10);
        System.out.println(res);
    }
    public static int sumNums(int n){
        boolean x = n > 1 && (n += sumNums(n-1)) > 0;
        return n;
    }
}
