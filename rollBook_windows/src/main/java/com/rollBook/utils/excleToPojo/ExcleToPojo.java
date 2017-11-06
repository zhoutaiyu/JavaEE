package com.rollBook.utils.excleToPojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rollBook.po.Student;
import com.rollBook.po.Teacher;

public class ExcleToPojo {
	/**
	 * 解析excel文件到VO [完美支持97-03-07-10]
	 * 
	 * @description: 每个sheet列数<=50个，多出部分自动舍弃
	 * @time: 上午10:15:11 2013-8-1
	 * @param url
	 *            文件的全路径
	 * @return sheet集合,文件不存在或没有sheet返回null
	 */
	public static List<List<RowVO>> excleTOVO(String url) {
		String errorMsg = "";
		try {
			url = URLDecoder.decode(url, "utf-8"); // 防止服务器路径中包含空格等问题
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String suffix = url.substring(url.lastIndexOf(".")); // 文件后辍.
		List<List<RowVO>> excelSheets = null;
		File file = new File(url);
		if (file.exists()) {
			try {
				Workbook workBook = null;
				InputStream is = new FileInputStream(new File(url));
				try {
					if (".xls".equals(suffix)) { // 97-03
						workBook = new HSSFWorkbook(is);
					} else if (".xlsx".equals(suffix)) { // 2007
						workBook = new XSSFWorkbook(is);
					} else {
						System.out.println("不支持的文件类型！");
						return null;
					}
				} catch (Exception e) {
					System.out.println("解析xls文件出错！");
					e.printStackTrace();
				} finally {
					try {
						is.close();
					} catch (Exception e2) {

					}
				}
				int sheets = null != workBook ? workBook.getNumberOfSheets() : 0;
				if (sheets > 0) {
					excelSheets = new ArrayList<List<RowVO>>();
					for (int i = 0; i < sheets; i++) {
						Sheet sheet = workBook.getSheetAt(i); // 读取第一个sheet
						int rows = sheet.getPhysicalNumberOfRows(); // 获得行数
						List<RowVO> sheetList = new ArrayList<RowVO>();
						if (rows > 1) { // 第一行默认为标题
							// sheet.getMargin(HSSFSheet.TopMargin);
							for (int j = 1; j < rows; j++) {
								Row row = sheet.getRow(j);
								RowVO commonVO = new RowVO();
								int cells = row.getLastCellNum();// 获得列数
								if (cells > 0) {
									for (int k = 0; k < cells; k++) {
										Cell cell = row.getCell(k);
										cell.setCellType(Cell.CELL_TYPE_STRING); // 全部置成String类型的单元格
										if (k <= 50) {
											PropertyUtils.setProperty(commonVO, "str" + k, cell.getStringCellValue());
										} else {
											System.out.println(
													"第" + (i + 1) + "个sheet,第" + (j + 1) + "行数据列数超过了最大储存的个数50，将自动舍弃!");
											break;
										}
									}
								} else {
									errorMsg = "第" + (j + 1) + "行数据没有列数为空!";
								}
								sheetList.add(commonVO);
							}
						} else {
							errorMsg = "第" + (i + 1) + "个sheet中数据行数<=1";
						}
						excelSheets.add(sheetList);
					}
				} else {
					errorMsg = "没有sheet!";
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			errorMsg = "文件不存在！";
		}
		if (errorMsg.length() > 0) {
			System.out.println("错误消息：" + errorMsg);
		}
		return excelSheets;
	}

	public List<Student> excleToExcle(String url) {
		String fileName = url;
		List<List<RowVO>> excelSheets = ExcleToPojo.excleTOVO(fileName);
		List<Student> students = new ArrayList<Student>();
		if (null != excelSheets) {
			for (int i = 0; i < excelSheets.size(); i++) {
				List<RowVO> sheet = excelSheets.get(i);
				for (int j = 0; j < sheet.size(); j++) {
					RowVO commonVO = sheet.get(j);
					Student stu = new Student();
					stu.setSno(commonVO.getStr1());
					stu.setName(commonVO.getStr2());
					stu.setSex(commonVO.getStr3());
					stu.setClassName(commonVO.getStr4());
					stu.setPassword("1234");
					stu.setCreateTime(new Date());
					stu.setModTime(new Date());
					stu.setIdentity(1);
					stu.setIsDel(false);
					students.add(stu);
				}
			}
		}
		return students;
	}
	/**
	 * 老师信息的excle转为teacher的list
	 * @param url
	 * @return
	 */
	public List<Teacher> tecExcleToExcle(String url) {
		String fileName = url;
		List<List<RowVO>> excelSheets = ExcleToPojo.excleTOVO(fileName);
		List<Teacher> teachers = new ArrayList<Teacher>();
		if (null != excelSheets) {
			for (int i = 0; i < excelSheets.size(); i++) {
				List<RowVO> sheet = excelSheets.get(i);
				for (int j = 0; j < sheet.size(); j++) {
					RowVO commonVO = sheet.get(j);
					Teacher tea=new Teacher();
					tea.setWid(Long.parseLong(commonVO.getStr0()));
					tea.setName(commonVO.getStr1());
					tea.setIdentity(0);
					tea.setPassword("1234");
					tea.setCreateTime(new Date());
					tea.setModTime(new Date());
					tea.setIsDel(false);
					teachers.add(tea);
				}
			}
		}
		return teachers;
	}

}