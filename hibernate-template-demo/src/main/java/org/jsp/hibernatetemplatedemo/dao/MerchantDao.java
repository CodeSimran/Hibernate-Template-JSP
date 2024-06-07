package org.jsp.hibernatetemplatedemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.jsp.hibernatetemplatedemo.dto.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public Merchant saveMerchant(Merchant merchant) {
		hibernateTemplate.save(merchant);
		return merchant;
	}

	public Merchant findById(int id) {
		return hibernateTemplate.get(Merchant.class, id);
	}

	@Transactional
	public Merchant updateMerchant(Merchant merchant) {
		Merchant dbMerchant = findById(merchant.getId());
		if (dbMerchant != null) {
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGstNumber(merchant.getGstNumber());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setPhone(merchant.getPhone());
//			hibernateTemplate.update(dbMerchant);
			return dbMerchant;
		}
		return null;
	}

	@Transactional
	public boolean delete(int id) {
		Merchant dbMerchant = findById(id);
		if (dbMerchant != null) {
			hibernateTemplate.delete(dbMerchant);
			return true;
		}
		return false;
	}

	public List<Merchant> findAll() {
		return hibernateTemplate.loadAll(Merchant.class);
	}
}