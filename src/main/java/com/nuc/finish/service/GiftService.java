package com.nuc.finish.service;

import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.LabelValue;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.pojo.Gift;
import com.nuc.finish.pojo.User;
import com.nuc.finish.vo.GiftVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/24 16:09
 */
public interface GiftService {
    PageInfo<Gift> getGifts(Pagination pagination);

    boolean uploadGift(MultipartFile file, GiftVO vo);

    LabelValue<Integer, String> exChangeGift(String token, GiftVO vo, User user);
}
