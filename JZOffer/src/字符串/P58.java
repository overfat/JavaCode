package 字符串;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
 */

public class P58 {
    public static void main(String[] args) {
        String s = "the sky is blue";
        String res = reverseWords(s);
        System.out.println(res);
    }
    public static String reverseWords(String s){
        //这种需要找到一个连续子序列的题目,要想到使用双指针法
        s = s.trim(); //把前后字符串全部都去掉
        StringBuilder res = new StringBuilder();
        int j = s.length() - 1;
        int i = j;
        while(i >= 0){
            while(i >= 0 && s.charAt(i) != ' ') i--;
            res.append(s.substring(i+1,j+1) + " ");
            while(i>=0 && s.charAt(i) == ' ') i--;
            j = i;// j指向下个单词的末尾就可以了
        }

        return res.toString().trim();
    }
}
