package service;

import domain.models.service.TubeServiceModel;
import org.modelmapper.ModelMapper;
import repository.TubeRepository;

import javax.inject.Inject;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;

    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean uploadTube(TubeServiceModel tubeServiceModel) {
        return false;
    }
}
