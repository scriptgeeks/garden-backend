package co.scriptgeek.gardenkeeper.domain.repository;

import co.scriptgeek.gardenkeeper.domain.model.MeasurePackage;

/**
 * Created by xiaofeng on 15/4/7.
 */
public interface MeasurePackageRepository {
    /**
     *
     * @param measurePackage
     */
    void save(MeasurePackage measurePackage);
}
