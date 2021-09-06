package training360.guinessapp.recorder;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecorderNotFoundException extends AbstractThrowableProblem {

    public RecorderNotFoundException(long id) {
        super(URI.create("recorders/not-found"),
                "Recorder not found",
                Status.NOT_FOUND,
                String.format("Recorder with id %d not found", id));
    }
}
