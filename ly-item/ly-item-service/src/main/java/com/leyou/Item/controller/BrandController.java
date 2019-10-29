package com.leyou.Item.controller;

import com.leyou.Item.pojo.Brand;
import com.leyou.Item.service.BrandService;
import com.leyou.common.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌种类
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
        @RequestParam(value = "page",defaultValue = "1") Integer page,      //页码
        @RequestParam(value = "rows",defaultValue = "5") Integer rows,      //当前页显示条数
        @RequestParam(value = "sortBy",required = false, defaultValue = "id") String sortBy,     //排序方式
        @RequestParam(value = "desc",defaultValue = "false") Boolean desc,  //是否降序，默认升序
        @RequestParam(value = "key",required = false) String key            //查询过滤条件
    ){
        return ResponseEntity.ok(brandService.queryBrandByPage(page,rows,sortBy,desc,key));
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids 品牌分类
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(
            Brand brand,
            @RequestParam("cids")List<Long> cids  //SpringMVC功能，品牌分类，一个品牌可以用很多分类，选择用list接受
    ){
        brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();//有返回值选择.body，没有选择build();
    }

}
