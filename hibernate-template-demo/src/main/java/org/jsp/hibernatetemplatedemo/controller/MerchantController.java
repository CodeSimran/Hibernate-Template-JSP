package org.jsp.hibernatetemplatedemo.controller;

import java.util.Scanner;

import org.jsp.hibernatetemplatedemo.dao.MerchantDao;
import org.jsp.hibernatetemplatedemo.dto.Merchant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MerchantController {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MerchantDao merchantDao = context.getBean(MerchantDao.class);
		System.out.println("1.Save Merchant");
		System.out.println("2.Update merchant");
		System.out.println("3.Find By id");
		System.out.println("4.deleted By Id");
		System.out.println("5.Find All Merchants");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter the name,phone, gstNumber,email and password to register");
			Merchant merchant = new Merchant();
			merchant.setName(sc.next());
			merchant.setPhone(sc.nextLong());
			merchant.setGstNumber(sc.next());
			merchant.setEmail(sc.next());
			merchant.setPassword(sc.next());
			merchant = merchantDao.saveMerchant(merchant);
			System.out.println("merchant saved with Id:" + merchant.getId());
			break;
		}
		case 2: {
			System.out.println("Enter  the id, name,phone, gstNumber,email and password to register");
			Merchant merchant = new Merchant();
			merchant.setId(sc.nextInt());
			merchant.setName(sc.next());
			merchant.setPhone(sc.nextLong());
			merchant.setGstNumber(sc.next());
			merchant.setEmail(sc.next());
			merchant.setPassword(sc.next());
			merchant = merchantDao.updateMerchant(merchant);
			if (merchant != null)
				System.out.println("merchant with Id:" + merchant.getId() + " Updated");
			else
				System.err.println("cannot Update Merchant as Id is Invalid");
			break;
		}
		case 3: {
			System.out.println("Enter the Merchant Id to display details");
			Merchant merchant = merchantDao.findById(sc.nextInt());
			if (merchant != null)
				System.out.println(merchant);
			else
				System.err.println("Invalid Merchant Id");
			break;
		}
		case 4: {
			System.out.println("Enter the Merchant Id to delete the record");
			boolean deleted = merchantDao.delete(sc.nextInt());
			if (deleted)
				System.out.println("Merchant with entered Id deleted");
			else
				System.err.println("Cannot delete merchant as id is Invalid");
			break;
		}
		case 5: {
			for (Merchant m : merchantDao.findAll()) {
				System.out.println(m);
				System.out.println("------------------------------------");
			}
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
		sc.close();
		((ClassPathXmlApplicationContext) context).close();
	}
}
