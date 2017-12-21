package com.bossien.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by A on 2017/11/7.
 */
@ApiModel(description="管理员首页排行榜接口请求json对象")
public class CompanyOrderJson {
    @ApiModelProperty("账户类型")
    private String company_id;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
