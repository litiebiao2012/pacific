package com.pacific.mapper;

import com.pacific.domain.entity.WebUrl;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WebUrlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebUrl record);

    int insertSelective(WebUrl record);

    WebUrl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebUrl record);

    int updateByPrimaryKeyWithBLOBs(WebUrl record);

    int updateByPrimaryKey(WebUrl record);

    void batchSaveWebUrl(List<WebUrl> webUrlList);

    List<WebUrl> selectByParam(@Param("applicationCode") String applicationCode,
                               @Param("clientIp") String clientIp,
                               @Param("beginDate") Date beginDate,
                               @Param("endDate") Date endDate);
}