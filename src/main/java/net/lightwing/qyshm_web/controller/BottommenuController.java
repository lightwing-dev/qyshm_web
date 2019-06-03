package net.lightwing.qyshm_web.controller;

import com.alibaba.fastjson.JSON;
import net.lightwing.qyshm_web.commons.util.PageInfo;
import net.lightwing.qyshm_web.pojo.QBottommenu;
import net.lightwing.qyshm_web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
public class BottommenuController {

    @Autowired
    private QBottommenuService qBottommenuService;

    @Autowired
    private QConfigService qConfigService;

    @Autowired
    private QQrcodeService qQrcodeService;
    @Autowired
    private QTechdevService qTechdevService;

    @Autowired
    private QProductService qProductService;

    @RequestMapping("about.html")
    public String selectWebPageInfo(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "9") Integer limit, Model model) {
        Map<String, Object> params = new HashMap<>();
        PageInfo pageInfo = new PageInfo(page, limit);
        pageInfo = qBottommenuService.selectPageInfo(pageInfo);
        PageInfo qb = new PageInfo(1, 9);
        qb = qBottommenuService.selectPageInfo(qb);
        PageInfo qc = new PageInfo(1, 1);
        qc = qConfigService.selectPageInfo(qc);
        PageInfo qq = new PageInfo(1, 2);
        qq = qQrcodeService.selectPageInfo(qq);
        PageInfo qt = new PageInfo(1,100000);
        qt=qTechdevService.selectPageInfo(qt);
        PageInfo qp = new PageInfo(1,100000);
        qp=qProductService.selectPageInfo(qp);
        model.addAttribute("PageInfo", pageInfo);
        model.addAttribute("qb", qb);
        model.addAttribute("qc", qc);
        model.addAttribute("qq", qq);
        model.addAttribute("qt", qt);
        model.addAttribute("qp", qp);
        return "about";
    }

    @RequestMapping("bottomDetail.html")
    public String getBottomDetail(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "9") Integer limit,Integer bmid,Model model)
    {
        Map<String, Object> params = new HashMap<>();
        PageInfo pageInfo = new PageInfo(page, limit);
        pageInfo = qBottommenuService.selectPageInfo(pageInfo);
        PageInfo qb = new PageInfo(1, 9);
        qb = qBottommenuService.selectPageInfo(qb);
        PageInfo qc = new PageInfo(1, 1);
        qc = qConfigService.selectPageInfo(qc);
        PageInfo qq = new PageInfo(1, 2);
        qq = qQrcodeService.selectPageInfo(qq);
        PageInfo qt = new PageInfo(1,100000);
        qt=qTechdevService.selectPageInfo(qt);
        PageInfo qp = new PageInfo(1,100000);
        qp=qProductService.selectPageInfo(qp);

        QBottommenu bmdata = qBottommenuService.selectById(bmid);


        model.addAttribute("PageInfo", pageInfo);
        model.addAttribute("qb", qb);
        model.addAttribute("qc", qc);
        model.addAttribute("qq", qq);
        model.addAttribute("qt", qt);
        model.addAttribute("qp", qp);
        model.addAttribute("bmdata",bmdata);
        return "bottomDetail";
    }

}
