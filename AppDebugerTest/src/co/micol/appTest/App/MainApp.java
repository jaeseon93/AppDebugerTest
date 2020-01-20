package co.micol.appTest.App;

import java.util.ArrayList;
import java.util.List;

import co.micol.appTest.dto.UserDto;
import co.micol.appTest.service.Service;

import co.micol.appTest.serviceImpl.UserServiceImpl;


public class MainApp {

	
	public static void main(String[] args) {
		List<UserDto> list = new ArrayList<UserDto>();
		Service service = new UserServiceImpl();
		list = service.selectUserAll();
		
		String msg = "= ID = 권한 = 가입일자 = ";
		title(msg);
		listPrint(list);
		
		//유저권한을 변경하는 구문 추가하기 USER -> ADMIN변경
		//1 dto instance 생성  2 dto.setter에 값담기   3.서비스가 가지고있는 update호출 4.결과받아서확인 5.전체리스트 호출 및출력
//		UserDto dto = new UserDto();  //수정
//		String str;	                  //수정
//		dto.setUser_id("kim001");    //수정
//		dto.setUser_level("ADMIN");  //수정
//		service.update(dto, "k");    //수정
		
		
	}
	
	

	
	private static void listPrint(List<UserDto> list) {
		for(UserDto dto : list) 
			dto.toString();
	}
	
	private static void title(String msg) {
		System.out.println(msg);
	}

}
