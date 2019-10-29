package com.leyou.Item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.Item.mapper.BrandMapper;
import com.leyou.Item.pojo.Brand;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.excption.LyException;
import com.leyou.common.vo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 品牌种类查询
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //1、分页
        PageHelper.startPage(page,rows);//(当前页码，每页大小)
        //2、过滤条件
        Example example = new Example(Brand.class);
        if(StringUtils.isNotBlank(key)){
            //过滤条件
            example.createCriteria()
                    .orLike("name","%"+key+"%")       //条件1
                    .orEqualTo("letter",key.toUpperCase()); //条件2
        }
        //3、排序
        if (StringUtils.isNotBlank(sortBy)) {
            //排序sql语句，其实就是"id desc",但是不能写死
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //4、查询
        List<Brand> list = brandMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOND);
        }
        //5、解析分页助手查询的结果
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        //6、返回总条数，当前页
        return new PageResult<>(pageInfo.getTotal(), list);

    }

    /**
     * 新增品牌种类，及其该品牌相关商品
     * @param brand brand对象
     * @param cids 品牌下的商品分类的id数组
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增品牌
        brand.setId(null);
        int count = brandMapper.insert(brand);//新增品牌，返回1：成功，0：失败
        if(count != 1){
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        //不仅要新增品牌，还要维护品牌和商品分类的中间表
        for (Long cid : cids){
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            if(count != 1 ){
                throw new LyException(ExceptionEnum.CATEGORY_BRAND_SAVE_ERROR);
            }
        }
    }

}
