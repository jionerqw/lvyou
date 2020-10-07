package com.lyf.dao;

import com.lyf.domain.Route;

import java.util.List;

public interface RouteDao {
    int findCountByName(String keyword);

    List<Route> findPageByName(String keyword, int start, int pageSize);

    int findCountById(int cid);

    List<Route> findPageByCid(int cid, int start, int pageSize);
}

