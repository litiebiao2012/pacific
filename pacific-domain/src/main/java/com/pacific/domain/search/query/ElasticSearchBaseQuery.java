package com.pacific.domain.search.query;

/**
 * Created by Fe on 16/5/30.
 */
public class ElasticSearchBaseQuery {

    public static final Integer DEFAULT_START = 0;
    public static final Integer DEFAULT_SIZE = 100;

    private String index;
    private String type;
    private String id;
    private String score;
    private Integer version;

    private Integer start = DEFAULT_START;
    private Integer size = DEFAULT_SIZE;

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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
