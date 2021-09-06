package training360.guinessapp.worldrecord;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NewValueNotBeatOldRecordException extends AbstractThrowableProblem {
    public NewValueNotBeatOldRecordException(double oldValue, double newValue) {
        super(URI.create("worldrecords/not-world-record"),
                "Can not beat",
                Status.BAD_REQUEST,
                String.format("New value (%f) is smaller than world record value (%f)", newValue, oldValue));

    }
}
