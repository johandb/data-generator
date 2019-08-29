package generator.randomizers;

import java.util.UUID;

public class UUIDRandomizer implements Randomizer<UUID> {

    public UUIDRandomizer() {
    }

    @Override
    public UUID getValue() {
        return UUID.randomUUID();
    }
}
