package com.leyou.Item.service;

import com.leyou.Item.mapper.CategoryMapper;
import com.leyou.Item.pojo.Category;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.excption.LyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点id查询商品分类
     * @param pid parent_id(查询商品的种类，延迟加载)
     * @return list 返回一个List<Category>，然后转发为json返回页面
     */
    public List<Category> queryCategoryListByPid(Long pid) {
        Category t = new Category();
        t.setParentId(pid);
        List<Category> list = categoryMapper.select(t);//传入对象，把对象中的非空条件当作参数，然后查询出对象其他参数
        //判断查询结果
        if(CollectionUtils.isEmpty(list)){
            //失败返回404
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return list;
    }
}
