package artur.azambuja.scholarship.service;

import org.modelmapper.ModelMapper;

public abstract class serviceClass {
    protected final ModelMapper modelMapper;

    public serviceClass(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
}
