package xyz.maojun.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.maojun.item.service.SpecService;
import xyz.maojun.pojo.SpecGroup;
import xyz.maojun.pojo.SpecParam;

import java.util.List;

/**
 * @Description: Spec Controller
 * @Created by maojun
 * @Date: 2020/05/01
 */

@Controller
@RequestMapping("spec")
public class SpecController {

    @Autowired
    private SpecService specService;


    /**
     * query spec group by id
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid) {

        List<SpecGroup> specGroupList = this.specService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(specGroupList)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specGroupList);
    }


    /**
     * query spec params by id
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamsByGid(@RequestParam("gid") Long gid) {

        List<SpecParam> specParamList = this.specService.queryParamsByGid(gid);

        if (CollectionUtils.isEmpty(specParamList)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(specParamList);
    }

}
