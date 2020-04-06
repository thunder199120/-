package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;
/**
 * 处理省 市 区 数据持久层接口
 * @author JAVA
 *
 */
public interface DistrictMapper {
	/**
	 * 全国的所有省/所有市/所有区的列表
	 * @param parent
	 * @return
	 */
	List<District> findByParent(String parent);
	/**
	 * 根据省/市/区代号获取数据详情
	 * @param code
	 * @return
	 */
	District findByCode(String code);
}
