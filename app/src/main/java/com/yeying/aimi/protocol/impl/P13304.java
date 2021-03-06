package com.yeying.aimi.protocol.impl;

import com.alibaba.fastjson.JSON;
import com.yeying.aimi.protoco.BasicProtocol;
import com.yeying.aimi.protoco.path.Result;
import com.yeying.aimi.protocol.request.Arg;
import com.yeying.aimi.utils.JSONUtil;

import java.io.Serializable;

/**
 * Created by tanchengkeji on 2017/7/28.
 */

public class P13304 extends BasicProtocol implements Serializable{


    public Req req;
    public Resp resp;

    public P13304() {
        super();
        req = new Req();
        req.transcode = "13304";
        resp = new Resp();
    }

    @Override
    public Arg[] serialize() {
        return JSONUtil.getReq(JSON.toJSONString(req), req.transcode, req.sessionId);
    }

    @Override
    public void unSerialize(Result result) {
        int index = result.content.indexOf("&");
        String trascodeid = result.content.substring(0, index);
        String content = result.content.substring(index + 1);
        resp = JSON.parseObject(content, resp.getClass());
        resp.setTranscode(trascodeid);
    }

    public static class Req extends BaseReq {
        public String barId;
        public String userId;
        public String fateCardId;
        public double locationX;
        public double locationY;
    }
    public static class Resp extends BaseResp implements Serializable {
        public int status;
    }

}
