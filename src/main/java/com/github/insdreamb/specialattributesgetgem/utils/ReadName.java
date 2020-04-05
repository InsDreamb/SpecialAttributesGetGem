package com.github.insdreamb.specialattributesgetgem.utils;

import com.github.insdreamb.specialattributesgetgem.Main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.yaml.snakeyaml.*;

/**
 *
 * @author InsDreamb
 * @date 2020.03.21
 * 用于带特殊符号的宝石名字(键)
 */
public final class ReadName {
    private ReadName(){
        throw new Error("不可实例化本类");
    }

    public static void read(){
        File file = new File(Main.getInstance().DATAFOLDER,"config.yml");
        try {
            FileInputStream input = new FileInputStream(file);
            Yaml yaml = new Yaml();
            Map map = (Map) yaml.load(input);
            Map name = (Map)map.get("Gem");
            for (Object object : name.keySet()){
                Variable.equips.put((String)object,(Double) name.get(object));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
