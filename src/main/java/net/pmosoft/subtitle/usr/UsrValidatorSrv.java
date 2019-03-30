package net.pmosoft.subtitle.usr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

//@SpringBootConfiguration
//(classes=net.pmosoft.fframe.FframeApplication.class)
@WebAppConfiguration // 웹 컨텍스트 테스트 활성화

@Service
public class UsrValidatorSrv {

	@Autowired
	private UsrDao usrDao;

	//String configLocation = "classpath:springJdbcOracle.xml"; // src/main/resources/springJdbcOracle.xml
	//AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
	//WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(((HttpServletRequest) request).getSession().getServletContext());
	//UsrDao UsrDao = ctx.getBean("UsrDao",UsrDao.class);
    //@Autowired
    //private WebApplicationContext webContext; // WebApplicationContext 주입

    //UsrDao UsrDao = webContext.getBean("UsrDao",UsrDao.class);

	public Map<String, String> validateInsertUsr(Usr usr) {

		Map<String, String> errors = new HashMap<String, String>();
		Usr dbUsr = usrDao.selectUsr(usr);
		if (dbUsr == null) {
			return errors;
		} else if (dbUsr.getUsrEmail().equals(usr.getUsrEmail())) {
			errors.put("errUsrMsg", "이미 등록된 유저입니다.");
		}

//		if (usr.getUsrId().length() < 5 || usr.getUsrId().length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (usr.getUsrEmail().length() < 5 || usr.getUsrEmail().length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (usr.getUsrPw().length() < 5 || usr.getUsrPw().length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!usr.getUsrPw().equals(usr.getUsrPw2())) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (usr.getUsrNm().length() < 5 || usr.getUsrNm().length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		return errors;
	}

	public Map<String, String> validateUsrLogin(Usr usr) {

		Map<String, String> errors = new HashMap<String, String>();
		Usr dbUsr = usrDao.selectUsr(usr);
		if (dbUsr == null) {
			errors.put("errUsrMsg", "유저이메일 확인하시거나 유저를 신규 등록 하시기 바랍니다.");
		} else if (!dbUsr.getUsrPw().equals(usr.getUsrPw())) {
			errors.put("errUsrMsg", "암호를 확인하시기 바랍니다.");
		}

		return errors;
	}


	public Map<String, String> validateSaveUsr(Usr usr) {

		Map<String, String> errors = new HashMap<String, String>();
		Usr dbUsr = usrDao.selectUsr(usr);
		if (dbUsr == null) {
			return errors;
		} else if (dbUsr.getUsrEmail().equals(usr.getUsrEmail())) {
			errors.put("errUsrMsg", "이미 등록된 유저입니다.");
		}

//		if (usr.getUsrId().length() < 5 || usr.getUsrId().length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (usr.getUsrEmail().length() < 5 || usr.getUsrEmail().length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (usr.getUsrPw().length() < 5 || usr.getUsrPw().length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!usr.getUsrPw().equals(usr.getUsrPw2())) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (usr.getUsrNm().length() < 5 || usr.getUsrNm().length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		return errors;
	}

	public Map<String, String> validateSaveUsrLang(Usr usr) {
		Map<String, String> errors = new HashMap<String, String>();
		return errors;
	}
	public Map<String, String> validateDeleteUsr(Usr usr) {

		Map<String, String> errors = new HashMap<String, String>();
		if (usr.getUsrId().length() < 5 || usr.getUsrId().length() > 15) {
			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
		//} else if  (UsrDao.selectUsrCnt(target)==0) {
		//	errors.put("errUsrMsg", "아이디가 미존재합니다.");
		}

		return errors;
	}

}
