package 数组;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 */

public class P50 {
    public static void main(String[] args){
        String s = "abaccdeff";
        char res = firstUniqChar(s);
        System.out.println(res);
    }
    public static char firstUniqChar(String s){
        // 字符串函数
        if(s.length() == 0 || s == null){
            return ' ';
        }
        // 本题目不用麻烦，直接使用哈希即可
        int[] target = new int[26];
        for(int i = 0; i<s.length(); i++){
            target[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i<s.length(); i++){
            if(target[s.charAt(i) - 'a'] == 1){
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
