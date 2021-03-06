package net.lightwing.qyshm_web.controller;

import net.lightwing.qyshm_web.commons.util.HtmlUtils;
import net.lightwing.qyshm_web.commons.util.PageInfo;
import net.lightwing.qyshm_web.pojo.QTechdev;
import net.lightwing.qyshm_web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
public class TechdevController {

    @Autowired
    private QTechdevService qTechdevService;

    @Autowired
    private QBottommenuService qBottommenuService;

    @Autowired
    private QConfigService qConfigService;

    @Autowired
    private QQrcodeService qQrcodeService;

    @Autowired
    private QProductService qProductService;

    @RequestMapping("techdev.html")
    public String selectWebPageInfo(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer limit, Model model) {
        Map<String, Object> params = new HashMap<>();
        PageInfo pageInfo = new PageInfo(page, limit);
        pageInfo = qTechdevService.selectPageInfo(pageInfo);
        List<QTechdev> qNewsList = pageInfo.getRows();
        for (QTechdev str : qNewsList) {
            String html = HtmlUtils.Html2Text(str.getTdetail());
            if (html.length() > 20) {
                str.setTdetail(html.substring(0, 20) + "……");
            } else {
                str.setTdetail(html);
            }
        }
        PageInfo qb = new PageInfo(1, 9);
        qb = qBottommenuService.selectPageInfo(qb);
        PageInfo qc = new PageInfo(1, 1);
        qc = qConfigService.selectPageInfo(qc);
        PageInfo qq = new PageInfo(1, 2);
        qq = qQrcodeService.selectPageInfo(qq);
        PageInfo qp = new PageInfo(1,100000);
        qp=qProductService.selectPageInfo(qp);
        PageInfo qt = new PageInfo(1, 100000);
        qt = qTechdevService.selectPageInfo(qt);

        model.addAttribute("PageInfo", pageInfo);
        model.addAttribute("qb", qb);
        model.addAttribute("qc", qc);
        model.addAttribute("qq", qq);
        model.addAttribute("qt", qt);
        model.addAttribute("qp", qp);
        return "techdev";
    }

    /**
     * 根据技术研发编号查询详情
     *
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping("techdevDetails.html")
    public String selectById(Integer cid, Model model) {
        QTechdev qTechdev = qTechdevService.selectById(cid);
        PageInfo qb = new PageInfo(1, 9);
        qb = qBottommenuService.selectPageInfo(qb);
        PageInfo qc = new PageInfo(1, 1);
        qc = qConfigService.selectPageInfo(qc);
        PageInfo qq = new PageInfo(1, 2);
        qq = qQrcodeService.selectPageInfo(qq);
        PageInfo qp = new PageInfo(1,100000);
        qp=qProductService.selectPageInfo(qp);
        PageInfo qt = new PageInfo(1,100000);
        qt=qTechdevService.selectPageInfo(qt);
        model.addAttribute("qCoop", qTechdev);
        model.addAttribute("qb", qb);
        model.addAttribute("qc", qc);
        model.addAttribute("qq", qq);
        model.addAttribute("qp", qp);
        model.addAttribute("qt", qt);
        return "techdevDetails";
    }
}
