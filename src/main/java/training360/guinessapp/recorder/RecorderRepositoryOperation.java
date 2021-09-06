package training360.guinessapp.recorder;

import org.springframework.stereotype.Component;

@Component
public class RecorderRepositoryOperation {
    private final RecorderRepository repository;

    public RecorderRepositoryOperation(RecorderRepository repository) {
        this.repository = repository;
    }

    public Recorder findById(long id){
        return repository.findById(id)
                .orElseThrow(()-> new RecorderNotFoundException(id));
    }
}
