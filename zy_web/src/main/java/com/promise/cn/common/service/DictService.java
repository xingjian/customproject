/*@文件名: DictService.java  @创建人: 邢健   @创建日期: 2011-12-9 下午04:05:46*/
package com.promise.cn.common.service;

import java.util.List;
import com.promise.cn.common.domain.Dict;
import com.promise.cn.common.domain.DictContent;

/**
 * @类名: DictService.java
 * @包名: com.promise.cn.common.service
 * @描述: 字典服务
 * @作者: 邢健 xingjian@dhcc.com.cn
 * @日期: 2011-12-9 下午04:05:46
 * @版本 V1.0
 */
public interface DictService {

	/**
	 * 保存字典
	 * @param dict
	 * @return
	 */
	public String saveDict(Dict dict);
	/**
	 * 编辑字典
	 * @param dict
	 * @return
	 */
	public String updateDict(Dict dict);
	/**
	 * 获取字典
	 * @param dictId
	 * @return
	 */
	public Dict getDict(String dictId);
	/**
	 * 删除字典
	 * @param dictId
	 */
	public void removeDict(String dictId);
	/**
	 * 获取字典集合
	 * @return
	 */
	public List<Dict> listDict();
	/**
	 * 获取字典内容集合通过
	 * @param dictCode
	 * @return
	 */
	public List<DictContent> listDictContent(String dictCode);
	/**
	 * 获取所有的DictContent对象
	 * @return
	 */
	public List<DictContent> listAllDictContent();
	/**
	 * 更新DictContent
	 * @param dictContent
	 * @return
	 */
	public String updateDictContent(DictContent dictContent);
	/**
	 * 通过id获取DictContent
	 * @param dictContentId
	 * @return
	 */
	public DictContent getDictContent(String dictContentId);
	/**
	 * 保存DictContent集合
	 * @param dictContents
	 * @return
	 */
	public String saveDictContentList(List<DictContent> dictContents);
	/**
	 * 保存DictContent
	 * @param dictContent
	 * @return
	 */
	public String saveDictContent(DictContent dictContent);
}
