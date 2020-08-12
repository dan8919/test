package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.CategoryDTO;
import kr.co.domain.ClothesDTO;
import kr.co.domain.GoodsDTO;
import kr.co.domain.PageTo;
import kr.co.persistence.BoardDAO;
import kr.co.persistence.CategoryDAO;
import kr.co.persistence.GoodsDAO;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Inject
	private GoodsDAO gDao;
	
	@Inject
	private CategoryDAO cateDao;
	
	@Inject
	private BoardDAO bDao;

	@Override //보드입력과 트랜잭션
	public void insert(GoodsDTO gooDto) {
		gDao.insert(gooDto);
		
	}

	@Override
	public List<CategoryDTO> selectCateList() {
		return cateDao.selectCateList();
	}

	@Override
	public PageTo<GoodsDTO> list(PageTo<GoodsDTO> to) {
		return gDao.list(to);
	}

	@Override
	public List<GoodsDTO> list() {
		return gDao.list();
	}

	@Override
	public GoodsDTO updateui(int gno) {
		return gDao.updateui(gno);
	}

	@Transactional
	@Override
	public void update(GoodsDTO gooDto) {
		gDao.update(gooDto);
//		bDao.updateCno();
		
	}

	
}
