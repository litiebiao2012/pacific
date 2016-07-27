package com.pacific.mapper;

import com.pacific.domain.dto.report.SqlReportDto;
import com.pacific.domain.entity.Sql;
import com.pacific.domain.search.query.SqlQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SqlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sql record);

    int insertSelective(Sql record);

    Sql selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sql record);

    int updateByPrimaryKey(Sql record);

    void batchSaveSql(List<Sql> sqlList);

    List<SqlReportDto> selectByParam(SqlQuery sqlQuery);

    int  getTotalByParam(SqlQuery sqlQuery);


    Sql queryAlSqlByParam(@Param("applicationCode") String applicationCode,
                                 @Param("hostName") String hostName,
                                 @Param("beginDate") Date beginDate,
                                 @Param("endDate") Date endDate,
                                 @Param("sqlHash")String sqlHash);

    List<Sql> querySqlErrorByParam(@Param("applicationCode") String applicationCode,
                                         @Param("hostName") String hostName,
                                         @Param("beginDate") Date beginDate,
                                         @Param("endDate") Date endDate,
                                         @Param("sqlHash")String sqlHash);

}