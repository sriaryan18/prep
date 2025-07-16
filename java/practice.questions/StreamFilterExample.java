import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamFilterExample {
        public static <T> List<T> filterList(List<T> list , Predicate<T> filter){
            return list.stream()
            .filter(filter)
            .collect(Collectors.toList());
        }
}
