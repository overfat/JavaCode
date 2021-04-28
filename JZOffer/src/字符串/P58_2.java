package 字符串;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 */

public class P58_2 {
    public static void main(String[] args) {
        String s = "abcdefg";
        String res = reverseLeftWords(s, 5);
        System.out.println(res);
    }
    public static String reverseLeftWords(String s, int n){

        int l = s.length();
        if(n > l)
            return null;
        StringBuilder res = new StringBuilder();
        res.append(s.substring(n,l));
        res.append(s.substring(0,n));
        return res.toString();
    }
}
