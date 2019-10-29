package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter//get方法
@NoArgsConstructor//无参构造方法
@AllArgsConstructor//全参构造方法
public enum ExceptionEnum {

    BRAND_NOT_FOND(404,"品牌没查到"),
    CATEGORY_NOT_FOND(404,"商品分类没查到"),
    SPEC_GROUP_NOT_FOND(404,"商品规格组没查到"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    CATEGORY_BRAND_SAVE_ERROR(500,"新增品牌分类失败"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    INVALID_FILE_TYPE(400,"无效的文件类型")
    ;
    private int code;
    private String msg;

}
