package net.pmosoft.subtitlestudy.subtitle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.pmosoft.subtitlestudy.SubtitlestudyApplication;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SubtitlestudyApplication.class)
public class SubtitleTest {

    @Autowired
    private SubtitleCtrl subtitleCtrl;
    
	@Autowired
	private SubtitleSrv subtitleSrv;

	@Autowired
	private SubtitleDao subtitleDao;

    @Test @Ignore
    public void testSaveSubtitle() {

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

        Map<String, Object> result = subtitleSrv.saveSubtitle(params);
    }
    
    
    @Test
    public void testSaveUsrSubtitles() throws IOException {
        FileInputStream inputFile = new FileInputStream( "path of the file");  
        MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
        //subtitleSrv.saveUsrSubtitles(file);
    }
    
    
//    @Test @Ignore
//    public void testExcel() throws Exception {
//        
//        ExcelUtil eu = new ExcelUtil();
//        eu.xlsToList(App.excelPath + "subtitle.xls");
//        
//    }


	@Test @Ignore
	public void testDeleteSubtitle() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("PKG_FUL_NM", "package1");
		subtitleSrv.deleteSubtitle(params);
	}

	@Test @Ignore
	public void testInsertSubtitle() {

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

		subtitleDao.deleteSubtitle(params);

		subtitleDao.insertSubtitle(params);

	}

	@Test @Ignore
	public void testUpdateSubtitle() {

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

		subtitleDao.updateSubtitle(params);

	}


}

