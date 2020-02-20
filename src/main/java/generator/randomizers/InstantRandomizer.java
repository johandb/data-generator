package generator.randomizers;

import java.time.Instant;

public class InstantRandomizer implements Randomizer<Instant> {

    public InstantRandomizer() {
    }

    @Override
    public Instant getValue() {
        return Instant.now();
    }
}
