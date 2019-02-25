public class Smile {
    public static void main(String[] args) {
        String str = ":-)))))";
        str = cutSmile(str, ":-)", "))))");
        str = cutSmile(str, ":-(", "(");
        System.out.println(str);
    }

    private static String cutSmile(String src, String smile, String postfix) {
        StringBuilder sb = new StringBuilder();
        int start = 0, end;
        while ( (end = src.indexOf(smile, start)) != -1 ) {
            sb.append(src, start, end);
            start = end + smile.length();
            while ( src.startsWith(postfix, start) ) {
                start += postfix.length();
            }
        }
        sb.append(src, start, src.length());
        return sb.toString();
    }


}
