package co.scriptgeek.gardenkeeper.persistence.repoimpl;

import co.scriptgeek.gardenkeeper.domain.model.MeasurePackage;
import co.scriptgeek.gardenkeeper.domain.repository.MeasurePackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaofeng on 15/4/7.
 */
@Repository
public class MeasurePackageRepositoryJdbcImpl implements MeasurePackageRepository {
    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public void save(MeasurePackage measurePackage) {

    }
}
