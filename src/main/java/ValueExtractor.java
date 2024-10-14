import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;
import java.util.HashMap;

@UdfDescription(name = "get_value_for_type", description = "Retrieve the value corresponding to a specific type from two input strings new")
public class ValueExtractor {

    @Udf(description = "Get value for specified type new")
    public String getValueForType(
            @UdfParameter(value = "types") String string1,
            @UdfParameter(value = "values") String string2,
            @UdfParameter(value = "type") String p_type) {

        HashMap<String, Integer> positionMap = new HashMap<>();
        String[] types = string1.split("\\|");
        System.out.println("types : " + types[0].toString());

        for (int i = 0; i < types.length; i++) {
            if (types[i].equals( p_type)) {
                positionMap.put(types[i], i + 1);
                break;
            }

        }

        Integer position = positionMap.get(p_type);

        System.out.println("position:" + position);
        if (position == null) {
            return null;
        }

        String[] values = string2.split("\\|");
        String result = position > 0 && position <= values.length ? values[position - 1] : null;
//        if (result.isEmpty()) {
//            return null;
//        }

        return result;

    }

//    public static void main(String[] args) {
//        ValueExtractor valueExtractor = new ValueExtractor();
//
//        // Chuỗi đầu vào
//        //String string1 = "nation.id|passport";
//        //String string2 = "0|2";
//        String string1 = "nation.id|passport";
//        String string2 = "accsads  asdad |bdbdbdbff";
//
//        // Kiểm tra các giá trị
//        System.out.println("Giá trị của 'passport': " +
//                valueExtractor.getValueForType(string1, string2, "passport")); // Kết quả: 012
//
//        System.out.println("Giá trị của 'nation.id': " +
//                valueExtractor.getValueForType(string1, string2, "nation.id")); // Kết quả: 012
//
////
//    }
}
