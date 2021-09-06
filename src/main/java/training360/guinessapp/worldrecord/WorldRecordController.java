package training360.guinessapp.worldrecord;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.BeatWorldRecordCommand;
import training360.guinessapp.dto.BeatWorldRecordDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;

import javax.validation.Valid;

@RestController
public class WorldRecordController {

    private final WorldRecordService service;

    public WorldRecordController(WorldRecordService service) {
        this.service = service;
    }

    @PostMapping("/api/worldrecords")
    @ResponseStatus(HttpStatus.CREATED)
    public WorldRecordDto createWorldRecord(@Valid @RequestBody WorldRecordCreateCommand command){
        return service.createWorldRecord(command);
    }

    @PutMapping("/api/worldrecords/{id}/beatrecords")
    public BeatWorldRecordDto beatWorldRecord(@PathVariable("id")long id, @Valid @RequestBody BeatWorldRecordCommand command){
        return service.beatWorldRecord(id, command);
    }
}
