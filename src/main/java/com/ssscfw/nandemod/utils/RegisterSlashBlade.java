package com.ssscfw.nandemod.utils;

import com.ssscfw.nandemod.item.slashblade.*;

public class RegisterSlashBlade {
    public static void register() {
        Alldata.items.put("slashblade_venus", new Venus());
        Alldata.items.put("slashblade_yamato2", new Yamato2());
    }
}
