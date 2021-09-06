package training360.guinessapp.worldrecord;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.BeatWorldRecordCommand;
import training360.guinessapp.dto.BeatWorldRecordDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;
import training360.guinessapp.recorder.Recorder;
import training360.guinessapp.recorder.RecorderRepositoryOperation;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class WorldRecordService {

    private final WorldRecordRepository repository;
    private final ModelMapper modelMapper;
    private final RecorderRepositoryOperation recorderRepositoryOperation;

    public WorldRecordService(WorldRecordRepository repository, ModelMapper modelMapper, RecorderRepositoryOperation recorderRepositoryOperation) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.recorderRepositoryOperation = recorderRepositoryOperation;
    }

    public WorldRecordDto createWorldRecord(WorldRecordCreateCommand command) {
        Recorder recorder = recorderRepositoryOperation.findById(command.getRecorderId());

        WorldRecord worldRecord = new WorldRecord(command.getDescription(),
                command.getValue(),
                command.getUnitOfMeasure(),
                command.getDateOfRecord(),
                recorder);

        repository.save(worldRecord);
        return modelMapper.map(worldRecord, WorldRecordDto.class);
    }

    @Transactional
    public BeatWorldRecordDto beatWorldRecord(long id, BeatWorldRecordCommand command) {
        WorldRecord record = findById(id);
        Recorder oldRecorder = recorderRepositoryOperation.findById(record.getRecorder().getId());
        Recorder newRecorder = recorderRepositoryOperation.findById(command.getRecorderId());

        double difference = setNewRecordData(command, record, newRecorder);

        return new BeatWorldRecordDto(record.getDescription(),
                record.getUnitOfMeasure(),
                oldRecorder.getName(),
                newRecorder.getName(),
                command.getValue(),
                difference);
    }

    private double setNewRecordData(BeatWorldRecordCommand command, WorldRecord record, Recorder newRecorder) {
        if (record.getValue() > command.getValue()) {
            throw new NewValueNotBeatOldRecordException(record.getValue(), command.getValue());
        }
        double difference = command.getValue() - record.getValue();
        record.setValue(command.getValue());
        record.setRecorder(newRecorder);
        record.setDateOfRecord(LocalDate.now());
        return difference;
    }

    private WorldRecord findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new WorldRecordNotFoundException(id));
    }
}
