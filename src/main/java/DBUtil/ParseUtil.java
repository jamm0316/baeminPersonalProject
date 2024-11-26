package DBUtil;

public class ParseUtil {
    public static int areaStringParseInt(String areaStr) {
        int location;
        switch (areaStr) {
            case "강남구" -> {location = 1; return location;}
            case "강동구" -> {location = 2; return location;}
            case "강서구" -> {location = 3; return location;}
            case "관악구" -> {location = 4; return location;}
            case "구로구" -> {location = 5; return location;}
            case "금천구" -> {location = 6; return location;}
            case "동작구" -> {location = 7; return location;}
            case "서초구" -> {location = 8; return location;}
            case "송파구" -> {location = 9; return location;}
            case "양천구" -> {location = 10;return location;}
            case "영등포구" -> {location = 11;return location;}
        }
        return 0;
    }

    public static String  areaIntParseString(int areaStr) {
        String strlocation;
        switch (areaStr) {
            case 1 -> {strlocation = "강남구"; return strlocation;}
            case 2 -> {strlocation = "강동구"; return strlocation;}
            case 3 -> {strlocation = "강서구"; return strlocation;}
            case 4 -> {strlocation = "관악구"; return strlocation;}
            case 5 -> {strlocation = "구로구"; return strlocation;}
            case 6 -> {strlocation = "금천구"; return strlocation;}
            case 7 -> {strlocation = "동작구"; return strlocation;}
            case 8 -> {strlocation = "서초구"; return strlocation;}
            case 9 -> {strlocation = "송파구"; return strlocation;}
            case 10 -> {strlocation = "양천구";return strlocation;}
            case 11 -> {strlocation = "영등포구";return strlocation;}
        }
        return null;
    }

    public static int categoryStringParseInt(String categoryStr) {
        int category;
        switch (categoryStr) {
            case "치킨" -> {category = 1; return category;}
            case "중식" -> {category = 2; return category;}
            case "돈까스" -> {category = 3; return category;}
            case "피자" -> {category = 4; return category;}
            case "회" -> {category = 5; return category;}
            case "한식" -> {category = 6; return category;}
            case "족발" -> {category = 7; return category;}
            case "디저트" -> {category = 8; return category;}
            case "분식" -> {category = 9; return category;}
            case "카페" -> {category = 10;return category;}
        }
        return 0;
    }
}
