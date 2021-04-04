import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberPlayList {
    public static void main(String[] args) {
        List<Integer> myNumberList = new ArrayList<>();
        for(int i=0;i<5;i++) myNumberList.add(i);

        //Method1
        Iterator<Integer> it = myNumberList.iterator();
        while(it.hasNext()){
            Integer i = it.next();
            System.out.println(i);
        }

        //Method2
        class MyConsumer implements Consumer<Integer> {
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }

        }
        MyConsumer action = new MyConsumer();
        myNumberList.forEach(action);


        //Method 3: Traversing with anonymous consumer interface implementation
        myNumberList.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
        });

        //Method 4: Traversing with anonymous consumer interface implementation
        Consumer<Integer> myListAction = n -> System.out.println("here "+n);
        myNumberList.forEach(myListAction);

        //Method 5: Implicit Lamda expression
        myNumberList.forEach(n-> {System.out.println(n);});

        //Method 6: Implicit Lamda expression to print double value
        Predicate<Integer> isEvenFunction = n -> n>0 && n%2==0;
        List<Double> doubleList = new ArrayList<Double>();
        Function<Integer, Double> toDoubleFuncton = Integer::doubleValue;
        List<Double> evenList = new ArrayList<Double>();
        myNumberList.forEach(n-> {
            doubleList.add(toDoubleFuncton.apply(n));
        });
        doubleList.forEach(n1 -> {
            if(isEvenFunction.test(n1.intValue())) {
                evenList.add(n1);
            }
        });
        System.out.println(evenList);


        //Method 7: Implicit Lamda expression to check even
         myNumberList.forEach(n->{System.out.println(isEvenFunction.test(n));});

         //Method 8 : Processing the stream
        myNumberList.stream().forEach(n-> {
            System.out.println(n);
        });

        //Method 9 : Process the streams, apply operations on the streams and then store result
        System.out.println("Method 9");
        List<Double> streamList = myNumberList.stream()
                .peek(n -> System.out.println(n))

                .map(toDoubleFuncton)
                .collect(Collectors.toList());
        System.out.println(streamList);

        //Method 10 : Listing the first even
        Integer first = myNumberList.stream()
                .filter(isEvenFunction)
                .peek(n->System.out.println(n))
                .findFirst()
                .orElse(null);
        System.out.println(first);

        //Method 11: Minimum even number
        Integer min = myNumberList.stream()
                .filter(isEvenFunction)
                .min((n1, n2)->n1-n2)
                .orElse(null);

        //Method 12: Maximum even number
        Integer max = myNumberList.stream()
                .filter(isEvenFunction)
                .max(Comparator.comparing(Integer::intValue))
                .orElse(null);

        //Method 13: sum, count, average
        Integer sum = myNumberList.stream()
                .reduce(0,Integer::sum);
        long count = myNumberList.stream().count();
        double avg = sum/count;

        //Method 14: all even, one even, multiple of six
        boolean allEven = myNumberList.stream().allMatch(isEvenFunction);
        boolean oneEven = myNumberList.stream().anyMatch(isEvenFunction);
        //boolean noneMultOfSix = myNumberList.stream().

    }
}
