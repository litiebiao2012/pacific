package com.pacific.domain.dto.report;

import java.util.List;

/**
 * Created by fe on 16/7/27.
 */
public class SqlDetailDto {

    private SqlDetailReportDto sqlDetailReportDto;
    private List<SqlErrorDetail> sqlErrorDetailList;

    public SqlDetailReportDto getSqlDetailReportDto() {
        return sqlDetailReportDto;
    }

    public void setSqlDetailReportDto(SqlDetailReportDto sqlDetailReportDto) {
        this.sqlDetailReportDto = sqlDetailReportDto;
    }

    public List<SqlErrorDetail> getSqlErrorDetailList() {
        return sqlErrorDetailList;
    }

    public void setSqlErrorDetailList(List<SqlErrorDetail> sqlErrorDetailList) {
        this.sqlErrorDetailList = sqlErrorDetailList;
    }
}
