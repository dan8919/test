package kr.co.ca;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.domain.BoardVO;
import kr.co.domain.CategoryDTO;
import kr.co.service.BoardService;
import kr.co.utils.Utils;

@Controller
public class UploadAjaxController {
	private String uploadPath = "C:"+File.separator+"upload";
	
	@Inject
	private BoardService bService;
		
	@ResponseBody
	@RequestMapping(value = "/getCateName/{cno}", method = RequestMethod.GET)
	public String getCateName(@PathVariable("cno") int cno) {
		
		return bService.getCateName(cno);
	}
	@ResponseBody
	@RequestMapping(value = "/getAttach/{bno}", method = RequestMethod.GET)
	public List<String> getAttach(@PathVariable("bno") Integer bno) {
		return bService.getAttach(bno);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deletefile", method = RequestMethod.POST)
	public String deletefile(String fileName) {
		
		fileName.replace('/', File.separatorChar);
		
//		1.fileName : 이미지타입=썸네일명
//			=> 이미지의 원본명 받고 삭제
		
//		-1) fileName 에서 확장자 format 받기
		int idx = fileName.lastIndexOf(".");
		String format = fileName.substring(idx + 1);
		
//		-2) 이미지타입인지 아닌지 구별
		MediaType mType = Utils.getMediaType(format); 
//								└이미지타입이 아니면 null
		
//		-3) 이미지타입일 경우, 원본명 따로 추출후 삭제
		if (mType != null) {
//			\2020\07\10\s_xxxxxx_a.jsp
//			\2020\07\10\
			String pre = fileName.substring(0, 12);
//			a.jsp
			String suf = fileName.substring(14);
//			\2020\07\10\ + a.jsp
			String oriName = pre + suf;
			
			File oriFile = new File(uploadPath + oriName);
			oriFile.delete();		
		}
		
//		2. fileName : 이미지외=원본명 / 이미지=썸네일명
		File f = new File(uploadPath + fileName);
		f.delete();
		
		//업로드파일 삭제 DB반영
		bService.delAttachFileName(fileName);

		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listImg", method = RequestMethod.GET)
	//통신정보 조작하여 헤더정보 넘겨주기
	public ResponseEntity<byte[]> listImg(String fileName) {
//														└썸네일 정보
		ResponseEntity<byte[]> entity = null;
		
		InputStream in = null;
		
		try {
			int idx = fileName.lastIndexOf(".");
			String format = fileName.substring(idx + 1);
			MediaType mType = Utils.getMediaType(format);
//									└이미지파일 외에는 null로 넘겨주는 메소드
			//이미지의 썸네일 가져오기 (썸네일과 스트림 연결)
			in = new FileInputStream(uploadPath + fileName);										
			//헤더정보 입력
			HttpHeaders headers = new HttpHeaders();
			//오리지널 네임 받기
			if (mType != null) {
				//이미지파일의 경우
				headers.setContentType(mType);
			} else {
				//이미지파일이 아닐 경우 -->다운로드 하기
				idx = fileName.indexOf("_");
				String originalName = fileName.substring(idx + 1); //오리지널네임 뽑아내기
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//													└다운로드 할수있게 해줌
//				headers.add(headerName, headerValue);
				headers.add(
						"Content-Disposition",
						"attachment;fileName=\"" + new String(originalName.getBytes("UTF-8"), "ISO-8859-1"));
//											└\" : 데이터로서의 큰따옴표 표기				
			}
			//이미지파일일 경우 entity값 받기 (img src에 소스 넣어주기)
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in), headers, HttpStatus.OK);
//					└img src 데이터 입력
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return entity;
	}
	@ResponseBody
	@RequestMapping(value = "/displayfile", method = RequestMethod.GET)
	//통신정보 조작하여 헤더정보 넘겨주기
	public ResponseEntity<byte[]> displayfile(String fileName) {
//														└썸네일 정보
		ResponseEntity<byte[]> entity = null;
		
		InputStream in = null;
		
		try {
			int idx = fileName.lastIndexOf(".");
			String format = fileName.substring(idx + 1);
			MediaType mType = Utils.getMediaType(format);
//									└이미지파일 외에는 null로 넘겨주는 메소드
			//이미지의 썸네일 가져오기 (썸네일과 스트림 연결)
			in = new FileInputStream(uploadPath + fileName);										
			//헤더정보 입력
			HttpHeaders headers = new HttpHeaders();
			//오리지널 네임 받기
			if (mType != null) {
				//이미지파일의 경우
				headers.setContentType(mType);
			} else {
				//이미지파일이 아닐 경우 -->다운로드 하기
				idx = fileName.indexOf("_");
				String originalName = fileName.substring(idx + 1); //오리지널네임 뽑아내기
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//													└다운로드 할수있게 해줌
//				headers.add(headerName, headerValue);
				headers.add(
						"Content-Disposition",
						"attachment;fileName=\"" + new String(originalName.getBytes("UTF-8"), "ISO-8859-1"));
//											└\" : 데이터로서의 큰따옴표 표기				
			}
			//이미지파일일 경우 entity값 받기 (img src에 소스 넣어주기)
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in), headers, HttpStatus.OK);
//					└img src 데이터 입력
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return entity;
	}
		
	@ResponseBody //레스트컨트롤러가 아닌 컨트롤러 어노테이션이므로
	@RequestMapping(value = "/uploadajax", 
					method = RequestMethod.POST,
					produces = "text/plain;charset=UTF-8") //한글인코딩	
	public String uploadajax(MultipartHttpServletRequest request) throws Exception {
		
		MultipartFile file = request.getFile("file");
		
		String originalName = file.getOriginalFilename();
		
		String saveFileName = Utils.saveFile(originalName, file, uploadPath);
//									└안에 파일위치가 들어있음
		
		return saveFileName;
	}
	
	@RequestMapping(value = "/uploadajax", method = RequestMethod.GET)
	public void uploadajax() {
		/*
		String[] paths = Utils.makeDirName();
		
		File f = new File(uploadPath + paths[2]);
//										└"C:\\upload\2020\07\09\"
		
		if (f.exists()) {
//			└위의 폴더가 존재하는지
			return; //존재하면 넘어가기
		}
		
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
//										└"C:\\upload\"
			if (!dirPath.exists()) {
//				└위의 폴더가 존재하지않는지
				dirPath.mkdir(); //존재하지않으면 만들어주기
			}
		} */

	}

}
