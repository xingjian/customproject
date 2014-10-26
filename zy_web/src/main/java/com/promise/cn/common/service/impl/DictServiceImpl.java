/*@文件名: DictServiceImpl.java  @创建人: 邢健   @创建日期: 2011-12-9 下午04:17:02*/
package com.promise.cn.common.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.promise.cn.common.domain.Dict;
import com.promise.cn.common.domain.DictContent;
import com.promise.cn.common.service.DictService;
import com.promise.cn.framework.service.PersistenceManager;
import com.promise.cn.framework.service.QueryManager;

/**   
 * @类名: DictServiceImpl.java 
 * @包名: com.promise.cn.common.service.impl 
 * @描述: 字典接口实现类
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-9 下午04:17:02 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
@Service("dictService")
@RemotingDestination(channels={"my-amf","my-secure-amf"})
public class DictServiceImpl implements DictService {

	//日志对象
	private Logger log = LoggerFactory.getLogger(DictServiceImpl.class);
	//pm对象
	private PersistenceManager persistenceManager;
	//查询对象
	private QueryManager queryManager;
	
	/**
	 * 保存字典对象
	 */
	@Override
	@RemotingInclude
	@Transactional(readOnly=false,propagation = Propagation.REQUIRES_NEW)
	public String saveDict(Dict dict) {
		List ls = queryManager.findByNamedQuery("getDictByCodeOrName",new Object[]{dict.getCode(),dict.getName()});
        if(ls.size()>0){
                return "存在相同的字典编码或字典名称!";
        }
        persistenceManager.save(dict);
        return "success";
	}

	/**
	 * 更新字典
	 */
	@Override
	@RemotingInclude
	@Transactional(readOnly=false,propagation = Propagation.REQUIRES_NEW)
	public String updateDict(Dict dict) {
        List ls = queryManager.findByNamedQuery("getDictByCodeOrName",new Object[]{dict.getCode(),dict.getName()});
        //当返回的size大于1时，则肯定存在相同的编码或名称
        if(ls.size()>1){
                return "存在相同的字典编码或字典名称!";
        }
        //当size等于1时
        if(ls.size() == 1){
                //如果ls.get(0)里面的dictId相同，则可以通过，否则，存在相同的字典编码或字典名称
                Dict d = (Dict)ls.get(0);
                if(d.getId().equals(dict.getId())){
                        BeanUtils.copyProperties(d,dict);
                        persistenceManager.update(d);
                        return "success";
                }else{
                        return "存在相同的字典编码或字典名称!";
                }
        }
        persistenceManager.update(dict);
        return "success";
	}

	/**
	 * 获取字典
	 */
	@Override
	@RemotingInclude
	public Dict getDict(String dictId) {
		return (Dict)queryManager.get(Dict.class, dictId);
	}

	/**
	 * 删除字典
	 */
	@Override
	@RemotingInclude
	public void removeDict(String dictId) {
		persistenceManager.remove(Dict.class,dictId);
	}

	/**
	 * 获取所有的字典集合
	 */
	@Override
	@RemotingInclude
	public List<Dict> listDict() {
		return queryManager.findByNamedQuery("listDict");
	}

	/**
	 * 获取字典内容
	 */
	@Override
	@RemotingInclude
	public List<DictContent> listDictContent(String dictCode) {
		return queryManager.findByNamedQuery("listDictContent",dictCode);
	}

	/**
	 * 获取所有字典内容
	 */
	@Override
	@RemotingInclude
	public List<DictContent> listAllDictContent() {
		return queryManager.findByNamedQuery("listDictContent");
	}

	/**
	 * 更新字典内容
	 */
	@Override
	@RemotingInclude
	public String updateDictContent(DictContent dictContent) {
		persistenceManager.update(dictContent);
		return "success";
	}

	/**
	 * 通过id获取字典项
	 */
	@Override
	@RemotingInclude
	public DictContent getDictContent(String dictContentId) {
		return (DictContent)queryManager.get(DictContent.class, dictContentId);
	}

	/**
	 * 批量保存字典项
	 */
	@Override
	@RemotingInclude
	public String saveDictContentList(List<DictContent> dictContents) {
		for(DictContent dictContent:dictContents){
    		persistenceManager.update(dictContent);
    	}
		return "success";
	}

	/**
	 * 保存字典项
	 */
	@Override
	@RemotingInclude
	public String saveDictContent(DictContent dictContent) {
		persistenceManager.save(dictContent);
		return "success";
	}

	public void setPersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	public void setQueryManager(QueryManager queryManager) {
		this.queryManager = queryManager;
	}
}
