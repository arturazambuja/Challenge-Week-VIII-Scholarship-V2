package artur.azambuja.scholarship.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public abstract class serviceClass {
    protected final ModelMapper modelMapper;

    public serviceClass(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
}
