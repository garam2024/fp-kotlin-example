package fp.humancoding;

import java.util.List;

@FunctionalInterface
public interface MyCountable {
     int myCount(List<Integer> list);
     String toString();
     boolean equals(Object o);
}
