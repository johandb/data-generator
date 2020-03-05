package generator.randomizers;

import java.util.Random;

public class EvenValueRandomizer extends IntegerRandomizer {

    @Override
    public Integer getValue() {
        Random random = new Random();
        int number = random.nextInt();
        while (!(number % 2 == 0)) {
            number = random.nextInt();
        }
        return number;
    }
}
