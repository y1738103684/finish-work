package com.nuc.finish.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.LabelValue;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.dao.GiftMapper;
import com.nuc.finish.dao.UserMapper;
import com.nuc.finish.pojo.Gift;
import com.nuc.finish.pojo.User;
import com.nuc.finish.service.CacheService;
import com.nuc.finish.service.GiftRecordService;
import com.nuc.finish.service.GiftService;
import com.nuc.finish.util.FileUploadUtil;
import com.nuc.finish.vo.GiftRecordVO;
import com.nuc.finish.vo.GiftVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/24 16:10
 */
@Service
public class GiftServiceImpl implements GiftService {
    @Autowired
    private GiftMapper giftMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GiftRecordService giftRecordService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<Gift> getGifts(Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
        List<Gift> gifts = giftMapper.listGifts();
        PageInfo<Gift> info = new PageInfo<>(gifts);
        return info;
    }

    @Override
    public boolean uploadGift(MultipartFile file, GiftVO vo) {
        String photo = FileUploadUtil.uploadFile(file);
        vo.setPhoto(photo);
        int i = giftMapper.insertGift(vo);
        return i > 0 ? true : false;
    }

    @Override
    public LabelValue<Integer, String> exChangeGift(String token, GiftVO vo, User user) {
        Boolean isVip = user.getIsVip();
        if (isVip) {
            Integer points = user.getVipPoints();
            if (points >= vo.getPoints()) {
                Integer resPoints = points - vo.getPoints();
                int i = userMapper.updatePoints(resPoints, user.getId());

                //更新兑换记录
                GiftRecordVO giftRecord = createGiftRecord(vo.getId(), user.getId());
                Boolean b = giftRecordService.addRecord(giftRecord);
                if (i > 0 && b == true) {
                    user.setVipPoints(resPoints);
                    redisTemplate.opsForValue().set(token, user, 30, TimeUnit.MINUTES);
                    return new LabelValue<>(3, "兑换成功");
                }
                return new LabelValue<>(4, "兑换失败");
            } else {
                return new LabelValue<>(2, "积分不足");
            }
        } else {
            return new LabelValue<>(1, "您还不是VIP");
        }
    }

    @Override
    public int deleteGift(Integer id) {
        return giftMapper.deleteGiftById(id);
    }

    private GiftRecordVO createGiftRecord(Integer giftId, Integer userId) {
        GiftRecordVO vo = new GiftRecordVO();
        vo.setGiftId(giftId);
        vo.setUserId(userId);
        return vo;
    }


}
