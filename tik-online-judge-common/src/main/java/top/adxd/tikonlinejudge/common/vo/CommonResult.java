package top.adxd.tikonlinejudge.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import top.adxd.tikonlinejudge.common.constant.PageConstant;
import top.adxd.tikonlinejudge.common.util.PageUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author wait_light
 * @create 2021/9/6
 */
public class CommonResult extends HashMap implements Serializable {
    //请求成功
    public static final int SUCCESS_CODE = 200;
    //请求失败
    public static final int ERROR_CODE = 500;
    //权限不足
    public static final int PERMISSION_DENY_CODE = 300;
    //不存在
    public static final int DOES_NOT_EXIST_CODE = 404;
    //请求成功
    public static final String SUCCESS_MSG = "请求成功";
    //请求失败
    public static final String ERROR_MSG = "请求失败";
    //权限不足
    public static final String PERMISSION_MSG = "权限不足";
    //不存在
    public static final String DOES_NOT_EXIST_MSG = "请求不存在";


    public Boolean isSuccess() {
        return this.get("success").equals(true);
    }

    private CommonResult(String msg, int code) {
        this.put("msg", msg);
        this.put("code", code);
        this.put("success", code == SUCCESS_CODE);
    }

    public static CommonResult success(String msg) {
        return new CommonResult(msg, SUCCESS_CODE);
    }

    public static CommonResult error(String msg) {
        return new CommonResult(msg, ERROR_CODE);
    }

    public static CommonResult permissionDeny(String msg) {
        return new CommonResult(msg, PERMISSION_DENY_CODE);
    }

    public static CommonResult doesNotExist(String msg) {
        return new CommonResult(msg, DOES_NOT_EXIST_CODE);
    }

    public static CommonResult success() {
        return new CommonResult(SUCCESS_MSG, SUCCESS_CODE);
    }

    public static CommonResult error() {
        return new CommonResult(ERROR_MSG, ERROR_CODE);
    }

    public static CommonResult permissionDeny() {
        return new CommonResult(PERMISSION_MSG, PERMISSION_DENY_CODE);
    }

    public static CommonResult doesNotExist() {
        return new CommonResult(DOES_NOT_EXIST_MSG, DOES_NOT_EXIST_CODE);
    }

    public CommonResult setMsg(String msg) {
        this.put("msg", msg);
        return this;
    }

    //利用fastjson的动态类型快速获取指定的类型
    public <T> T getData(String key, TypeReference<T> typeReference) {
        return JSON.parseObject(JSON.toJSONString(this.get(key)), typeReference);
    }

    public CommonResult add(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public <T> CommonResult listData(List<T> list) {
        Page<Object> page = PageUtils.pageInfo();
        PageInfo pageInfo = null;
        if (page != null) {
            pageInfo = new PageInfo(page);
        } else {
            pageInfo = new PageInfo(list);
        }
        add(PageConstant.HAS_PREVIOUS_PAGE, pageInfo.isHasPreviousPage());
        add(PageConstant.HAS_NEXT_PAGE, pageInfo.isHasNextPage());
        add(PageConstant.PAGE_SIZE, pageInfo.getPageSize() != 0 ? 10 : pageInfo.getPageSize());
        add(PageConstant.CURRENT_PAGE, pageInfo.getPageNum());
        add(PageConstant.TOTAL_PAGE, pageInfo.getPages());
        add(PageConstant.TOTAL, pageInfo.getTotal());
        return add(PageConstant.LIST_KEY, list);
    }

    public <T> CommonResult listData(Page page, List<T> list) {
        PageInfo pageInfo = null;
        if (page == null) {
            pageInfo = new PageInfo(list);
        } else {
            pageInfo = new PageInfo(page);
        }
        add(PageConstant.HAS_PREVIOUS_PAGE, pageInfo.isHasPreviousPage());
        add(PageConstant.HAS_NEXT_PAGE, pageInfo.isHasNextPage());
        add(PageConstant.PAGE_SIZE, pageInfo.getPageSize());
        add(PageConstant.CURRENT_PAGE, pageInfo.getPageNum());
        add(PageConstant.TOTAL_PAGE, pageInfo.getPages());
        add(PageConstant.TOTAL, pageInfo.getTotal());
        return add(PageConstant.LIST_KEY, list);
    }

    public <T> CommonResult singleData(T entity) {
        return add("dto", entity);
    }

}
