package training360.guinessapp.worldrecord;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class WorldRecordNotFoundException extends AbstractThrowableProblem {
    public WorldRecordNotFoundException(long id) {
        super(URI.create("worldrecords/not-found"),
                "World record not found",
                Status.NOT_FOUND,
                String.format("World record  with id %d not found.", id));
    }
}
