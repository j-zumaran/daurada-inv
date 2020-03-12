package com.daurada.inv.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daurada.base.BasicService;
import com.daurada.inv.barcode.BarcodeGen;
import com.daurada.inv.color.Color;
import com.daurada.inv.size.Size;

@Service
public class ProductItem_Service extends BasicService<ProductItem> {

	@Override
	protected void update(ProductItem old, ProductItem updated) {}
	
	@Transactional
	public List<ProductItem> insertAll(Product entity) {
		List<Size> sizes = entity.getSizes();
		List<Color> colors = entity.getColors();
		
		List<ProductItem> items = new ArrayList<ProductItem>(sizes.size() * colors.size());
		
		for (Size s: sizes) {
			for (Color c: colors) {
				ProductItem item = new ProductItem();
				item.setName(entity.getName() + " " + s.getName() + " " + c.getName());
				item.setBarcode(BarcodeGen.generate(entity.getCode(), s.getId(), c.getId()));
				item.setSize(s);
				item.setColor(c);
				item.setProduct(entity);
				
				items.add(item);
			}
		}
		return super.insertAll(items);
	}

}
