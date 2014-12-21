package org.fruit.blueberry.dao.jdbc;

import org.fruit.blueberry.dto.condition.FruitCondition;
import org.fruit.blueberry.model.Fruit;

import java.util.List;

/**
 * Created by Stephen on 9/1/2014.
 */
public interface FruitDao extends JdbcDao {
    long save(Fruit fruit);

    int delete(long id);

    List<Fruit> findByCondition(FruitCondition condition);

    int update(Fruit fruit);
}
