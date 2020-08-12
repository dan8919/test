package kr.co.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
//	datepath를 paths[2]가 가지고 있으므로 빼내기
	public static String makeDir(String uploadPath) {
		String[] paths = Utils.makeDirName();
		
		File f = new File(uploadPath + paths[2]);
//										└"C:\\upload\2020\07\09\"
		/*
		if (f.exists()) {
//			└위의 폴더가 존재하는지
			return; //존재하면 넘어가기
		}*/
		if (f.exists()) {
//			└위의 폴더가 존재하면			
			return paths[2]; //datepath 출력
		}

		
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
//										└"C:\\upload\"
			if (!dirPath.exists()) {
//				└위의 폴더가 존재하지않는지
				dirPath.mkdir(); //존재하지않으면 만들어주기
			}
		}
		return paths[2]; 
	}
	
	public static String[] makeDirName() {

		int[] arr = Utils.getDateInfo();
		
//		"C:\\upload\2020\07\09\xxxxx_a.jpg"
		
		String yearPath = File.separator + arr[0]; // \2020
//		String monthPath = yearPath+File.separator + arr[1]; // \2020\7
		String monthPath = yearPath+File.separator + String.format("%02d", arr[1]);
//		String datePath = monthPath+File.separator + arr[2]; // \2020\7\9
		String datePath = monthPath+File.separator + String.format("%02d", arr[2]);
		
		String[] paths = {yearPath, monthPath, datePath};
		
		return paths;
	}
	
	public static int[] getDateInfo() {
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
//									└0월부터 11월을 출력하므로 +1
		int date = cal.get(Calendar.DATE);
		
		int arr[] = {year, month, date};
		
		return arr;
	}
	
	//upload 폴더 내 날짜폴더안에 저장하도록 경로 수정
	public static String saveFile(String originalName, MultipartFile file, String uploadPath) throws Exception {
		String newName = Utils.makeNewName(originalName);
		String datePath = Utils.makeDir(uploadPath);
//				└datePath에는 "C:\\upload\ 정보가 없음
//		File target = new File("C:"+File.separator+"upload", newName);
		/*
		File target = new File(uploadPath, newName);
//								└파일위치 변수로 받기*/
		File target = new File(uploadPath + datePath, newName);
//										└"C:\\upload\에 datePath 합치기
		
		FileCopyUtils.copy(file.getBytes(), target);
		
		boolean isImgFile = isImg(originalName);
		
		if (isImgFile) {
//			System.out.println("썸네일을 만듦");
			return makeThumbnail(uploadPath, datePath, newName);
		} else {
//			System.out.println("썸네일을 안 만듦");
			String beforeChangeName = datePath + File.separator + newName;
//					└이미지파일이 아니므로 썸네일 생성X,
			return beforeChangeName.replace(File.separatorChar, '/');
//								└브라우저로 인식할수있도록 변경		
		}				
//		return newName;
//		return datePath + File.separator + newName;		
	}
	
	//이미지 썸네일
	public static String makeThumbnail(
			String uploadPath, String datePath, String newName) throws Exception {
//														└saveFile 메소드의 newName
		File f1 = new File(uploadPath + datePath, newName);
		BufferedImage sourceImg = ImageIO.read(f1); //원본 이미지 불러오기
		BufferedImage destImg = Scalr.resize( //램에 이미지 불러오고,
				sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, 200);
//																└가로세로 200사이즈로 맞추기
		String thumbnailName = uploadPath + datePath +File.separator+"s_"+ newName;
//								└썸네일 경로 지정
		File newFile = new File(thumbnailName);
		
		int idx = newName.lastIndexOf("."); //확장자 뽑아내기
		String format = newName.substring(idx + 1).toUpperCase();
//													└확장자 대문자로 가져오기
		ImageIO.write(destImg, format, newFile); //이미지 저장
		
//		결과값 = C:\\upload\2020\07\09\s_xxxxxx_show.jpg 
		
//		브라우저로 보낼때 => /2020/07/09/s_xxxxxx_show.jpg
		return thumbnailName.substring(
				uploadPath.length()).replace(File.separatorChar, '/');
//													└브라우저로 인식할수 있도록
	}
	
	//이미지 업로드
	public static boolean isImg(String fileName) {		
//		PNG, gif, jpeg, jpg 이미지 확장자
//		a.b.jpg --마지막 점을 기준으로 확장자 구분
		int idx = fileName.lastIndexOf(".");
		String format = fileName.substring(idx+1);
//											└점 앞까지 자르기

		MediaType mType = getMediaType(format);
		
		if (mType != null) {
			return true; //이미지 확장자를 받았다면
		} else {
			return false; //이미지 확장자를 받지 않았다면
		}
	}
	
	
	//새로운 이름 지정해주는 메소드
	public static String makeNewName(String originalName) {
		UUID uid = UUID.randomUUID(); //새로운 이름 생성
		String newName = uid.toString()+"_"+originalName; //언더바로 구분
		
		return newName;
	}
	
	
	//필터로 인코딩이 안될때	
	public static String toKor(String msg) {
		try {
			return new String(msg.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null; //null포인트익센션 방지
		}
	}

	//이미지 확장자를 받는 메소드
	public static MediaType getMediaType(String format) {
		Map<String, MediaType> map = new HashMap<String, MediaType>();
		map.put("JPG", MediaType.IMAGE_JPEG);
		map.put("JPEG", MediaType.IMAGE_JPEG);
		map.put("PNG", MediaType.IMAGE_PNG);
		map.put("GIF", MediaType.IMAGE_GIF);
		//해당 이미지 타입에 맞는 확장자를 받는다
		//이미지 타입이 아니면 아무것도 받지 않는다 =null
		
		MediaType mType = map.get(format.toUpperCase());

		return mType;
	}

}
