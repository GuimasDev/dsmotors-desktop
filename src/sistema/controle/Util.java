package controle;

import java.sql.Date;

public class Util {
    public static String transformDateToString(Date data) {
        String dataString = data.toString();
        String[] dataFormatada = dataString.split("-");
        return dataFormatada[2] +" / "+ dataFormatada[1] +" / "+ dataFormatada[0];
    }

    public static Date transformStringToDate(String data) {
        String[] arrData = data.split(" / ");
        String dataFormatada = arrData[2] + "-" + arrData[1] + "-" + arrData[0];
        return Date.valueOf(dataFormatada);
    }
}
