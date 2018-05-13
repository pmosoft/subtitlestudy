package net.pmosoft.subtitlestudy.parse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.pmosoft.fframe.FframeApplication;
import net.pmosoft.fframe.comm.App;
import net.pmosoft.fframe.comm.util.ExcelUtil;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FframeApplication.class)
public class ParseTest {

	@Autowired
	private ParseSrv codeSrv;

	@Autowired
	private ParseDao codeDao;

    @Test
    public void testExcel() throws Exception {
        
        ExcelUtil eu = new ExcelUtil();
        eu.xlsToList(App.excelPath + "code.xls");
        
    }

	
	
	@Test @Ignore
	public void testParseList() {
		Map<String, String> params = new HashMap<String, String>();
		//params.put("searchValue", "us");
		//params.put("searchValue", "유저");
		params.put("searchValue", "");
		codeSrv.selectParseList(params);
		//TermDao.selectParseList(params);
	}

	@Test @Ignore
	public void testSaveParse() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("PKG_FUL_NM", "package1");
		params.put("PKG2_NM"   , "pk");
		params.put("PKG3_NM"   , "pkg");
		params.put("PKG4_NM"   , "pack");
		params.put("PKG_HNM"   , "패키지");
		params.put("PKG_DESC"  , "패키지4");
		params.put("USE_YN"     , "Y");
		params.put("REG_USR_ID", "admin");
		params.put("UPD_USR_ID", "admin");

		//TermDao.deleteUser(params);

		Map<String, Object> result = codeSrv.saveParse(params);

		System.out.println(result);
		testParseList();
	}

	@Test @Ignore
	public void testDeleteParse() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("PKG_FUL_NM", "package1");
		codeSrv.deleteParse(params);
	}

	@Test @Ignore
	public void testInsertParse() {

		Map<String, String> params = new HashMap<String, String>();
		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
		params.put("PKG_FUL_NM", "package");
		params.put("PKG2_NM"   , "pk");
		params.put("PKG3_NM"   , "pkg");
		params.put("PKG4_NM"   , "pack");
		params.put("PKG_HNM"   , "패키지");
		params.put("PKG_DESC"  , "패키지");
		params.put("USE_YN"     , "Y");
		params.put("REG_USR_ID", "admin");
		params.put("UPD_USR_ID", "admin");

		codeDao.deleteParse(params);

		codeDao.insertParse(params);

		testParseList();
	}

	@Test @Ignore
	public void testUpdateParse() {

		Map<String, String> params = new HashMap<String, String>();
		//params.put("searchKeyCombo", ""); params.put("searchValue", "");
		params.put("PKG_FUL_NM", "package");
		params.put("PKG2_NM"   , "pk");
		params.put("PKG3_NM"   , "pkg");
		params.put("PKG4_NM"   , "pack");
		params.put("PKG_HNM"   , "패키지");
		params.put("PKG_DESC"  , "패키지2");
		params.put("USE_YN"     , "Y");
		params.put("REG_USR_ID", "admin");
		params.put("UPD_USR_ID", "admin");

		codeDao.updateParse(params);

		testParseList();
	}


}

