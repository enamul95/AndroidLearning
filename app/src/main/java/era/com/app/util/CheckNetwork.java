package era.com.app.util;

public class CheckNetwork {

    public static String getRetrofit_NullCheck(String s){
        String rValue = "";
        if(s == null || s.isEmpty()||s.endsWith("null")){
            rValue = "";
        }else{
            rValue = s;
        }
        return rValue;
    }
}
