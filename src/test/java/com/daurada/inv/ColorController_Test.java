package com.daurada.inv;

import org.springframework.boot.test.context.SpringBootTest;

import com.daurada.BaseTest;
import com.daurada.inv.color.Color;

@SpringBootTest
public class ColorController_Test extends BaseTest<Color> {

	public ColorController_Test() {
		super(new Color());
	}

}
