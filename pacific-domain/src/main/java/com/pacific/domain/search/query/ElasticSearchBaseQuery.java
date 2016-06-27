package com.pacific.domain.search.query;

import com.pacific.domain.query.BaseQuery;

/**
 * Created by Fe on 16/5/30.
 */
public class ElasticSearchBaseQuery extends BaseQuery {

    public static final Integer DEFAULT_START = 0;
    public static final Integer DEFAULT_SIZE = 5000;

    private String index;
    private String type;
    private String id;
    private String score;
    private Integer version;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
