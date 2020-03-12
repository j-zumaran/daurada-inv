package com.daurada.inv.barcode;

public final class BarcodeGen {

	public static String generate(String code, Long sizeId, Long colorId) {
		StringBuilder barcode = new StringBuilder();
		char[] chars = code.toUpperCase().toCharArray();
		for (char c: chars) {
			String fixchar = String.valueOf(c); 
			switch (c) {
				case '-': fixchar = "00"; break;
                case 'A': fixchar = "01"; break;
                case 'B': fixchar = "02"; break;
                case 'C': fixchar = "03"; break;
                case 'D': fixchar = "04"; break;
                case 'E': fixchar = "05"; break;
                case 'F': fixchar = "06"; break;
                case 'G': fixchar = "07"; break;
                case 'H': fixchar = "08"; break;
                case 'I': fixchar = "09"; break;
                case 'J': fixchar = "10"; break;
                case 'K': fixchar = "11"; break;
                case 'L': fixchar = "12"; break;
                case 'M': fixchar = "13"; break;
                case 'N': fixchar = "14"; break;
                case 'O': fixchar = "15"; break;
                case 'P': fixchar = "16"; break;
                case 'Q': fixchar = "17"; break;
                case 'R': fixchar = "18"; break;
                case 'S': fixchar = "19"; break;
                case 'T': fixchar = "20"; break;
                case 'U': fixchar = "21"; break;
                case 'V': fixchar = "22"; break;
                case 'W': fixchar = "23"; break;
                case 'X': fixchar = "24"; break;
                case 'Y': fixchar = "25"; break;
                case 'Z': fixchar = "26"; break;
                case 'Ñ': fixchar = "27"; break;
			}
			barcode.append(fixchar);
		}
			
		String sizeCode = (sizeId > 99) ? String.valueOf(sizeId)
				:(sizeId < 10) ? "00" + sizeId: "0" + sizeId;
		
		barcode.append(sizeCode);
		
		String colorCode = (colorId > 99) ? String.valueOf(colorId)
				: (colorId < 10) ? "00" + colorId: "0" + colorId;

		barcode.append(colorCode);
		
		return barcode.toString();
	}
}
