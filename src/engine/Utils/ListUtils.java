package engine.Utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtils {

    public static <E> List<E> getSortedNotNull(List<E> list) {
        return Stream.of(list)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .sorted()
                .collect(Collectors.toList());
    }
}
