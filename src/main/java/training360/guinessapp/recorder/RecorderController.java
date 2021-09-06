package training360.guinessapp.recorder;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;

import javax.validation.Valid;
import java.util.List;


@RestController
public class RecorderController {

    private final RecoderService service;

    public RecorderController(RecoderService service) {
        this.service = service;
    }

    @PostMapping("/api/recorders")
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto createRecorder(@Valid @RequestBody RecorderCreateCommand command) {
        return service.createRecorder(command);
    }

    @GetMapping("/api/recorders")
    public List<RecorderShortDto> listRecorders() {
        return service.listRecorders();
    }
}
