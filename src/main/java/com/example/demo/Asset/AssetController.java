package com.example.demo.Asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AssetController {

    @Autowired
    private AssetService service;

    @GetMapping("/assetList")
    public String ShowListAll(Model model){
        List<Asset> listAsset = service.listAll();

        model.addAttribute("listAssets",listAsset);
        return "assetList";
    }

    @GetMapping("/assetList1K")
    public String ShowListAll1k(Model model){
        List<Asset> listAsset = service.listAllMore1K();

        model.addAttribute("listAssets1K",listAsset);
        return "assetList1K";
    }
}
