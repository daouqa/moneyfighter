package com.daou.daouqa.moneyfighter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value=Parameterized.class)
public class MoneyBookTest {
	private String date;
	private String type;
	private String category;
	private String price;
	private String note;
	
	@Parameters
	public static Collection<String[]> getTestParameters() {
		return Arrays.asList(new String[][] {
			{"2015-07-23", "지출", "커피", "1000", "한턱쏨"},
			{"","","","",""},
		});
	}
	
	public MoneyBookTest(String date, String type, String category,
			String price, String note) {
		this.date = date;
		this.type = type;
		this.category = category;
		this.price = price;
		this.note = note;
	}
	
	@Test
	public void save() {
		Integer result;
		MoneyBook data = new MoneyBook();
		result = data.saveMoneybook(date, type, category, price, note);
		assertNotNull(result);
	}
	
	/*
	@Test
	public void list() {
		MoneyBook list = new MoneyBook();
		list.listMoneybook();
	}
	*/
}
