package osc.gobaby.statistics_cloud.controller.mv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
public class IndexViewController {

    @RequestMapping("/")
    public String indexView() {
        return "index";
    }
    
    @RequestMapping("/gate")
    public String gateView() {
        return "gate";
    }

}
