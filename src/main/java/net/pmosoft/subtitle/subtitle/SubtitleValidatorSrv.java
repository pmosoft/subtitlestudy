package net.pmosoft.subtitle.subtitle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
                                                                                     
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
                     
//@SpringBootConfiguration
//(classes=net.pmosoft.fframe.FframeApplication.class)
@WebAppConfiguration // 웹 컨텍스트 테스트 활성화

@Service
public class SubtitleValidatorSrv {

	//String configLocation = "classpath:springJdbcOracle.xml"; // src/main/resources/springJdbcOracle.xml
	//AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
	//WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(((HttpServletRequest) request).getSession().getServletContext());
	//SubtitleDao SubtitleDao = ctx.getBean("SubtitleDao",SubtitleDao.class);
    //@Autowired
    //private WebApplicationContext webContext; // WebApplicationContext 주입

    //SubtitleDao SubtitleDao = webContext.getBean("SubtitleDao",SubtitleDao.class);

    
    public Map<String, String> validateSaveSubtitle(List<Map<String,String>> target) {
    

		//System.out.println("validateSaveSubtitle");

		Map<String, String> errors = new HashMap<String, String>();
		//System.out.println("validateSaveSubtitle11");

        if (target.size() == 0) {
            errors.put("errUsrMsg", "저장 정보가 없습니다.");
        }    		
		
//		if (target.get("Subtitle_ID").length() < 5 || target.get("Subtitle_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (target.get("Subtitle_EMAIL").length() < 5 || target.get("Subtitle_EMAIL").length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (target.get("Subtitle_PW").length() < 5 || target.get("Subtitle_PW").length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!target.get("Subtitle_PW").equals(target.get("Subtitle_PW2"))) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (target.get("Subtitle_NM").length() < 5 || target.get("Subtitle_NM").length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		//System.out.println("validateSaveSubtitle55");

		return errors;
	}


	public Map<String, String> validateDeleteSubtitle(Map<String, String> target) {

		Map<String, String> errors = new HashMap<String, String>();
//		if (target.get("Subtitle_ID").length() < 5 || target.get("Subtitle_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (SubtitleDao.selectSubtitleCnt(target)==0) {
//			errors.put("errUsrMsg", "아이디가 미존재합니다.");
//		}

		return errors;
	}

}
