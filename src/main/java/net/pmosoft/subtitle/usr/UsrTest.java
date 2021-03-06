package net.pmosoft.subtitle.usr;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.pmosoft.subtitle.SubtitlestudyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SubtitlestudyApplication.class)
public class UsrTest {

//	@Autowired
//	private WebApplicationContext webApplicationContext;
//	private MockMvc mockMvc;
//
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();		
//	}
//	@Test
//	public void testUsrListCtrl() throws Exception { 
//		 this.mockMvc.perform(get("/"))
//		 .andDo(print())
//		 .andExpect(status().isOk())
//		 .andExpect(model().attributeExists("serverTime"));  
//		
//	}
	
	@Autowired
	private UsrSrv UsrSrv;
	
	@Autowired
	private UsrDao UsrDao;

	@Test
	public void testUsrCtl() { 
		Usr usr = new Usr();
		usr.setUsrEmail("lifedomy@gmail.com");
		UsrSrv.selectUsr(usr);
	}
		
	@Test @Ignore
	public void testUsrSrv() { 
		Usr usr = new Usr();
		usr.setUsrEmail("lifedomy@gmail.com");
		UsrSrv.selectUsr(usr);
	}
		
	@Test @Ignore
	public void testUsrDao() { 
		Usr usr = new Usr();
		usr.setUsrEmail("lifedomy@gmail.com");
		UsrDao.selectUsr(usr);
	}
	
//	@Test @Ignore
//	public void testUsrList() { 
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");		
//		params.put("searchKeyCombo", "Usr_ID"); params.put("searchValue", ""); 
//		UsrSrv.selectUsrList(params);
//	}
//
//	@Test
//	public void testSaveUsr() {
//		
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");		
//		params.put("Usr_ID"    , "test1"); 
//		params.put("Usr_EMAIL" , "test1@pmosoft.net"); 
//		params.put("Usr_PW"    , "test1"); 
//		params.put("Usr_NM"    , "test1"); 
//		params.put("Usr_AGE"   , "40");  
//		params.put("USE_YN"     , "Y"); 
//		params.put("REG_Usr_ID", "admin"); 
//		params.put("UPD_Usr_ID", "admin");
//		
//		//UsrSrv.deleteUsr(params);
//
//		Map<String, Object> result = UsrSrv.saveUsr(params);
//
//		System.out.println(result);
//		//testUsrList();
//	}
//	
//	
//	@Test @Ignore
//	public void testInsertUsr() {
//		
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");		
//		params.put("Usr_ID"    , "test1"); 
//		params.put("Usr_EMAIL" , "test1@pmosoft.net"); 
//		params.put("Usr_PW"    , "test1"); 
//		params.put("Usr_NM"    , "test1"); 
//		params.put("Usr_AGE"   , "40"); 
//		params.put("USE_YN"     , "Y"); 
//		params.put("REG_Usr_ID", "admin"); 
//		params.put("UPD_Usr_ID", "admin");
//		
//		UsrDao.deleteUsr(params);
//
//		UsrDao.insertUsr(params);
//
//		testUsrList();
//	}
//
//	@Test @Ignore 
//	public void testUpdateUsr() {
//		
//		Map<String, String> params = new HashMap<String, String>();
//		//params.put("searchKeyCombo", ""); params.put("searchValue", "");		
//		params.put("Usr_ID"    , "test1"); 
//		params.put("Usr_EMAIL" , "test1@pmosoft.net"); 
//		params.put("Usr_PW"    , "test11"); 
//		params.put("Usr_NM"    , "test11"); 
//		params.put("Usr_AGE"    , "40"); 
//		params.put("USE_YN"     , "Y"); 
//		params.put("UPD_Usr_ID", "admin");
//		
//		UsrDao.updateUsr(params);
//
//		testUsrList();
//	}
}

