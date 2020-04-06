package cn.tedu.store.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;
@Service
public class DistrictServiceImpl implements IDistrictService {
	
	@Autowired
	private DistrictMapper districtMapper;
	
	/**
	 * 全国的所有省/所有市/所有区的列表
	 * @param parent
	 * @return
	 */
	private List<District> findByParent(String parent){
		return districtMapper.findByParent(parent);
	}
	
	private District findByCode(String code){
		return districtMapper.findByCode(code);
	}
	
	@Override
	public List<District> getByParent(String parent) {
		List<District> district = findByParent(parent);
		return district;
	}

	@Override
	public District getByCode(String code) {
		return findByCode(code);
	}

}
