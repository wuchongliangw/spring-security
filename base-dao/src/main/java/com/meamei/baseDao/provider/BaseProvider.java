package com.meamei.baseDao.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * Area class
 *
 * @author hboxs022
 * @date 2019/3/29
 */
public class BaseProvider {

    public void appendStore(List<Long> storeIds, SQL sql) {
        if (storeIds.size() <= 1) {
            sql.WHERE(" store_id = " + storeIds.get(0));
        } else {
            StringBuilder storeId = new StringBuilder("(");
            for (Long id : storeIds) {
                storeId.append(id).append(",");
            }
            sql.WHERE(" store_id in " + storeId.substring(0, storeId.length() - 1) + ")");
        }
    }
}
