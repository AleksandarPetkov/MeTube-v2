package service;

import domain.models.service.TubeServiceModel;

public interface TubeService {

    boolean uploadTube(TubeServiceModel tubeServiceModel);

    TubeServiceModel findTubeById(String id);
}
