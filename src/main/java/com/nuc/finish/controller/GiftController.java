package com.nuc.finish.controller;

import com.github.pagehelper.PageInfo;
import com.nuc.finish.common.LabelValue;
import com.nuc.finish.common.Pagination;
import com.nuc.finish.exception.CommonException;
import com.nuc.finish.pojo.Gift;
import com.nuc.finish.pojo.User;
import com.nuc.finish.response.CommonResponseEnum;
import com.nuc.finish.response.Response;
import com.nuc.finish.service.GiftService;
import com.nuc.finish.vo.GiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/24 16:02
 */
@RestController
public class GiftController extends BaseController{
    @Autowired
    private GiftService giftService;

    @RequestMapping("/gift/show")
    public Response giftShow(@RequestBody Pagination pagination) {
        PageInfo<Gift> gifts = giftService.getGifts(pagination);
        return Response.create(gifts);
    }

    @RequestMapping("/gift/upload")
    public Response giftUpload(GiftVO vo, @RequestParam("file") MultipartFile file) {
        boolean b = giftService.uploadGift(file, vo);
        return Response.create(b);
    }

    @RequestMapping("/gift/exchange")
    public Response giftExchange(@RequestBody GiftVO vo) throws CommonException {
        User loginUser = getLoginUser();
        String token = getToken();
        if (loginUser == null) {
            throw new CommonException(CommonResponseEnum.USER_LOGIN_NOT);
        }
        LabelValue<Integer, String> labelValue = giftService.exChangeGift(token, vo, loginUser);
        return Response.create(labelValue);
    }
}
