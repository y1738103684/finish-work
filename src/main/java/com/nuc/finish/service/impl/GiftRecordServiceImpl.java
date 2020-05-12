package com.nuc.finish.service.impl;

import com.nuc.finish.common.LabelValue;
import com.nuc.finish.dao.GiftRecordMapper;
import com.nuc.finish.service.GiftRecordService;
import com.nuc.finish.vo.GiftRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/27 18:28
 */
@Service
public class GiftRecordServiceImpl implements GiftRecordService {
    @Autowired
    private GiftRecordMapper giftRecordMapper;

    @Override
    public Boolean addRecord(GiftRecordVO vo) {
        int i = giftRecordMapper.insertGiftRecord(vo);
        return i > 0 ? true : false;
    }
}
