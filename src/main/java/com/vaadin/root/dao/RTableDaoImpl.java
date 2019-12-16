package com.vaadin.root.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.root.model.RColor;
import com.vaadin.root.model.RGender;
import com.vaadin.root.model.RSize;

public class RTableDaoImpl extends DefaultDao implements RTableDao{

	@Override
	public List<RSize> findAllSize() {
		return getEntityManager().createNamedQuery("RSize.findAll", RSize.class).getResultList();
	}
	

	@Override
	public List<String> findAllSizeStrings() {
		
//		List<String> sizesListStr = getEntityManager().createNamedQuery("RSize.findAll", RSize.class)
		return getEntityManager().createNamedQuery("RSize.findAll", RSize.class)
								.getResultList()
								.stream()
								.map(RSize::getValue).collect(Collectors.toList());
													
		
	}


	@Override
	public List<RColor> findAllColor() {
		return getEntityManager().createNamedQuery("RColor.findAll", RColor.class).getResultList();
	}

	@Override
	public List<RGender> findAllGender() {
		return getEntityManager().createNamedQuery("RGender.findAll", RGender.class).getResultList();
	}


	@Override
	public List<String> findAllColorStrings() {
		// TODO Auto-generated method stub
//		return getEntityManager().createNamedQuery("RColor.findAll", RColor.class).getResultList();
		return getEntityManager().createNamedQuery("RColor.findAll", RColor.class)
								.getResultList()
								.stream()
								.map(RColor::getValue).collect(Collectors.toList());
	}


	@Override
	public List<String> findAllGenderStrings() {
		// TODO Auto-generated method stub
		return getEntityManager().createNamedQuery("RGender.findAll", RGender.class)
								.getResultList()
								.stream()
								.map(RGender::getValue).collect(Collectors.toList());
	}

}
