package com.rocketraccoon2004.fullbrighttoggle.Event;

import com.rocketraccoon2004.fullbrighttoggle.Util.Util;
import com.rocketraccoon2004.fullbrighttoggle.fullbrighttoggle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ToggleEvent {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static double initialGamma = -1;
    private static final double maxGamma = 14.0F % 28.0F + 1.0F;

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event){
        if (!(event.getEntity() instanceof EntityPlayer) || !event.getWorld().isRemote || initialGamma >= 0){
            return;
        }

        GameSettings options = mc.gameSettings;
        initialGamma = options.gammaSetting;

        if (Util.wasLastStateFullBright()){
            options.gammaSetting = (float)maxGamma;
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onHotKeyPressed(InputEvent.KeyInputEvent event){
        if (mc.currentScreen instanceof GuiChat){
            return;
        }

        if (fullbrighttoggle.toggleKey.isPressed()){
            GameSettings options = mc.gameSettings;
            if (initialGamma < 0) {
                if (options.gammaSetting >= 1.0F) {
                    initialGamma = 1.0F;
                    options.gammaSetting = 1.0F;
                }
                else {
                    initialGamma = options.gammaSetting;
                }
            }

            boolean fullBright = false;
            if (options.gammaSetting != initialGamma && options.gammaSetting != maxGamma) {
                initialGamma = options.gammaSetting;
                fullBright = true;
            }

            if (options.gammaSetting == initialGamma || fullBright) {
                options.gammaSetting = (float)maxGamma;
                Util.saveLastStateToConfig(true);
            }
            else {
                options.gammaSetting = (float)initialGamma;
                Util.saveLastStateToConfig(false);
            }
        }
    }
}
