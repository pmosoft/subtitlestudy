package net.pmosoft.subtitle.usr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsrSrv {
	
	@Autowired
	private UsrDao usrDao;

	@Autowired
	private UsrValidatorSrv usrValidatorSrv;

	public Map<String, Object> insertUsr(Usr usr){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> errors = new HashMap<String, String>();
		errors = usrValidatorSrv.validateInsertUsr(usr);
		System.out.println(errors);
		if(errors.size()>0){
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			return result;
		} else {	 
			try {
		    	result.put("isSuccess", true);
				
			    if  (usrDao.selectUsrCnt(usr)==0) {
			    	usrDao.insertUsr(usr);
			    	result.put("usrMsg", "입력 되었습니다");
			    } else {
			    	usrDao.updateUsr(usr);
			    	result.put("usrMsg", "갱신 되었습니다");
			    }	
			} catch (Exception e){
				e.printStackTrace();
				result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
				//result.put("errSysMsg", e.toString());
			}
			return result;
		}	
	}
		
	
	public Map<String, Object> saveUsr(Usr usr){

		System.out.println(usr.getUsrEmail());		
		System.out.println(usr.getUsrPw());		
		System.out.println(usr.getUsrPw2());		
		
		System.out.println("selectUsrCnt=="+usrDao.selectUsrCnt(usr));		
		
		Map<String, Object> result = new HashMap<String, Object>();
		//return result;
		Map<String, String> errors = new HashMap<String, String>();
		errors = usrValidatorSrv.validateSaveUsr(usr);
		if(errors.size()>0){
			//model.addAttribute("tbUsr", tbUsr);
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			result.put("usr", usr);
			return result;
		} else {	 
			try{
		    	result.put("isSuccess", true);
				
			    if  (usrDao.selectUsrCnt(usr)==0) {
			    	usrDao.insertUsr(usr);
			    	result.put("usrMsg", "입력 되었습니다");
			    } else {
			    	usrDao.updateUsr(usr);
			    	result.put("usrMsg", "갱신 되었습니다");
			    }	
			} catch (Exception e){
				e.printStackTrace();
				result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
				//result.put("errSysMsg", e.toString());
			}
			return result;
		}	
	}

	public Map<String, Object> selectUsrLogin(Usr usr){
		
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> errors = new HashMap<String, String>();

		errors = usrValidatorSrv.validateUsrLogin(usr);
		if(errors.size()>0){
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			return result;
		} else {	 
			try{
		    	result.put("isSuccess", true);
			} catch (Exception e){
				e.printStackTrace();
				result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
				//result.put("errSysMsg", e.toString());
			}
			return result;
		}	
	}
 	
	
	public Map<String, Object> selectUsrList(Usr usr){
		System.out.println("start UsrSrv selectUsrList");
		
		//System.out.println("params111 searchKeyCombo="+params.get("searchKeyCombo"));
		//System.out.println("params221 searchValue="+params.get("searchValue"));
		
		Map<String, Object> result = new HashMap<String, Object>();

		List<Usr> list = null;
		try{
			list = usrDao.selectUsrList(usr);;
			result.put("isSuccess", true);
			result.put("data", list);
            result.put("total", list.size());			
		} catch (Exception e){
			result.put("isSuccess", false);
			result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
			result.put("errSysMsg", e.getMessage());
			e.printStackTrace();
		}
		return result;		
	}
 
	public Map<String, Object> selectUsr(String usrEmail){
		
		Map<String, Object> result = new HashMap<String, Object>();

		try{
			Usr usr = usrDao.selectUsr(usrEmail);;
			result.put("isSuccess", true);
			result.put("data", usr);
            result.put("total", 1);			
		} catch (Exception e){
			result.put("isSuccess", false);
			result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
			result.put("errSysMsg", e.getMessage());
			e.printStackTrace();
		}
		return result;		
	}
	

	public Map<String, Object> deleteUsr(Usr usr){
		
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = usrValidatorSrv.validateDeleteUsr(usr);
		if(errors.size()>0){
			//model.addAttribute("tbUsr", tbUsr);
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			System.out.println(result);
			return result;
		} else {	 
			usrDao.deleteUsr(usr);
			result.put("isSuccess", true);
			result.put("usrMsg", "삭제 되었습니다");
			return result;			
		}	
	}
	
}
