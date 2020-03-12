package com.daurada.inv;

import org.springframework.boot.test.context.SpringBootTest;

import com.daurada.BaseTest;
import com.daurada.inv.size.Size;

@SpringBootTest
public class SizeController_Test extends BaseTest<Size> {

	public SizeController_Test() {
		super(new Size());
	}

}
