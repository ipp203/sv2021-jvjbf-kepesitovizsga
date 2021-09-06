package training360.guinessapp;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;

import java.util.List;

@Service
public class RecoderService {
    private final RecorderRepository repository;
    private final ModelMapper modelMapper;

    public RecoderService(RecorderRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public RecorderDto createRecorder(RecorderCreateCommand command) {
        Recorder recorder = new Recorder(command.getName(), command.getDateOfBirth());
        repository.save(recorder);
        return modelMapper.map(recorder, RecorderDto.class);
    }

    public List<RecorderShortDto> listRecorders() {
        return repository.findAllFilteredByLettersOrderedByDate();
    }
}
