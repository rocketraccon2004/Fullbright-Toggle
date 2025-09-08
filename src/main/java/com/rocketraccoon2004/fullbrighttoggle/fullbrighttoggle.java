package com.rocketraccoon2004.fullbrighttoggle;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@Mod(modid = fullbrighttoggle.MOD_ID)
public class fullbrighttoggle {
    public static final String MOD_ID = "fullbrighttoggle";
    public static KeyBinding toggleKey;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        toggleKey = new KeyBinding("key.fullbrighttoggle.toggle", Keyboard.KEY_G, "key.categories.misc");
        ClientRegistry.registerKeyBinding(toggleKey);
    }
}
