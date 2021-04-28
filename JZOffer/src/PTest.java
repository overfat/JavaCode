import java.util.ArrayList;

/**
 * 消消乐问题,输入一组数字，如果相邻两个数字只和为10，则消去他们,余下的数字可以继续消去，知道余下的数字不在为10
 */
public class PTest {
    public static void main(String[] args) {
        int n = 7;
        int number = 2134314;
        int res = RemoveDigit(7,number);
        System.out.println(res);
    }

     public static int RemoveDigit(int n, int number){
        //先把数字转换为字符串，然后对字符串进行遍历
        String str = number + "";
        char[] charArray = str.toCharArray();
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0; //用来记录第几次消除
        boolean flag = false;
        while(str.length() > 0){
            for(int i = 0; i<str.length()-1; i++){
                char c1 = str.charAt(i);
                char c2 = str.charAt(i+1);
                if(Integer.parseInt(c1 +"") + Integer.parseInt(c2+"") == 10){
                    count += 1;
                    flag = true;
                    charArray[i] = '0';
                    charArray[i+1] = '0';
                    list.add(i);
                    i = i + 1;


                }
            }
            // 通过一个循环找到所有的位置，然后把余下的字符串进行拼接
            if(flag == false){
                if(count == 0){
                    //说明没有相加为10的
                    System.out.println("不能消除");
                    break;
                }else{
                    //说明余下的字符串中没有相加为10的了，直接中断程序即可
                    break;
                }
            }else{
                //说明有消除的，需要把余下的字符进行拼接
                char[] res = new char[charArray.length - list.size()*2];
                int count2 = 0;
                for(int i = 0; i < charArray.length;i++){
                    if(charArray[i] == '0')
                        continue;
                    else
                        res[count2++] = charArray[i];
                }
                str = String.valueOf(res);
                charArray = res;
                list.clear();
                flag = false;
            }
        }
        return Integer.parseInt(str);
     }
}
