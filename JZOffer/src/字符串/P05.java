package 字符串;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 */
public class P05 {
    public static void main(String[] args){
        String s = "We are happy";
        String result = replaceSpace(s);
        System.out.println(result);
    }
    public static String replaceSpace(String s){
        if(s == null || s.length() == 0)
            return s;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == ' '){
                result.append("%20");
            }else{
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }
}
