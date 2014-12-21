package org.fruit.blueberry.dao.jdbc.impl;

import org.fruit.blueberry.dao.jdbc.FruitDao;
import org.fruit.blueberry.dto.condition.FruitCondition;
import org.fruit.blueberry.model.Fruit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Stephen on 9/1/2014.
 */
@Repository(value = "fruitDao")
public class FruitDaoImpl extends JdbcDaoSupport implements FruitDao {
    @Override
    public long save(Fruit fruit) {
        String sql = "INSERT INTO fruit(name,retail_price,trade_price,picture,create_time) VALUES(?,?,?,?,?)";
        return JdbcHelper.save(getJdbcTemplate(), sql,
                fruit.getName(), fruit.getRetailPrice(), fruit.getTradePrice(),
                fruit.getPicture(), fruit.getCreateTime());
    }

    @Override
    public int delete(long id) {
        String sql = "DELETE FROM fruit WHERE id=?";
        return JdbcHelper.executeSql(getJdbcTemplate(), sql, id);
    }

    @Override
    public List<Fruit> findByCondition(FruitCondition condition) {
        return null;
    }

    @Override
    public int update(Fruit fruit) {
        String sql = "UPDATE fruit SET name=?,retail_price=?,trade_price=?,picture=?,update_time=? WHERE id=?";
        return JdbcHelper.executeSql(getJdbcTemplate(), sql,
                fruit.getName(), fruit.getRetailPrice(), fruit.getTradePrice(),
                fruit.getPicture(), fruit.getUpdateTime(), fruit.getId());
    }
}
