package com.lxn.front.service;

import com.lxn.front.annotation.MethodName;
import com.lxn.front.annotation.ParamName;
import com.lxn.front.exception.ApiException;
import com.lxn.front.constant.ErrorCode;
import com.lxn.front.model.PagedListResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Lin Xiangnan on 2017/2/20.
 *
 * @author Lin Xiangnan
 * @func
 **/
@Service(value = "normal_action")
public class NormalAction {

    @Autowired
    private Logger logger;

    @MethodName(value = "print_all_members_by_name")
    public PagedListResult printAllMembersByName(@ParamName(value = "name") String name, @ParamName(value = "password") long password, @ParamName(value = "members") List members) throws Exception {

        if (StringUtils.isEmpty(name)) {
            throw new ApiException(ErrorCode.USER_OR_PASSWORD_EMPTY, "用户名或密码不能为空");
        }
        if (!name.equals("林向南")) {
            throw new ApiException(ErrorCode.USER_OR_PASSWORD_ERROR, "用户名、密码错误");
        }

        PagedListResult pagedListResult = new PagedListResult();
        pagedListResult.setList(members);
        pagedListResult.setTotalNum((long)members.size());
        pagedListResult.setCurrentPage(1);

        return pagedListResult;
    }

}
