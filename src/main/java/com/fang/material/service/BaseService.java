package com.fang.material.service;

import com.fang.material.condition.BaseCondition;
import com.fang.material.entity.mapper.BaseMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by fang on 2017/4/16.
 */
public class BaseService<T, M> {
    private final Log logger = LogFactory.getLog(BaseService.class);

    private BaseMapper<T> baseMapper;

    private Class<T> clazz;


    //    @PostConstruct//在构造方法执行之后执行
    private void initBaseMapper() throws Exception {
        //完成以下逻辑，需要对研发本身进行命名与使用规范  
        //this关键字指对象本身，这里指的是调用此方法的实现类（子类）  
        logger.info("==========this-->" + this);
        logger.info("======父类的基本信息-->" + this.getClass().getSuperclass());
        logger.info("父类和泛型的信息-->" + this.getClass().getGenericSuperclass());

        //得到父类的泛型信息  
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取第一个参数的class  
//      Type typetmp[]=type.getActualTypeArguments();//拿到尖括号里面的泛型，因为不止一个，所以是数组  
//      for(Type tmp:typetmp){  
//          logger.info(tmp);  
//      }  
        Class clazz = (Class) type.getActualTypeArguments()[0];//得到泛型 com.cris.scm.entity.具体的pojo  
        logger.info("====第一个参数的class" + clazz);

        //转化为属性名,第一个字母转成小写  
        //com.cris.scm.entity.Supplier.class->supplier+dao  
        String localField = clazz.getSimpleName().substring(0, 1).toLowerCase() +
                clazz.getSimpleName().substring(1) + "dao";//例如 basedao  
        logger.info("=======localString-->" + localField);

        //getDeclaredField 获得所有属性，包括私有和保护  
        //通过下面的方法获得到父类的声明属性  
        Field field = this.getClass().getSuperclass().getDeclaredField(localField);//例如，得到supplierdao属性  
        logger.info("=====field-->" + field);
        logger.info("=====field对应的对象-->" + field.get(this));//得到一个代理对象，代理这个SupplierMapper接口  
        //supplierserviceimpl引用的是一个代理对象？org.apache.ibatis.binding.MapperProxy@367970fa  
        //得到父类的basedao属性  
        Field baseField = this.getClass().getSuperclass().getDeclaredField("basedao");
        logger.info("baseField" + baseField);
        logger.info("=====baseField对应的对象-->" + baseField.get(this));

        //field.get(this)获取当前this的field字段的  
        //值。例如：Supplier对象中，baseMapper所指向  
        //的对象为其子类型SupplierMapper对象，子类型对象  
        //已被spring实例化于容器中     
        baseField.set(this, field.get(this));//把basedao的引用，设置到去引用supplierdao指向的堆内存  
        //后面basedao实际上指向的是SupplierMapper的代理对象在堆内存的实际存在  
        logger.info("baseField对应的对象-->" + baseField.get(this));
        //org.apache.ibatis.binding.MapperProxy@367970fa  
    }

    public int insert(T record) {
        return baseMapper.insert(record);
    }

    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    public T selectByPrimaryKey(String id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    public List<T> list() {
        return baseMapper.list();
    }

    public List<T> getList(BaseCondition conditon) {
        return baseMapper.getList(conditon);
    }

    public int getListCount(BaseCondition conditon) {
        return baseMapper.getListCount(conditon);
    }

    public int delete(List<String> ids) {
        return baseMapper.delete(ids);
    }

    public int deleteByPrimaryKey(String id) {
        return baseMapper.deleteByPrimaryKey(id);
    }
}
